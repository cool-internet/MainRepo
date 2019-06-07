## Auth
### api
Click [Here](http://202.120.40.8:30334/swagger-ui.html).
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

