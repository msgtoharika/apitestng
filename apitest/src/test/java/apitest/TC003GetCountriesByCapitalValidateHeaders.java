package apitest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003GetCountriesByCapitalValidateHeaders {
	
	
	@Test
	public void getCountriesByCapValidateHeaders() {
		
		RestAssured.baseURI = "https://restcountries.eu";
		
		RequestSpecification httpReq = RestAssured.given();
		
		Response resp = httpReq.request(Method.GET, "/rest/v2/capital/paris");

		
		String responseBody = resp.getBody().asString();
		System.out.println("responseBody ----------> "+responseBody);
		
		
		//validate content type
		String contentType = resp.header("Content-Type");
		System.out.println("contentType---------------->"+contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
		
		//validate connection header
		String connection = resp.header("Connection");
		System.out.println("connection---------------->"+connection);
		Assert.assertEquals(connection, "keep-alive");
		
		
		//validate content encoding
		String contentEncoding = resp.header("Content-Encoding");
		System.out.println("contentEncoding---------------->"+contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
		
		
	}
	
	
			
	

}
