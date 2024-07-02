package org.clothifys.controller.order;

import org.clothifys.controller.item.ItemController;
import org.clothifys.db.DBConnection;
import org.clothifys.dto.tm.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    private static OrderController instance;
    private OrderController(){}

    public Boolean placeOrder(Order order) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement psTm = connection.prepareStatement("INSERT INTO orders VALUE(?, ?, ?)");
            psTm.setString(1, order.getOrderId());
            psTm.setObject(2,order.getOrderDate());
            psTm.setString(3, order.getCustomerId());

            boolean isOrderAdd = psTm.execute();

            if(!isOrderAdd){
                boolean isOrderDetailAdd = OrderDetailController.getInstance().addOrderDetail(order.getOrderDetailList());
                if(isOrderDetailAdd){
                    boolean isUpdateStock = ItemController.getInstance().updateStock(order.getOrderDetailList());
                    if(isUpdateStock){
                        connection.setAutoCommit(true);
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
            connection.setAutoCommit(true);
        }
    }

    public static OrderController getInstance(){
        if(instance==null){
            return instance = new OrderController();
        }
        return instance;
    }

}
