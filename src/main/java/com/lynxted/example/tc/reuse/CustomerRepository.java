package com.lynxted.example.tc.reuse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class CustomerRepository {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerRepository.class);
    private static final String SAVE_NEW_CUSTOMER = "INSERT INTO db_plain.customer(id, first_name, last_name) values (?, ?, ?)";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT id, first_name, last_name FROM db_plain.customer WHERE id = ?";

    private final Connection connection;

    CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    void save(Customer customer) {
        try (PreparedStatement insertStatement = connection.prepareStatement(SAVE_NEW_CUSTOMER)) {
            insertStatement.setString(1, customer.id().value());
            insertStatement.setString(2, customer.firstName());
            insertStatement.setString(3, customer.lastName());
            insertStatement.execute();
        } catch (SQLException e) {
            LOG.info("Could not save customer {} {}, due to {}", customer.firstName(), customer.lastName(), e.getMessage());
        }
    }

    Optional<Customer> findById(CustomerId customerId) {
        try (PreparedStatement selectStatement = connection.prepareStatement(FIND_CUSTOMER_BY_ID)){
            selectStatement.setString(1, customerId.value());
            return Optional.ofNullable(customerFrom(selectStatement.executeQuery()));
        } catch (SQLException e) {
            LOG.info("Could not find customer for id {}", customerId.value());
        }
        return Optional.empty();
    }

    private Customer customerFrom(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return Customer.of(
                    CustomerId.of(resultSet.getString(1)),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return null;
    }

}
