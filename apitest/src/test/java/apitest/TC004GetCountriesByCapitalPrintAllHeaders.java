package apitest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004GetCountriesByCapitalPrintAllHeaders {
	
	
	@Test
	public void getCountriesByCapitalPrintHeaders() {
		
		RestAssured.baseURI = "https://restcountries.eu";
		
		RequestSpecification httpReq = RestAssured.given();
		
		Response resp = httpReq.request(Method.GET, "/rest/v2/capital/paris");

		
		String responseBody = resp.getBody().asString();
		System.out.println("responseBody ----------> "+responseBody);
		
		
		//print all headers
		Headers allHeaders = resp.headers();
		
		for(Header h: allHeaders) {
			
			String headerName = h.getName();
			String headerValue = h.getValue();
			System.out.println("headerName : "+headerName + " -->  "+"headerValue: "+headerValue);
			
			
		}
		
		
		
		
	}
	
	
			
	

}
