package org.tnmk.practice.uuid;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.RepeatedTest;

import java.util.*;
import java.util.function.Supplier;

public class UuidConcurrencyTests {
    private final TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator();

    /**
     * Retry a few times, this method will fail the test because the generated Uuid is not unique!!!
     * @throws InterruptedException
     */
    @RepeatedTest(10)
    public void test_CustomizedUuidGenerator_Fail() throws InterruptedException {
        testUuidGenerator(() -> {
            return UuidGenerator.generateTimeBasedUuid();
        });
    }

    @RepeatedTest(50)
    public void test_StandardUuidGenerator_Success() throws InterruptedException {
        testUuidGenerator(() -> {
            return timeBasedGenerator.generate();
        });
    }

    private void testUuidGenerator(Supplier<UUID> uuidGenerationFunc) throws InterruptedException {
        int listSize = 10000;
        //I don't want to use synchronous list because it will impact threads processing
        //And don't use regular thread because it's not thread-safe.
        UUID[] allGeneratedUuids = new UUID[listSize];
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            final int index = i;
            Runnable runnable = () -> {
                UUID customizedUuid = uuidGenerationFunc.get();
                allGeneratedUuids[index] = customizedUuid;
            };
            Thread uuidGeneratorThread = new Thread(runnable);
            uuidGeneratorThread.start();
            threads.add(uuidGeneratorThread);
        }
        for (Thread thread : threads) {
            thread.join();
        }

        //Assert no duplicated uuids
        Set<UUID> uniqueUuidSet = new HashSet<>(Arrays.asList(allGeneratedUuids));
        System.out.println("Threads count: "+threads.size());
        System.out.println("All Generated Uuids count: "+allGeneratedUuids.length);
        System.out.println("Unique Uuids count: "+allGeneratedUuids.length);
        Assert.assertEquals(allGeneratedUuids.length, uniqueUuidSet.size());

    }
}
