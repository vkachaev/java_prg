package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void deleteGroupFromContact(GroupData myGroup) {
    click(By.xpath("//input[@name='remove']"));
    wd.findElement(By.xpath("//a[@href='./?group=" + myGroup.getId() + "']")).click();
  }
  public void addToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    click(By.xpath("//select[@name='to_group']"));
    click(By.xpath("//select[@name='to_group']/option[@value='" + group.getId()+ "']"));
    click(By.xpath("//input[@type='submit'][@value='Add to']"));

    //wd.findElement(By.xpath("//a[@href='./?group=" + group.iterator().next().getId() + "']")).click();
    //wd.findElement(By.cssSelector("input[group='" + contact.getId() + "']")).click();
  }

  public void setGroupFilter(ContactData contact, GroupData myGroup) {
      wd.findElement(By.xpath("//select[@name='group']")).click();
      wd.findElement(By.xpath("//select[@name='group']/option[@value='"+ myGroup.getId() + "']")).click();
      selectContactById(contact.getId());
  }

  public void returnToContactPageAfterGroupAdded(GroupData myGroup) {
    wd.findElement(By.xpath("//a[@href='./?group=" + myGroup.getId() + "']")).click();
  }

  public String getGroupFromHomePage(GroupData myGroup) {
    String s = wd.findElement(By.xpath("//select[@name='group']/option[1]")).getText();

    return s;
    //click(By.xpath("//select[@name='group']"));
    //click(By.xpath("//select[@name='group']/option[@value='" + myGroup.getId()+ "']"));
    //return result.iterator().next();
  }


  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomePhone());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("email"),contactData.getEmail1());
    type(By.name("email2"),contactData.getEmail2());
    type(By.name("email3"),contactData.getEmail3());
    //attach(By.name("photo"), contactData.getPhoto());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    }
    else {
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
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allemails = cells.get(4).getText();
      //String allcontacts = firstname + lastname + address + allPhones;
      //String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contactCache.add(new ContactData().withId(id).withLastname(lastname).withFirstname(firstname)
              .withAllPhones(allPhones)
              .withAddress(address)
              .withAllEmails(allemails));
              //.withAllcontacts(allcontacts)); // добавляем созданный объект в список
    }
    return new Contacts(contactCache);
  }

  public Set<Integer> alllinkedtogroup() {
    Set<Integer> result = new HashSet<Integer>();
    if (contactCache !=null){
      return result;
    }
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : rows){
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      result.add(id);
    }
    return result;
  }


  public ContactData infoFromDetails(ContactData contact) {
    initContactDetailById(contact.getId());
    String contactdetail = wd.findElement(By.xpath("//div[@id='content']")).getText();
    wd.navigate().back();
    return new ContactData().withContactdetails(contactdetail);
  }

  private void initContactDetailById(int id) {
    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[7]/a", id))).click();
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
