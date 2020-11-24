package com.wsy.blog.config;

import com.wsy.blog.filter.LoginFilter;
import com.wsy.blog.realm.AdminRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wsy
 * @date 2020-08-10 10:55
 */
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroConfig {

    private String logOutUrl = "/logout*";

    private final ShiroFilterConfig shiroFilterConfig;

    public ShiroConfig(ShiroFilterConfig shiroFilterConfig) {
        this.shiroFilterConfig = shiroFilterConfig;
    }

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        // 设置安全管理器
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 把自定义的过滤器放入shiro中
        Map<String, Filter> shiroFilters = shiroFilterFactoryBean.getFilters();
        shiroFilters.put("authc", new LoginFilter());
//        /**
//         * 常用过滤器
//         *  anon：无需认证可以访问
//         *  authc：必须认证才能访问
//         *  user：如果使用rememberMe的功能可以直接访问
//         *  perms：该资源必须得到权限才可以访问
//         *  role：该资源必须得到角色权限才可以访问
//         */
        Map<String, String> filterMap =new LinkedHashMap<>();
        List<String> annos = this.shiroFilterConfig.getAnons();
        for (String anno : annos) {
            filterMap.put(anno,"anon");
        }
        List<String> authc = this.shiroFilterConfig.getAuthc();
        for (String s : authc) {
            filterMap.put(s,"authc");
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultSecurityManager
     */
    @Bean
    public SecurityManager securityManager(AdminRealm adminRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联realm
        securityManager.setRealm(adminRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean
    public AdminRealm adminRealm() {
        return new AdminRealm();
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
