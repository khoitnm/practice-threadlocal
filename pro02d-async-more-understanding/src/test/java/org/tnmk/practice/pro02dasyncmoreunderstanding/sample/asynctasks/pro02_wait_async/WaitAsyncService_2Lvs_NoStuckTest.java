package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_wait_async;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.testsupport.CleanUpSupport;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Slf4j
@SpringBootTest
@SpringJUnitConfig
@TestPropertySource(properties = {"spring.task.execution.pool.core-size=2"})

// After each test case, we still cannot totally clean up the TaskExecutor independently,
// so DirtiesContext will make sure taskExecutor of one test case won't affect the other test cases.
@DirtiesContext
class WaitAsyncService_2Lvs_NoStuckTest
{
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private WaitAsyncService service;

    @Test
    @DisplayName("Test when there are 2 Levels async, if the number of level1 < core-size, no stuck.")
    void test_when_2Levels_If_Lv1AndLv2_lessThan_CoreSize_NoStuck() {
        // This case will demonstrate that the app will get stuck and won't be finished within 5 seconds.
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            service.asyncSpawnChildren(1, 3);
        });
    }

    @AfterEach
    public void afterEach() {
        CleanUpSupport.stopTaskExecutor(taskExecutor);
    }

}