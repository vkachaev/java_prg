package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Katya on 09.10.2016.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeTest
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().all().size() == 0) {
      app.contact().createContact(new ContactData().withLastname("Kachaev update").withFirstname("Vladimir update").withGroup("test1"),true);
    }
  }

  @Test
  public void testContactDeletion(){

    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() -1 ));
    Contacts after = app.db().contacts();
    //Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedContact);
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
