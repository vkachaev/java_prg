package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Katya on 15.11.2016.
 */
public class ContactInfoTests extends TestBase {

  @Test
  public void testContactInfo(){
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromDetails = app.contact().infoFromDetails(contact);
    String so = mergeContactsDetails(contactInfoFromDetails);
    assertThat(so, equalTo(mergeContactsFromMain(contact)));
    Assert.assertEquals(mergeContactsDetails(contactInfoFromDetails), mergeContactsFromMain(contact));

  }

  private String mergeContactsFromMain(ContactData contact) {
    return Arrays.asList(contact.getAllcontacts())
            .stream().filter((s -> ! s.equals("")))

            .map(ContactInfoTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
  private String mergeContactsDetails(ContactData contact) {
    return Arrays.asList(contact.getContactdetails())
            .stream().filter((s -> ! s.equals("")))

             .map(ContactInfoTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    //for space, tab, enter
    return phone.replaceAll("\\s","").replaceAll("[-()]","").replaceAll("W:","").replaceAll("H:","").replaceAll("M:","");
  }
}
