package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Katya on 09.10.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().list().size() == 0 ){
      app.group().create(new GroupData().withName("Test1"));
    }
  }

  @Test
  public void testGroupModification(){

    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    GroupData group = new GroupData()
            .withId(before.get(index).getId()).withName("test1").withHeader("test2").withFooter("test3");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());
    //удаляем в списке последнюю запись, т.к. она была изменена
    before.remove(index);
    //добавляем созданный объект
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    //сравниваем отсортированные списки
    Assert.assertEquals(before, after);

  }


}
