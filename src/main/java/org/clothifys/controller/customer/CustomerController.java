package org.clothifys.controller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.clothifys.util.CrudUtil;
import org.clothifys.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CustomerController implements CustomerService{
    private static CustomerController instance;

    private CustomerController(){}

    public static CustomerController getInstance(){
        if (instance==null){
            return instance=new CustomerController();
        }
        return instance;
    }

    public Customer searchCustomer(String customerId){
        try {
            PreparedStatement psTm = CrudUtil.execute("SELECT * FROM customer WHERE custID=?");

            psTm.setString(1,customerId);
            boolean execute = psTm.execute();
            if(execute){
                ResultSet resultSet = psTm.getResultSet();
                ;
                while (resultSet.next()){
                    return new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4).toLocalDate(),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getString(10)
                    );
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ObservableList<Customer> getAllCustomers() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");

            ObservableList<Customer> listTable = FXCollections.observableArrayList();
            while (resultSet.next()){
                listTable.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDate(4).toLocalDate(),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getString(9),
                            resultSet.getString(10)
                    )
                );
            }
            return listTable;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addCustomer(Customer customer){
        try {
            String SQL = "INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?,?)";
            CrudUtil.execute(
                    SQL,
                    customer.getId(),
                    customer.getTitle(),
                    customer.getName(),
                    customer.getDob(),
                    customer.getNic(),
                    customer.getAddress(),
                    customer.getEmail(),
                    customer.getContact(),
                    customer.getBankName(),
                    customer.getBankAccountNo()
                    );


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}
