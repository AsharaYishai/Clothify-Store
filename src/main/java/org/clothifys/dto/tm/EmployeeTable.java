package org.clothifys.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeTable {
    private String customerId;
    private String title;
    private String name;
    private String nic;
    private LocalDate dob;
    private String email;
    private String contact;
    private String address;
}
