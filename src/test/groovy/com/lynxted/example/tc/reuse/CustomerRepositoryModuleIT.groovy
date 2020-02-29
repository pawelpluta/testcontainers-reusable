package com.lynxted.example.tc.reuse

import org.testcontainers.containers.PostgreSQLContainer
import spock.lang.Specification

import java.sql.Connection
import java.sql.DriverManager

import static com.lynxted.example.tc.reuse.CustomerFixture.customerForA
import static com.lynxted.example.tc.reuse.CustomerIdFixture.randomCustomerId

class CustomerRepositoryModuleIT extends Specification implements ExecutionTimeMeasurementSupport {

    CustomerRepository customerRepository

    def setup() {
        startTimeMeasure()
        PostgreSQLContainer container = new PostgreSQLContainer<>("postgres:9.6.17")
                .withReuse(true)
                .withUsername("testUser")
                .withPassword("testUser")
                .withDatabaseName("test_db")
                .withInitScript("db/customer_database_schema.sql")
        container.start()
        Connection connection = DriverManager.getConnection(container.getJdbcUrl(), "testUser", "testUser")
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
