package apitest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005GetCallValidateReponseBody {
	
	@Test
	public void getValidateJsonResponse() {
		
		RestAssured.baseURI = "https://api.thecatapi.com";
		
		RequestSpecification httpReq = RestAssured.given();
		Header requestHeader1 = new Header("x-api-key","17d94b92-754f-46eb-99a0-65be65b5d18f");
		httpReq.header(requestHeader1);
				
		Response resp = httpReq.request(Method.GET, "/v1/favourites");
		
		
		String responseBody = resp.getBody().asString();
		System.out.println("responseBody ----------> "+responseBody);
		
		JsonPath jsonPath = new JsonPath(responseBody);
		
		System.out.println("id ----> "+jsonPath.get("[0].id"));
		System.out.println("user_id ----> "+jsonPath.get("[0].user_id"));
		System.out.println("image_id ----> "+jsonPath.get("[0].image_id"));
		System.out.println("sub_id ----> "+jsonPath.get("[0].sub_id"));
		System.out.println("created_at ----> "+jsonPath.get("[0].created_at"));
		System.out.println("image.id ----> "+jsonPath.get("[0].image.id"));
		System.out.println("image.url ----> "+jsonPath.get("[0].image.url"));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
