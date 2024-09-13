
# Web Application Automation and API Automation Project

Welcome to the Selenium Automation Framework repository! This guide will walk you through the process of setting up a new Selenium framework project from scratch using Maven, including dependency management, project configuration.


## Table of content
- Prerequisites 
- Setting Up the Environment 
- Creating the Maven Project 
- Adding Maven Dependencies 
- Project Structure 
- Writing Test Case
  - Login Test Cases
  - API Test Case
- Creating TestNG XML File
- Running Tests
- Checking Test Reports
## Prerequisites
Before you start, ensure you have the following installed:

- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher
- Eclipse IDE for Java Developers
## Setting Up the Environment

**1. Install Java 8**
Download and install the Java JDK 8 from the official Oracle website or use an OpenJDK version.

**2. Install Eclipse IDE**
Download and install Eclipse IDE from the official Eclipse website. Choose the "Eclipse IDE for Java Developers" package.
    
## Creating the Maven Project
  1. Open Eclipse IDE.

  2. Create a New Maven Project:

- Go to File > New > Other....
- Select Maven > Maven Project and click Next.
- Check Create a simple project (skip archetype selection) and click Next.
- Enter Group ID (e.g., com.Automation), Artifact ID (e.g., selenium-automation), and Version (e.g., 1.0-SNAPSHOT).
- Click Finish.
## Adding Maven Dependencies
1. Open pom.xml located in the root directory of your Maven project.

2. Add the following dependencies inside the <dependencies> tag:
   
  <>
    
    <dependencies>
    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.7.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Selenium WebDriver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.20.0</version>
    </dependency>
    <!-- Apache Commons IO -->
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.11.0</version>
    </dependency>

    <!-- Rest Assured for API Testing -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.3.1</version>
        <scope>test</scope>
    </dependency>

    <!-- Hamcrest for assertions in Rest Assured -->
    <dependency>
        <groupId>org.hamcrest</groupId>
        <artifactId>hamcrest-library</artifactId>
        <version>2.2</version>
        <scope>test</scope>
    </dependency>
    </dependencies>
3. Save the pom.xml file and update the Maven project by right-clicking on the project in Eclipse and selecting Maven > Update Project.
## Project Structure
The Maven project structure will look like this:

    selenium-automation/
    ├── src/
    │   ├── main/
    │   │   └── java/
    │   │       └── com/
    │   │           └── example/
    │   │               └── utils/
    │   ├── test/
    │   │   └── java/
    │   │       └── com/
    │   │           └── example/
    │   │               └── test/
    │   │              
    │   └── resources/
    ├── pom.xml
    └── .project

- com.automation.Tests: Contains Login-related test classes and API-related test classes.
- com.example.utils: Utility classes (e.g., WebDriver setup).

## Writing Test Cases
Login Test Case 
1. Create a Java class in the com.Automations.Tests package named LoginTest.java.

2. Add the following code for the login functionality:

package com.Automations.Tests;


    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;

    import com.Automations.PageObjects.HomePage;
    import com.Automations.PageObjects.LandingPage;
    import com.Automations.TestComponents.BaseClass;


    public class LoginTest extends BaseClass{
	String message = "Epic sadface: Username and password do not match any user in this service";
	@Test(dataProvider ="getValidData", priority=1)
	public void validloginTest(String email, String password) throws InterruptedException {
		launchApplication();
		LandingPage landingPage = new LandingPage(driver);
		HomePage page = landingPage.loginApplication(email, password);
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		String actualUrl = page.checkHomePage(driver);
		//Here we are verifying 'signing page url' and 'homepage url' after logged in as ther is no login message on saucelab website after log in
		Assert.assertEquals(actualUrl, expectedUrl);	
	}
	
	@Test(dataProvider ="getInvalidData", priority =2)
	public void invalidLoginTest(String email, String password) throws InterruptedException {
		launchApplication();
		LandingPage landingPage = new LandingPage(driver);
		HomePage page = landingPage.loginApplication(email, password);
		if(message.equals(landingPage.getErrorMsg())) {
			System.out.println("User is not logged in due to wrong email or password");
		}

	}
	@DataProvider
	public Object[][] getValidData(){
		return new Object[][] {{"standard_user","secret_sauce"},{"visual_user","secret_sauce"}};
		
	}
	
	@DataProvider
	public Object[][] getInvalidData(){
		return new Object[][] {{"invalid_user","secret_sauce"}};
		
	}
    }

