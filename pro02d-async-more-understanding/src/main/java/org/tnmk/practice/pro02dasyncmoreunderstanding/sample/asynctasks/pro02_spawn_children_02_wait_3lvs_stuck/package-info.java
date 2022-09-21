/**
 * Scenario in this package:<br/>
 * Parent thread wait for all child threads. But no room in pool for creating child thread, so parent thread get stuck, too!
 * And hence the pool will not be able to clean up, it always full.
 */
package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_02_wait_3lvs_stuck;