package org.clothifys.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderDetailsFormController {
    public TableView tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn ColCustomerName;
    public TableColumn ColContact;
    public TableColumn colEmail;
    public TableColumn colBankName;
    public TableColumn colBankAccNo;
    public TableView tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colSize;
    public TableColumn colAmount;
    public TextField txtOrderId;

    public void btnViewOnAction(ActionEvent actionEvent) {
    }
}
