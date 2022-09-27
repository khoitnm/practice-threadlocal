/**
 * Scenario in this package:<br/>
 * Parent (Thread Level 01) thread create Child Threads (Thread Level 02) and wait for all child threads to finish.
 * But no room in pool for creating Thread Level 02.
 * The thing is, Thread Level 02 also trying to create Thread Level 03 and wait for them all.
 * But not Thread Pool are full, hence no single Thread level 03 is created, hence no one can be finished.
 * Then no single Thread Level 02 could be finished.
 * And hence no single Thread Level 01 could be finished.
 * And hence the pool will not be able to clean up, it's always full.
 */
package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_03_wait_3lvs_stuck;