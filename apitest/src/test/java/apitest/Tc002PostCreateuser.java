package apitest;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Tc002PostCreateuser {

	@Test
	public void createNewUser() {
		
		
		//define base URI
		RestAssured.baseURI = "http://localhost:3000";

		//create req specification
		RequestSpecification httpReq = RestAssured.given();

		
		//add payload 
		JSONObject reqParams = new JSONObject();
		reqParams.put("employee_name", "ABC");
		reqParams.put("employee_salary", 45000);
		reqParams.put("employee_age", 25);
		reqParams.put("profile_image", "https://img123.com");
		
		
		//define content type and attach the payload to body
		httpReq.header("Content-Type", "application/json");
		httpReq.body(reqParams.toJSONString());
		
		
		//send request
		Response response = httpReq.request(Method.POST, "/users");
		

		// print the response in console
		String responseBody = response.getBody().asString();
		System.out.println("Body: ---> " + responseBody);

		// verify status code
		int statusCode = response.getStatusCode();
		System.out.println("Status code is " + statusCode);
		Assert.assertEquals(statusCode, 201);

		// status line verification
		String statusLine = response.getStatusLine().trim();
		System.out.println("Status line is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
		
		//fetch empname from the response
		String empName = response.jsonPath().get("employee_name");
		System.out.println("empName------->"+empName);
		Assert.assertEquals(empName, "ABC");
		

	}

}
