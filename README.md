# 闲言博客
闲言博客，一个前后端分离博客系统。Web端使用Vue + Element ,。后端使用Spring boot + mybatis进行开发，使用 Shiro做登录验证，文件支持上传七牛云
## 更新记录
- 2020-11-17 09:25 update:将fastdfs上传图片更新为七牛云上传
- 2020-11-23 21:48 update:登录从前端MD5加密改为后端MD5加密后校验以及日志从所有controller记录改为被@Log注解标记的方法记录
- 2020-11-17 09:25 add:如果添加博客不上传封面将会设置一个默认封面图片
- 2020-11-18 21:48 update:将原本前端md5加密后再跟后端匹配改为后端进行加密后再匹配
- 2020-11-19 22:15 fix:将页面url后面会添加publicPath问题解决
- 2020-11-24 11:25 update:将前端静态资源使用cdn加速、日志原记录为controller层的所有方法切入改为Log注解处切入
- 2020-11-24 21:51 fix:修复推荐博客下的时间bug
- 2020-11-24 21:51 add:注册时添加上传头像功能
- 2020-11-25 11:46 fix:修复数据库时间问题，具体问题已发博客，[点我查看](https://oopsw.top/2021/01/07/springboot%20%E5%90%8E%E7%AB%AF%E4%BC%A0%E7%BB%99%E5%89%8D%E7%AB%AF%E7%9B%B8%E5%B7%AE8%E5%B0%8F%E6%97%B6/)
- 2020-11-27 23:47 fix:登录注册页面卡片超出页面没有显示滚动条、背景图片在页面放大时会出现多个背景图片
- 2020-11-29 00:51 update:将前后台的所有富文本编辑器更换为markdown编辑器，并用markdown的样式
- 2020-12-01 08:30 update:修改markdown代码块样式
- 2020-12-01 08:57 fix:手机访问登录注册页面时样式体验差
- 2020-12-01 09:30 fix:阅读博客时version问题
- 2020-12-01 16:11 update:session存入服务器改为存入redis
- 2020-12-01 17:50 fix:部署后token问题
- 2020-12-02 08:30 fix:点赞超链接问题
- 2020-12-03 22:02 update:更换markdown存储方式，从存储markdown源码改为存储HTML源码

