/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lcaohoanq.sparkjava;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public String toString() {
        return new StringBuffer().append(getEmail()).toString();
    }
}
