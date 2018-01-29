package com.ampei.testng.maven.RestAssured;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

public class TestNgRestAssuredDeleteExample {

	private Response res = null; // Response

	@BeforeTest
	public void beforeTest() {
		// Sets Base URI
		RestAssured.baseURI = "https://reqres.in";
	}

	@AfterTest
	public void afterTest() {
		Utils.resetBaseURI();
		Utils.resetBasePath();
	}

	@Test(description = "DELETE")
	public void exampleDelete() {
		RestAssured.basePath = "/api/users/2";

		res = given().delete();

		Assert.assertEquals(res.getStatusCode(), 204);
	}

}
