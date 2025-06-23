/**
 * Scenario in this package:<br/>
 * This scenario is exactly the same as {@link org.tnmk.practice.pro02gasyncvirtualthreadsimple.sample.springasync.pro02_wait_async}
 * The only difference is this code use {@link java.util.stream.Stream#parallel()} instead of using {@link org.springframework.scheduling.annotation.Async}
 * But the strange thing is this case won't get stuck no matter how many child threads (at both levels) are created.
 */
package org.tnmk.practice.pro02gasyncvirtualthreadsimple.sample.parallelitems.pro01_wait_parallelstream;