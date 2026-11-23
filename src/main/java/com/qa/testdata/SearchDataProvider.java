package com.qa.testdata;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {
	
	@DataProvider(name = "searchProducts")
	public Object[][] searchProducts()
	{
		return new Object[][]
				{
			 	{"Blue Top"},
	            {"Men Tshirt"},
	            {"Sleeveless Dress"},
	            {"Winter Top"},
	            {"Madame Top"}
		};
	}
}
