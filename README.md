# app-bwi


management.security.enabled=false
management.context-path=/actuator

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
