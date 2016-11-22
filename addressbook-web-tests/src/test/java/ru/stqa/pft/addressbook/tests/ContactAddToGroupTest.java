package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Katya on 18.11.2016.
 */
public class ContactAddToGroupTest extends TestBase {
  @BeforeTest
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0){
      app.goTo().contactPage();
      app.contact().createContact(new ContactData().withLastname("Kachaev update").withFirstname("Vladimir update").inGroups(groups.iterator().next()),true);
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
  }
  @Test
  public void testContactAddToGroup(){

    Groups group = app.db().groups();
    Contacts before = app.db().contacts();
//   ContactData modifiedContact = before.iterator().next();
  //  Groups existingGroups = modifiedContact.getGroups();

    GroupData myGroup = null;
    ContactData contact = null;
    for(GroupData g : group) {
      boolean found = false;
      ContactData modifiedContact = before.iterator().next();
      Groups existingGroups = modifiedContact.getGroups();

      for(GroupData e: existingGroups) {
        if (e.getId() == g.getId()) {
          found = true;
          System.out.println(found);
          contact = modifiedContact;
          break;
        }
      }
      if (!found) {
        myGroup = g;
        contact = modifiedContact;
        System.out.println(found);
        break;
      }
    }

    if (myGroup == null) {
      //создать новую группу
       app.goTo().groupPage();
       app.group().create(new GroupData().withName("Test2"));
       app.goTo().contactPage();
      Groups after = app.db().groups();
      myGroup = new GroupData().withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()).withName("Test2");
      ContactData contactwithnogroup = new ContactData().withId(contact.getId())
              .inGroups(myGroup);
    }
    app.contact().addToGroup(contact, myGroup);

    //check#1 added group in db equals with ui by clicking on group_id in ui
    app.contact().returnToContactPageAfterGroupAdded(myGroup);

    //check#2 проверить что в базе появился связанный контакт
    Groups newGroups = app.db().contactById(contact.getId()).getGroups();
    GroupData dbGroup  = app.db().groups(myGroup.getId());

    boolean ok = false;
    for(GroupData g: newGroups) {
      if (g.getId() == myGroup.getId()) {
        ok = true;
        break;
      }
    }
    Assert.assertTrue(ok);
    System.out.println("Verified that group with name" + myGroup.getName() + " and id " + myGroup.getId() + " was added to contact");
    String groupFromHomePage = app.contact().getGroupFromHomePage(myGroup);
    Assert.assertEquals(groupFromHomePage, myGroup.getName());


    //check#3 my contact in list equals with db
    Set<Integer> uiContacts = app.contact().alllinkedtogroup();
    Set<Integer> dbContacts = new HashSet<Integer>();

    for(ContactData c: dbGroup.getContacts()) {
      dbContacts.add(c.getId());
    }

    boolean ok2 = uiContacts.containsAll(dbContacts) && dbContacts.containsAll(uiContacts);
  }
}
