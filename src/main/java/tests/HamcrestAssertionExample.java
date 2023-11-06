package tests;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HamcrestAssertionExample {

	
	@Test
	public void hamcrestMatchers() {
		Response resp = 
				given().
					get("https://swapi.dev/api/planets/1/").
				then().
					extract().response();
		System.out.println(resp.asString());
		
		JsonPath jsnPath = resp.jsonPath();
		String name = jsnPath.getString("name");
		System.out.println(name);
		
		//Testng assert
		assertEquals(name, "Tatooine");
		
		//Hamcrest assert
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is("Tatooine"));	//is (T value)
		assertThat(name, is(equalTo("Tatooine")));	//is(Matcher) o metoda care ma ajuta i compararea de obiecte
		
		//Testng assert
		assertNotEquals(name, "Terra");
		
		//Hamcrest assert
		assertThat(name,is(not("Terra")));
		assertThat(name, is(not(equalTo("Terra"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		assertThat(name,is(instanceOf(String.class)));
		
		//starts-with
		assertThat(resp.asString(),startsWith("{\"name\":"));
		assertThat(name,startsWith("Tato"));
		assertThat(resp.asString(),startsWithIgnoringCase("{\"nAME\":"));
		
		//ends-with
		assertThat(resp.asString(), endsWith("api/planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("api/PLAnETS/1/\"}"));
		
		//contains
		assertThat(name,containsStringIgnoringCase("tooi"));
		assertThat(resp.asString(), containsString("desert"));
		
		//pattern
		// + din regex parcurge tot stringu, se aplica recursiv pe tot obiectul
		// fara + va lua caracter cu caracter
		
		assertThat(name, matchesPattern("[A-Za-z]+"));
		name = "Tatooine123";
		assertThat(name, matchesPattern("[A-Za-z]+"));
		String diameter = jsnPath.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));
		
	}
}
