package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupCreationTests extends TestBase {

  @DataProvider
  //итератор массивов объектов
  public Iterator<Object[]> validGroups(){
    //формируем список массивов
    List<Object[]> list = new ArrayList<Object[]>();
    //заполняем список тестовыми данными
    list.add(new Object[]{new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
    list.add(new Object[]{new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
    list.add(new Object[]{new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
    return list.iterator();
  }

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
      app.goTo().groupPage();
      Groups before = app.group().all();
      app.group().create(group);
      assertThat(app.group().count(), equalTo(before.size() + 1));
      Groups after = app.group().all();
      //функция преобразования объекта в число
      //нужно новой добавленой группе присвоить правильный id
      //коллекция с id певращаем в поток объектов типа GroupData превратим в поток id при помощи mapToInt
      //mapToInt описывает как объект преобразуется в число, передаем анонимную функцию
      //которая посл-во будет применяться ко всем элементам потока и каждый из них будет преобразовываться в число
      //в результате получаем поток целых чисел

      //hamcrest
      assertThat(after, equalTo(
              before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

  @Test (enabled = false)
  public void testGroupBadCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Test'");
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    //hamcrest
    assertThat(after, equalTo(before));

  }

}
