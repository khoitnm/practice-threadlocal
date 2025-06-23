/**
 * Overview
 * This package demonstrates a scenario where threads are created in two levels by using @Async.
 * And this can lead to a case that make application hang when the thread pool becomes full when creating level 1 threads, so they cannot create level 2 threads.
 * <p/>
 * Overview logic:
 *      Main Thread: The thread that invokes the WaitAsyncService methods, which try to create many level 1 threads by using @Async.
 *      Thread Level 01: Parent threads that spawn many level 2 threads by using @Async.
 *      Thread Level 02: Just sleep a bit and finish.
 */
package org.tnmk.practice.pro02gasyncvirtualthreadsimple.sample.springasync.pro02_wait_async;