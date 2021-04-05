package apitest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001GetCountriesByNameAPI {

	
	@Test
	public void getCountriesByName() {
		
		//specify base URI
		RestAssured.baseURI = "https://restcountries.eu/rest/v2/name/";
		
		//Create request object
		RequestSpecification httpReq = RestAssured.given();
		
		//Store response in response object
		Response response = httpReq.request(Method.GET, "/Ukraine");
		
		//print the response in console
		String responseBody = response.getBody().asString();
		System.out.println("Body: ---> "+responseBody);
		
		//verify status code
		int statusCode = response.getStatusCode();
		System.out.println("Status code is "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//status line verification
		String statusLine = response.getStatusLine().trim();
		System.out.println("Status line is "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200");
		
		
		
	}
	
}
