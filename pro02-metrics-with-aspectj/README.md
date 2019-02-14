# Introduction

> This project will use Spring Boot + Micrometer + Prometheus + TimedAspect (AspectJ)
>
> In the first glance, some metrics only works with @RestController layer (e.g. @Timed). 
> @Timed doesn't work with @Service layer. 
>
> This project will need TimedAspect which is configured in the TimedAspectConfig.java. 
> This is also a way to monitor metrics data when working with gRPC  

  
## Build our Spring Boot application
```
mvn clean install -DskipTests

```

Open the browser:
+ Business Logic APIs will be exposed via the port 8080 (by default): 
    + http://localhost:8080/welcome
    + http://localhost:8080/sample-api
 
+ While metrics APIs will be exposed via the port 9090 (configured in application.yml): 
    + http://localhost:9090/actuator/prometheus >>> this request will show a lot of information, you can see some metrics related to hotel_file there.

## Start Prometheus Server
> 
> **Note:**
> Somehow the docker still doesn't work, I'll check it later
> 


Using the docker compose
```
cd deployment\prometheus-server
docker-compose up -d --build
```
That will start 2 containers: Prometheus and Grafana.
 
Next on, run Spring boot app!

## Reference:
+ https://stackoverflow.com/questions/48704789/how-to-measure-service-methods-using-spring-boot-2-and-micrometer
