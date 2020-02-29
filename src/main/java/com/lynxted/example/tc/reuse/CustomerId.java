package com.lynxted.example.tc.reuse;

import java.util.Objects;
import java.util.UUID;

class CustomerId {
    private String value;

    CustomerId(String value) {
        this.value = value;
    }

    static CustomerId random() {
        return new CustomerId(UUID.randomUUID().toString());
    }

    static CustomerId of(String value) {
        return new CustomerId(value);
    }

    String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerId that = (CustomerId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
