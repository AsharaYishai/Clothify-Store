package org.clothifys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetails {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
    private double totalProfit;
    private double discount;
}
