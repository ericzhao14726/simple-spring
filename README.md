# simple-spring
## 简介
simple-spring是模仿实现一个简单的springBoot框架，依据自己的理解实现简单的springBoot框架的基本功能，包括ioc、aop、springMvc等，除此之外结合了netty作为http网络服务。目前该框架（其实谈不上）基本实现了spring的ioc功能和http的get/post请求功能，后续功能持续更新…… 

## 基本功能
- Ioc(已完成)
- Aop(待更新)
- springMvc（实现get、post请求）

## 注解支持
**启动**
- @SimpleSpring(标识为一个SimpleSpring项目，与@SpringBoot一致)
- @ComponentScan 

**Ioc**
- @Component
- @Service
- @Autowride
- ... 

**Aop**
- 暂时为实现，持续更新 

**springMvc**
- @RestController
- @RequestMapping
- @GetMapping
- @RequestMapping 

## 声明
- 这个项目只是用作学习的目的，没有任何生产能力，所以如果被大家翻到，也请大家抱着学习的态度去看待，本人也是菜鸟一只，实现代码中也免不了会有很多bug，也请大家能帮我指正，提出issues，后续我也会持续修改和更新，谢谢！
