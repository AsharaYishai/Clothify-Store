package org.clothifys.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.clothifys.entity.Employee;
import org.clothifys.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController implements EmployeeService{
    private static EmployeeController instance;

    private EmployeeController(){}
    public static EmployeeController getInstance(){
        if(instance==null){
            return instance = new EmployeeController();
        }
        return instance;
    }

    public Employee searchEmployee(String employeeId){
        try {
            PreparedStatement psTm = CrudUtil.execute("SELECT * FROM employee WHERE empID=?");

            psTm.setString(1,employeeId);
            boolean execute = psTm.execute();
            if(execute){
                ResultSet resultSet = psTm.getResultSet();

                while (resultSet.next()){
                    return new Employee(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDate(5).toLocalDate(),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)
                    );
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ObservableList<Employee> getAllEmployees() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Employee");

            ObservableList<Employee> listTable = FXCollections.observableArrayList();
            while (resultSet.next()){
                listTable.add(
                        new Employee(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getDate(5).toLocalDate(),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getString(8)

                        )
                );
            }
            return listTable;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addEmployee(Employee employee){
        try {
            String SQL = "INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?)";
            CrudUtil.execute(
                    SQL,
                    employee.getEmpId(),
                    employee.getTitle(),
                    employee.getName(),
                    employee.getNic(),
                    employee.getDob(),
                    employee.getEmail(),
                    employee.getContact(),
                    employee.getAddress()

            );


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
