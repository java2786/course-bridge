# Spring Cloud Config Server and Client Setup Guide
This guide will help you configure a Spring Cloud Config Server and Config Client in a Spring Boot microservices architecture. The Config Server provides external configuration for your applications, allowing you to manage configurations centrally.


## Create Config Server Application
Add the following dependencies to your `pom.xml`:
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
```

## application.yml for Config Server
Create an application.yml file with the following content:

```yml
server:
  port: 8888

spring:
  application:
    name: config-server
  cloud:
    config:
      server:
        git:
          # uri: https://github.com/yourusername/your-config-repo
          # uri: file:///${user.home}/Desktop/config-repo
          uri: file:///home/jaivik/thispc/trainings/BM/java-fullstack/course-bridge-app/config-repo
          # Optional: If your repository is private, you may need to provide credentials
          # username: your-username
          # password: your-password
          clone-on-start: true
          default-label: master # branch name

  # discovery:
  #   client:
  #     service-url:
  #       defaultZone: http://localhost:8762/eureka/  # Eureka server URL

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8762/eureka/

logging:
  level:
    org.springframework.cloud: DEBUG
```

## Enable Config server
In your main application class, enable the Config Server:
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}

```

## Create Local Config Repository
Create a directory for your config repository and initialize it:
```bash
mkdir config-repo
cd config-repo
touch course-bridge-student-service.yml
git init
git add .
git commit -m "Initial commit"
```

### config-repo/course-bridge-student-service.yml
Add the following configuration to your course-bridge-student-service.yml:

```yml
server:
  port: 9091

spring:
  application:
    name: course-bridge-student-service

  datasource:
    url: jdbc:h2:mem:studentdb
    # url: jdbc:h2:file:/data/demo
    driverClassName: org.h2.Driver
    username: sa
    password: 
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: create
    show-sql: true
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        trace: false
        web-allow-others: false

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8762/eureka/
```

## Test the Config Server
You can test if your Config Server is working correctly by accessing the following URLs in your browser:
- For the student service configuration:
    - http://localhost:8888/course-bridge-student-service/default
This should return the configuration properties defined in course-bridge-student-service.yml.

    - http://10.0.2.15:8888/config-server/course-bridge-student-service


## Add Dependency to the MicroService
In your microservice application, add the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

## Update Service application.yml
```yml
spring:
  config:
    import: "configserver:"  # Add this line to import configuration from Config Server
```



By following these steps, you should have a fully functional Spring Cloud Config Server and Config Client setup. This allows your microservices to retrieve their configuration from a central location, making it easier to manage and update configurations across multiple services.