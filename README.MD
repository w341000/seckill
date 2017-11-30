# SECKILL 秒杀系统  
项目来自于慕课网的`Java高并发秒杀API`系列,虽然说是高并发秒杀,但对于最关键的商品库存竞争没有采用缓存等
解决方案,不过项目对SSM的整合及前端页面编写的思路值得让人学习

## 项目环境
- IDE: `Eclipse Oxygen`
- JDK: `jdk1.8` (其实1.7也可以,因为没有用到1.8的新特性)  

## 后端技术
- 表现层: `springmvc4.3.12.RELEASE`
- 核心框架:`spring4.3.12.RELEASE`
- 持久层:`mybatis3.3.0`  
- 依赖管理:`maven`
- 版本管理:`git`

## 前端技术
- `jquery1.8`
- `Twitter Bootstrap`

## 项目结构
`src` 目录下保存了项目源代码  
`数据库sql`文件夹下保存了数据库相关sql语句,运行项目前在`mysql`中新建一个数据库`seckill`并执行该sql即可  
`pom.xml`中含有maven的tomcat7插件,直接在项目上右键`Run As Maven Build ` ,然后在`Goals`中填写tomcat7:run即可  

## 个人心得
整个项目对`SSM`的整合及秒杀业务做了详细说明,是慕课网上不可多得的好项目,项目同样也用到了`redis`这样比较流行的缓存进行优化,对于新手学习很有帮助,项目距离真正的秒杀场景当然有一定区别,但对于没有经验的自学者来说,足以锻炼了.
