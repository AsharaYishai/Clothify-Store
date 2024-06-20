package org.clothifys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private Date date;
    private String itemCode;
    private String description;
    private String qty;
    private double unitPrice;
    private double discount;
    private double total;
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerContact;
}
