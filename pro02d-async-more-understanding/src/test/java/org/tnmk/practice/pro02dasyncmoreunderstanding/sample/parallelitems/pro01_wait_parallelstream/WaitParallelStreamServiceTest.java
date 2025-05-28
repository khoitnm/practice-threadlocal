package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro01_wait_parallelstream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tnmk.practice.pro02dasyncmoreunderstanding.sample.springasync.pro02_wait_async.WaitAsyncServiceTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Slf4j
@SpringBootTest
class WaitParallelStreamServiceTest {
    @Autowired
    private WaitParallelStreamService service;

    /**
     * You see there's big difference with test case {@link WaitAsyncServiceTest}:
     * - Both test case has 2 levels of async tasks, and parallelism here is also 2, equals to spring.task.execution.pool.core-size.
     * - But this test case won't get stuck, while {@link WaitAsyncServiceTest} get stuck.
     */
    @Test
    @DisplayName("Test when there are 2 Levels that use parallel stream, it will never get stuck no matter how many children threads each level has.")
    void test_when_2Levels_never_get_Stuck() {
        // Configure parallelism for the common ForkJoinPool (which is used by Java parallel streams).
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");

        // When using parallel stream, it will never get stuck.
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            service.asyncSpawnChildren(3, 3);
        });
    }
}