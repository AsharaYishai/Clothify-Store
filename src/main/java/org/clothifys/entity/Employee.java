package org.clothifys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private Integer empId;
    private String title;
    private String name;
    private String nic;
    private String dob;
    private String email;
    private String contact;
    private String address;
}
