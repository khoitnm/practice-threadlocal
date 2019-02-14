package org.tnmk.practice.localthread.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ThreadObjectConcurrencyTests {

    @Test
    public void startContext() {
        for (int i = 0; i < 5; i++) {
            ThreadObjectHolderRunnable runnable = new ThreadObjectHolderRunnable(i);
            new Thread(runnable).start();
        }
    }

}
