package com.api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		
		String path = System.getProperty("user.dir")+"//testData//UsersData.xlsx";
		
			//String path = "C:\\Users\\91968\\Eclipse_New_Workspace\\RestAssured_PetStore_API_Automation\\testData\\UserData.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int rowsnum = xlutil.getRowCount("Sheet1"); //Sheet name = DataSheet
		
        int colscount = xlutil.getCellCount("Sheet1", 1);
        
        String apidata[][]=new String[rowsnum][colscount];
        
        for(int i=1;i<=rowsnum;i++) {
        	for(int j=0;j<colscount;j++) {
        		apidata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
        	}
        	
        }
		
		return apidata;
		
	}

	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException{
		
		//String path = "C:\\Users\\91968\\Eclipse_New_Workspace\\RestAssured_PetStore_API_Automation\\testData\\UserData.xlsx";
		
		
		String path = System.getProperty("user.dir")+"//testData//UsersData.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int rowsnum = xlutil.getRowCount("Sheet1"); //Sheet name = DataSheet
		
        //int colscount = xlutil.getCellCount("Sheet1", 1);
        
        String apidata[]=new String[rowsnum];
        
        for(int i=1;i<=rowsnum;i++) {
        
        		apidata[i-1]=xlutil.getCellData("Sheet1", i, 1);
        	}	
		return apidata;
		
	}
	
}
