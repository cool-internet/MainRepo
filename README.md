## Zuul
### How to configure
In `application.yml`:
```yaml
server:
  port: 8092
spring:
  application:
    name: service-zuul
  cloud:
    consul:
      discovery:
        service-name: service-zuul
      host: localhost
      port: 8500
management:
  endpoint:
    health:
      show-details: always
zuul:
  routes:
    feign:
      path: /api-feign/**
      serviceId: service-ribbon
    bookstore:
      path: /api-bookstore/**
      serviceId: service-bookstore
```
This time, when you type `localhost:8092/api-bookstore/storages` 
in your browser, you will get the same as you do in `service-ribbon`.