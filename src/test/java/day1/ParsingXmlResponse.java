package day1;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.*; 

public class ParsingXmlResponse {
	
	@Test
	void testXmlResponse()
	{
		Response res=given()
		
		.when()
			.get("http://localhost:3000/api/xml");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("content-type"),"application/xml; charset=utf-8");
		
		String id=res.xmlPath().get("users.user.id[0]").toString();
		Assert.assertEquals(id, "1");
		
		String name=res.xmlPath().get("users.user.name[0]").toString();
		Assert.assertEquals(name, "John aDoe");
		
	}

}
