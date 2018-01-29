package com.ampei.testng.maven.RestAssured;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class TestNgRestAssuredPutExample {

	@BeforeTest
	public void beforeTest() {
		// Sets Base URI
		RestAssured.baseURI = "https://reqres.in";
		Utils.setContentType(ContentType.JSON);
	}

	@AfterTest
	public void afterTest() {
		Utils.resetBaseURI();
		Utils.resetBasePath();
	}

	@Test(description = "UPDATE")
	public void examplePut() {
		RestAssured.basePath = "/api/users/2";
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "morpheus");
		requestParams.put("job", "zion resident");

		Response response = makePut(requestParams);

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("job"), "zion resident");
		Assert.assertNotNull(response.jsonPath().get("updatedAt"));
	}

	public Response makePut(JSONObject requestParams) {
		return Utils.setContentType(ContentType.JSON).body(requestParams.toString()).when().put();
	}

}
