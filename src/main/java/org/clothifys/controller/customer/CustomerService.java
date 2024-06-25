package org.clothifys.controller.customer;

import javafx.collections.ObservableList;
import org.clothifys.entity.Customer;

public interface CustomerService {
    Customer searchCustomer(String customerId);

    ObservableList<Customer> getAllCustomers();

    boolean addCustomer(Customer customer);

}
