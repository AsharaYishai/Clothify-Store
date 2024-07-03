package org.clothifys.controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.clothifys.controller.customer.CustomerController;
import org.clothifys.dto.tm.CustomerTable;
import org.clothifys.dto.tm.EmployeeTable;
import org.clothifys.entity.Customer;
import org.clothifys.entity.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeRegistrationForm implements Initializable {
    public DatePicker dateDob;
    public TextField txtContactNo;
    public TextField txtEmail;
    public TextField txtCustomerId;
    public TextField txtAddress;
    public TextField txtNic;
    public TextField txtName;
    public ComboBox cmbTitle;
    public TableView tblEmployee;
    public TableColumn colId;
    public TableColumn colTitel;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableColumn colAddress;
    public AnchorPane LodeFormContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colTitel.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        LoadDropMenu();
        loadTable();
    }

    public void loadTable(){
        ObservableList<EmployeeTable> employeeTable = FXCollections.observableArrayList();
        ObservableList<Employee> allEmployees = EmployeeController.getInstance().getAllEmployees();
        allEmployees.forEach(employee -> {
            EmployeeTable employeeTable1 = new EmployeeTable(
                    employee.getEmpId(),
                    employee.getTitle(),
                    employee.getName(),
                    employee.getNic(),
                    employee.getDob(),
                    employee.getEmail(),
                    employee.getContact(),
                    employee.getAddress()
            );
            employeeTable.add(employeeTable1);
        });
        tblEmployee.setItems(employeeTable);
    }

    private void LoadDropMenu() {
        ObservableList<Object> items = FXCollections.observableArrayList();
        items.add("MRS");
        items.add("MR");
        items.add("MS");
        items.add("MISS");
        cmbTitle.setItems(items);
    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtCustomerId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtNic.setText("");
        txtEmail.setText("");
        txtContactNo.setText("");
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
}
