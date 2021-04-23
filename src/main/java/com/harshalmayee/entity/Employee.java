package com.harshalmayee.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EMPLOYEE_TABLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMP_ID")
    private Long id;

    @Column(name = "EMP_FIRSTNAME")
    @NotNull
    private String empFirstName;

    @Column(name="EMP_LASTNAME")
    @NotNull
    private String empLastName;

    @Column(name="EMP_EMAILID")
    @NotNull
    private String empEmailId;

    @Column(name="EMP_ADDRESS")
    private String empAddress;

    @Column(name="EMP_MOBILE")
    private String empMobile;


}
