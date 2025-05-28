package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.springasync.pro00_simple_async;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleAsyncServiceTest {

    @Autowired
    private SimpleAsyncService simpleAsyncService;

    @DirtiesContext
    @Test
    void asyncSpawnChildren_shouldReturnResult() throws ExecutionException, InterruptedException {
        // Arrange
        int requestIndex = 1;
        int sleepDuration = 10;

        // Act
        String result = simpleAsyncService.asyncSpawnChildren(requestIndex, sleepDuration);

        // Assert
        assertNotNull(result, "Result should not be null");
    }
}