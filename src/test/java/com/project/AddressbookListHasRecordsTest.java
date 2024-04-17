package com.project;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AddressbookListHasRecordsTest extends BaseConfig
{
	@Test(description = "THR-001")
	void testAddressContainsContactRecords() throws IOException {	
		driver.get(String.format("%sallList.php",baseURL));		
		
		List<WebElement> tBodyContactsList = driver.findElements(By.xpath("/html/body/table/tbody/tr"));
		int recordsCount = tBodyContactsList.size();
		System.out.println(String.format("Check for Address list ContainsRecords. Seed data is minimum of 2 records. Count of entries: \"%s\"\n",recordsCount));
		CaptureScreenShot("Check for Address list ContainsRecords");
		Assert.assertTrue(recordsCount >= 2);
	}

}
