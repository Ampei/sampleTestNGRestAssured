package com.ampei.testng.maven.RestAssured;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class TestNgRestAssuredPostExample {

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

	@Test(description = "CREATE")
	public void exampleCreate() {
		RestAssured.basePath = "/api/users";
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "morpheus"); // Cast
		requestParams.put("job", "leader");

		Response response = makePost(requestParams);

		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.jsonPath().get("name"), "morpheus");
	}

	@Test(description = "REGISTER UNSUCCESSFUL")
	public void exampleRegisterFail() {
		RestAssured.basePath = "/api/register";
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "sydney1@fife");
		
		Response res = makePost(requestParams);

		Assert.assertEquals(res.getStatusCode(), 400);
		Assert.assertEquals(res.jsonPath().get("error"), "Missing password");
	}
	
	@Test(description = "LOGIN SUCCESSFUL")
	public void exampleLoginSuccessful() {
		RestAssured.basePath = "/api/register";
		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "peter@klaven");
		requestParams.put("password", "cityslicka");
		
		Response res = makePost(requestParams);
		
		Assert.assertEquals(res.getStatusCode(), 201);
		Assert.assertEquals(res.jsonPath().get("token"), "QpwL5tke4Pnpja7X");
	}

	public Response makePost(JSONObject requestParams) {
		return Utils.setContentType(ContentType.JSON).body(requestParams.toString()).when().post();
	}

}
