package ru.stqa.pft.sandbox;

public class MyFirstProgram
{
public static void main(String[] args) {
  hello("world");
  hello("user");
  hello("Vladimir");

  Square s = new Square(5);

  System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

  Rectangle r = new Rectangle(4, 6);


  System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());


  Point p1 = new Point(0, 0);
  Point p2 = new Point(4, 3);
  System.out.println("Расстояние между точкой с координатой x1=" + p1.x + "; y1=" + p1.y + " и координатой x2=" + p2.x + "; y2=" + p2.y + " равно " + p1.distance(p2));
}
  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }


  }


