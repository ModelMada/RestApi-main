package tests;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;
import static io.restassured.RestAssured.given;

import java.util.List;

public class JsonPathExample extends BaseComponent {
	
	@Test
	public void jsonPathExamples() {
		Response response = given()
				.get("https://keytrcrud.herokuapp.com/api/users/")
				.then().extract().response();

		System.out.println(response.asString());
		System.out.println("----------------------------------------------");
		JsonPath jsonPath = response.jsonPath();
		//accesez obiect pe baza de index
		System.out.println(jsonPath.getString("[0]"));
		System.out.println(jsonPath.getString("[0].name"));
		System.out.println(jsonPath.getString("[0]._id"));
		System.out.println(jsonPath.getString("[0].age"));
		System.out.println(jsonPath.getString("[0].gender"));
		//accesez direct attribut fara index -> imi va lua toate numele de pe toate obiectele din json
		System.out.println(jsonPath.getString("name"));
		System.out.println(jsonPath.getString("gender"));
		System.out.println("----------------------------------------------");
		//cautam in interiorul Jsonului toate atributele cu gender M si pentru fiecare obiect cu gender M vom aduce numele
		//it.gender e un fel de this.<obiect>
		System.out.println(jsonPath.getList("findAll{it.gender == 'm'}.name"));
		//putem face si un obiect de tip Lista de stringuri
		List <String> allMales = jsonPath.getList("findAll{it.gender == 'm'}.name");
		System.out.println(allMales);
		
		System.out.println("----------------------------------------------");
		List <String> allFemales = jsonPath.getList("findAll{it.gender == 'f'}.name");
		System.out.println(allFemales);
		
		System.out.println("----------------------------------------------");
		List <String> allElla = jsonPath.getList("findAll{it.name == 'Ella'}.age");
		System.out.println(allElla);
		//daca nu pun .getAttribute imi va aduce obiectul cu totul
		System.out.println("----------------------------------------------");
		List <String> allOldElla = jsonPath.getList("findAll{it.name == 'Ella' && it.age >= 60}");
		System.out.println(allOldElla);
		
		String oldestElla = jsonPath.getString("find{it.name == 'Ella' && it.age > 80}");
		System.out.println(oldestElla);
		
		System.out.println("----------------------------------------------");
		List <String> allEllaOrClaudia = jsonPath.getList("findAll{it.name == 'Ella' ||  it.name == 'Claudia'}");
		System.out.println(allEllaOrClaudia);
		
	}
}
