# new feature
# Tags: optional

Feature: Employee CRUD operation

 Scenario Outline: Creating New Employee Record
    Given Employee First Name as "<FirstName>"
    And   Employee Last Name as "<LastName>"
    And   Employee EmailId as "<EmailId>"
    And   Employee Address as "<Address>"
    And   Employee Mobile as "<Mobile>"
    When  Records are Submitted for creating employee
    Then  Valid Response is received with httpcode 200
    When  Get All Employee Endpoint Is called
    Then  List should not be empty
   Examples:
   |FirstName       |LastName     |EmailId                          |Address       |Mobile          |
   |Harshal         |Mayee        |harshalmayee1288@gmail.com       |Jalgaon       |8879623767      |
   |Rahul           |Gonda        |rahul.rao.gonda@infrasofttech.com|Mancherial    |9619132262      |

