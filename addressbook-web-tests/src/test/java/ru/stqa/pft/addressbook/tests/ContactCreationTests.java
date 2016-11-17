package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().contactPage();

        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/phot.png");
        ContactData contact = new ContactData().withLastname("Kachaev2").withFirstname("Vladimir2")
                .withGroup("test1").withPhoto(photo).withAddress("").withAllEmails("").withAllPhones("");
        app.contact().createContact(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1 ));
        Contacts after = app.contact().all();
        //Assert.assertEquals(after, before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
    @Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/phot.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }



}
