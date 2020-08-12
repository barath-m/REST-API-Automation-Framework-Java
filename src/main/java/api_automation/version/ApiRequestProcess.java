package api_automation.version;

import static io.restassured.RestAssured.get;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiRequestProcess {
	public static  int code ;
	public static  String respons ;
	public static String jsonobj;
	public static String logger2;
//	static int code = 0;
	@Test
	public static int testResponseCode(
			String reqUrl, 
			String methodName, 
			double expectedCode1, 
			String jsonBody,
			ExtentReports extent) 
	{
		System.out.println("method : " + methodName);
		int expectedCode = Integer.valueOf((int) Math.round(expectedCode1));
		System.out.println("expected code is :- " + expectedCode);
		if (!jsonBody.contains("NA")) {
			JSONObject jsonobj = new JSONObject(jsonBody);
		}
		
		Response resp = null ;
		RestAssured.baseURI = reqUrl;
	//Without token	
	//	RequestSpecification request = RestAssured.given().body(jsonBody);
		RequestSpecification request = RestAssured.given().auth().oauth2(InputData.token).body(jsonBody);
		ExtentTest logger2 = null;

		if (methodName.equalsIgnoreCase("get")) {
			resp = get(reqUrl);
			code = resp.getStatusCode();
			respons =resp.getBody().asString();
			System.out.println(respons);
		} else if (methodName.equalsIgnoreCase("post")) {

			request.header("Content-Type", "application/json");
			resp =request.post();
			code = resp.getStatusCode();
			respons =resp.getBody().asString();
			System.out.println(respons);
		} else if (methodName.equalsIgnoreCase("put")) {

			request.header("Content-Type", "application/json");
			resp = request.put();
			code = resp.getStatusCode();
			respons =resp.getBody().asString();
			System.out.println(respons);
		} else if (methodName.equalsIgnoreCase("delete")) {
			
			request.header("Content-Type", "application/json");
			resp = request.delete();
			code = resp.getStatusCode();
			respons =resp.getBody().asString();
			System.out.println(respons);
		}
		else if (methodName.equalsIgnoreCase("patch")) {
		
			request.header("Content-Type", "application/json");
			resp = request.patch();
			code = resp.getStatusCode();
			respons =resp.getBody().asString();
			System.out.println(respons);
		}
		System.out.println(methodName + " method :" + code);
		if (resp != null) {
			code = resp.getStatusCode();
			respons =resp.getBody().asString();
			System.out.println(respons);
			//return code;
		
		
		if(code == expectedCode) {
			
			System.out.println("Pass");
			
		}
		if(code != expectedCode) {
			System.out.println("Fail");
		}
		return code;
		}
		
	

	if (code == expectedCode) {
		logger2.log(Status.PASS,
				"<b>Expected code:</b> " + expectedCode + "<br /><b>Actual response:</b> " + code + "<br />"
						+ "<b>Response time: </b>" + resp.getTime() + "<b><br />Expected time: </b>" + expectedCode
			+ "<br />Response Body: " + resp.asString());
		
		return 0;
	}
	
	 else  {
			logger2.log(Status.FAIL,
					"<b>Faild reason: </b>Response Code is not equal to Expected Code.<br />"
							+"<b>Expected code:</b> " + expectedCode + "<br /><b>Actual response:</b> " + code + "<br />"
							+ "<b>Response time: </b> " + resp.getTime() + "<br /><b>Response Body: </b>"
							+ resp.asString());
			 return -1;
	
	 }
		}
	
	}

	

	

