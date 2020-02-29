package com.lynxted.example.tc.reuse

import spock.lang.Specification

import java.sql.Connection
import java.sql.DriverManager

import static com.lynxted.example.tc.reuse.CustomerFixture.customerForA
import static com.lynxted.example.tc.reuse.CustomerIdFixture.randomCustomerId

class CustomerRepositoryTcLibraryIT extends Specification implements ExecutionTimeMeasurementSupport {

    CustomerRepository customerRepository

    def setup() {
        startTimeMeasure()
        Connection connection = DriverManager.getConnection("jdbc:tc:postgresql:9.6.17:///test_db" +
                        "?TC_INITSCRIPT=db/customer_database_schema.sql" +
                        "&TC_REUSABLE=true")
        stopTimeMeasure()
        customerRepository = new CustomerRepository(connection)
    }

    def "should be able to retrieve saved customer"() {
        given:
            CustomerId customerId = randomCustomerId()
            Customer customer = customerForA(customerId)

        and:
            customerRepository.save(customer)

        when:
            Optional<Customer> foundCustomer = customerRepository.findById(customerId)

        then:
            foundCustomer.isPresent()
            foundCustomer.get() == customer
    }
}
