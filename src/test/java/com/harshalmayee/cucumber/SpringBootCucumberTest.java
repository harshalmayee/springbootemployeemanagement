package com.harshalmayee.cucumber;

import com.harshalmayee.SpringBootEmployeeManagementApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@ActiveProfiles("dev")
@org.springframework.boot.test.context.SpringBootTest(
        classes = SpringBootEmployeeManagementApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class SpringBootCucumberTest {



    @Autowired
    private TestRestTemplate testRestTemplate;



    public ResponseEntity addNewEmployee(String url,Object inputData,ParameterizedTypeReference parameterizedTypeReference){
        return testRestTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(inputData,getDefaultHeaders()),
                parameterizedTypeReference);
    }

    public ResponseEntity getAllEmployees(String url,Object inputData,ParameterizedTypeReference parameterizedTypeReference){
        return testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity(inputData,getDefaultHeaders()),
                parameterizedTypeReference);
    }

    private HttpHeaders getDefaultHeaders(){
        final HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
