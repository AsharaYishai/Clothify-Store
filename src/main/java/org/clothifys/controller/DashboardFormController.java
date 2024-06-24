package org.clothifys.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {
    public AnchorPane LodeFormContent;
    public Label lblDate;
    public Label lblTime;

    public void btnOrderOnAction(ActionEvent actionEvent){
        try {
            URL resource = this.getClass().getResource("/view/plac_order_form.fxml");
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

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        try {
            URL resource = this.getClass().getResource("/view/customer_registration_form.fxml");
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

    public void btnItemOnAction(ActionEvent actionEvent) {
        try {
            URL resource = this.getClass().getResource("/view/add_item_form.fxml");
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

    public void btnOrderDetailsOnAction(ActionEvent actionEvent) {
        try {
            URL resource = this.getClass().getResource("/view/order_details_form.fxml");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime time = LocalTime.now();
            lblTime.setText(time.getHour()+" : "+ time.getMinute()+" : "+ time.getSecond());

        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
