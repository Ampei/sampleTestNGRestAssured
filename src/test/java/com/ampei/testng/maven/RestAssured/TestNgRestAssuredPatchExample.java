package com.ampei.testng.maven.RestAssured;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class TestNgRestAssuredPatchExample {

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

	@Test(description = "UPDATE")
	public void examplePatch() {
		RestAssured.basePath = "/api/users/2";
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "morpheus"); // Cast
		requestParams.put("job", "zion resident");

		Response response = makePatch(requestParams);

		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("job"), "zion resident");
		Assert.assertNotNull(response.jsonPath().get("updatedAt"));
	}

	public Response makePatch(JSONObject requestParams) {
		return Utils.setContentType(ContentType.JSON).body(requestParams.toString()).when().patch();
	}
	
}
