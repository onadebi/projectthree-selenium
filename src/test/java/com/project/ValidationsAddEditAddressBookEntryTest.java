package com.project;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
public class ValidationsAddEditAddressBookEntryTest extends BaseConfig
{
	String addr_first_name = "Onome", addr_last_name = "Emilokan", addr_business = "Onaxsys",
			addr_addr_line_1 = "Fanshawe",addr_addr_line_2="Boulevard", addr_addr_line_3 ="Campus", addr_city ="London",
			addr_region = "Ontario", addr_country="Canada", addr_post_code = "N5W2P6", addr_email_1="onome.e@fanshaweonline.ca",
			addr_email_2="onome.e@fanshaweonline.ca",addr_email_3 = "onome.e@fanshaweonline.ca", addr_phone_1_type ="Mobile",
			addr_phone_1="6472984321", addr_phone_2_type="Work", addr_phone_2="6472981234",addr_phone_3_type="Home",
			addr_phone_3 = "6472982468", addr_web_url_1="www.onaxsys.com", addr_web_url_2="www.fanshawe.ca",
			addr_web_url_3="www.fanshaweonline.ca";	
			
		
	@Test(description = "TVAE-001-Validation of new addressbook contact, not having any record of Names, or Business name.", priority = 1)
	void Add_Validate_NamesInfo_AddressBookContactRecord(){
		driver.get(String.format("%snewEntry.php", baseURL));
		
		WebElement entryType = driver.findElement(By.xpath("//*[@id='addr_type']"));
		Select dropdown = new Select(entryType);
		dropdown.selectByVisibleText("Friend");
		
		driver.findElement(By.id("addr_addr_line_1")).sendKeys(addr_addr_line_1);		
		driver.findElement(By.id("addr_addr_line_2")).sendKeys(addr_addr_line_2);
		driver.findElement(By.id("addr_addr_line_3")).sendKeys(addr_addr_line_3);
		driver.findElement(By.id("addr_city")).sendKeys(addr_city);		
		driver.findElement(By.id("addr_region")).sendKeys(addr_region);
		driver.findElement(By.id("addr_country")).sendKeys(addr_country);
		driver.findElement(By.id("addr_post_code")).sendKeys(addr_post_code);		
		driver.findElement(By.id("addr_email_1")).sendKeys(addr_email_1);
		driver.findElement(By.id("addr_email_2")).sendKeys(addr_email_2);
		driver.findElement(By.id("addr_email_3")).sendKeys(addr_email_3);
		
		(new Select(driver.findElement(By.xpath("//*[@id='addr_phone_1_type']")))).selectByVisibleText(addr_phone_1_type);
		driver.findElement(By.id("addr_phone_1")).sendKeys(addr_phone_1);
		
		(new Select(driver.findElement(By.xpath("//*[@id='addr_phone_2_type']")))).selectByVisibleText(addr_phone_2_type);
		driver.findElement(By.id("addr_phone_2")).sendKeys(addr_phone_2);
		
		(new Select(driver.findElement(By.xpath("//*[@id='addr_phone_3_type']")))).selectByVisibleText(addr_phone_3_type);
		driver.findElement(By.id("addr_phone_3")).sendKeys(addr_phone_3);
		
		driver.findElement(By.id("addr_web_url_1")).sendKeys(addr_web_url_1);
		driver.findElement(By.id("addr_web_url_2")).sendKeys(addr_web_url_2);
		driver.findElement(By.id("addr_web_url_3")).sendKeys(addr_web_url_3);
		
		CaptureScreenShot("Add_Validate_NamesInfo_AddressBookContactRecord-BeforeSubmission");
		driver.findElement(By.id("submit_button")).submit();
		CaptureScreenShot("Add_Validate_NamesInfo_AddressBookContactRecord-AfterSubmission");
		
		
		WebElement errorDisplay = driver.findElement(By.xpath("/html/body/p"));
		String errorMessage = errorDisplay.getText();
		System.out.println(String.format("ValidationErrorDisplay-message: %s",errorMessage));
		assertEquals("An person's name or business name must be specified.", errorMessage);
	}
	
	
@Test(description = "TVAE-002-Validation of new addressbook contact, not having any record of address, email, website, or phone.")
void Add_Validate_ContactsInfo_AddressBookContactRecord() {
	driver.get(String.format("%snewEntry.php", baseURL));
	
	WebElement entryType = driver.findElement(By.xpath("//*[@id='addr_type']"));
	Select dropdown = new Select(entryType);
	dropdown.selectByVisibleText("Friend");
	
	driver.findElement(By.id("addr_first_name")).sendKeys(addr_first_name);		
	driver.findElement(By.id("addr_last_name")).sendKeys(addr_last_name);
	driver.findElement(By.id("addr_business")).sendKeys(addr_business);	
	
	CaptureScreenShot("Add_Validate_ContactsInfo_AddressBookContactRecord_BeforeSubmission");
	driver.findElement(By.id("submit_button")).submit();
	CaptureScreenShot("Add_Validate_ContactsInfo_AddressBookContactRecord_AfterSubmission");

	String confirmationResult = driver.findElement(By.xpath("/html/body/p")).getText();

	assertEquals("At least one of the following must be entered: street/mailing address, email address, phone number or web site url.", confirmationResult);
}

		
	@Test(description = "TVAE-003-Validation of Edit of addressbook contact, not having any record of Names, or Business name.", priority = 2)
	void Edit_Validate_NamesInfo_AddressBookContactRecord()  {
		driver.get(String.format("%sallList.php",baseURL));	
		String addr_email1="professor_xavier@fanshaweonline.ca";
		
		List<WebElement> tBodyContactsList = driver.findElements(By.xpath("/html/body/table/tbody/tr"));
		int recordsCount = tBodyContactsList.size();
		WebElement lastEntry = tBodyContactsList.get(recordsCount -1);
		if(lastEntry != null) {
			lastEntry.findElement(By.xpath("./td[4]/form[2]/input[3]")).click();
		}
		
		driver.findElement(By.id("addr_first_name")).clear();
		
		driver.findElement(By.id("addr_last_name")).clear();
		
		driver.findElement(By.id("addr_business")).clear();
				
		WebElement addrEmail1 = driver.findElement(By.id("addr_email_1"));
		addrEmail1.clear();
		addrEmail1.sendKeys(addr_email1);
		
		WebElement addrEmail2 = driver.findElement(By.id("addr_email_2"));
		addrEmail2.clear();
		addrEmail2.sendKeys(addr_email1);
		
		WebElement addrEmail3 = driver.findElement(By.id("addr_email_3"));
		addrEmail3.clear();
		addrEmail3.sendKeys(addr_email1);
		
		CaptureScreenShot("Edit_Validate_NamesInfo_AddressBookContactRecord_BeforeSubmission");
		driver.findElement(By.id("submit_button")).click();
		CaptureScreenShot("Edit_Validate_NamesInfo_AddressBookContactRecord_AfterSubmission");
		WebElement errorDisplay = driver.findElement(By.xpath("/html/body/p"));
		String errorMessage = errorDisplay.getText();
		System.out.println(String.format("ValidationErrorDisplay-message: %s",errorMessage));
		assertEquals("An person's name or business name must be specified.", errorMessage);
	}
	
