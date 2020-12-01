package com.wsy.blog.config;

import com.wsy.blog.filter.LoginFilter;
import com.wsy.blog.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.Filter;
import java.util.*;

/**
 * @author wsy
 * @date 2020-10-02 18:18
 */
@Configuration
@EnableConfigurationProperties(value = {ShiroFilterConfig.class, RedisProperties.class})
public class ShiroAutoConfiguration {

    private ShiroFilterConfig shiroFilterConfig;

    private RedisProperties redisProperties;

    public static final String SHIRO_FILTER_NAME = "loginFilter";

    public ShiroAutoConfiguration(ShiroFilterConfig shiroFilterConfig, RedisProperties redisProperties) {
        this.shiroFilterConfig = shiroFilterConfig;
        this.redisProperties = redisProperties;
    }

    /**
     * 创建凭证匹配器
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //注入散列算法名
        matcher.setHashAlgorithmName(shiroFilterConfig.getHashAlgorithmName());
        //注入散列次数
        matcher.setHashIterations(shiroFilterConfig.getHashIterations());
        return matcher;
    }

    /**
     * 创建自定义realm
     * 并注入凭证匹配器
     */
    @Bean
    @ConditionalOnClass(value = {UserRealm.class})
    public UserRealm getUserRealm(HashedCredentialsMatcher matcher) {
        UserRealm userRealm = new UserRealm();
        //注入凭证匹配器
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    /**
     * 创建安全管理器
     */
    @Bean
    @ConditionalOnClass(value = DefaultWebSecurityManager.class)
    public DefaultWebSecurityManager getSecurityManager(DefaultWebSessionManager defaultWebSessionManager, UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入realm
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(defaultWebSessionManager);
        return securityManager;
    }

    /**
     * -声明过滤器
     * Shiro 的Web过滤器 id必须和web.xml里面的shiroFilter的 targetBeanName的值一样
     */
    @Bean(value = SHIRO_FILTER_NAME)
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //注入安全管理器
        bean.setSecurityManager(securityManager);
        //处理用户未认证访问要认证的地址的跳转问题   默认是跳转到shiroProperties.getLoginUrl()现在改成以json串形式返回
        Map<String, Filter> filters = new HashMap<>(8);
        filters.put("authc", new LoginFilter());
        Map<String, String> filterMap =new LinkedHashMap<>();
        //配置不拦截的路径
        for (String anon : this.shiroFilterConfig.getAnons()) {
            filterMap.put(anon,"anon");

        }
        //配置拦截的路径
        for (String s : this.shiroFilterConfig.getAuthc()) {
            filterMap.put(s,"authc");
        }
        bean.setFilters(filters);
        bean.setFilterChainDefinitionMap(filterMap);

        Map<String, String> map = new LinkedHashMap<>(8);
        return bean;
    }


    /**
     * 注册DelegatingFilterProxy
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> registDelegatingFilterProxy() {
        //创建注册器
        FilterRegistrationBean<DelegatingFilterProxy> bean = new FilterRegistrationBean<>();
        //创建过滤器
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        //注入过滤器
        bean.setFilter(proxy);
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName(SHIRO_FILTER_NAME);
        Collection<String> servleNames = new ArrayList<>();
        servleNames.add(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
        bean.setServletNames(servleNames);
        return bean;
    }


    /**
     * sessionManager 里面可以决定sessionDAO
     * defaultWebSessionManager来源com.wsy.system.config.TokenWebSessionManager
     * 开发时暂时使用默认的避免每次都要验证token
     *
     * @param redisSessionDao
     * @return
     */
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(RedisSessionDAO redisSessionDao) {
        DefaultWebSessionManager defaultWebSessionManager = new TokenWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDao);
        return defaultWebSessionManager;
    }

    /**
     * 使用Redis  来存储登录的信息
     * sessionDao 还需要设置给sessionManager
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(IRedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        //操作那个redis
        redisSessionDAO.setRedisManager(redisManager);
        // 用户的登录信息保存多久？ 半小时
        redisSessionDAO.setExpire(1800);
        return redisSessionDAO;
    }

    /**
     * 因为分步式项目，所以使用redis去存我们的登陆Session
     *
     * @return
     */
    @Bean
    public IRedisManager redisManager() {
        //因为RedisManager要操作redis所以必须把Redis的客户端给RedisManager
        RedisManager redisManager = new RedisManager();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(), 5000, redisProperties.getPassword());
        redisManager.setJedisPool(jedisPool);
        return redisManager;
    }


    /*加入注解的使用，不加入这个注解不生效--开始*/

    /**
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /*加入注解的使用，不加入这个注解不生效--结束*/
}
