## Load Balance
### What to use
[Ribbon](https://github.com/Netflix/ribbon)

[Feign](https://github.com/OpenFeign/feign)

[Hystrix](https://github.com/Netflix/Hystrix)
### How to use
#### Add dependency packages
`pom.xml`:
```xml
<dependencies>
        <!--hystrix dependencies-->
        <dependency>
            <groupId>com.netflix.hystrix</groupId>
            <artifactId>hystrix-core</artifactId>
            <version>1.5.18</version>
        </dependency>
        <dependency>
            <groupId>com.netflix.hystrix</groupId>
            <artifactId>hystrix-javanica</artifactId>
            <version>1.5.18</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-netflix-hystrix-dashboard</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>
        <!--feign dependencies-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-openfeign-core</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>
        <!--ribbon dependency-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
        </dependency>
        <!--service register dependencies-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
        <!--other dependencies-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
</dependencies>
```
#### Add configurations
`application.yml` :
```yaml
server:
  port: 8091
spring:
  application:
    name: ribbon
  cloud:
    consul:
      discovery:
        service-name: service-ribbon
      host: localhost
      port: 8500
management:
  endpoint:
    health:
      show-details: always
feign:
  client:
    config:
      feignName:
        connectTimeout: 5000
        readTimeout: 5000
        loggerLevel: full
  hystrix:
    enabled: true
```
#### Add annotations
In main java class, for example `RibbonApplication` :
```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
// if you'd like to user dashboard, add this annotation
@EnableHystrixDashboard
public class RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    // if you'd like to user dashboard, add below:
    @Bean
    public ServletRegistrationBean getServlet()
    {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
```
`@LoadBalanced` means this restTemplate bean will deal with load balance.
#### Nuclear Usage
At service layer, use `BookstoreService` as an example:
```java
@Service
public class BookstoreService {
    @Autowired
    RestTemplate restTemplate;

    public String getStorageContent(){
        return restTemplate.getForObject("http://service-bookstore/storages",String.class);
    }
}
```
`service-bookstore` will be translated to the ip address registered in consul.

But with `Feign`, `BookstoreService` only needs to write:
```java
@FeignClient(value = "service-bookstore")
public interface BookstoreService {
    @GetMapping(value = "/storages")
    String getStorageContent();
}
```
To customize your fallback method, you can change `BookstoreService` to:
```java
@FeignClient(value = "service-bookstore",fallback = BookstoreServiceFailure.class)
public interface BookstoreService {
    @GetMapping(value = "/storages")
    String getStorageContent();
}
```
and add `BookstroreServiceFailure` class:
```java
@Service
public class BookstoreServiceFailure implements BookstoreService{
    @Override
    public String getStorageContent()
    {
        return "service failed";
    }
}
```
This time, when you type `localhost:8091` in your browser,
you may get `service failed` on it if `service-bookstore` is down.