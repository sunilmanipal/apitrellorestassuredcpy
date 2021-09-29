package demos;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EcommerceRestTesting {
	
	public static String baseURI = "https://ecommerceservice.herokuapp.com";
	public String accesstoken;
	public String id;
	
	//what type of method is signup
	//Post Method //with body
	//i am not able to see the response
	@Test(enabled=false)
	public void signup()
	{
		RestAssured.baseURI = baseURI;
		//i have to create my body
		
		String requestbody = "{\r\n" + 
				"	\"email\": \"Radha001@gmail.com\",\r\n" + 
				"	\"password\": \"Radha001@123\"\r\n" + 
				"}\r\n" + 
				""; 
		
	Response response =given()
		.header("content-Type","application/json")
		.body(requestbody)
		
		.when()
		.post("/user/signup")
		
		.then()
		.assertThat()
		.contentType(ContentType.JSON)
		
		.extract().response();
	System.out.println(response.asString());
	}
	
	@Test(priority=0)
	public void Login()
	{
		RestAssured.baseURI = baseURI;
		//i have to create my body
		
		String requestbody = "{\r\n" + 
				"	\"email\": \"Radha001@gmail.com\",\r\n" + 
				"	\"password\": \"Radha001@123\"\r\n" + 
				"}\r\n" + 
				""; 
		
	Response response =given()
		.header("content-Type","application/json")
		.body(requestbody)
		
		.when()
		.post("/user/login")
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		
		.extract().response();
	//i have to convert the response data from string to json
	System.out.println(response.asString());
	String jsonresponsebody = response.asString();
//i want to convert this in to json
	JsonPath responsebody  = new JsonPath(jsonresponsebody);
	accesstoken = responsebody.get("accessToken");
	System.out.println(accesstoken);
	}

	@Test(priority=1)
	public void getalluser()
	{
		RestAssured.baseURI = baseURI;
		//i have to create my body
		
		
		
	Response response =given()
		.header("content-Type","application/json")
		.header("Authorization","bearer " + accesstoken )
		
		.when()
		.get("/user")
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		
		.extract().response();
	//i have to convert the response data from string to json
	System.out.println(response.asString());
	String jsonresponsebody = response.asString();
//i want to convert this in to json
	JsonPath responsebody  = new JsonPath(jsonresponsebody);
	id = responsebody.get("users[2]._id");
	System.out.println(id);
	}
	
	@Test(priority=2)
	public void deleteauser()
	{
		RestAssured.baseURI = baseURI;
		//i have to create my body
		
		
		
	Response response =given()
		.header("content-Type","application/json")
		.header("Authorization","bearer " + accesstoken )
		
		.when()
		.delete("/user/" + id)
		
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		
		.extract().response();
	//i have to convert the response data from string to json
	System.out.println(response.asString());
	String jsonresponsebody = response.asString();
//i want to convert this in to json
	JsonPath responsebody  = new JsonPath(jsonresponsebody);
	String message = responsebody.get("message");
	System.out.println(message);
	}
}
