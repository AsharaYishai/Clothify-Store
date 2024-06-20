package org.clothifys.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

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
    public AnchorPane LodeFormContent;

    public void btnViewOnAction(ActionEvent actionEvent) {
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
