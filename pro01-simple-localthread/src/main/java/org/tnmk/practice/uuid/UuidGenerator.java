package org.tnmk.practice.uuid;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import java.util.UUID;

/**
 * A utility for creating UUIDs that have a few more requirements than the standard java UUID generator.
 * It is by no means a requirement for all services to use this to generate UUIDs, only in situations where
 * requirements dictate.
 *
 * Created by mick on 22/10/18.
 */
public class UuidGenerator {
    private static final TimeBasedGenerator generator = Generators.timeBasedGenerator();

    /**
     * Generates a String represented, time based uuid, with hyphens removed. The left most bits are derived from the time
     * the UUID is generated. So UUIds generated with in a small time span will all start with the same characters.
     * @return A time based, string UUID.
     */
    public static UUID generateTimeBasedUuid(){
        //Split by hyphens
        String[] splitByHyphens = generator.generate().toString().toUpperCase().split("-");

        //rebuild as ordered: [HighTime][MidTime][LowTime][Randomness]
        return UUID.fromString(splitByHyphens[2] + '-' + splitByHyphens[1] + '-' + splitByHyphens[0] + '-' + splitByHyphens[3] + '-' + splitByHyphens[4]);
    }
}
