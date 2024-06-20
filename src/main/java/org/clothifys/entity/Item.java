package org.clothifys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    private String itemCode;
    private String description;
    private int quantity;
    private double sellingPrice;
    private double buyingPrice;
    private String size;
    private String supplierId;

}
