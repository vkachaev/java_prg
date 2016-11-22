package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0){
      app.goTo().contactPage();
      app.contact().createContact(new ContactData().withLastname("Kachaev update").withFirstname("Vladimir update").inGroups(groups.iterator().next()),true);
    }
  }
  @Test
  public void testContactModification(){
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withLastname("Kachaev2").withFirstname("Vladimir2")
            .inGroups(groups.iterator().next()).withAddress("erfjwnfkre").withHome("324298").withMobile("123").withWork("24789").withEmail1("reheifqq")
            .withEmail2("few").withEmail3("grw e");
    app.contact().modify(contact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));

    Contacts after = app.db().contacts();
    //удаляем в списке последнюю запись, т.к. она была изменена
    before.remove(modifiedContact);
    //добавляем созданный объект
    before.add(contact);

    Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
