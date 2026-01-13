package day1;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetUserTest {

	@Test
	public void verifyGetUserAPI() {
		Response response = RestAssured
				.given()
				
				
				.when()
				.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
				.then()
				.extract()
				.response();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		
		System.out.println("API Test Passed");
		
	}
	
}
