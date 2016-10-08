package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Katya on 08.10.2016.
 */
public class ApplicationManager {
  FirefoxDriver wd;
  // we did reference from class applicationManager to class SessionHelper
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    wd = new FirefoxDriver(); //initialize attribute of object
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd); // initialize reference in init method
    sessionHelper.login("admin", "secret");
  }



  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