	@Test(description = "TVAE-004-Validation of Edit of addressbook contact, not having any record of address, email, website, or phone.", priority = 2)
	void Edit_Validate_ContactsInfo_AddressBookContactRecord() throws IOException, InterruptedException {
		driver.get(String.format("%sallList.php",baseURL));	
		
		List<WebElement> tBodyContactsList = driver.findElements(By.xpath("/html/body/table/tbody/tr"));
		int recordsCount = tBodyContactsList.size();
		WebElement lastEntry = tBodyContactsList.get(recordsCount -1);
		if(lastEntry != null) {
			lastEntry.findElement(By.xpath("./td[4]/form[2]/input[3]")).click();
		}
		
		driver.findElement(By.id("addr_addr_line_1")).clear();		
		driver.findElement(By.id("addr_addr_line_2")).clear();	
		driver.findElement(By.id("addr_addr_line_3")).clear();	
		driver.findElement(By.id("addr_city")).clear();	
		driver.findElement(By.id("addr_region")).clear();	
		driver.findElement(By.id("addr_country")).clear();	
		driver.findElement(By.id("addr_post_code")).clear();			
		driver.findElement(By.id("addr_email_1")).clear();	
		driver.findElement(By.id("addr_email_2")).clear();	
		driver.findElement(By.id("addr_email_3")).clear();	
		driver.findElement(By.id("addr_phone_1")).clear();	
		driver.findElement(By.id("addr_phone_2")).clear();	
		driver.findElement(By.id("addr_phone_3")).clear();	
		driver.findElement(By.id("addr_web_url_1")).clear();	
		driver.findElement(By.id("addr_web_url_2")).clear();	
		driver.findElement(By.id("addr_web_url_3")).clear();
		
		
		CaptureScreenShot("Edit_Validate_ContactsInfo_AddressBookContactRecord-BeforeSubmission");
		driver.findElement(By.id("submit_button")).submit();
		CaptureScreenShot("Edit_Validate_ContactsInfo_AddressBookContactRecord-AfterSubmission");
		
		WebElement errorDisplay = driver.findElement(By.xpath("/html/body/p"));
		String errorMessage = errorDisplay.getText();
		System.out.println(String.format("ValidationErrorDisplay-message: %s",errorMessage));
		assertEquals("At least one of the following must be entered: street/mailing address, email address, phone number or web site url.", errorMessage.trim());
	}
	
	
}
