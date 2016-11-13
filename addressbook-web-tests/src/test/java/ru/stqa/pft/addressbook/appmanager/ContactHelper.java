package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Katya on 08.10.2016.
 */
public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void returnToContactPage() {
    click(By.linkText("home"));
  }


  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getTelephonehome());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void deleteSelectedContact() {

    //click(By.xpath("//input[@type='checkbox' or @name='selected[]']"));
    click(By.xpath("//input[@type='button'][@value='Delete']"));;
    wd.switchTo().alert().accept();
  }

  public void selectContact(int index) {
    wd.findElements(By.xpath("//input[@type='checkbox'][@name='selected[]']")).get(index).click();
    //click(By.xpath("//input[@type='checkbox'][@name='selected[]']"));
  }

  public void initContactModification(int index) {
wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
    //click(By.xpath("//img[@title='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact,boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    returnToContactPage();

  }
  public void modifyContact(int index, ContactData contact) {
    selectContact(index);
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
    returnToContactPage();
  }
  public void delete(int index) {
   selectContact(index);
   deleteSelectedContact();
   returnToContactPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//input[@type='checkbox'][@name='selected[]']"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//input[@type='checkbox'][@name='selected[]']")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    //List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));

      //String conLastName = element.findElement(By.xpath("//td[4]")).getText();
      //String conLastName = element.findElement(By.tagName("td")).getText();
      //String conName = element.findElement(By.tagName("td")).getText();
      ContactData contact = new ContactData(id, name, null, null, null, null, null); // создаем объект типа contactDate
      contacts.add(contact); // добавляем созданный объект в список
    }

    return contacts;
  }
}
