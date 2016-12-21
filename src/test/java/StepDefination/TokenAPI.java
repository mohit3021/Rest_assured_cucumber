package StepDefination;

import java.io.FileReader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.OAuthSignature;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

public class TokenAPI {

	Response r, r1;
	String responsebody;

	static String jwttoken;
	

	@Given("^Making api token request with post method API$")
	public void making_api_token_request_with_post_method_API() throws Throwable {
		YamlReader reader = new YamlReader(new FileReader("Mapplication.yml"));

		Object object = reader.read();

		Map map = (Map) object;
		String pass = (String) map.get("password");

		String user = (String) map.get("username");

		String baseurl = (String) map.get("baseURL");

		String api = (String) map.get("Api");

		RestAssured.baseURI = baseurl;
		r = RestAssured.given().contentType("application/json")
				.body("{\"password\": \"" + pass + "\", \"username\": \"" + user + "\"}").when().post(api);

	}

	@Then("^Validate Status code$")
	public void validate_Status_code() throws Throwable {

		int staticcode = r.getStatusCode();
		System.out.println(staticcode);

	}

	@Then("^Validate Response Body$")
	public void validate_Response_Body() throws Throwable {
		responsebody = r.getBody().asString();
		System.out.println(responsebody);

	}

	@Then("^Save JWT token in a variable$")
	public void save_JWT_token_in_a_variable() throws Throwable {

		JsonPath jsonPath = new JsonPath(responsebody);
		System.out.println("inside save_JWT_token_in_a_variable()");  
		jwttoken = jsonPath.get("jwt");
		System.out.println("Here is JWT Token: " + jwttoken);

	}

	@Given("^make project call with input body and post method api$")
	public void make_project_call_with_input_body_and_post_method_api() throws Throwable {
		Thread.sleep(5000);

		System.out.println(jwttoken);
		RestAssured.baseURI = "https://stage-api.core.merrillcorp.com";
		r1 = RestAssured.given().authentication().oauth2(jwttoken, OAuthSignature.HEADER)
				.contentType("application/json")
				.body("{\r\n\"projectInfo\": {\r\n  \"active\": true,\r\n  \"datacenter\": \"Unique_DCenter_3\",\r\n  \"emailId\": \"dummyiud17h86@gmail.com\",\r\n  \"projectDescription\": \"Mohit_12ksharma_1hproject_details\",\r\n  \"projectName\": \"Mohit_h1Pjroject11140fd4\",\r\n  \"projectType\": \"M1Type\",\r\n  \"salesforceProjectId\": \"mm2Sales_Forjce_14146111\"\r\n}\r\n\r\n}")
				.when().post("/api/projects");

	}

	@Then("^Validate Status for project code$")
	public void validate_Status_for_project_code() throws Throwable {
		System.out.println(r1.getStatusCode());

	}

	@Then("^Validate Response Body for project$")
	public void validate_Response_Body_for_project() throws Throwable {
		System.out.println(r1.getBody().asString());

	}

}
