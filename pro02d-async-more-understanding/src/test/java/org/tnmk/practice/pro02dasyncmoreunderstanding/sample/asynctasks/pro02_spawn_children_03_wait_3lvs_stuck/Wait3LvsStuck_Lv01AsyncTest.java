
package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro03_spawn_children_03_wait_3lvs_stuck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_03_wait_3lvs_stuck.Wait3LvsStuck_Lv01Async;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@SpringJUnitConfig
class Wait3LvsStuck_Lv01AsyncTest {

    @Autowired
    private Wait3LvsStuck_Lv01Async lv01Async;

    @Test
    void spawnChildren() {
        Assertions.assertThrowsExactly(TimeoutException.class, () -> {
            // spring.task.execution.pool.core-size is 5 (configured in application.yml)
            // So the following number will make it get stuck.
            // This case will demonstrate that the app will get stuck and won't be finished within 10 seconds.
            lv01Async.spawnChildren(4, 4, 50)
                    // If you use .get() without timeout, it will get stuck forever.
                    .get(5, TimeUnit.SECONDS);
        });
    }
}