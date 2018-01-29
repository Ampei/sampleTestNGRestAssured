package com.ampei.testng.maven.RestAssured;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class TestNgRestAssuredGetExample {

	private Response res = null; // Response
	private JsonPath jp = null; // JsonPath

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

	@Test(description = "SINGLE USER")
	public void exampleSingleUser() {
		// Sets base path
		RestAssured.basePath = "/api/users/2";
		getJson();
		Assert.assertEquals("Weaver", jp.getString("data.last_name"));
	}

	@Test(description = "LIST USERS")
	public void exampleListUsers() {
		RestAssured.basePath = "/api/users?page=2";
		getJson();
		List<String> last_names = Arrays.asList("Bluth", "Weaver", "Wong");
		Assert.assertEquals(last_names, jp.getList("data.last_name"));
	}

	@Test(description = "SINGLE USER NOT FOUND")
	public void exampleUserNotFound() {
		RestAssured.basePath = "/api/users/23";
		getJson();
		Assert.assertEquals(404, res.getStatusCode());
	}

	@Test(description = "LIST <RESOURCE>")
	public void exampleList() {
		RestAssured.basePath = "/api/unknown";
		getJson();
		List<String> last_names = Arrays.asList("cerulean", "fuchsia rose", "true red");
		Assert.assertEquals(last_names, jp.getList("data.name"));
	}

	public void getJson() {
		res = Utils.getResponse(); // Get response
		jp = Utils.getJsonPath(res); // Set JsonPath
	}

}
