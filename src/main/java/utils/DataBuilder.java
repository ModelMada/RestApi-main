package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class DataBuilder {

	public static JSONObject buildToDo() {
		
		JSONObject toDoBuilder = new JSONObject();
		Faker faker =  new Faker();		
		toDoBuilder = new JSONObject();
		toDoBuilder.put("title", faker.lebowski().quote());
		toDoBuilder.put("body", faker.dune().quote());
		
		return toDoBuilder;
	}
}
