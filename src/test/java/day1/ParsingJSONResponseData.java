package day1;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;



public class ParsingJSONResponseData {

	@Test(priority=1)
	void testJsonResponse()
	{
		//Approach 1
		
	/*	given()
			.contentType("application/json")

		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.header("content-type", "application/json")
			//.log().all()
			.body("[3].name", equalTo("External Json file")); */
		
		
		
		//Approach 2 
		//By using this approach we can do more validations
		
		
		Response res = given()
							.contentType("application/json")
				
						.when()
							.get("http://localhost:3000/students");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		String name=res.jsonPath().get("[3].name").toString();
		Assert.assertEquals(name, "External Json file");
		
		
		
		
	}
}
