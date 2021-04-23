package com.harshalmayee.controller;

import com.harshalmayee.exception.ResourceNotFoundException;
import com.harshalmayee.model.EmployeeDTO;
import com.harshalmayee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employees", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
         Long employeeId=employeeService.addNewEmployee(employeeDTO);
         return ResponseEntity.ok("Employee Created with ID "+employeeId);
    }

    @GetMapping(value = "/employees",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployees());
    }

    @GetMapping(value = "/employees/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        EmployeeDTO employeeDTO=employeeService.getEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @PutMapping(value ="/employees/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(value = "id") Long employeeId
            ,@RequestBody EmployeeDTO employeeDTO) throws ResourceNotFoundException {
       return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.updateEmployee(employeeId,employeeDTO));
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body("Employee Deleted Successfully for "+employeeId);
    }
}
