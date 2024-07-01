package org.clothifys.controller.order;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.clothifys.controller.customer.CustomerController;
import org.clothifys.controller.item.ItemController;
import org.clothifys.db.DBConnection;
import org.clothifys.dto.tm.CartTbl;
import org.clothifys.dto.tm.Order;
import org.clothifys.dto.tm.OrderDetail;
import org.clothifys.entity.Customer;
import org.clothifys.entity.Item;
import org.clothifys.entity.OrderDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceOrderFormController implements Initializable {

    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContact;
    public JFXTextField txtEmail;
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public Label lblTotal;
    public JFXTextField txtOrderId;
    public JFXTextField txtDiscount;
    public JFXTextField txtSize;
    public TableColumn colItemCode;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public AnchorPane LodeFormContent;
    public Label lblTime;
    public Label lblDate;
    public ComboBox cmdCustomerIds;
    public ComboBox cmbItemCode;
    public Label lblCustomerName;
    public Label lblContact;
    public Label lblCustomerEmail;
    public Label lblNic;
    public ComboBox cmbtitle;
    public Label tblDescription;
    public Label lblZise;
    public Label lblQty;
    public Label lblSellingPrice;
    public Label lblDescription;
    public Label lblOrderId1;
    public TextField txtQty2;
    public Label lblOrderId;
    public TableView tblCart;
    public TextField txtQtyForCustomer;
    public Label lblUnitPrice;
    public TableColumn colDescription;
    public TableColumn colTotal;
    public Label lblNetTotal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadCustomerIDs();
        loadItemCodes();
        generateOrderId();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        cmdCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable,aldValue,newValue) -> {
            System.out.println(newValue);
            setCustomerDataForLbl((String)newValue);
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable,aldValue,newValue) ->{
            System.out.println(newValue);
            setItemDataForLbl((String)newValue);
        });
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {

        try {
            String orderId = lblOrderId.getText();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date orderDate = format.parse(lblDate.getText());
            String customerIds = cmdCustomerIds.getValue().toString();

            List<OrderDetail> orderDetailList = new ArrayList<>();

            for (CartTbl cartTbl : cartList) {
                String oID = lblOrderId.getText();
                String itemCode = cartTbl.getItemCode();
                Integer qty = cartTbl.getQty();
                Double discount = cartTbl.getDiscount();
                orderDetailList.add(new OrderDetail(oID, itemCode, qty, discount));
            }

            Order order = new Order(orderId, orderDate, customerIds, orderDetailList);
            System.out.println(order);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<CartTbl> cartList = FXCollections.observableArrayList();

    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        String itemCode = (String)cmbItemCode.getValue();
        String desc = lblDescription.getText();
        Integer qty = Integer.parseInt(txtQtyForCustomer.getText());
        Double unitPrice = Double.valueOf(lblUnitPrice.getText());
        Double total = qty*unitPrice;

        CartTbl cartTbl = new CartTbl(itemCode, desc, qty, unitPrice, total,0.0);

        int qtyStock = Integer.parseInt(lblQty.getText());
        if(qtyStock<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid qty").show();
            return;
        }

        cartList.add(cartTbl);
        tblCart.setItems(cartList);

        calcNetTotal();

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



    private void setItemDataForLbl(String itemCode) {
        Item item = ItemController.getInstance().searchItem(itemCode);

        lblDescription.setText(item.getDescription());
        lblZise.setText(item.getSize());
        lblQty.setText(String.valueOf(item.getQuantity()));
        lblSellingPrice.setText(String.valueOf(item.getSellingPrice()));
    }

    private void setCustomerDataForLbl(String customerId) {
        Customer customer = CustomerController.getInstance().searchCustomer(customerId);

        lblCustomerName.setText(customer.getName());
        lblContact.setText(customer.getContact());
        lblCustomerEmail.setText(customer.getEmail());
        lblNic.setText(customer.getNic());
    }

    private void loadItemCodes() {
        ObservableList<Item> allItems = ItemController.getInstance().getAllItems();

        ObservableList<String> itemCode = FXCollections.observableArrayList();
        allItems.forEach(item -> {
            itemCode.add(item.getItemCode());
        });
        cmbItemCode.setItems(itemCode);

    }

    private void loadCustomerIDs() {
        ObservableList<Customer> allCustomers = CustomerController.getInstance().getAllCustomers();

        ObservableList<Object> ids = FXCollections.observableArrayList();

        allCustomers.forEach(customer -> {
            ids.add(customer.getCustomerId());
        });
        cmdCustomerIds.setItems(ids);

    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,e->{
            LocalTime time = LocalTime.now();
            lblTime.setText(time.getHour()+" : "+ time.getMinute()+" : "+ time.getSecond());

        }),
            new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public  void generateOrderId() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM orders");
            Integer count = 0;
            while (resultSet.next()){
                count = resultSet.getInt(1);

            }
            if (count == 0) {
                lblOrderId1.setText("OR#00001");
            }
            String lastOrderId="";
            ResultSet resultSet1 = connection.createStatement().executeQuery("SELECT OrderID\n" +
                    "FROM orders\n" +
                    "ORDER BY OrderID DESC\n" +
                    "LIMIT 1;");
            if (resultSet1.next()){
                lastOrderId = resultSet1.getString(1);
                Pattern pattern = Pattern.compile("[A-Za-z](\\d+)");
                Matcher matcher = pattern.matcher(lastOrderId);
                if (matcher.find()) {
                    int number = Integer.parseInt(matcher.group(1));
                    number++;
                    lblOrderId1.setText(String.format("O#%04d", number));
                } else {
                    new Alert(Alert.AlertType.WARNING,"hello").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void calcNetTotal(){
        double ttl=0;
        for (CartTbl cartObj : cartList){
            ttl+=cartObj.getTotal();
        }
        lblNetTotal.setText(String.valueOf(ttl)+"/=");
    }

    public void txtAddToCartOnAction(ActionEvent actionEvent) {
        btnAddToCartOnAction(actionEvent);
    }
}
