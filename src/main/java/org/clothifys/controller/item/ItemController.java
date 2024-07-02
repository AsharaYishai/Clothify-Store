package org.clothifys.controller.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.clothifys.controller.customer.CustomerController;
import org.clothifys.crudUtil.CrudUtil;
import org.clothifys.db.DBConnection;
import org.clothifys.dto.tm.OrderDetail;
import org.clothifys.entity.Customer;
import org.clothifys.entity.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemController {

    private static ItemController instance;

    private ItemController(){}

    public static ItemController getInstance(){
        if (instance==null){
            return instance=new ItemController();
        }
        return instance;
    }

    public ObservableList<Item> getAllItems() {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item");

            ObservableList<Item> listTable = FXCollections.observableArrayList();
            while (resultSet.next()){
                listTable.add(
                        new Item(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getDouble(5),
                                resultSet.getInt(6)
                        )
                );
            }
            return listTable;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Item searchItem(String itemCode){
        try {
            PreparedStatement psTm = CrudUtil.execute("SELECT * FROM Item WHERE ItemCode=?");

            psTm.setString(1,itemCode);
            boolean execute = psTm.execute();
            if(execute){
                ResultSet resultSet = psTm.getResultSet();
                ;
                while (resultSet.next()){
                    return new Item(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getDouble(5),
                            resultSet.getInt(6)
                    );

                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean updateStock(List<OrderDetail> orderDetailList) {
        boolean isUpdate = false;
        for (OrderDetail orderDetail : orderDetailList){
          isUpdate = updateStock(orderDetail);
       }
        if(isUpdate) {
            return true;
        }
        return false;
    }

    public boolean updateStock(OrderDetail orderDetail) {

        try {
            Object isUpdate = CrudUtil.execute("UPDATE item SET QtyOnHand=QtyOnHand-? WHERE ItemCode = ?", orderDetail.getQty(), orderDetail.getItemCode());
            return (Boolean) isUpdate;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
