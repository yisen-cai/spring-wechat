## Spring Wechat

> Referencing wechat official document, supplying wechat server client APIs, integrating with spring.

[Following this tutorial](https://reflectoring.io/spring-boot-starter/)

### Usage

#### Add Dependency

`Gradle`:

~~~groovy
implementation("com.glancebar.wechat:spring-boot-wechat-starter:0.0.2")
~~~

`Maven`:

~~~xml
<dependency>
  <groupId>com.glancebar.wechat</groupId>
  <artifactId>spring-boot-starter-wechat</artifactId>
  <version>LATEST</version>
</dependency>
~~~



#### Define Configuation

~~~yaml
# resources/application.yml
wechat:
  mini-program:
    app-id: your-app-id
    app-secret: your-app-secret
~~~



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

#### [spring-boot-wechat-starter](https://github.com/yisen-cai/spring-wechat/tree/master/spring-boot-wechat-starter)

> Enabling directly usage in spring boot project.

