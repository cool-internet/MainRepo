## Auth
### api
Click [Here](localhost:8088/swagger-ui.html).
### note
`application.yml` should change because consul's position:
```yaml
  cloud:
    consul:
      discovery:
        service-name: service-auth
        heartbeat:
          enabled: true
      host: 202.120.40.8
      port: 30332
```

