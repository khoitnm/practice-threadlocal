package org.tnmk.practice.localthread.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ThreadObjectConcurrencyTests {

    @Test
    public void startContext() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ThreadObjectHolderRunnable threadObjectHolderRunnable = new ThreadObjectHolderRunnable(i);
            Thread successThread = new Thread(threadObjectHolderRunnable);


            FailThreadObjectHolderRunnable failThreadObjectHolderRunnable = new FailThreadObjectHolderRunnable(i);
            Thread failThread = new Thread(failThreadObjectHolderRunnable);

            successThread.start();
            failThread.start();
            threads.add(successThread);
            threads.add(failThread);
        }
        for (Thread thread : threads) {
            thread.join();
        }

    }

}
