package crm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

public class TestBase {
  
	public static WebDriver driver;
	public static Properties config = null;
	public static Properties OR = null;
	public static Properties login = null;
	public static Random random = new Random();
	public String Basedir = System.getProperty("user.dir");
	
  @BeforeSuite
  public void BeforeSuite() throws IOException{
  config = new Properties();
  OR = new Properties();
  login = new Properties();
  
  FileInputStream fp1 = new FileInputStream(Basedir + "\\src\\ConfigFiles\\config.properties");
  config.load(fp1);
  FileInputStream fp2 = new FileInputStream(Basedir + "\\src\\ConfigFiles\\OR.properties");
  OR.load(fp2);
  FileInputStream fp3 = new FileInputStream(Basedir + "\\src\\ConfigFiles\\login.properties");
  login.load(fp3);
  } 
}