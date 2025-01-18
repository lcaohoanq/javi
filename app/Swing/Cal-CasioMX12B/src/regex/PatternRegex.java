/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package regex;

/**
 * @author Admin
 */
public enum PatternRegex {
  IS_DIGIT("^[\\d]+$"),
  IS_DIGIT_CALCULATOR("^[\\d]{1,2}$"), //vì nó có chứa một số 00
  IS_OPERATOR("^[+\\-*/]$");
  private String regex;

  private PatternRegex(String regex) {
    this.regex = regex;
  }

  public String getRegex() {
    return regex;
  }


}
