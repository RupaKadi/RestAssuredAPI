package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1,dataProvider="Data", dataProviderClass=DataProviders.class) //if we are writing dataprovider in same class then no need to specify dataProviderClass parameter
	public void testPostUser(String userID, String userName, String fname,String lname,String email, String pwd,String ph)
	{
		User userpayload=new User();
		userpayload.setId(Integer.parseInt(userID)); //converting string to integer type
		userpayload.setUsername(userName);
		userpayload.setFirstname(fname);
		userpayload.setLastname(lname);
		userpayload.setEmail(email);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response response=UserEndpoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2,dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUsersbyName(String userName)
	{
		Response response=UserEndpoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
