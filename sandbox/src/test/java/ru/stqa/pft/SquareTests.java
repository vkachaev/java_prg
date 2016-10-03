package ru.stqa.pft;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Square;

/**
 * Created by Katya on 30.09.2016.
 */
public class SquareTests {

  @Test
  public void testAreas(){
    Square s = new Square(5);

    Assert.assertEquals(s.area(), 25.0);
  }
}
