package org.tnmk.practice.localthread.simple;

import org.junit.Assert;
import org.junit.Test;
import org.tnmk.practice.uuid.UuidGenerator;

import java.util.*;

public class UuidConcurrencyTests {

    @Test
    public void startContext() throws InterruptedException {
        List<UUID> uuids = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Runnable runnable = () -> {
                UUID uuid = UuidGenerator.generateTimeBasedUuid();
                uuids.add(uuid);
            };
            Thread uuidGeneratorThread = new Thread(runnable);
            uuidGeneratorThread.start();
            threads.add(uuidGeneratorThread);
        }
        for (Thread thread : threads) {
            thread.join();
        }

        //Assert no duplicated uuids
        Set<UUID> uuidSet = new HashSet<>(uuids);
        Assert.assertEquals(uuids.size(), uuidSet.size());
    }

}
