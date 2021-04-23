package com.harshalmayee.service.impl;

import com.harshalmayee.entity.Employee;
import com.harshalmayee.exception.ResourceNotFoundException;
import com.harshalmayee.model.EmployeeDTO;
import com.harshalmayee.repository.EmployeeRepository;
import com.harshalmayee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDTO> getAllEmployees() throws ResourceNotFoundException {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeRepository.findAll().forEach(employee -> {
            employeeDTOList.add(new EmployeeDTO(employee.getId()
                    ,employee.getEmpFirstName()
                    ,employee.getEmpLastName()
                    ,employee.getEmpEmailId()
                    ,employee.getEmpAddress()
                    ,employee.getEmpMobile()));
        });
        if(employeeDTOList.isEmpty()){
            throw new ResourceNotFoundException("Employee Records Are Empty");
        }
        return employeeDTOList;
    }

    @Override
    public Long addNewEmployee(EmployeeDTO employeeDTO) {
        final Employee employee=convertDTOtoEntity(employeeDTO);
        return employeeRepository.save(employee).getId();
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) throws ResourceNotFoundException {
        Optional<Employee> optional=employeeRepository.findById(id);
        if(optional.isPresent()){
          Employee employee=new Employee();
          employee.setId(optional.get().getId());
          employeeDTO.setEmpId(employee.getId());
          employee=convertDTOtoEntity(employeeDTO);
          employeeRepository.save(employee);
        }else{
            throw new ResourceNotFoundException("Employee Not Found For Id :"+id);
        }
        return employeeDTO;
    }


    private Employee convertDTOtoEntity(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .id(employeeDTO.getEmpId())
                .empFirstName(employeeDTO.getEmpFirstName())
                .empLastName(employeeDTO.getEmpLastName())
                .empAddress(employeeDTO.getEmpAddress())
                .empEmailId(employeeDTO.getEmpEmailId())
                .empMobile(employeeDTO.getEmpMobile())
                .build();
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) throws ResourceNotFoundException {
        EmployeeDTO employeeDTO=null;
        Employee employee=new Employee();
        Optional<Employee> optional=employeeRepository.findById(id);
        if(optional.isPresent()){
            employee=optional.get();
            employeeDTO=new EmployeeDTO(employee.getId()
                    ,employee.getEmpFirstName()
                    ,employee.getEmpLastName()
                    ,employee.getEmpEmailId()
                    ,employee.getEmpAddress()
                    ,employee.getEmpMobile());
        }else{
            throw new ResourceNotFoundException("Employee Not Found For Id :"+id);
        }
        return employeeDTO;
    }

    @Override
    public void deleteEmployee(Long id) throws ResourceNotFoundException {
       if(!employeeRepository.findById(id).isPresent()) {
           throw new ResourceNotFoundException("Employee Not Found For Id :"+id);
       }
       employeeRepository.deleteById(id);
    }
}
