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
     */
    @RepeatedTest(10)
    public void test_CustomizedUuidGenerator_Fail() throws InterruptedException {
        boolean expectAllGeneratedUuidsAreUnique = false;
        testUuidGenerator(expectAllGeneratedUuidsAreUnique, () -> {
            return UuidGenerator.generateTimeBasedUuid();
        });
    }

    @RepeatedTest(50)
    public void test_StandardUuidGenerator_Success() throws InterruptedException {
        boolean expectAllGeneratedUuidsAreUnique = true;
        testUuidGenerator(expectAllGeneratedUuidsAreUnique, () -> {
            return timeBasedGenerator.generate();
        });
    }

    private void testUuidGenerator(boolean expectAllGeneratedUuidsAreUnique, Supplier<UUID> uuidGenerationFunc) throws InterruptedException {
        int uuidsCount = 10000;
        //I don't want to use synchronous lists because it will impact threads processing
        //And don't use regular lists because it's not thread-safe.
        UUID[] allGeneratedUuids = new UUID[uuidsCount];
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < uuidsCount; i++) {
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
        System.out.println("Threads count: " + threads.size());
        System.out.println("All Generated Uuids count: " + allGeneratedUuids.length);
        System.out.println("Unique Uuids count: " + allGeneratedUuids.length);
        if (expectAllGeneratedUuidsAreUnique) {
            Assert.assertEquals(allGeneratedUuids.length, uniqueUuidSet.size());
        } else {
            Assert.assertNotEquals(allGeneratedUuids.length, uniqueUuidSet.size());
        }

    }
}
