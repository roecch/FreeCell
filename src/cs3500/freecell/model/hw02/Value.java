package cs3500.freecell.model.hw02;

import java.util.Objects;

/**
 * This is the enum representing all of the possible values that a card can have.
 * Used to create all the possible combination of cards in a deck of cards.
 */

public enum Value {
  ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
  EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);
  private final int value;

  /**
   * Constructor for Value Enum.
   * Only values 1 - 13 allowed since that is all the values in a deck of card.
   *
   * @param value the int of value
   */

  Value(int value) {
    if (value < 1 || value > 13) {
      throw new IllegalArgumentException("Card value must be between 1 - 13");
    }

    this.value = Objects.requireNonNull(value, "Value cannot be null");
  }

  /**
   * Called to retrieve the value of value.
   *
   * @return int of the value
   */

  int getValue() {
    return this.value;
  }

  /**
   * String based on what value is called.
   * Special cases for values on 1, 11, 12, 13 since they are an ace or face cards.
   *
   * @return String corresponding to Value
   */

  @Override
  public String toString() {
    switch (this.value) {
      case 1:
        return "A";
      case 11:
        return "J";
      case 12:
        return "Q";
      case 13:
        return "K";
      default:
        return Integer.toString(this.value);

    }
  }

  /**
   * Ensures that the given Value  matches one of the values created in Enum.
   *
   * @param givenV Value to check if valid
   * @return whether or not given value is valid
   */

  public boolean isValid(Value givenV) {
    if (givenV == null) {
      return false;
    }

    for (Value v : Value.values()) {
      if (v.toString().equals(givenV.toString())) {
        return true;
      }
    }
    return false;
  }
}
