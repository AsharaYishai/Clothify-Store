package org.clothifys.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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
        LoadDropMenu();
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
