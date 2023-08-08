# SeleniumTestNGFramework
A repository to automate the test scripts using Selenium Test NG Framework integrated with Docker Selenium Grid for executions.This repository contains a sample Test Automation Framework that uses Selenium for web automation, Maven for build and dependency management, TestNG for test execution, and Docker for containerized execution.

## Features

- **Modular Structure**: The framework follows a modular structure for easy organization and maintenance.
- **Data Parameterization**: Test data is stored in JSON files for data parameterization.
- **Page Object Model (POM)**: Implementing the POM design pattern for better test code maintainability.
- **Selenium Grid Using Docker**: Integration with docker for ondemand test executions on Selenium Grid.
  
## Prerequisites
Before you begin, ensure Docker is installed.

## Setup and Execution
- Clone this repository to your local machine:  git clone using https or ssh.
  -   ssh - git@github.com:swathiraj85/SeleniumTestNGFramework.git
  -   https - https://github.com/swathiraj85/SeleniumTestNGFramework.git
- Navigate to the cloned directory cd <repository_folder>
-  Run the Docker Compose file to start the test environment:
    -   The repository has two compose files because the selenium hub image for M1 Mac is different to other machines due to issues with setting up grid.The default compose file           is for M1 Mac.Run the command
           "docker compose up"
    -  If you want to run the docker compose for other machines,use the following command  
         Run the command  - "docker compose -f docker-compose-windows.yaml up"
-  The Docker logs  display the test status.
-  You can watch the live execution as mentioned on "How to view live execution on Selenium grid 4" section.
-  If the test script execution is complete, "exited with code 1" is shown in console.Press Control C to kill the container because the selenium grid is still running on the 
    container
-  After the tests complete, you can find the test reports in the //reports directory of the project.

## How to view live execution on Selenium grid 4
- **Live Video**: The Selenium grid is hosted on port http://<hostname>:4444/.Port mapping is done between the container port and local port.So,the executions can be monitored  
- **Steps**:
    - Navigate to http://localhost:4444/
    - Click on the session section to view the current execution
    - The password to view the session execution on grid is "secret"
      

## Project Structure
The project structure is organized as follows:
SeleniumTestNGDockerFramework/
- src/main/java/
  - pages
  - browserManager
  - browserOptions
  - utilities
- src/test/java/
  - BaseTest.java
- src/test/resources/ 
  - config/config.properties
  - testData/testdata.json
- reports/
- Dockerfile
- docker-compose.yaml
- docker-compose-windows.yaml
- healthcheck.sh
- README.md
- pom.xml

## Configuration
- Modify config.properties in the src/test/resources/config directory to configure your environment settings.
- **TestNG and Maven Configuration**
    - TestNG test classes are located in the src/test/java directory.
    - TestNG suite XML file (testng.xml) is used to define the test suite configuration.
    - Maven is used for build and dependency management. The pom.xml file defines project dependencies and build configuration.
- **Test data**
    - Test data is supplied to the test scripts using Json file format.Its located in src/test/resources/testData/testdata.json.The test data works for Adults & Teens.Child & 
      Infant scenarios are currently out of scope due to additional business logic
- **Reports**
    -  TestNG HTML reports are generated after test execution in the ./reports directory.
- **Docker**
    - The Dockerfile contains instructions to build the Docker image for the test framework.
        The docker-compose.yaml file defines services for Selenium Hub, Chrome Node, and your test environment.

## Prerequisites for Local Setup and Execution
- Install Java JDK 1.11 and above
 - Apache Maven
 - Clone and Import the Maven project on your directory.
 - Build dependencies on your preferred IDE.
 -  Execute as Maven project using mvn clean test

