package com.harshalmayee.controller;

import com.harshalmayee.cucumber.SpringBootCucumberTest;
import com.harshalmayee.model.EmployeeDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest extends SpringBootCucumberTest {

    private static final String BASE_URL="/api/v1";
    private ParameterizedTypeReference parameterizedTypeReference;
    private ResponseEntity responseEntity;

    @Test
    public void getAllEmployees(){
        parameterizedTypeReference=new ParameterizedTypeReference<List<EmployeeDTO>>() {};
        responseEntity=getAllEmployees(BASE_URL+"/employees",null,parameterizedTypeReference);
    }


}
