package org.clothifys.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AddItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtSellingPrice;
    public JFXTextField txtSize;
    public JFXTextField txtQty;
    public JFXTextField txtSupplierName;
    public JFXTextField txtBuyingPrice;
    public TableView tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colSize;
    public TableColumn colQty;
    public TableColumn colBuyingPrice;
    public TableColumn colSellingPrice;
    public TableColumn colSupplierId;
    public AnchorPane LodeFormContent;

    public void btnAddItemOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtItemCode.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        txtSize.setText("");
        txtBuyingPrice.setText("");
        txtSellingPrice.setText("");
        txtSupplierName.setText("");
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
