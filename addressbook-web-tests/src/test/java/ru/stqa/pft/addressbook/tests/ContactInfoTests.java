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
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    Assert.assertEquals(mergeContactsDetails(contactInfoFromDetails), mergeContactsFromEdit(contactInfoFromEditForm).replaceAll("[\\n\\r]",""));
    //assertThat(equalTo(mergeContactsDetails(contactInfoFromDetails)), equalTo(mergeContactsFromEdit(contactInfoFromEditForm).replaceAll("[\\n\\r]","")));
  }

  private String mergeContactsFromEdit(ContactData contact) {
    return Arrays.asList(contact.getFirstname(),contact.getLastname(),contact.getAddress(),contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s -> ! s.equals("")))
            .map(ContactInfoTests::cleanedDetail)
            .collect(Collectors.joining("\n"));
  }
  private String mergeContactsDetails(ContactData contact) {
    return Arrays.asList(contact.getContactdetails())
            .stream()
             .map(ContactInfoTests::cleanedDetail)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedDetail(String contactdetail){
    //for space, tab, enter
    return contactdetail.replaceAll("\\s","").replaceAll("[-()]","").replaceAll("W:","").replaceAll("H:","").replaceAll("M:","");
  }

}
