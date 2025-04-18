package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Endpoints.UserEndpoints;
import api.Endpoints.UserEndpointsWithPropFile;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsWithPropFile {

	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUpData()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode()); //we use hashcode to generate no.s unique.If we won't use it then it may generate same ids sometimes
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());		
		
	}
	
	@Test(priority=1)
	public void testPostuser()
	{
		logger.info(" ******Creating User****** ");
		Response response=UserEndpointsWithPropFile.createUser(userPayload);
		response.then().log().all(); //we can directly use then() here to do validations
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" ******Created User****** ");
	}
	
	@Test(priority=2)
	public void testGetUserbyName()
	{
		logger.info(" ******Reading User****** ");
		Response response=UserEndpointsWithPropFile.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" ******Got User****** ");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info(" ******Updating User****** ");
		//update data using payload. Here we are updating data using username so no need to pass id here. we are passing username
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndpointsWithPropFile.updateUser(userPayload,this.userPayload.getUsername());
		response.then().log().all();
		response.then().log().body().statusCode(200); //this is also correct way to check statuscode from body
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update--for this we again execute getuser method
		
		Response responseAfterupdate=UserEndpointsWithPropFile.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);	
		logger.info(" ******Updated User****** ");
	}
	
	@Test(priority=4)
	public void testDeleteUserbyName()
	{
		logger.info(" ******Deleting User****** ");
		Response response=UserEndpointsWithPropFile.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" ******Deleted User****** ");
	}
}
