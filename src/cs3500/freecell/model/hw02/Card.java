package cs3500.freecell.model.hw02;

import java.util.Objects;

/**
 * This is the class to represent a card.
 * Used to create a deck of cards to play card game.
 */

public class Card implements ICard {

  protected final Value value;
  protected final Suite suite;

  /**
   * Constructor for Value Enum.
   *
   * @param value Value corresponding to card
   * @param suite Suite corresponding to card
   */

  public Card(Value value, Suite suite) {
    this.value = Objects.requireNonNull(value);
    this.suite = Objects.requireNonNull(suite);
  }


  /**
   * Called to retrieve the suite of card.
   *
   * @return String of the card suite
   */

  @Override
  public String getSuite() {
    return this.suite.getSuite();
  }

  /**
   * Called to retrieve the value of card.
   *
   * @return int of the card value
   */

  @Override
  public int getValue() {
    return this.value.getValue();
  }

  /**
   * Ensures that the card fields are not null and its
   * suite matches one of the suites created in Suite Enum
   * value matches one of the values created in Value Enum.
   *
   * @return booelan whether or not given suite is valid
   */

  public boolean isValidCard() {
    return this.value != null && this.suite != null
        && this.value.isValid(this.value) && this.suite.isValid(this.suite);
  }

  /**
   * String based on the card suite and value.
   *
   * @return String corresponding to card suite and value
   */

  @Override
  public String toString() {
    return value.toString() + suite;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Card) {
      ICard c = (ICard) obj;
      return this.getSuite().equals(c.getSuite()) && this.getValue() == c.getValue();
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return 1;
  }
}
