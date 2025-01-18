package regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
  public static void main(String[] args) {
    handleExpression();
  }

  public static void handleExpression(){
//    String inputString = "12+2";

    Scanner sc = new Scanner(System.in);
System.out.println("Enter an expression by form [number|operator|number]:");
    String inputString = sc.nextLine();

    Pattern pattern = Pattern.compile("(\\d+)([+\\-*/])(\\d+)");
    Matcher matcher = pattern.matcher(inputString);
    System.out.println("Giá trị của inputString: " + inputString);
    System.out.println("Giá trị của matcher: " + matcher);
    if (matcher.matches()) {
      String[] result = new String[]{
          matcher.group(1),  // First number
          matcher.group(2),  // Operator
          matcher.group(3)   // Second number
      };

      // Print the array elements
      for (String element : result) {
        System.out.println(element);
      }
    } else {
      System.out.println("Input does not match the pattern.");
    }
  }
}
