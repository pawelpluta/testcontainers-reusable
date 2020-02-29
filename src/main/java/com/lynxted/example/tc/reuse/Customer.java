package com.lynxted.example.tc.reuse;

import java.util.Objects;

class Customer {
    private CustomerId id;
    private String firstName;
    private String lastName;

    Customer(CustomerId id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    static Customer of(CustomerId id, String firstName, String lastName) {
        return new Customer(id, firstName, lastName);
    }

    CustomerId id() {
        return id;
    }

    String firstName() {
        return firstName;
    }

    String lastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
