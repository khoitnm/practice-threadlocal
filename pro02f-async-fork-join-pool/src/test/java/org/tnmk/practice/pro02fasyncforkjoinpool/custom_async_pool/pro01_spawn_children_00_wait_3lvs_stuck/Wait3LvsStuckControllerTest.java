package org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.pro01_spawn_children_00_wait_3lvs_stuck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practice.pro02fasyncforkjoinpool.test_infrastructure.BaseIntegrationTest;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class Wait3LvsStuckControllerTest extends BaseIntegrationTest {

  @Autowired
  private Wait3LvsStuckController wait3LvsStuckController;

  @Test
  public void test() throws ExecutionException, InterruptedException {
    // Given
    int requestIndex = 0;

    // When and then
    Assertions.assertThrows(AssertionFailedError.class, () -> {
      // We expect the logic inside will get stuck, so it won't return any result.
      // Hence, we expect the assertTimeout will fail, means it will throw AssertionFailedError
      //
      // And because the target method will get stuck,
      // the assertions need to be in another thread so that the Assertions itself won't get stuck.
      // That's why we use assertTimeoutPreemptively(...) instead of assertTimeout(...)
      assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {

        wait3LvsStuckController.asyncSpawnChildren(12, 2, 10);
      });
    });
  }
}
