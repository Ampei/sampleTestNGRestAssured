package com.ampei.testng.maven.RestAssured;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class Utils {

	// Reset Base URI (after test)
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	// Reset base path
	public static void resetBasePath() {
		RestAssured.basePath = null;
	}

	// Returns JsonPath object
	public static JsonPath getJsonPath(Response res) {
		String json = res.asString();
		// System.out.print("returned json: " + json +"\n");
		return new JsonPath(json);
	}

	// Returns response
	public static Response getResponse() {
		// System.out.print("path: " + path +"\n");
		return get();
	}

	// Sets ContentType
	public static RequestSpecification setContentType(ContentType Type) {
		return given().contentType(Type);
	}

}
