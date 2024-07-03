package org.clothifys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
    private String customerId;
    private String title;
    private String name;
    private LocalDate dob;
    private String nic;
    private String address;
    private String email;
    private String contact;
    private String bankName;
    private String bankAccountNo;



}
