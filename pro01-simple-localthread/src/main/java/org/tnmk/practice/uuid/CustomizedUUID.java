package org.tnmk.practice.uuid;

import java.util.Objects;
import java.util.UUID;

public class CustomizedUUID {
    private final UUID originalUUID;
    private final UUID transformedUUID;

    public CustomizedUUID(UUID originalUUID, UUID transformedUUID) {
        this.originalUUID = originalUUID;
        this.transformedUUID = transformedUUID;
    }

    public UUID getOriginalUUID() {
        return originalUUID;
    }

    public UUID getTransformedUUID() {
        return transformedUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomizedUUID that = (CustomizedUUID) o;
        return Objects.equals(transformedUUID, that.transformedUUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transformedUUID);
    }

}