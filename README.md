# Playground for Dropwizard

How to start the Playground for Dropwizard application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-playground-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
