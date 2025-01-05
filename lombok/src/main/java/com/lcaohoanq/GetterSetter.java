package com.lcaohoanq;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetterSetter {

    private int id;
    private String name;

    @Setter(AccessLevel.PROTECTED)
    private int yob;

    /*
        Above annotation is specify the access level of the setter method.

        protected void setYob (int yob){
            this.yob = yob;
        }
    */

    public GetterSetter(int id, String name, int yob) {
        this.id = id;
        this.name = name;
        this.yob = yob;
    }

    public static void main(String[] args) {
        System.out.println(new GetterSetter(1, "Hoang", 1999).getYob());
    }

}
