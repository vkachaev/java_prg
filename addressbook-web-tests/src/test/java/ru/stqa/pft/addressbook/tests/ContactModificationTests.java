package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactModificationTests extends TestBase {

  @BeforeTest
   public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData().withLastname("Kachaev update").withFirstname("Vladimir update").withGroup("test1"),true);
    }
  }
  @Test
  public void testContactModification(){


    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("Kachaev2").withFirstname("Vladimir2").withGroup("test1");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));

    Contacts after = app.contact().all();
    //удаляем в списке последнюю запись, т.к. она была изменена
    before.remove(modifiedContact);
    //добавляем созданный объект
    before.add(contact);

    Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
