package com.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.payload.User;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class UserModuleTestCases {
	
	Faker faker=new Faker();
	User userModulePayload;
	
	public Logger logger;
	
	@BeforeClass
	public void testSetup() {
		
		userModulePayload = new User();
		
		userModulePayload.setId(faker.idNumber().hashCode());
		userModulePayload.setUsername(faker.name().username());
		userModulePayload.setFirstName(faker.name().firstName());
		userModulePayload.setLastName(faker.name().lastName());
		userModulePayload.setEmail(faker.internet().safeEmailAddress());
		userModulePayload.setPassword(faker.internet().password(5,10));
		userModulePayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	
	
	@Test(priority=1)
	public void testUserCreation() {
		logger.info("************ Creating User *********************");
		
		Response response =UserEndPoints.createUser(userModulePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User is Created *********************");
	}
	
	@Test(priority=2)
	public void testGetUser() {
		logger.info("************ Reading User Info*********************");
		
		Response response =UserEndPoints.readUser(this.userModulePayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User Info is displayed *********************");
		
		
	}
	

	
	
	@Test(priority=3)
	public void testUpdateUser() {
		logger.info("************ Updating User *********************");
		
		userModulePayload.setFirstName(faker.name().firstName());
		userModulePayload.setLastName(faker.name().lastName());
		userModulePayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response =UserEndPoints.updateUser(this.userModulePayload.getUsername(),userModulePayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Check response after update
		
		Response responseAfterUpdate =UserEndPoints.readUser(this.userModulePayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************ User is Updated *********************");
		
		
		
		
	}
	
	

	@Test(priority=4)
	public void testDeleteUser() {
		
		logger.info("************ Deleting User *********************");
		Response response =UserEndPoints.deleteUser(this.userModulePayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ User is deleted *********************");
	}
	
}
