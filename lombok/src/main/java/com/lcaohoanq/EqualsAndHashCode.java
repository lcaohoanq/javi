package com.lcaohoanq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@lombok.EqualsAndHashCode
//@lombok.EqualsAndHashCode(of = {"id", "name"}) //EqualsAndHashCode only compare id and name
//@lombok.EqualsAndHashCode(callSuper = true) //EqualsAndHashCode will also include the superclass
//@lombok.EqualsAndHashCode(exclude = "surname") //EqualsAndHashCode will exclude surname
//@lombok.EqualsAndHashCode(doNotUseGetters = true) //EqualsAndHashCode will not use getters
//@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true) //EqualsAndHashCode will only include explicitly marked fields
//@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true) //EqualsAndHashCode will only include explicitly marked fields and also include the superclass
public class EqualsAndHashCode {

    private int id;
    private String name;
    private String surname;

}
