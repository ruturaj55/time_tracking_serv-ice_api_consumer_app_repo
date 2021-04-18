# employee_time_tracking_service_api_consumer_app_details (Service Client App)
Employee Time Tracking Legacy Service API Consumer App Project 
Application is designed and developed based on layered architecture.Here we have used TestTemplate to deal with external services API’s .By using HTTP methods like get ,post etc we have access to these services to get and post data from frontend by end user.

Presentation layer-->
The presentation layer is a view for the end user (frontend system) is designed and developed  in Standard HTML files along with bootstrap features for better look and feel. Retrieved data is presented on the html table component and input data is provided to the controller and service class with form action elements. HTML Pages contain tables and other components like buttons, input text fields etc. to display different data and to perform certain required actions.

Controller Layer-->
Spring controller class used to redirect user requests to particular methods based on action defined in frontend pages and EmployeeRecordsServiceProvider provides API service to each method to interact with external services and responses sent back to frontend pages. spring controller receives request along with required input data and passes to service class to hit external services endpoint urls with required data based on action and request mapping configuration, it will select the method to be executed.
Below controller class for controller layer implementation:
RecordController

Service Layer-->
Service classes annotated with @Service provide service to controller classes to interact with external services to execute http methods like get post etc with requested data as input and output. Here EmployeeRecordsServiceProvider class provides service methods as getAllEmployees,getRecordByEmail,createRecord. 
In the EmployeeRecordsServiceProvider class, we have used RestTemplate which provides necessary methods like getForObject,exchange,postForEntity etc to interact with services endpoint url.
We have used @Service to work as a service class for restful service like http get,http post ,http put with help of HttpHeaders ,HttpEntity, ResponseEntity etc.
Below Model class for Model layer implementation:
EmployeeRecordsServiceProvider

Model(Entity)-->
Model classes are a representation of entity elements which are plain pojo classes . With the help of model class we can map json request and response body to perform http operation. 
Here Record class represents the record element with the data like start,end,email data. 
Below Model class for Model layer implementation:
Record 

Technical configuration 
For developing this consumer app we have used the Spring Boot starter project using Spring Tool Suite 4. Created a new Spring starter project with initial dependencies Thymeleaf,Spring web,DevTools etc.
For deploying web applications we have used Spring Boot inbuilt tomcat server for testing purposes and JAVA 8 development environment used to develop java applications.

Project name and package structure
Project Name - employee_time_tracking_service_api_consumer_app

/employee_time_tracking_service_api_consumer_app/
src/main/java
com.timetrackingrecord
com.timetrackingrecord.model.controller
com.timetrackingrecord.service
com.timetrackingrecord.model

/employee_time_tracking_service_api_consumer_app/src/
main/resources
/templates

Thank you.
