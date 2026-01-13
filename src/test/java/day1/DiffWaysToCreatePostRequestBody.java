package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


			
public class DiffWaysToCreatePostRequestBody {

	static String id;
	
	//-------------------------------------------- Post using HashMap --------------------------------------------------------
	//@Test(priority=1)
	public void testPostUsingHashmap()
	{
		HashMap data = new HashMap();
		data.put("name", "Arun");
		data.put("phone", "1122334455");
		
		Response response =given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.log().all()
			.extract()
			.response();
		
		id = response.jsonPath().getString("id");
		
	}
	
	//@Test(priority=2,dependsOnMethods="testPostUsingHashmap")
	void deleteUserUsingIdHashMap()
	{
		//Thread.sleep(2000);
		given()
		
		.when()
			.delete("http://localhost:3000/students/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//-------------------------------------------- Post using org.Json --------------------------------------------------------
	
	//@Test(priority=1)
	public void testPostUsingJsonLibrary()
	{
		JSONObject data = new JSONObject();
		
		data.put("name", "Json Object");
		data.put("phone", "2233445566");
		
		Response response =given()
				.contentType("application/json")
				.body(data.toString()) //for org.json we need to conver the data to string orelse it will pass null value
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("Json Object"))
				.body("phone", equalTo("2233445566"))
				.log().all()
				.extract()
				.response();
			
			id = response.jsonPath().getString("id");
	}
	//@Test(priority=2,dependsOnMethods="testPostUsingJsonLibrary")
	void deleteUserUsingIdJSON()
	{
		//Thread.sleep(2000);
		given()
		
		.when()
			.delete("http://localhost:3000/students/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//-------------------------------------------- Post using POJO class (plain old java object) --------------------------------------------------------
	
	@Test(priority=1)
		public void testPostUsingPojo()
		{
			Pojo_PostRequest data = new Pojo_PostRequest();
			data.setName("Pojo2");
			data.setPhone("3344556677");
			
			Response response =given()
					.contentType("application/json")
					.body(data) //for org.json we need to convert the data to string or else it will pass null value
				.when()
					.post("http://localhost:3000/students")
				.then()
					.statusCode(201)
					.body("name", equalTo("Pojo2"))
					.body("phone", equalTo("3344556677"))
					.log().all()
					.extract()
					.response();
				
				id = response.jsonPath().getString("id");
		}
		//@Test(priority=2,dependsOnMethods="testPostUsingJsonLibrary")
		void deleteUserUsingIdPojo()
		{
			//Thread.sleep(2000);
			given()
			
			.when()
				.delete("http://localhost:3000/students/"+id)
			.then()
				.statusCode(200)
				.log().all();
		}
		
		//-------------------------------------------- Post using External Json file --------------------------------------------------------
		
		//@Test(priority=1)
		public void testPostUsingExternalJsonFile() throws FileNotFoundException
		{
			File f = new File(".\\body.json");
			
			FileReader fr = new FileReader(f);
			
			JSONTokener jt = new JSONTokener(fr);
			
			JSONObject data = new JSONObject(jt);
			
			Response response =given()
					.contentType("application/json")
					.body(data.toString()) //for org.json we need to conver the data to string orelse it will pass null value
				.when()
					.post("http://localhost:3000/students")
				.then()
					.statusCode(201)
					.body("name", equalTo("External Json file"))
					.body("phone", equalTo("445566778899"))
					.log().all()
					.extract()
					.response();
				
				id = response.jsonPath().getString("id");
		}
		//@Test(priority=2,dependsOnMethods="testPostUsingJsonLibrary")
		void deleteUserUsingIdEJF()
		{
			//Thread.sleep(2000);
			given()
			
			.when()
				.delete("http://localhost:3000/students/"+id)
			.then()
				.statusCode(200)
				.log().all();
		}
}
