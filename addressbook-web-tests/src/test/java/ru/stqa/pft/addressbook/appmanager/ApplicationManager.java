package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Katya on 08.10.2016.
 */
public class ApplicationManager{


  private final Properties properties;
  WebDriver wd;
  // we did reference from class applicationManager to class SessionHelper
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    //создаем объект типа properties и сохраняем его в поле этого класса
    properties = new Properties();
  }



  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    //инициализируем соединение к базе
    dbHelper = new DbHelper();
    //либо читаем target.properties либо local.properties
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    if (Objects.equals(browser, BrowserType.FIREFOX)){
      wd = new FirefoxDriver(); //initialize attribute of object
    } else if (Objects.equals(browser, BrowserType.CHROME)){
      wd = new ChromeDriver(); //initialize attribute of object
    } else if (Objects.equals(browser, BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }


    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd); // initialize reference in init method
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }



  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
  //метод который будет возвращать помошника
  public DbHelper db(){return dbHelper;}
}
