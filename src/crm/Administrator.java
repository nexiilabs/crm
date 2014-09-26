package crm;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utils.Helper;
public class Administrator extends Helper{
	Administrator run;
	int i =0;
	
	@Test
	public void admin_test() throws Exception{
		run = new Administrator();
		run.create_user();
		run.update_user();
	}
	
	//User creation
	public void create_user() throws Exception{
	helper.openBrowser();
	driver.manage().window().maximize();
	helper.login_CRM(login.getProperty("admin_uname"),login.getProperty("admin_pwd"));
	List<WebElement> admin_menu =driver.findElement(By.id("tree_menu")).findElements(By.cssSelector("li.close"));
	for(int i = 0; i < admin_menu.size(); i++)
	    admin_menu.get(i).findElement(By.cssSelector("span.symbol-close")).click();
	driver.findElement(By.id("createUser")).click();
	helper.sleep(2);
	driver.findElement(By.name("firstname")).sendKeys("Ajay");
	driver.findElement(By.name("lastname")).sendKeys("Kovuri");
	driver.findElement(By.name("email")).sendKeys("kovuriajay@nexiilabs.com");
	driver.findElement(By.name("empid")).sendKeys("NEX0067");
	List<WebElement> user_mgr = driver.findElement(By.name("manager")).findElements(By.tagName("option"));
	helper.random_click(user_mgr, 1);
	List<WebElement> user_role = driver.findElement(By.name("role")).findElements(By.tagName("option"));
	helper.random_click(user_role, 1);
	driver.findElement(By.id("registerbutton")).submit();
	helper.sleep(4);
	while(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("please select manager")) {
		helper.random_click(user_mgr, 1);	
		driver.findElement(By.id("registerbutton")).submit();
		helper.sleep(4);
	}
	while(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("please select role")) {
		helper.random_click(user_role, 1);
		driver.findElement(By.id("registerbutton")).submit();
		helper.sleep(4);
	}
	while(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("please select service")) {
		List<WebElement> architect_service = driver.findElement(By.name("service")).findElements(By.tagName("option"));
		helper.random_click(architect_service, 1);
		driver.findElement(By.id("registerbutton")).submit();
		helper.sleep(4);
	}
	while(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("already exists")){
		helper.sleep(3);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("kovuriajay"+i+""+"@nexii.com");
		helper.sleep(3);
		driver.findElement(By.id("registerbutton")).submit();
		helper.sleep(3);
	i++;
	}
	helper.sleep(4);
	if(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("successfully"))
		System.out.println("user created successfully");
	else Assert.fail("File a bug: Unable to create the user");
	driver.close();
	}
	
	//Updating the user
	public void update_user(){
		helper.openBrowser();
		driver.manage().window().maximize();
		helper.login_CRM(login.getProperty("admin_uname"),login.getProperty("admin_pwd"));
		List<WebElement> admin_menu =driver.findElement(By.id("tree_menu")).findElements(By.cssSelector("li.close"));
		for(int i = 0; i < admin_menu.size(); i++)
		    admin_menu.get(i).findElement(By.cssSelector("span.symbol-close")).click();
		driver.findElement(By.id("updateUser")).click();
		helper.sleep(3);
		List<WebElement> rows_perpage = driver.findElement(By.id("example_length")).findElements(By.tagName("option"));
		rows_perpage.get(3).click();
		helper.sleep(5);
		List<WebElement> total_users = driver.findElements(By.className("sorting_1"));
		for (int i = 0; i < 10; i++) {
			helper.sleep(20);
			List<WebElement> rows_perpage1 = driver.findElement(By.id("example_length")).findElements(By.tagName("option"));
			rows_perpage1.get(3).click();
			driver.findElement(By.id(total_users.get(i).getText())).click();
			helper.sleep(4);
			driver.findElement(By.name("firstname")).sendKeys(" modified ");
			driver.findElement(By.name("lastname")).sendKeys(" modified ");
			List<WebElement> user_mgr = driver.findElement(By.name("manager")).findElements(By.tagName("option"));
			helper.random_click(user_mgr, 1);
			List<WebElement> user_role = driver.findElement(By.name("role")).findElements(By.tagName("option"));
			helper.random_click(user_role, 1);
			driver.findElement(By.id("updatebutton")).submit();
			helper.sleep(30);
			System.out.println("get text: "+driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText());
			while(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("please select manager")) {
				helper.random_click(user_mgr, 1);	
				driver.findElement(By.id("updatebutton")).submit();
				helper.sleep(5);
			}
			while(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("please select role")) {
				helper.random_click(user_role, 1);
				driver.findElement(By.id("updatebutton")).submit();
				helper.sleep(5);
			}
			while(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("please select service")) {
				List<WebElement> architect_service = driver.findElement(By.name("service")).findElements(By.tagName("option"));
				helper.random_click(architect_service, 1);
				driver.findElement(By.id("updatebutton")).submit();
				helper.sleep(5);
			}
		
		helper.sleep(10);
		System.out.println("success");
		if(driver.findElement(By.id("result_msg_div")).findElement(By.tagName("label")).getText().toLowerCase().contains("successfully")){
			System.out.println("user updated successfully");
			driver.findElement(By.cssSelector("button.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-icon-only.ui-dialog-titlebar-close")).click();
		}	
		else Assert.fail("File a bug: Unable to update the user");
		
		}
		driver.close();
	}
}