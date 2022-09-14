/**
 * Scenario in this package:<br/>
 * Parent thread don't need to wait for all child threads to complete.
 * So, no matter whether there's room in pool for creating child thread or not, the parent thread just finish right away.
 * and hence the pool have room for continue handling child threads.
 * So it won't get stuck.
 */
package org.tnmk.practice.pro02easyncseparatepools.sample.asynctasks.pro02_spawn_children_02_no_wait_no_stuck;