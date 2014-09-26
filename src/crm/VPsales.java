package crm;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import utils.Helper;

public class VPsales extends Helper{
  @Test
  public void vp_test() {
	  helper.openBrowser();

	  helper.login_CRM(login.getProperty("vpsales_uname"),login.getProperty("vpsales_pwd"));
	  driver.findElement(By.className("   symbol-close")).click();
	  driver.findElement(By.id("proposalsList")).click();
	 

	 // helper.login_CRM();
	  //Shiva, continue the code here

	  
  }
}
