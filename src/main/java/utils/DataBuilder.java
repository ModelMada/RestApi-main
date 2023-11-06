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
	
	@SuppressWarnings("unchecked")
	public static JSONObject buildUser() {
		JSONObject user = new JSONObject();
		Faker faker = new Faker();
		
		user.put("name", faker.name().firstName());
		user.put("email", faker.internet().safeEmailAddress());
		user.put("age", faker.number().numberBetween(5,130));
		user.put("gender", "f");
		
		return user;
	}
}
