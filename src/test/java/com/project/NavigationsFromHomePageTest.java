package com.project;

import static org.testng.Assert.*;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.*;

public class NavigationsFromHomePageTest extends BaseConfig
{	
	@Test(description = "TCN-001-Validate homepage has title", priority = 1)
	void testAddressbookHomePageTitle() throws IOException {
		String expectedTitle = "Address Book";		
		driver.get(baseURL);
		String actualTitle = driver.getTitle();
		CaptureScreenShot("HomePage");
		assertEquals(expectedTitle, actualTitle);
	}
	
	@Test(description ="TCN-002-Validate navigation to page for displaying all contacts list entries.")
	void NavigationToContactsListFunctionalityTest() {
		driver.navigate().to(baseURL);
		
		CaptureScreenShot("NavigationToContactsListFunctionalityTest-BeforeSubmission");	
		driver.findElement(By.xpath("/html/body/doctype/ul/li[1]/a")).click();
		CaptureScreenShot("NavigationToContactsListFunctionalityTest-AfterSubmission");
		
		String pageHeaderText = driver.findElement(By.xpath("/html/body/div[1]/h1")).getText();		
		assertEquals("Address Book - All Entries", pageHeaderText.trim());		
	}
	
	@Test(description ="TCN-003-Validate navigation to homepage from add new contact page.")
	void NavigationReturnToHomePageFromAddFunctionalityTest() {
		driver.navigate().to(String.format("%snewEntry.php", baseURL));
		
		CaptureScreenShot("NavigationReturnToHomePageFromAddFunctionalityTest-BeforeSubmission");
		driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr/td[3]/a")).click();
		CaptureScreenShot("NavigationReturnToHomePageFromAddFunctionalityTest-AfterSubmission");
		
		String saveAddressBtnText = driver.findElement(By.xpath("/html/body/doctype/h1")).getText();		
		assertEquals("Address Book Main Menu", saveAddressBtnText.trim());		
	}

	@Test(description ="TCN-004-Validate navigation to homepage from contacts list page.")
	void NavigationReturnToHomePageFromListPageFunctionalityTest() {
		driver.navigate().to(String.format("%sallList.php", baseURL));
		
		CaptureScreenShot("NavigationReturnToHomePageFromAddFunctionalityTest-BeforeSubmission");
		driver.findElement(By.xpath("/html/body/div[2]/a")).click();
		CaptureScreenShot("NavigationReturnToHomePageFromAddFunctionalityTest-AfterSubmission");
		
		String saveAddressBtnText = driver.findElement(By.xpath("/html/body/doctype/h1")).getText();		
		assertEquals("Address Book Main Menu", saveAddressBtnText.trim());		
	}
	
	@Test(description ="TCN-005-Validate navigation to page for adding new contact entry.")
	void NavigationToAddNewPageFunctionalityTest() {
		driver.navigate().to(baseURL);
		
		CaptureScreenShot("NavigationToAddNewPageFunctionalityTest-BeforeSubmission");
		driver.findElement(By.xpath("/html/body/doctype/ul/li[2]/a")).click();
		CaptureScreenShot("NavigationToAddNewPageFunctionalityTest-AfterSubmission");
		
		String saveAddressBtnText = driver.findElement(By.name("submit_button")).getAttribute("value");		
		assertEquals("Save Address", saveAddressBtnText.trim());		
	}
}
