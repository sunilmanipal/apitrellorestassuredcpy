package demos;

import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class restassuredDemo {
	
	public static String baseURI = "https://api.trello.com";
	
	@Test(enabled=false)
	public void testcase1()
	{
		//Base Uri gets loaded
		RestAssured.baseURI = baseURI;
		//given, when and Then 
		given()
		.param("key", "0eaaedb54cd3b1d89f59fe2169d5bd6e")
		.param("token", "4dcbec6e1c5c0df54bde3a7c9fb063069b2e6df0cd32afc981257178865686a7")
		.when()
		.get("/1/boards/60c8635ccd273755e792e19c")
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.and()
		.body("name",equalTo("Divya API board 5"))
		.and()
		.body("desc",equalTo("Creating newly every time"));
		System.out.println("Executed successfully");
	}
	
	@Test(enabled=false)
	public void testcase2()
	{
		//Base Uri gets loaded
		RestAssured.baseURI = baseURI;
		//given, when and Then 
		given()
		.param("key", "0eaaedb54cd3b1d89f59fe2169d5bd6e")
		.param("token", "4dcbec6e1c5c0df54bde3a7c9fb063069b2e6df0cd32afc981257178865686a7")
		.when()
		.get("/1/boards/60c8635ccd273755e792e19c")
		.then()
		.assertThat()
		.statusCode(200)
		.contentType(ContentType.JSON)
		.and()
		.header("Content-Type", "application/json; charset=utf-8");
		System.out.println("Executed succesfully");
	}
	
	//post method//without body
	//create board//key//token//board name
	@Test
	public void testcase3()
	{
		String boardname = "Creationofboardfromeclipse" + (int) (Math.random()*100);
		
		RestAssured.baseURI = baseURI;
		given()
		.queryParam("name",boardname)
		.queryParam("key", "5203ef7973b5e7b57a1b0c488797f34c")
		.queryParam("token", "85ca20f0fa848e1cec9c638b088ea8fc0590bf5b32cc8a1de825eb3ea917b525")
		.header("Content-Type", "application/json")
		.when()
		.post("/1/boards")
		.then()
		.assertThat()
		.statusCode(200);
	}

}
