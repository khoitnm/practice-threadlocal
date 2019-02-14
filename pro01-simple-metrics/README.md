# Introduction

> This project will use Spring Boot + Micrometer + Prometheus
>
> In the first glance, some metrics only works with @RestController layer (e.g. @Timed).
>
> The @Timed doesn't work with @Service layer, so we may need to think about integrating with Dropwizard metrics. <br/>

  
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
+ https://dzone.com/articles/monitoring-using-spring-boot-2-prometheus-and-graf
+ http://www.java-allandsundry.com/2017/11/using-micrometer-with-spring-boot-2.html 
