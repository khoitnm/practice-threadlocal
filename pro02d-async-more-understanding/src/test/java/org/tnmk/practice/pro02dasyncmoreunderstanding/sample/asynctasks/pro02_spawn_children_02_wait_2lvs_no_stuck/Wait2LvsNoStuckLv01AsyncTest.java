
package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_02_wait_2lvs_no_stuck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@SpringJUnitConfig
class Wait2LvsNoStuckLv01AsyncTest {

    @Autowired
    private Wait2LvsNoStuckLv01Async asyncService;

    @Test
    void spawnChildren() throws InterruptedException, ExecutionException, TimeoutException {
        asyncService.spawnChildren(5, 5);
    }
}