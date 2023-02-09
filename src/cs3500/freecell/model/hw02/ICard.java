package cs3500.freecell.model.hw02;

/**
 * The interface for Card.
 * Specifies the needed operations for a card.
 */

public interface ICard {

  /**
   * Called to retrieve the suite of ICard.
   *
   * @return string of the card suite
   */

  String getSuite();

  /**
   * Called to retrieve the value of ICard.
   *
   * @return int of the card value
   */

  int getValue();

  /**
   * Ensures that the card fields are not null and
   * its fields are valid as well.
   *
   * @return boolean whether or not ICard is valid
   */

  boolean isValidCard();

  @Override
  String toString();

  @Override
  boolean equals(Object obj);

  @Override
  int hashCode();
}
