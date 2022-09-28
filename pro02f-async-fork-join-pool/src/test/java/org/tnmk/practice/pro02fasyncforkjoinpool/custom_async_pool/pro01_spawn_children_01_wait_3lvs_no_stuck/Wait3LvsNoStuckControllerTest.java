package org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.pro01_spawn_children_01_wait_3lvs_no_stuck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practice.pro02fasyncforkjoinpool.test_infrastructure.BaseIntegrationTest;

import java.util.concurrent.ExecutionException;

public class Wait3LvsNoStuckControllerTest extends BaseIntegrationTest {

  @Autowired
  private Wait3LvsNoStuckController wait3LvsNoStuckController;

  @Test
  public void test() throws ExecutionException, InterruptedException {
    // Given
    int requestIndex = 0;

    // When
    wait3LvsNoStuckController.asyncSpawnChildren("" + requestIndex, 12, 2, 10);

    // Then
    // No stuck.
  }
}
