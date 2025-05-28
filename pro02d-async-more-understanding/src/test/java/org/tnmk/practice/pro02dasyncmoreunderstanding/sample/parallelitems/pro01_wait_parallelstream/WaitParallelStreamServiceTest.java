package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro01_wait_parallelstream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@Slf4j
@SpringBootTest
@SpringJUnitConfig
class WaitParallelStreamServiceTest {
    @Autowired
    private WaitParallelStreamService service;

    @Test
    @DisplayName("Test when there are 2 Levels that use parallel stream, it will never get stuck no matter how many children threads each level has.")
    void test_when_2Levels_never_get_Stuck() {
        // When using parallel stream, it will never get stuck.
        assertTimeoutPreemptively(Duration.ofSeconds(200), () -> {
            service.asyncSpawnChildren(50, 50);
        });
    }
}