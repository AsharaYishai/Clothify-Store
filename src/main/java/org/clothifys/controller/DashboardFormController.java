package org.clothifys.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DashboardFormController {
    public AnchorPane LodeFormContent;
    public Label lblDate;
    public Label lblTime;

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("view/plac_order_form.fxml");

        assert resource != null;

        Parent load = (Parent) FXMLLoader.load(resource);
        this.LodeFormContent.getChildren().clear();
        this.LodeFormContent.getChildren().add(load);

    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("view/customer_registration_form.fxml");

        assert resource != null;

        Parent load = (Parent) FXMLLoader.load(resource);
        this.LodeFormContent.getChildren().clear();
        this.LodeFormContent.getChildren().add(load);
    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("view/add_item_form.fxml");

        assert resource != null;

        Parent load = (Parent) FXMLLoader.load(resource);
        this.LodeFormContent.getChildren().clear();
        this.LodeFormContent.getChildren().add(load);
    }

    public void btnOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("");

        assert resource != null;

        Parent load = (Parent) FXMLLoader.load(resource);
        this.LodeFormContent.getChildren().clear();
        this.LodeFormContent.getChildren().add(load);
    }
}
