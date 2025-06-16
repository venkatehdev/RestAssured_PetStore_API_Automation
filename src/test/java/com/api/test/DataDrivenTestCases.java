package com.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.payload.User;
import com.api.utilities.DataProviders;

import io.restassured.response.Response;

public class DataDrivenTestCases {
	
	
	//Data Driven TestCases-Read data from excel
	
	@Test(priority=1, dataProvider="Data", dataProviderClass= DataProviders.class)
	public void testUserCreation(String UserID,String UserName,String fname,String lname,String email,String Psw,String ph) {
		
		User userPayload =new User();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(Psw);
		userPayload.setPhone(ph);
		
		
		Response response =UserEndPoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass= DataProviders.class)
	public void testDeleteUser(String userName) {
		Response response =UserEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
