package enums;

public enum Btn {
  EMPTY_0(0, ""),
  EMPTY_1(0, ""),
  EMPTY_2(0, ""),

  PERCENT(1, "%"),
  MEMORY_USE(2, "MU"),
  MEMORY_CLEAR(3, "MC"),
  MEMORY_RECALL(4, "MR"),
  MEMORY_SUBTRACT(5, "M-"),
  MEMORY_ADD(6, "M+"),
  DIVIDE(7, "/"),
  NEGATE(8, "+/_"),
  SEVEN(9, "7"),
  EIGHT(10, "8"),
  NINE(11, "9"),
  MULTIPLY(12, "X"),
  REMOVE(13, "|>"),
  FOUR(14, "4"),
  FIVE(15, "5"),
  SIX(16, "6"),
  SUBTRACT(17, "-"),
  CLEAR(18, "C/AC"),
  ONE(19, "1"),
  TWO(20, "2"),
  THREE(21, "3"),
  ADD(22, "+"), ////////////
  ZERO(23, "0"),
  DOUBLE_ZERO(24, "00"),
  DECIMAL(25, "."),
  EQUALS(26, "="),
  PLUS(27, "+");///////////////

  private final int key;
  private final String value;

  Btn(int key, String value) {
    this.key = key;
    this.value = value;
  }

  public int getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }

}
