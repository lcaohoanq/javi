package com.lcaohoanq;

import lombok.AllArgsConstructor;

//@lombok.ToString //ToString(id=1, name=Hoang, surname=Le)
//@lombok.ToString(includeFieldNames = false) //ToString(1, Hoang, Le)
//@lombok.ToString(exclude = "id") //ToString(name=Hoang, surname=Le)
@lombok.ToString(of = {"name", "surname"}) //ToString(name=Hoang, surname=Le)


@AllArgsConstructor
public class ToString {

    private int id;
    private String name;
    private String surname;

    public static void main(String[] args) {
        System.out.println(new ToString(1, "Hoang", "Le"));
    }

    /*
        @lombok.ToString: default with option (includeFieldNames = true), will return ToString(id=1, name=Hoang, surname=Le)
        @lombok.ToString(includeFieldNames = false) //ToString(1, Hoang, Le)

        All non-static, non-transient fields are printed. If you want to exclude some fields, you can use the exclude parameter.
        @lombok.ToString(exclude = "id") //ToString(name=Hoang, surname=Le)

        Otherwise, you can select each field you wish to be taken into account.
        @lombok.ToString(of = {"name", "surname"}) //ToString(name=Hoang, surname=Le)
     */

}
