package tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;

public class SendFromJsonFile extends BaseComponent {

	//read multiple json objects
	//json parser ne parcurge jsonul
	/*@Test
	public void testJsonFile() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();			//obiect care stie sa parcurga fisierul json si care imi va popula obiectul obj
		Object obj = parser.parse(new FileReader("data2.json"));		//un obiect in care dau pathul catre json si fisierul pe care vreau sa il citeasca	
		//scot arrayul de tip Json din fisierul data2
		JSONArray toDoList = (JSONArray) obj;
		//pt fiecare obiect din toDoList, obiectul il transform in obiect de tip Json pentru a putea lucra cu el
		for(Object toDo : toDoList) {
			JSONObject objTodo = (JSONObject)toDo;
			Response response = doPostRequest("/api/save", objTodo.toJSONString(), 200);
			System.out.println(response.asPrettyString());			
		}
	}
	*/
	
	@Test
	public void parseJson() throws IOException, ParseException {
		//1. trebuie sa facem un obiect de tip aprses pentru Json
		JSONParser parser = new JSONParser();
		//2. sa incarc fisierul pe care vreau sa il parsez
		FileReader file = new FileReader("data3.json");
		//3. parcurg fisierul incarcat la pasul anterior
		Object obj = parser.parse(file);
		//4. punem continutul fisierului intr-un JsonArray
		JSONArray employeeList = (JSONArray) obj;
		System.out.println(employeeList);
		System.out.println(employeeList.get(0));
		System.out.println(employeeList.get(1));
		//5. vreau sa iau un camp individual din obiectul Json
			//primul obiect employee
		System.out.println("----------------------------------------------");
		JSONObject employeeObject = (JSONObject) employeeList.get(0);
		System.out.println(employeeObject);
		JSONObject employeeAtribute = (JSONObject) employeeObject.get("employee");
		System.out.println(employeeAtribute);
		System.out.println(employeeAtribute.get("firstName"));
		
		
		//tot codul de mai sus il putem reduce la ce avem dedesubt
		//nu mai coboram obiect cu obiect prin jsonBody
		System.out.println("----------------------------------------------");
		FileReader jsonFile = new FileReader("data3.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);				//aici deja parsez fisierul data3 pana la final
		// [0] index de 0 inseama primul obiect
		System.out.println(jsonPath.getString("[0].employee.firstName"));
		
		
	}
}
