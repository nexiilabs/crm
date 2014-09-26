package utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

import crm.TestBase;

public class Helper extends TestBase {
   
    public static Helper helper = new Helper();
	
	public void openBrowser(){
				
		if(config.getProperty("browserType").equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
		}
		else if(config.getProperty("browserType").equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", Basedir + "\\src\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(config.getProperty("browserType").equalsIgnoreCase("internetexplorer")){
			System.setProperty("webdriver.ie.driver", Basedir + "\\src\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		driver.get(config.getProperty("appURL"));	
	}
	public void login_CRM(String uname, String pwd){
		//helper.openBrowser();
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.cssSelector("p.login.button")).findElement(By.tagName("input")).submit();
		
	}
	
	public void sleep(int seconds){
		try {
			Thread.sleep(seconds*1000);
	    	} catch (InterruptedException e) {
			e.printStackTrace();
	    	}
	  }
	
	public boolean waitToLoadForElement(int timeout, By by){
		int k=timeout;
		while (timeout>0){
			sleep(1);
		List<WebElement> list = driver.findElements(by);
		  if(list.size()!=0){
			return true;
		  }
		timeout--;
		}
	Reporter.log("Waited for"+k+"minutes but "+by+" not found");	
	  return false;
	}
		
	public void  takeScreenshot(String filename, int i){
		  File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  try {
			FileUtils.copyFile(scrfile, new File(Basedir+"\\src\\screenshots\\"+filename+":"+i+".jpg"));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	  }
	public void iterator_click(String locator){
		   List<WebElement> list = driver.findElements(By.id(locator));
		   Iterator<WebElement> iterator = list.iterator();
		   while(iterator.hasNext())
				iterator.next().click();
	   }
	public void random_click(List<WebElement> list, int k){
		   for(int j = 0; j < k; j++){
			   int i = random.nextInt(list.size());
			   list.get(i).click();
		   }
}
}