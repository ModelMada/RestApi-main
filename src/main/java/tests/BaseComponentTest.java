package tests;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;

public class BaseComponentTest extends BaseComponent {

	String id;
	
	@Test(priority=1)
	public void postToDo() {
		Response response = doPostRequest("/api/save", DataBuilder.buildToDo().toJSONString(),200);
		id = response.jsonPath().getString("id");
		assertEquals(response.jsonPath().getString("info"),"Todo saved! Nice job!");
		assertThat(response.jsonPath().getString("info"), is(equalTo("Todo saved! Nice job!")));	
		
	}
	
	@Test(priority=2)
	public void getTodo() {
		Response response = doGetRequest("/api/", id, 200);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("_id"),is(id));
		//assertEquals(response.jsonPath().getString("_id"),is(equalTo(id)));
	}
	
	@Test(priority=3)
	public void putToDo() {
		Response response = doPutRequest("/api/todos/", id, DataBuilder.buildToDo().toJSONString(),201);
		System.out.println(response.asPrettyString());
		assertThat(response.jsonPath().getString("msg"), is(equalTo("Item updated")));	
	}
}
