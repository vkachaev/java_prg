package ru.stqa.pft.sandbox;

/**
 * Created by Katya on 28.09.2016.
 */
public class Rectangle {

  public double a;
  public double b;

  public Rectangle(double a, double b) {
    this.a = a;
    this.b = b;
  }

    public double area(){
      return this.a * this.b;

  }
}
