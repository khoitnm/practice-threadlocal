# Threads:
## ExecutorService vs. ForkJoin
This knowledge is important to use the right approach and avoid the whole app to get stuck when scale up:
https://www.linkedin.com/pulse/handle-long-running-tasks-java-threads-sameh-muhammed/?trk=pulse-article_more-articles_related-content-card
- ForkJoin:
  - Good explanation: https://www.youtube.com/watch?v=5wgZYyvIVJk 
  - More details explanation: https://www.youtube.com/watch?v=whHaNMmIOgI&t=6s
  - ForkJoin in pure Java: https://www.baeldung.com/java-fork-join

  
Spring `@Async` will use `ThreadPoolTaskExecutor`, which is basically `ExecutorService`
https://medium.com/globant/asynchronous-calls-in-spring-boot-using-async-annotation-d34d8a82a60c