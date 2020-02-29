package com.lynxted.example.tc.reuse

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic

class CustomerFixture {
    static Customer customerForA(CustomerId customerId) {
        String firstName = randomAlphabetic(10)
        String lastName = randomAlphabetic(10)
        Customer.of(customerId, firstName, lastName)
    }
}
