package org.clothifys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String customerId;
    private String name;
    private String title;
    private String address;
    private String nic;
    private Date dob;
    private String email;
    private String bankName;
    private String bankAccountNo;
    private String contact;


}
