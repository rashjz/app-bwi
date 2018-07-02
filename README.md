# app-bwi


###### management.security.enabled=false
###### management.context-path=/actuator


By default hystrix dashboard url will be ../actuator/hystrix.stream 
after enter url you can specify app name and delay interval 

spring boot [hystrix](http://www.baeldung.com/spring-cloud-netflix-hystrix) implementation example 

```xml
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
			<version>1.4.4.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
			<version>1.4.4.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
			<version>${spring-boot-starter.version}</version>
		</dependency>
```

Java code example 

```java
    @HystrixCommand(fallbackMethod = "defaultGreeting")
    public Long someMethod(String data){
        return service.getExternalData(data);
    }
    
    private Long defaultGreeting(String data) {
        return 1L;
    }
```
And Finally you have to add hystrix annotation to main class


```java
@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```
