package datadriventest;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCreateNewUser {
	
	
	@DataProvider(name = "empData")
	public String[][] getUserdata() {
		String empData[][] = {{"abctyu", "30000", "40"}, {"xyz123", "50000", "50"}, {"pqr678", "60000", "60"}};
		return  empData;
	}
	
	
	@Test(dataProvider="empData")
	public void createNewUsers(String name, String salary, String age) {
		
		
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		
		RequestSpecification httpReq = RestAssured.given();
		
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", name);
		jsonObj.put("salary", salary);
		jsonObj.put("age", age);
		
		httpReq.header("Content-Type", "application/json");
		httpReq.body(jsonObj.toJSONString());
		
		Response resp = httpReq.request(Method.POST, "/api/v1/create");
		
		Assert.assertEquals(resp.getBody().asString().contains(name), true);
		Assert.assertEquals(resp.getBody().asString().contains(salary), true);
		Assert.assertEquals(resp.getBody().asString().contains(age), true);
		
		Assert.assertEquals(resp.getStatusCode(), 200);
		
	}

}
