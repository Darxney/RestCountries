# RestCountries
Application was Developed on Java 11 using IntelliJ idea IDE and Mysql database. Usage:

IF you are using Mysql Open mysql workbench and execute this query: CREATE SCHEMA IF NOT EXISTS \`countriesdb`; the rest of the database setup will be completed by the application through liquibase

Once you have the project open in your IDE you can launch The application using RestCountriesApplication.java to ensure that everything is working correctly run the tests in the test directory. The RestTemplate tests require the application to be running in order for them to pass.

if all tests have passed you can continue to manually test/play around with the available options for the RESTful api by going to http://localhost:8080/swagger.html in your browser.

