package com.harshalmayee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long empId;
    private String empFirstName;
    private String empLastName;
    private String empEmailId;
    private String empAddress;
    private String empMobile;
}
