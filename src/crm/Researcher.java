package crm;

import org.testng.annotations.Test;

import utils.Helper;

public class Researcher extends Helper{
  @Test
  public void rearcher_test() {
	  helper.openBrowser();
	  helper.login_CRM(login.getProperty("researcher_uname"),login.getProperty("researcher_pwd"));
	  //Ajay, continue the code here
  }
}
