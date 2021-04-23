package com.harshalmayee.service;

import com.harshalmayee.entity.Employee;
import com.harshalmayee.exception.ResourceNotFoundException;
import com.harshalmayee.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    public List<EmployeeDTO> getAllEmployees() throws ResourceNotFoundException;
    public Long addNewEmployee(EmployeeDTO employeeDTO);
    public EmployeeDTO updateEmployee(Long id,EmployeeDTO employeeDTO) throws ResourceNotFoundException;
    public EmployeeDTO getEmployeeById(Long id) throws ResourceNotFoundException;
    public void deleteEmployee(Long id) throws ResourceNotFoundException;

}
