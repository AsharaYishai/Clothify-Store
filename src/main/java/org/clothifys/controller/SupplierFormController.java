package org.clothifys.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {
    public TextField txtContactNo;
    public TextField txtEmail;
    public TextField txtSupplierId;
    public TextField txtAddress;
    public TextField txtCompany;
    public TextField txtName;
    public ComboBox cmbTitle;
    public TableView tblSupplier;
    public TableColumn colSupplier;
    public TableColumn colTitle;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colCompany;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;

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

    public void btnAddSupplierOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }


}
