package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class HTTPRequests {
	
	String id;
	@Test(priority=1)
	void getUsers()
	{
		given()
		
		.when()
			.get("http://localhost:3000/students")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=2)
	void createUser() 
	{
		HashMap data = new HashMap();
		data.put("name", "Hari");
		data.put("phone", "9876543456712");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
		.post("http://localhost:3000/students")
		.jsonPath().getString("id");
		
//		.then()
//		.statusCode(201)
//		.log().all();
		
	}
	
	@Test(priority=3)
	void getUsersM()
	{
		given()
		
		.when()
			.get("http://localhost:3000/students")
		
		.then()
			.statusCode(200)
			.log().all();
	}
	@Test(priority=4,dependsOnMethods = {"createUser"})
	void updateUser()
	{
		HashMap data = new HashMap();
		data.put("name", "HariNew");
		data.put("phone", "98765434577");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
		.post("http://localhost:3000/students/"+id)
		
		.then()
			.statusCode(404)
			.log().all();
	}
	@Test(priority=5)
	void getUsersF()
	{
		given()
		
		.when()
			.get("http://localhost:3000/students")
		
		.then()
			.statusCode(200)
			.log().all();
	}

}