## API Test Case
1. Create a Java class in the com.Automations.Tests package named Apiautomation.java.

2. Add the following code for API testing:
    package com.Automations.Tests;
    import static io.restassured.RestAssured.given;
    import java.io.File;
    import java.io.IOException;

    import org.apache.commons.io.FileUtils;
    import org.testng.annotations.Test;

    import io.restassured.RestAssured;
    import io.restassured.module.jsv.JsonSchemaValidator;

        public class APIautomation {

	    @Test
	    public void getMethod() throws IOException {
		String filepath = "C:\\Users\\tejum\\eclipse-workspace1\\WysaAutomations\\src\\test\\expectedJsonSchema.txt";
		//read json schema from text file
		String jsonSchema = FileUtils.readFileToString(new File(filepath), "UTF-8");
		RestAssured.baseURI= "https://jsonplaceholder.typicode.com";
		//check for 200 Ok status code and json schema structure
		String res = given().log().all()
				.when().get("posts")
				.then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(jsonSchema)).assertThat().toString();
			} }

## Creating TestNG XML File
1. Create a TestNG XML file named testng.xml in the root directory of your project.

2. Add the following configuration:
    
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
        <suite name="Suite">
        <test thread-count="5" name="Login Test">
            <classes>
                <class name="WysaAutomations.Tests.LoginTest"/>
            </classes>
        </test>
  
        <test thread-count="5" name="APIautomation Test">
            <classes>
                <class name="WysaAutomations.Tests.APIautomation"/>
            </classes>
        </test>  <!-- Test -->
        </suite> <!-- Suite -->

## Running Tests
1. Right-click on the testng.xml file in Eclipse.

2. Select Run As > TestNG Suite.

   This will execute all the tests defined in the testng.xml file
## Checking Test Reports
1. Navigate to the test-output directory in your project.

2. Open the index.html file in a web browser.

   This will display a detailed report of your test execution, including passed, failed, and skipped tests.
## Run Locally
Go to the desired directory

```bash
  cd my-project
```

Clone the project

```bash
  git clone https://github.com/tejashwaree/WysaAutomation
```

Steps to Add a Cloned Project into Eclipse
1. Open Eclipse IDE
Launch your Eclipse IDE. If you don't have Eclipse installed, you can download it from the Eclipse website.

2. Import the Maven Project
    1. Go to the Import Wizard:
    - Click on File in the top menu.
    - Select Import
    2. Select Maven Project:
    - In the import wizard, expand the Maven folder.
    - Choose Existing Maven Projects and click Next.

3. Select the Root Directory:

- Click on Browse... next to the Root Directory field.
- Navigate to the directory where you cloned the project using Git Bash.
- Select the root directory of the project (the directory containing the pom.xml file).
- Click OK.
4. Finish the Import:
- Eclipse will scan the directory and list the projects found.
- Ensure the correct project is checked.
- Click Finish.
Eclipse will import the project, read the pom.xml file, and set up the project according to the Maven configuration.

5. Update Maven Project:
- Right-click on the project in the Project Explorer view.
- Select Maven > Update Project.
- In the dialog that appears, ensure that your project is checked.
- Click OK.
This action updates the Maven dependencies and configurations in Eclipse.

6. Run Tests
    1. Locate Test Classes:
        - Navigate to the src/test/java directory within the Project Explorer view.
    2. Run Tests:
        - Right-click on the test class or test suite you want to run.
        - Select Run As > TestNG Test (or JUnit Test if using JUnit).
