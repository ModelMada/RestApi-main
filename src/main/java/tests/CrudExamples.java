package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class CrudExamples {
	//ca sa il putem folosi peste tot in toate metodele
	JSONObject requestBody, updateBody;
	String id;			//pentru a stoca IDul din response si a il folosi in celelalte metode
	
	@BeforeClass
	public void setup() {
		//salvam BaseURLul pt requesturi
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com";
		
		Faker faker =  new Faker();		
		requestBody = new JSONObject();
		requestBody.put("title", faker.cat().name());
		requestBody.put("body", faker.chuckNorris().fact());
		
		updateBody = new JSONObject();
		updateBody.put("title", faker.ancient().primordial());
		updateBody.put("body", faker.dune().quote());
	}
	
	/*
	 * CRUD
	 * C = Create = POST
	 * R = Read = GET
	 * U = Update = PUT/PATCH
	 * D = Delete = DELETE
	 * 
	 */
	
	//CREATE
	@Test(priority=1)
	public void postAToDoMessage() {
		//salvam callul intr-un obiect de tip response pt a putea apela la final valorile din response pt a le folosi in requesturile urmatoare
		Response obj = given().			//in given punem preconditiile body authorization type etc
			//.header("Content-Type","application/json")
			contentType(ContentType.JSON).
			body((requestBody.toJSONString())).
		when().
			post("/api/save").
		then().
			statusCode(200).log().all().
			body("info", equalTo("Todo saved! Nice job!")).
			body("id",anything()).
			extract().response();		//obligatoriu pentru un obiect de tip response
		
		id = obj.jsonPath().getString("id");
		System.out.println(id);
	}
	
	//READ
	@Test(priority=2)
	public void getToDo() {
		Response response = given().
				get("/api/"+id).
			then().
				statusCode(200).extract().response();
		
		System.out.println(response.asPrettyString());
	}
	
	//UPDATE
	@Test(priority=3)
	public void updateToDo() {
		Response response = given().
			contentType(ContentType.JSON).
			body((updateBody.toJSONString())).
			put("/api/todos/"+id).
		then().extract().response();
		
		System.out.println(response.asPrettyString());
				
	}
	
	//GET AFTER UPDATE
	@Test(priority=4)
	public void getToDoAfterUpdate() {
		Response response = given().
				get("/api/"+id).
			then().
				statusCode(200).extract().response();
		
		System.out.println(response.asPrettyString());
	}
	
	//DELETE
	@Test(priority=5)
	public void deleteToDo() {
		Response response = given().
				delete("/api/delete/" + id).
			then().
				extract().response();
		
		System.out.println(response.asPrettyString());
	}
	
	
}
