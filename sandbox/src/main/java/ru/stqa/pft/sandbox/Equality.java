package ru.stqa.pft.sandbox;

/**
 * Created by Katya on 15.10.2016.
 */
public class Equality {

  public static void main(String[] args){
    String s1 = "firefox"; //reference on an object
    String s3 = s1;
    String s2 = new String(s1); //new object
    String s4 = "fire"+"fox";
    String s5 = "firefox 2.0";
    String s6 = "firefox " + Math.sqrt(4.0);

    System.out.println(s1 == s2);//compare physical: s1 reference on a object s1; s2 refers on a new object - false
    System.out.println(s3 == s1);//s1, s3 refer on a object s1 - true
    System.out.println(s1.equals(s2)); //logic comparation - true
    System.out.println(s1 == s4); //literaly comparation by java compilator - true
    System.out.println(s4.equals(s1));//logic comparation - true
    System.out.println(s5 == s6); //literaly comparation by java compilator cannot make math evaluation - false
    System.out.println(s6.equals(s5)); //logic comparation - true
  }
}
