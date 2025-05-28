package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_03_wait_2lvs_stuck;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Slf4j
@SpringBootTest
@SpringJUnitConfig
@TestPropertySource(properties = {
    "spring.task.execution.pool.core-size=2"
})
class Wait2LvsStuck_ServiceTest {
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private Wait2LvsStuck_Service service;

    @Test
    void spawnChildren() {
        // This case will demonstrate that the app will get stuck and won't be finished within 5 seconds.
        // This is how the app gets stuck:
        // spring.task.execution.pool.core-size=2
        // 
        assertThrows(AssertionFailedError.class, () -> {
            assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
                service.asyncSpawnChildren(2, 1);
            });
        });
    }

    @AfterEach
    public void afterEach() {
        if (taskExecutor instanceof ThreadPoolTaskExecutor executor) {
            log.info("Shutdown thread pool task executor:");
            // If we don't do that, the children threads still exist and running even after the test case finished.
            // We want to stop those children threads, so we need to forcefully stop the thread pool. 
            executor.stop();
        }
    }

}