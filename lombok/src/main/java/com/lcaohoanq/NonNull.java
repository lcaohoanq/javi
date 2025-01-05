package com.lcaohoanq;

public class NonNull {

    private int id;
    private String name;
    private String surname;

    public NonNull(
        @lombok.NonNull int id,
        @lombok.NonNull String name,
        String surname
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    /*
    public NonNull(
      int id,
      String name,
      String surname
    ) {
        if (id == null) {
          throw new NullPointerException("id is marked @NonNull but is null");
        }
        this.id = id;
        if (name == null) {
          throw new NullPointerException("name is marked @NonNull but is null");
        }
        this.name = name;
        this.surname = surname;
  }
   */

}
