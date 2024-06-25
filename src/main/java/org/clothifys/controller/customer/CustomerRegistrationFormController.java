package org.clothifys.controller.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.clothifys.db.DBConnection;
import org.clothifys.dto.tm.CustomerTable;
import org.clothifys.entity.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerRegistrationFormController  implements Initializable {
    
    public TableView tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colTitle;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colDob;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colBankAccNo;

    public DatePicker dateDob;
    public TextField txtBankName;
    public TextField txtContactNo;
    public TextField txtEmail;
    public TextField txtCustomerId;
    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtBankAccNo;
    public TextField txtName;
    public ComboBox cmbTitle;
    public AnchorPane LodeFormContent;
    public TableColumn colBankName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colBankName.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        colBankAccNo.setCellValueFactory(new PropertyValueFactory<>("bankAccountNo"));
        LoadDropMenu();
        loadTable();

    }

    private void loadTable(){
        ObservableList<CustomerTable> customerTable = FXCollections.observableArrayList();
        ObservableList<Customer> allCustomers = CustomerController.getInstance().getAllCustomers();
        allCustomers.forEach(customer -> {
            CustomerTable customerTable1 = new CustomerTable(
                    customer.getCustomerId(),
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
            customerTable.add(customerTable1);
        });
        tblCustomer.setItems(customerTable);
    }



    private void LoadDropMenu() {
        ObservableList<Object> items = FXCollections.observableArrayList();
        items.add("MRS");
        items.add("MR");
        items.add("MS");
        items.add("MISS");
        cmbTitle.setItems(items);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws ParseException, RuntimeException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateDob.getValue().toString());
        Customer customer = new Customer(
                txtCustomerId.getText(),
                cmbTitle.getValue().toString(),
                txtName.getText(),
                dateDob.getValue(),
                txtNic.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtContactNo.getText(),
                txtBankName.getText(),
                txtBankAccNo.getText()

        );

        System.out.println(customer);

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?,?)");
            psTm.setString(1,customer.getCustomerId());
            psTm.setString(2,customer.getTitle());
            psTm.setString(3,customer.getName());
            psTm.setObject(4,customer.getDob());
            psTm.setString(5,customer.getNic());
            psTm.setString(6,customer.getAddress());
            psTm.setString(7,customer.getEmail());
            psTm.setString(8,customer.getContact());
            psTm.setString(9,customer.getBankName());
            psTm.setString(10,customer.getBankAccountNo());


            psTm.execute();

            loadTable();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) throws ParseException {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean execute = DBConnection.getInstance().getConnection().createStatement().execute("DELETE FROM Customer WHERE CustId='" + txtCustomerId.getText() + "'");
            System.out.println(execute);

            loadTable();
            if (execute){
                System.out.println("Customer not Deleted ");
            }else {
                System.out.println("Customer Deleted");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCustomerId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtBankAccNo.setText("");
        txtEmail.setText("");
        txtContactNo.setText("");
        txtBankName.setText("");
        cmbTitle.setValue(null);
        dateDob.setValue(null);
    }


    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            URL resource = this.getClass().getResource("/view/dash_board_form.fxml");
            if (resource == null) {
                throw new IllegalArgumentException("FXML file not found");
            }
            FXMLLoader loader = new FXMLLoader(resource);
            Parent load = loader.load();
            LodeFormContent.getChildren().clear();
            LodeFormContent.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IOException (e.g., show an alert to the user)
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            // Handle IllegalArgumentException (e.g., show an alert to the user)
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) {
        Customer customer = CustomerController.getInstance().searchCustomer(txtCustomerId.getText());

    }

}