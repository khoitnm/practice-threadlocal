package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.springasync.pro02_wait_async;

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
import org.tnmk.practice.pro02dasyncmoreunderstanding.sample.springasync.testsupport.CleanUpSupport;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {"spring.task.execution.pool.core-size=2"})

// After each test case, we still cannot totally clean up the TaskExecutor independently,
// so DirtiesContext will make sure taskExecutor of one test case won't affect the other test cases.
@DirtiesContext
class WaitAsyncService_1Lv_NoStuckTest {
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private WaitAsyncService service;


    @Test
    @DisplayName("Test when there's only 1 Level async, if the number of level1 threads is greater than core-size, no stuck.")
    void test_when_1Level_NoStuck() {
        // This case will demonstrate that the app will get stuck and won't be finished within 5 seconds.
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            service.asyncSpawnChildren(10, 0);
        });
    }

    @AfterEach
    public void afterEach() {
        CleanUpSupport.stopTaskExecutor(taskExecutor);
    }

}