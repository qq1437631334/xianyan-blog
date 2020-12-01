# 闲言博客
闲言博客，一个前后端分离博客系统。Web端使用Vue + Element ,。后端使用Spring boot + mybatis进行开发，使用 Shiro做登录验证，文件支持上传七牛云
## 更新记录
- @Data 2020-11-17 09:25        update:将fastdfs上传图片更新为七牛云上传
- @Data 2020-11-23 21:48        update:登录从前端MD5加密改为后端MD5加密后校验以及日志从所有controller记录改为被@Log注解标记的方法记录
- @Data 2020-11-24 11:25        add:添加注册时上传头像功能
- @Data 2020-11-25 11:46        fix:数据库时间问题and前端页面小调整
- @Data 2020-11-27 19:58        fix:登录注册页面滚动条问题
- @Data 2020-11-29 00:51        update:将富文本编辑器换为markdown编辑器
- @Data 2020-12-01 08:30        update:修改markdown代码块样式
- @Data 2020-12-01 08:57        fix:手机访问登录注册页面时样式体验差