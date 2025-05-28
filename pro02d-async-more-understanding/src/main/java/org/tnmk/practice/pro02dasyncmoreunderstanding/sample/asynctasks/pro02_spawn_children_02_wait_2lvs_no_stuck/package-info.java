/**
 * Scenario in this package:<br/>
 * Parent (Thread Level 01) thread create Child Threads (Thread Level 02) and wait for all child threads to finish.
 * But no room in pool for creating Thread Level 02.
 * Until now, it's the same as scenario in the package {@link org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_wait_async}
 *
 * Now, the difference is:
 * Thread Level 02 won't create Child Threads anymore, so even if the Thread Pool is full, each Thread Level 02 in the pool will be finished, one by one, regularly.
 * And hence, at some point, Thread Level 01 will also be finished.
 * And the pool won't be stuck.
 */
package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_02_wait_2lvs_no_stuck;