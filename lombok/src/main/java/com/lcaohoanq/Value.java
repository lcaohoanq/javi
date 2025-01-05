package com.lcaohoanq;

@lombok.Value
public class Value {

    /*
        @Value is the immutable variant of @Data
        - All field are private and final (required args constructor is generated)
        - Class will become final
        - Setter not generated
        - equals, hashCode, and toString methods are generated
    */

    int id; //private final int id;
    String name; //private final String name;

    public static void main(String[] args) {
        Value value = new Value(1, "Hoang");
        System.out.println(value);
    }

}
