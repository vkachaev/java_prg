package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    type(By.name("home"),contactData.getHomePhone());

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

    click(By.xpath("//input[@type='button'][@value='Delete']"));;
    wd.switchTo().alert().accept();
  }

  public int count() {
    return wd.findElements(By.xpath("//input[@type='checkbox'][@name='selected[]']")).size();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void createContact(ContactData contact,boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    contactCache =null;
    returnToContactPage();

  }
  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache =null;
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    contactCache =null;
    returnToContactPage();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache !=null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : rows){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      String address = cells.get(3).getText();
      String[] email = cells.get(4).getText().split("\n");
      //String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactCache.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname)
              .withHome(phones[0])
              .withMobile(phones[1])
              .withWork(phones[2])
              .withAddress(address)
              .withEmail1(email[0])
              .withEmail2(email[1])
              .withEmail3(email[2])); // добавляем созданный объект в список
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withLastname(lastname).withFirstname(firstname).withHome(home).withMobile(mobile).withWork(work)
            .withAddress(address)
            .withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    //wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  }

  //unused
  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//input[@type='checkbox'][@name='selected[]']"));
  }

  public void selectContact(int index) {
    wd.findElements(By.xpath("//input[@type='checkbox'][@name='selected[]']")).get(index).click();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    returnToContactPage();
  }

}
