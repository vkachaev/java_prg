package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Katya on 20.10.2016.
 */
public class Collections {
  public static void main(String[] args){
    String[] lang = new String[4];
    lang[0]="Java";
    lang[1]="C#";
    lang[2]="Python";
    lang[3]="PHP";
    String[] langs = {"Java","C#","Python","PHP"}; // equavalent of above

    for (int i=0; i<langs.length; i++){
      System.out.println("Я хочу выучить " + langs[i] );
    }
    for (String l : lang){
      System.out.println("Я хочу выучить l " + l);
    }

    for (String la : langs){
      System.out.println("Я хочу выучить la " + la);
    }

    List<String> language = new ArrayList<String>();
    language.add("Java");
    language.add("C#");
    language.add("Python");
    language.add("PHP");

    for (String l : language){
      System.out.println("Я хочу выучить arrayList " + l);
    }

    List<String> languages = Arrays.asList("Java","C#","Python","PHP");
    for (int i = 0; i < languages.size(); i++){
      System.out.println("Я хочу выучить asList2 " + languages.get(i));
    }
    //так более правильно
    for (String l : languages){
      System.out.println("Я хочу выучить asList " + l);
    }
  }
}
