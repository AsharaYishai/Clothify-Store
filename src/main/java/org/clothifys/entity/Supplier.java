package org.clothifys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier {
    private Integer supplierId;
    private String title;
    private String supplierName;
    private String contact;
    private String company;
    private String email;
    private String address;

}
