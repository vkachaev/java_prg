package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Katya on 22.11.2016.
 */
public class ContactDelFromGroupTest extends TestBase {
  @Test
  public void testContactDelFromGroup(){

    //получить любую группу у которой есть контакт
    Groups group = app.db().groups();
    Contacts before = app.db().contacts();
    GroupData myGroup = null;
    ContactData contact = null;

    for(GroupData g : group) {
      ContactData modifiedContact = before.iterator().next();
      Groups existingGroups = modifiedContact.getGroups();
      boolean found = false;
      for(GroupData e: existingGroups) {
        if (e.getId() == g.getId()) {
          found = true;
          System.out.println(found);
          myGroup = g;
          contact = modifiedContact;
          break;
        }
      }
      if (!found) {
        System.out.println(found);
        contact = modifiedContact;
        break;
      }
    }

    //если групп нет заасайненых на контакт то создать группу
    if (myGroup == null) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test2"));
      app.goTo().contactPage();
      Groups after = app.db().groups();
      myGroup = new GroupData().withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()).withName("Test2");
      ContactData contactwithgroup = new ContactData().withId(contact.getId())
              .inGroups(myGroup);
      app.contact().addToGroup(contact, myGroup);
      app.contact().returnToContactPageAfterGroupAdded(myGroup);
    }

    //выбрать из списка эту группу
    //выбрать любой контакт из списка контактов
    app.contact().setGroupFilter(contact, myGroup);

    //удалить группу из контакта
    //перейти после подтверждения на хомяк
    app.contact().deleteGroupFromContact(myGroup);


    //проверить что в базе нет связанного контакта
    Groups newGroups = app.db().contactById(contact.getId()).getGroups();
    GroupData dbGroup  = app.db().groups(myGroup.getId());

    boolean ok = true;
    for(GroupData g: newGroups) {
      if (g.getId() == myGroup.getId()) {
        ok = false;
        break;
      }
    }
    Assert.assertTrue(ok);
    System.out.println("Verified that group with name" + myGroup.getName() + " and id " + myGroup.getId() + " was deleted from contact");

    //проверить что в ui нет контакта с удаленной группой
    //check#2 my contact in list equal from db
    Set<Integer> uiContacts = app.contact().alllinkedtogroup();
    Set<Integer> dbContacts = new HashSet<Integer>();


    for(ContactData c: dbGroup.getContacts()) {
      dbContacts.add(c.getId());
    }

    boolean ok2 = uiContacts.containsAll(dbContacts) && dbContacts.containsAll(uiContacts);
}
}
