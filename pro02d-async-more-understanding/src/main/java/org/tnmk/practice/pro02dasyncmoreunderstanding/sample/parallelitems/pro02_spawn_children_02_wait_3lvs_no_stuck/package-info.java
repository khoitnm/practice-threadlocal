/**
 * Scenario in this package:<br/>
 * This scenario is exactly the same as {@link org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_03_wait_2lvs}
 * The only difference is this code use {@link java.util.stream.Stream#parallel()} instead of using {@link org.springframework.scheduling.annotation.Async}
 * And hence it won't get stuck.
 * The reason it doesn't get stuck is each {@link java.util.stream.Stream#parallel()} will create one separate ForkJoinPool, hence it won't block other pool.
 * However, the only concern is memory if we create too many pools.
 */
package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro02_spawn_children_02_wait_3lvs_no_stuck;