package org.clothifys.controller.order;

import org.clothifys.util.CrudUtil;
import org.clothifys.dto.tm.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    private static OrderDetailController instance;
    private OrderDetailController(){}

    public boolean addOrderDetail(List<OrderDetail> orderDetailList){
        boolean isAdd = false;
        for(OrderDetail orderDetail : orderDetailList){
            isAdd = addOrderDetail(orderDetail);
        }
        if(isAdd){
            return true;
        }
        return false;
    }

    public boolean addOrderDetail(OrderDetail orderDetail){

        try {
            Object isAdd = CrudUtil.execute("INSERT INTO orderDetail VALUES(?, ?, ?, ?)",
                    orderDetail.getOrderId(),
                    orderDetail.getItemCode(),
                    orderDetail.getQty(),
                    orderDetail.getDiscount()
            );
            return (Boolean)isAdd;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static OrderDetailController getInstance(){
        if(instance==null){
            return instance = new OrderDetailController();
        }
        return instance;
    }

}
