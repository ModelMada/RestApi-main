package utils;


import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BaseComponent {
	
	@BeforeClass
	public void setup() {
		//salvam BaseURLul pt requesturi
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com";
	}
	
	//le apelez direct daca le fac statice
	public static Response doPostRequest(String path, String requestBody, int statusCode) {
		Response response = 
			given().
				contentType(ContentType.JSON).
				body(requestBody).
			when().
				post(path).
			then().
				statusCode(statusCode).
				extract().response();
		return response;
				
	}
	
	public static Response doGetRequest(String path, String id, int statusCode) {
		Response response = 
				given().
					contentType(ContentType.JSON).
				when().
					get(path+id).
				then().
					statusCode(statusCode).
					extract().response();
			return response;
	}
	
	public static Response doPutRequest(String path, String id, String requestBody, int statusCode) {
		Response response = 
			given().
				contentType(ContentType.JSON).
				body(requestBody).
			when().
				put(path+id).
			then().
				statusCode(statusCode).
				extract().response();
		return response;
	
	
	
}
}
