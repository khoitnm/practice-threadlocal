package org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.springasync.pro02_wait_async;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.springasync.testsupport.CleanUpSupport;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {"async.pool.parallelism=2"})
public class WaitAsyncServiceTest {

    @Autowired
    private WaitAsyncService service;

//    @BeforeEach
//    public void setup() {
//        // Configure parallelism for the common ForkJoinPool (which is used by Java parallel streams).
////        System.setProperty("async.pool.parallelism", "2");
//    }

    // After each test method, we still cannot totally clean up the TaskExecutor independently,
    // so DirtiesContext will reset the TaskExecutor after each test method.
    @DirtiesContext
    @Test
    @DisplayName("Test when there's only 1 Level async, if the number of level1 threads is greater than core-size, no stuck.")
    void test_when_1Level_NoStuck() {
        // This case will demonstrate that the app will get stuck and won't be finished within 5 seconds.
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            service.asyncSpawnChildren(10, 0);
        });
    }

    @DirtiesContext //reset the TaskExecutor after each test method.
    @Test
    @DisplayName("Test when there are 2 Levels async, if the number of level1 >= core-size, no stuck, thanks for @Async(AsyncSupport.EXECUTOR_BEAN_NAME).")
    void test_when_2Levels_If_Lv1AndLv2_GreaterThan_CoreSize_NoStuck() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            service.asyncSpawnChildren(2, 1);
        });
    }


    @DirtiesContext //reset the TaskExecutor after each test method.
    @Test
    @DisplayName("Test when there are 2 Levels async, if the number of level1 < core-size, no stuck.")
    void test_when_2Levels_If_Lv1AndLv2_lessThan_CoreSize_NoStuck() {
        // This case will demonstrate that the app will get stuck and won't be finished within 5 seconds.
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            service.asyncSpawnChildren(1, 3);
        });
    }

}