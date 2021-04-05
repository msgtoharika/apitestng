package datadriventest;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestUtils.ReadExcelData;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCreateNewUserWithCsv {
	
	
	@DataProvider(name = "empData")
	public Object[][] getUserdata() {
		Object[][] empData = ReadExcelData.getData("user"); 
		return  empData;
	}
	
	
	@Test(dataProvider="empData")
	public void createNewUsers(String name, String salary, String age, String imageUrl) {
		
		System.out.println(name);
		
		
		RestAssured.baseURI = "http://localhost:3000";
		
		RequestSpecification httpReq = RestAssured.given();
		
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("employee_name", name);
		jsonObj.put("employee_salary", salary);
		jsonObj.put("employee_age", age);
		jsonObj.put("profile_image", imageUrl);
		
		httpReq.header("Content-Type", "application/json");
		httpReq.body(jsonObj.toJSONString());
		
		Response resp = httpReq.request(Method.POST, "/users");
		
		Assert.assertEquals(resp.getBody().asString().contains(name), true);
		Assert.assertEquals(resp.getBody().asString().contains(salary), true);
		Assert.assertEquals(resp.getBody().asString().contains(age), true);
		Assert.assertEquals(resp.getBody().asString().contains(imageUrl), true);
		
		Assert.assertEquals(resp.getStatusCode(), 201);
		
	}

}
