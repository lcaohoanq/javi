package com.lcaohoanq;

@lombok.Data
public class Data {

    /* @lombok.Data will generate the following methods:
        - Getter for all fields
        - Setter for all non-final fields
        - equals, hashCode, and toString methods
        - Constructor with all final fields
    */

    private final int id;
    private final String birthPlace;
    private String name;
    private String surname;

    public static void main(String[] args) {
        Data data = new Data(1,"1"); //only the id is required to be initialized
        //Data data2 = new Data(1,"Hoang","Vietnam") //error
        System.out.println(data);
    }

}
