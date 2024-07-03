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
import org.clothifys.bo.BoFactory;
import org.clothifys.bo.custom.CustomerBo;
import org.clothifys.db.DBConnection;
import org.clothifys.dto.tm.CustomerTable;
import org.clothifys.entity.Customer;
import org.clothifys.util.BoType;

import java.io.IOException;
import java.net.URL;
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


    private CustomerBo customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

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

        //boolean b = CustomerController.getInstance().addCustomer(customer);
        boolean b = customerBo.saveCustomer(customer);
        if(b){
            new  Alert(Alert.AlertType.CONFIRMATION,"Customer not Added !").show();
        }else {
            new  Alert(Alert.AlertType.CONFIRMATION,"Customer  Added !").show();

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