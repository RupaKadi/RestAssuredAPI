package api.Endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	//UserEndpoints, StoreEndpoints, PetEndpoints---these classes are used to perform CRUD(Create, Read, Update, Delete) operations
	
	public static Response createUser(User payload) {
		Response response=given()
		 .contentType(ContentType.JSON)
		 .accept(ContentType.JSON)
		 .body(payload)
		 
		.when()
		 .post(Routes.post_url);
		return response;
		
	}
	
	public static Response readUser(String username)
	{
		Response response=given()
				.pathParam("username", username)
				
		.when()
		 .get(Routes.get_url);
		
		return response;
		
	}
	
	public static Response updateUser(User payload,String username)
	{
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)
				
		.when()
		 .put(Routes.update_url);
		
		return response;
		
	}
	public static Response deleteUser(String username)
	{
		Response response=given()
				.pathParam("username", username)
				
				
		.when()
		 .delete(Routes.delete_url);
		
		return response;
	}
	
}
