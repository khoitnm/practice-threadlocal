This sample code shows a way to copy data of a ThreadLocal (MDC) from a parent thread to child threads (spawn threads) 
by using `AsyncConfigurerSupport` of Spring framework.

# References
- https://github.com/spring-cloud/spring-cloud-sleuth/issues/1022
- https://grokonez.com/java-integration/start-spring-async-spring-boot

Thread vs. Executor:
- https://javarevisited.blogspot.com/2016/12/difference-between-thread-and-executor.html 
- https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
- https://stackify.com/java-thread-pools/ (mentioned worker thread)


Spring Task Executor:
- https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/scheduling.html  

Others:
- https://github.com/micronaut-projects/micronaut-core/issues/1049
- https://proandroiddev.com/synchronization-and-thread-safety-techniques-in-java-and-kotlin-f63506370e6d
- https://www.baeldung.com/java-thread-safety
