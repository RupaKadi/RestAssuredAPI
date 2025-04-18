package api.Endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpointsWithPropFile {

	
	static ResourceBundle getURL()    //we use resourcebundle class from java to read properties file
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");  //to load properties file. Here we specified "routes" in braces because we should specify properties file. No need to specify entire path just mention name of the properties file
	    
		return routes;
	}
	
	public static Response createUser(User payload) 
	{
		String post_url=getURL().getString("post_url");   //specify key(post_url)
		
		Response response=given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		 
		.when()
		 .post(post_url);
		return response;
		
	}
	
	public static Response readUser(String username)
	{
		String get_url=getURL().getString("get_url");
		
		Response response=given()
				.pathParam("username", username)
				
		.when()
		 .get(get_url);
		
		return response;
		
	}
	
	public static Response updateUser(User payload,String username)
	{
		String update_url=getURL().getString("update_url");
		
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
				
		.when()
		 .put(update_url);
		
		return response;
		
	}
	public static Response deleteUser(String username)
	{
		String delete_url=getURL().getString("delete_url");
		
		Response response=given()
				.pathParam("username", username)
				
				
		.when()
		 .delete(delete_url);
		
		return response;
	}
	
}
