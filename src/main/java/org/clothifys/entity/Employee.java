package org.clothifys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String empId;
    private String title;
    private String name;
    private String nic;
    private LocalDate dob;
    private String email;
    private String contact;
    private String address;
}
