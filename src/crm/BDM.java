package crm;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.Helper;

public class BDM extends Helper{
  
	@BeforeMethod
	public void BDM_test_login()
	{
		 helper.openBrowser();
		 helper.sleep(3);
		 helper.login_CRM(login.getProperty("bdm_uname"), login.getProperty("bdm_pwd"));
	}
 
	@Test   
	public void aBDM_test_expand_close_menu() 
	{
	  List<WebElement> lis = driver.findElement(By.className("menu")).findElements(By.className("close"));
	  helper.sleep(3);
	  System.out.println(lis.size());
	  helper.sleep(1);
	  for (int i = 0; i < lis.size(); i++)
	  {
	    System.out.println("Expanding::"+lis.get(i).getText());
		lis.get(i).findElement(By.className("    symbol-close")).click();
		helper.sleep(1);
	  }
	  helper.sleep(3);
	  List<WebElement> lis2 = driver.findElement(By.className("menu")).findElements(By.className("open"));
	  helper.sleep(1);
	  System.out.println(lis2.size());
	  helper.sleep(1);
	  for (int i = 0; i < lis2.size(); i++)
	  {
	    System.out.println("Closing::"+lis2.get(i).getText());
		lis.get(i).findElement(By.className("     symbol-open")).click();
		helper.sleep(1);
	  }  
	  driver.close();
    }
	
	@Test
	public void bBDM_assign_lead()
	{
		List<WebElement> lis = driver.findElement(By.className("menu")).findElements(By.className("close"));
		  helper.sleep(1);
		  System.out.println(lis.size());
		  
		  lis.get(0).findElement(By.className("    symbol-close")).click();
		  helper.sleep(1);
		  driver.findElement(By.id("assignlead")).click();
		  helper.sleep(2);
		  
		  List<WebElement> lis3 = driver.findElement(By.name("service")).findElements(By.tagName("option"));
		  for (int i = 0; i < lis3.size(); i++)
		  {
			System.out.println("----------"+lis3.get(i).getText()+"----------");
			new Select(driver.findElement(By.name("service"))).selectByIndex(i);
			helper.sleep(2);
			
			if(driver.findElement(By.className("button")).isDisplayed())
			{
				System.out.println("Submit button displayed");
			}
			else
			{
				System.out.println("Submit button not displayed");
			}
			
			if (driver.findElement(By.id("result_table")).findElements(By.id("container")).size()!=0)
			{
				System.out.println("Service table available");
			}
			else
			{
				System.out.println("Service table not available");
			}
		  }
	
	}
	

}
