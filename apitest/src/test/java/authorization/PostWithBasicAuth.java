package authorization;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostWithBasicAuth {
	
	@Test
	public void basicAuthTest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		
		//set un and pwd
		PreemptiveBasicAuthScheme premptiveAuthScheme = new PreemptiveBasicAuthScheme();
		premptiveAuthScheme.setUserName("postman");
		premptiveAuthScheme.setPassword("password");
		
		
		//set auth type
		RestAssured.authentication = premptiveAuthScheme;
		
		RequestSpecification httpReq = RestAssured.given();
		
		//if we don't have any path we can just pass "/" here
		Response resp = httpReq.request(Method.GET, "/basic-auth");
		
		String responseBody = resp.getBody().asString();
		System.out.println("responseBody------->"+responseBody);
		
	}

}
