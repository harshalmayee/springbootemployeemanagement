package com.harshalmayee.cucumber;

import com.harshalmayee.constants.Constants;
import com.harshalmayee.model.EmployeeDTO;
import com.harshalmayee.repository.EmployeeRepository;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class EmployeeStepdefs extends SpringBootCucumberTest {

    private ResponseEntity response;
    private ParameterizedTypeReference parameterizedTypeReference;
    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeStepdefs.class);
    private static EmployeeDTO employeeDTO=null;
    private List<String> responseBody;
    private List<Long> empIdList=new ArrayList<>();


    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void setup(Scenario scenario){
        LOGGER.info("----Scenario "+scenario.getName() +" Started");
        employeeDTO=new EmployeeDTO();
    }

    @After
    public void DeleteAllRecords(Scenario scenario){
        LOGGER.info("----Scenario "+scenario.getName() +" Ended. Deleting All Records");
        employeeRepository.deleteAll();
    }

    @When("Get All Employee Endpoint Is called")
    public void getAllEmployeeEndpointIsCalled() throws Throwable {
        LOGGER.info("Retrieving All Employees...");
        parameterizedTypeReference=new ParameterizedTypeReference<List<EmployeeDTO>>() {};
        response=getAllEmployees(Constants.BASE_URL_EMPLOYEE,null,parameterizedTypeReference);
        assertThat("In Step Def:"+this.getClass().toString(),response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Then("List should not be empty")
    public void listShouldNotBeEmpty() throws Throwable {

        Assert.assertNotNull("Checking Response is not null :",response);
        responseBody= (List<String>) response.getBody();
        Assert.assertNotEquals("In Step Def",0,responseBody.size());
    }

    @Given("Employee First Name as {string}")
    public void employeeFirstNameAs(String arg0) throws Throwable {
        employeeDTO.setEmpFirstName(arg0);
    }

    @And("Employee Last Name as {string}")
    public void employeeLastNameAs(String arg0) throws Throwable {
        employeeDTO.setEmpLastName(arg0);
    }

    @And("Employee EmailId as {string}")
    public void employeeEmailIdAs(String arg0) throws Throwable {
        employeeDTO.setEmpEmailId(arg0);
    }

    @And("Employee Address as {string}")
    public void employeeAddressAs(String arg0) throws Throwable {
        employeeDTO.setEmpAddress(arg0);
    }

    @And("Employee Mobile as {string}")
    public void employeeMobileAs(String arg0) throws Throwable {
        employeeDTO.setEmpMobile(arg0);
    }

    @When("Records are Submitted for creating employee")
    public void recordsAreSubmittedForCreatingEmployee() throws Throwable {
        LOGGER.info("Submitting Employee Details...");
        parameterizedTypeReference=new ParameterizedTypeReference<String>() {};
        response=addNewEmployee(Constants.BASE_URL_EMPLOYEE, employeeDTO, parameterizedTypeReference);
        assertThat("In Step Def:"+this.getClass().toString(),response.getStatusCode(),is(equalTo(HttpStatus.OK)));
    }

    @Then("Valid Response is received with httpcode {int}")
    public void validResponseIsReceivedWithHttpcode(int httpstatuscode) throws Throwable {
        LOGGER.info("Valid Response with Status Code 200");
        LOGGER.info("Response Body :"+response.getBody());
        assertThat("In Step Def:"+this.getClass().toString(),response.getStatusCode().value(),is(equalTo(httpstatuscode)));
    }
}
