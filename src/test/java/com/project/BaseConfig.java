package com.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class BaseConfig
{
	
	static WebDriver driver;
	static String baseURL = "http://localhost/addressbook/";
	static String screenshotDirectory;
	static List<AddressBookModel> generatedFakeData;
	
	@BeforeClass
	static void setup() {
		String currentDir = System.getProperty("user.dir");
	      String fullPath = Paths.get(currentDir, "drivers","X64", "chromedriver-win32.exe").toString();
	      screenshotDirectory = Paths.get(currentDir, "screenshots").toString();
	      generatedFakeData = FetchCsvData(null);
	      
	      System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
	      System.setProperty("webdriver.chrome.driver", fullPath);
	      ChromeOptions chromeOptions = new ChromeOptions();
	      chromeOptions.addArguments("--headless"); 
	      
	      driver = new ChromeDriver(chromeOptions);
	      driver.manage().window().maximize();
	}
	
	@AfterTest
	static void TearDown() {
		driver.close();
	}
	
	void CaptureScreenShot(String screenshotName){
		try {
			 // Get the current date
      Calendar calendar = Calendar.getInstance();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      String formattedDate = formatter.format(calendar.getTime());
      
			// Generate image name using combination of image name, Current date and unique GUID
			screenshotName = String.format("%s-%s-%s.png",screenshotName,formattedDate,UUID.randomUUID().toString());
			TakesScreenshot screenshot = ((TakesScreenshot)driver);
			File screenFile = screenshot.getScreenshotAs(OutputType.FILE);
			
			Path path = Paths.get(screenshotDirectory);
	    boolean directoryExists = Files.exists(path) && Files.isDirectory(path);
	    if(!directoryExists) {
	    	try {
	        Files.createDirectories(path);
	        System.out.println("Directory created successfully.");
			    } catch (IOException e) {
			        System.err.println("Failed to create directory: " + e.getMessage());
			    }
	    }		
			File destFile = new File(Paths.get(screenshotDirectory,screenshotName).toString());
			ImageIO.write(ImageIO.read(screenFile), "png", destFile);
			System.out.println(String.format("Screenshot \"%s\" captured.", screenshotName));
		}catch(Exception ex) {
			System.err.println("Exception: " + ex.getMessage());
		}
		
	}
	
	
	/**
	 * Purpose: Method to read all records from data generated using generatedata application running on localhost 
	 */
	private static List<AddressBookModel> FetchCsvData(String filePathOverride) {
		String defaultFilePath = Paths.get(System.getProperty("user.dir"),"src","test", "resources","data.csv").toString();
		if(filePathOverride != null && filePathOverride.isBlank() == false) {
			defaultFilePath = filePathOverride;
		}
		List<AddressBookModel> addressBookList = new ArrayList<AddressBookModel>();
		try (Reader reader = new FileReader(defaultFilePath)) {
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
			for (CSVRecord csvRecord : csvParser) {
				AddressBookModel addressBook = new AddressBookModel();
				addressBook.addr_first_name = csvRecord.get("addr_first_name");
				addressBook.addr_last_name = csvRecord.get("addr_last_name");
				addressBook.addr_business = csvRecord.get("addr_business");
				addressBook.addr_addr_line_1 = csvRecord.get("addr_addr_line_1");
				addressBook.addr_addr_line_2 = csvRecord.get("addr_addr_line_2");
				addressBook.addr_addr_line_3 = csvRecord.get("addr_addr_line_3");
				addressBook.addr_city = csvRecord.get("addr_city");
				addressBook.addr_region = csvRecord.get("addr_region");
				addressBook.addr_country = csvRecord.get("addr_country");
				addressBook.addr_post_code = csvRecord.get("addr_post_code");
				addressBook.addr_email_1 = csvRecord.get("addr_email_1");
				addressBook.addr_email_2 = csvRecord.get("addr_email_2");
				addressBook.addr_email_3 = csvRecord.get("addr_email_3");
				addressBook.addr_phone_1_type = csvRecord.get("addr_phone_1_type");
				addressBook.addr_phone_1 = csvRecord.get("addr_phone_1");
				addressBook.addr_phone_2_type = csvRecord.get("addr_phone_2_type");
				addressBook.addr_phone_2 = csvRecord.get("addr_phone_2");
				addressBook.addr_phone_3_type = csvRecord.get("addr_phone_3_type");
				addressBook.addr_phone_3 = csvRecord.get("addr_phone_3");
				addressBook.addr_web_url_1 = csvRecord.get("addr_web_url_1");
				addressBook.addr_web_url_2 = csvRecord.get("addr_web_url_2");
				addressBook.addr_web_url_3 = csvRecord.get("addr_web_url_3");
				addressBook.addr_type = csvRecord.get("addr_type");
				addressBookList.add(addressBook);
			}
			csvParser.close();
		} catch (FileNotFoundException e) {
			// Handle file not found exception
			e.printStackTrace();
		} catch (IOException e) {
			// Handle IO exception
			e.printStackTrace();
		}
		return addressBookList;
	}
}
