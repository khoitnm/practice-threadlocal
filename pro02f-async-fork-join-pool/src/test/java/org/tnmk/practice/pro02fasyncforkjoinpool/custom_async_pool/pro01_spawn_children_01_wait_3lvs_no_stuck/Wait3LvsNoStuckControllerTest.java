package org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.pro01_spawn_children_01_wait_3lvs_no_stuck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practice.pro02fasyncforkjoinpool.test_infrastructure.BaseIntegrationTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class Wait3LvsNoStuckControllerTest extends BaseIntegrationTest {

  @Autowired
  private Wait3LvsNoStuckController wait3LvsNoStuckController;

  @Test
  public void when_usingDecoratedForkJoinPool_then_itWontGetStuck_and_MDCisCopiedAndCleanUpProperly() {
    // Given
    String requestIndex = "0";

    // When execution method, it won't get stuck. Hence, it should finish within 3 seconds.
    assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
      wait3LvsNoStuckController.asyncSpawnChildren(requestIndex, 12, 2, 10);
    });

  }
}
