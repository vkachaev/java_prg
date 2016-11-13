package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Test2");
    app.group().create(group);

    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));
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

}
