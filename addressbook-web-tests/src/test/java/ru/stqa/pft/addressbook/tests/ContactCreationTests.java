package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  //итератор массивов объектов
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null){
      xml +=line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }
  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null){
        json +=line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> groups = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType()); //the same List<GroupData>.class
      return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test //(dataProvider = "validContactsFromJson")
    //public void testContactCreation(ContactData contact) {
    public void testContactCreation() {
    //создать предусловие создать группу если нет группы!
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test1"));
    }
    Groups groups = app.db().groups();
        app.goTo().contactPage();
        Contacts before = app.db().contacts();
        //File photo = new File("src/test/resources/phot.png");

      ContactData newcontact = new ContactData().withLastname("Kachaev2").withFirstname("Vladimir2")
            .inGroups(groups.iterator().next()).withAddress("erfjwnfkre").withHome("324298").withMobile("123").withWork("24789").withEmail1("reheifqq")
            .withEmail2("few").withEmail3("grw e");

      app.contact().createContact(newcontact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1 ));
        Contacts after = app.db().contacts();
        //Assert.assertEquals(after, before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())));
        assertThat(after, equalTo(
                before.withAdded(newcontact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

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
