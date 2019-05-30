## Load Balance
### What to use
[Ribbon](https://github.com/Netflix/ribbon)

[Feign](https://github.com/OpenFeign/feign)
### How to use
#### Add dependency packages
`pom.xml`:
```xml
<dependencies>
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
```
#### Add annotations
In main java class, for example `RibbonApplication` :
```java
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
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
