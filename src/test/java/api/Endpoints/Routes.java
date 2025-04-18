package api.Endpoints;

/*Swagger URI---https://petstore.swagger.io
 
 Create User---https://petstore.swagger.io/v2/user
 Get User---https://petstore.swagger.io/v2/user/{username}
 Update User---https://petstore.swagger.io/v2/user/{username}
 Delete user---https://petstore.swagger.io/v2/user/{username}
 
 Here "https://petstore.swagger.io/v2"--base url
 user/{username}---endpoint for user
 store/{username}---endpoint for store
 
 */
public class Routes {

	//this class is only to store URLs
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User module
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//Store module
	
	  //<----here we will create store module urls---->
	
	//Pet module
	
	  //<----here we will create pet module urls---->
	
}
