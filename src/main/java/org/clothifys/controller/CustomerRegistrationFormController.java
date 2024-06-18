package org.clothifys.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomerRegistrationFormController {
    public JFXTextField txtCustomerId;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtNic;
    public JFXTextField txtTitle;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public JFXTextField txtBankAccNo;
    public JFXTextField txtDob;
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

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }
}
