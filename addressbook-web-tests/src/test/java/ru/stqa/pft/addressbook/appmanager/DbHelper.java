package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Created by Katya on 18.11.2016.
 */
public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper(){
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list();

    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated='0000-00-00'" ).list();
    for (ContactData contact : result ) {
      System.out.println(contact);
      System.out.println(contact.getGroups());
    }
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public ContactData contactById(int contactId){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated='0000-00-00' and id=" + contactId ).list();
    System.out.println(result);
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }

  public GroupData groups(int groupId){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData where group_id=" + groupId ).list();

    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }
}
