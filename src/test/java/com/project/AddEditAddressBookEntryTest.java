package com.project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class AddEditAddressBookEntryTest extends BaseConfig
{

	String addr_first_name = "Onome", addr_last_name = "Emilokan", addr_business = "Onaxsys",
			addr_addr_line_1 = "Fanshawe",addr_addr_line_2="Boulevard", addr_addr_line_3 ="Campus", addr_city ="London",
			addr_region = "Ontario", addr_country="Canada", addr_post_code = "N5W2P6", addr_email_1="onome.e@fanshaweonline.ca",
			addr_email_2="onome.e@fanshaweonline.ca",addr_email_3 = "onome.e@fanshaweonline.ca", addr_phone_1_type ="Mobile",
			addr_phone_1="6472984321", addr_phone_2_type="Work", addr_phone_2="6472981234",addr_phone_3_type="Home",
			addr_phone_3 = "6472982468", addr_web_url_1="www.onaxsys.com", addr_web_url_2="www.fanshawe.ca",
			addr_web_url_3="www.fanshaweonline.ca";	
			
		
	@Test(description = "TAE-001-Add new addressbook contact.", priority = 1)
	void Add_AddressBookContactRecord() {
		driver.get(String.format("%snewEntry.php", baseURL));
		WebElement entryType = driver.findElement(By.xpath("//*[@id='addr_type']"));
		Select dropdown = new Select(entryType);
		dropdown.selectByVisibleText("Friend");
		
		driver.findElement(By.id("addr_first_name")).sendKeys(addr_first_name);		
		driver.findElement(By.id("addr_last_name")).sendKeys(addr_last_name);
		driver.findElement(By.id("addr_business")).sendKeys(addr_business);
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
		
		CaptureScreenShot("Add_AddressBookContactRecord-BeforeSubmission");
		driver.findElement(By.id("submit_button")).submit();
		CaptureScreenShot("Add_AddressBookContactRecord-AfterSubmission");
		
		String confirmationResult = driver.findElement(By.xpath("/html/body/form/div/h2")).getText();
		CaptureScreenShot("Confirmaion of Contact Addition");

		assertEquals("The new address book entry was added successfully", confirmationResult);
	}
	
	@Test(description = "TDC-001-Add dynamically created addressbook contacts and assert entry.", priority = 2)
	void Add_AddressBookDynamicallyCreatedContactRecords() {
		driver.get(String.format("%snewEntry.php", baseURL));
		

		List<AddressBookModel> addressBookList = generatedFakeData;
		
		for(AddressBookModel addressBook : addressBookList) {
			WebElement entryType = driver.findElement(By.xpath("//*[@id='addr_type']"));
			Select dropdown = new Select(entryType);
			dropdown.selectByVisibleText(addressBook.addr_type); //"Friend");
			
			driver.findElement(By.id("addr_first_name")).sendKeys(addressBook.addr_first_name);		
			driver.findElement(By.id("addr_last_name")).sendKeys(addressBook.addr_last_name);
			driver.findElement(By.id("addr_business")).sendKeys(addressBook.addr_business);
			driver.findElement(By.id("addr_addr_line_1")).sendKeys(addressBook.addr_addr_line_1);		
			driver.findElement(By.id("addr_addr_line_2")).sendKeys(addressBook.addr_addr_line_2);
			driver.findElement(By.id("addr_addr_line_3")).sendKeys(addressBook.addr_addr_line_3);
			driver.findElement(By.id("addr_city")).sendKeys(addressBook.addr_city);		
			driver.findElement(By.id("addr_region")).sendKeys(addressBook.addr_region);
			driver.findElement(By.id("addr_country")).sendKeys(addressBook.addr_country);
			driver.findElement(By.id("addr_post_code")).sendKeys(addressBook.addr_post_code);		
			driver.findElement(By.id("addr_email_1")).sendKeys(addressBook.addr_email_1);
			driver.findElement(By.id("addr_email_2")).sendKeys(addressBook.addr_email_2);
			driver.findElement(By.id("addr_email_3")).sendKeys(addressBook.addr_email_3);
			
			(new Select(driver.findElement(By.xpath("//*[@id='addr_phone_1_type']")))).selectByVisibleText(addr_phone_1_type);
			driver.findElement(By.id("addr_phone_1")).sendKeys(addressBook.addr_phone_1);
			
			(new Select(driver.findElement(By.xpath("//*[@id='addr_phone_2_type']")))).selectByVisibleText(addr_phone_2_type);
			driver.findElement(By.id("addr_phone_2")).sendKeys(addressBook.addr_phone_2);
			
			(new Select(driver.findElement(By.xpath("//*[@id='addr_phone_3_type']")))).selectByVisibleText(addr_phone_3_type);
			driver.findElement(By.id("addr_phone_3")).sendKeys(addressBook.addr_phone_3);
			
			driver.findElement(By.id("addr_web_url_1")).sendKeys(addressBook.addr_web_url_1);
			driver.findElement(By.id("addr_web_url_2")).sendKeys(addressBook.addr_web_url_2);
			driver.findElement(By.id("addr_web_url_3")).sendKeys(addressBook.addr_web_url_3);
			
			CaptureScreenShot("Add_AddressBookContactRecord-BeforeSubmission");
			driver.findElement(By.id("submit_button")).submit();
			CaptureScreenShot("Add_AddressBookContactRecord-AfterSubmission");
			
			String confirmationResult = driver.findElement(By.xpath("/html/body/form/div/h2")).getText();
			CaptureScreenShot("Confirmaion of Contact Addition");

			assertEquals("The new address book entry was added successfully", confirmationResult);
			driver.get(String.format("%snewEntry.php", baseURL));
		}
	}

	@Test(description = "TAE-002-Edit_AddressBookContactRecord", priority = 3)
	void Edit_AddressBookContactRecord() {
		driver.get(String.format("%sallList.php",baseURL));	
		String addr_f_name = "Ejiro", addr_l_name = "BAT",
				addr_bus = "CoOp-B-z",addr_email1="professor_xavier@fanshaweonline.ca";
		
		List<WebElement> tBodyContactsList = driver.findElements(By.xpath("/html/body/table/tbody/tr"));
		int recordsCount = tBodyContactsList.size();
		WebElement lastEntry = tBodyContactsList.get(recordsCount -1);
		if(lastEntry != null) {
			lastEntry.findElement(By.xpath("./td[4]/form[2]/input[3]")).click();
		}
		
		WebElement fName = driver.findElement(By.id("addr_first_name"));
		fName.clear(); 
		fName.sendKeys(addr_f_name);
		
		WebElement lName = driver.findElement(By.id("addr_last_name"));
		lName.clear();
		lName.sendKeys(addr_l_name);
		
		WebElement addrBus = driver.findElement(By.id("addr_business"));
		addrBus.clear();
		addrBus.sendKeys(addr_bus);
				
		WebElement addrEmail1 = driver.findElement(By.id("addr_email_1"));
		addrEmail1.clear();
		addrEmail1.sendKeys(addr_email1);
		
		WebElement addrEmail2 = driver.findElement(By.id("addr_email_2"));
		addrEmail2.clear();
		addrEmail2.sendKeys(addr_email1);
		
		WebElement addrEmail3 = driver.findElement(By.id("addr_email_3"));
		addrEmail3.clear();
		addrEmail3.sendKeys(addr_email1);
		
		CaptureScreenShot("Edit_AddressBookContactRecord-BeforeSubmission");
		driver.findElement(By.id("submit_button")).submit();
		CaptureScreenShot("Edit_AddressBookContactRecord-AfterSubmission");
		
		String updateConfirmedText = driver.findElement(By.xpath("/html/body/form/div/h2")).getText().trim();
		
		
		System.out.println(String.format("Check for Address list ContainsRecords. Seed data is minimum of 2 records. Count of entries: \"%s\"\n",recordsCount));
		CaptureScreenShot("EditedRecord-Update COnfirmation");
		assertTrue("The address book entry was updated successfully".equals(updateConfirmedText));	
	}
}
