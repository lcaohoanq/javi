package com.lcaohoanq;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class ArgsConstructor {

    private int id;
    private String name;
    private String surname;
    private final String birthPlace; //the final field is required to be initialized in the constructor

    /*
    // @NoArgsConstructor
    public Author() {}

    // @AllArgsConstructor
    public Author(int id, String name, String surname, String birthPlace) {
      this.id = id
      this.name = name
      this.surname = surname
      this.birthPlace = birthPlace
    }

    // @RequiredArgsConstructor
    public Author(String birthPlace) {
      this.birthPlace = birthPlace
    }
    */

    public static void main(String[] args) {
        ArgsConstructor author = new ArgsConstructor(1, "Hoang", "Le", "Vietnam");
        System.out.println(author);
    }

}
