package org.clothifys.controller.employee;

import javafx.collections.ObservableList;
import org.clothifys.entity.Employee;

public interface EmployeeService {

    ObservableList<Employee> getAllEmployees();

    boolean addEmployee(Employee employee);

}
