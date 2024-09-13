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
			
	}
}
