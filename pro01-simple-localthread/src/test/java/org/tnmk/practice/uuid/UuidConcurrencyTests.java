package org.tnmk.practice.uuid;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.RepeatedTest;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class UuidConcurrencyTests {
    private final TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator();

    /**
     * Depend on your computer power, you may want to increase the uuidsCount to see duplications.
     *
     * @throws InterruptedException
     */
    @RepeatedTest(10)
    public void test_CustomizedUuidGenerator_Fail() throws InterruptedException {
        findDuplicatedCustomizedUUID(() -> {
            return UuidGenerator.generateTimeBasedUuid();
        }, 40000);
    }

    @RepeatedTest(50)
    public void test_StandardUuidGenerator_Success() throws InterruptedException {
        testUuidGenerator(() -> {
            return timeBasedGenerator.generate();
        },40000);
    }

    private void testUuidGenerator(Supplier<Object> uuidGenerationFunc, int uuidsCount) throws InterruptedException {
        //I don't want to use synchronous lists because it will impact threads processing
        //And don't use regular lists because it's not thread-safe.
        Object[] allGeneratedUuids = new Object[uuidsCount];
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < uuidsCount; i++) {
            final int index = i;
            Runnable runnable = () -> {
                Object customizedUuid = uuidGenerationFunc.get();
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
        Set<Object> uniqueUuidSet = new HashSet<>(Arrays.asList(allGeneratedUuids));
        System.out.println("Threads count: " + threads.size());
        System.out.println("All Generated Uuids count: " + allGeneratedUuids.length);
        System.out.println("Unique Uuids count: " + allGeneratedUuids.length);
        Assert.assertEquals(allGeneratedUuids.length, uniqueUuidSet.size());

    }

    private void findDuplicatedCustomizedUUID(Supplier<CustomizedUUID> uuidGenerationFunc, int uuidsCount) throws InterruptedException {
        //I don't want to use synchronous lists because it will impact threads processing
        //And don't use regular lists because it's not thread-safe.
        CustomizedUUID[] allGeneratedUuids = new CustomizedUUID[uuidsCount];
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < uuidsCount; i++) {
            final int index = i;
            Runnable runnable = () -> {
                CustomizedUUID customizedUuid = uuidGenerationFunc.get();
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
        Set<UUID> uniqueTransformedUuidSet = new HashSet<>();
        //Key: transformedUUID, Value: duplicated values.
        Map<UUID, List<CustomizedUUID>> duplicatedCustomizedUUIDsMap = new HashMap<>();
        for (CustomizedUUID customizedUUID : allGeneratedUuids) {
            UUID transformedUUID = customizedUUID.getTransformedUUID();
            boolean isAddedTranformedUUID = uniqueTransformedUuidSet.add(transformedUUID);
            boolean isDuplicated = !isAddedTranformedUUID;
            if (isDuplicated) {

                List<CustomizedUUID> duplicatedCustomizedUUIDs = findExistingCustomizedUUIDWithTheSameTransformedUUID(transformedUUID, allGeneratedUuids);
                duplicatedCustomizedUUIDsMap.put(transformedUUID, duplicatedCustomizedUUIDs);
            }
        }

        System.out.println("Threads count: " + threads.size());
        System.out.println("All Generated Uuids count: " + allGeneratedUuids.length);
        System.out.println("Unique Uuids count: " + allGeneratedUuids.length);

        for (UUID transformedUUID : duplicatedCustomizedUUIDsMap.keySet()) {
            List<CustomizedUUID> duplicatedCustomizedUUIDs = duplicatedCustomizedUUIDsMap.get(transformedUUID);
//            List<UUID> duplicatedOriginalUUIDs = duplicatedCustomizedUUIDs.stream().map(customizedUUID -> customizedUUID.getOriginalUUID()).collect(Collectors.toList());
            System.out.println("TransformedUUID: " + transformedUUID + ". Duplicated original: " + duplicatedCustomizedUUIDs);
        }
        Assert.assertEquals(allGeneratedUuids.length, uniqueTransformedUuidSet.size());

    }

    private List<CustomizedUUID> findExistingCustomizedUUIDWithTheSameTransformedUUID(UUID transformedUUID, CustomizedUUID[] allGeneratedUuids) {
        List<CustomizedUUID> allCustomizedUUIDWithTheSameTranformedUUID = new ArrayList<>();
        for (CustomizedUUID customizedUUID : allGeneratedUuids) {
            if (customizedUUID.getTransformedUUID().equals(transformedUUID)) {
                allCustomizedUUIDWithTheSameTranformedUUID.add(customizedUUID);
            }
        }
        return allCustomizedUUIDWithTheSameTranformedUUID;
    }
}
