## Spring Wechat

> Referencing wechat official document, supplying wechat server client APIs, integrating with spring.



### Usage

#### Add Dependency

Gradle:

~~~groovy
implementation("com.glancebar.wechat:spring-boot-wechat-starter:0.0.1")
~~~

Maven:

~~~xml
<dependency>
  <groupId>com.glancebar.wechat</groupId>
  <artifactId>spring-boot-wechat-starter</artifactId>
  <version>0.0.1</version>
</dependency>
~~~



#### Define Configuation

~~~yaml
wechat:
  mini-program:
    app-id: your-app-id
    app-secret: your-app-secret
~~~



#### Add Scan autoconfiguration annotation

~~~java
# At your Spring Boot application entrance point.
@SpringBootApplication
@ComponentScan(value = ["com.glancebar"])
class Application {
		...
}
~~~



# 

#### MiniProgram Login Example

[code2Session](https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html)

~~~kotlin
@Autowired
lateinit var wechatMiniProgramApi: WechatMiniProgramApi

val code2SessionResult = wechatMiniProgramApi.code2Session("031AXC0w3XU3LV2rqy0w3icRl42AXC0C")
~~~




### Modules

#### [wechat-library](https://github.com/yisen-cai/spring-wechat/tree/master/wechat-library)

> Define basic request response object, encapsulate basic params and authentication related works.


#### [spring-boot-wechat-autoconfigure](https://github.com/yisen-cai/spring-wechat/tree/master/spring-boot-wechat-autoconfigure)

> Define beans with condition(if not exists, and auto inject configuration, which using `spring-boot-autoconfigure`).

#### [spring-boot-wechat-starter](https://github.com/yisen-cai/spring-wechat/tree/master/spring-boot-wechat-starter)

> Integrating upwards two library, enabling directly usage in spring boot project.

