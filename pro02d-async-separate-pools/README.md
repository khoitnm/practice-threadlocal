# Threads:
## ExecutorService vs. ForkJoin
This knowledge is important to use the right approach and avoid the whole app to get stuck when scale up:
https://www.linkedin.com/pulse/handle-long-running-tasks-java-threads-sameh-muhammed/?trk=pulse-article_more-articles_related-content-card
- ForkJoin:
  - Good explanation: https://www.youtube.com/watch?v=5wgZYyvIVJk 
  - More details explanation: https://www.youtube.com/watch?v=whHaNMmIOgI&t=6s
  - ForkJoin in pure Java: https://www.baeldung.com/java-fork-join
  - ForkJoin CommonPool understanding: https://dzone.com/articles/be-aware-of-forkjoinpoolcommonpool
- ExecutorService:
  - Concept: 
    - Java ExecutorService - Part 1 - Introduction: 
      - https://www.youtube.com/watch?v=6Oo-9Can3H8&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=5
      - https://www.youtube.com/watch?v=Dma_NmOrp1c&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=7
      - Explain how Future works: https://www.youtube.com/watch?v=NEZ2ASoP_nY&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=8
  - Pure Java: https://www.baeldung.com/java-executor-service-tutorial
  
Spring `@Async` will use `ThreadPoolTaskExecutor`, which is basically `ExecutorService`
https://medium.com/globant/asynchronous-calls-in-spring-boot-using-async-annotation-d34d8a82a60c