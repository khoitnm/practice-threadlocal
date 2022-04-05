package org.tnmk.practice.pro01simplethreadlocal.localthread.simple;

import org.junit.Test;

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
