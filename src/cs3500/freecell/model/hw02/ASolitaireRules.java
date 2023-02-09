package cs3500.freecell.model.hw02;

import cs3500.freecell.model.PileType;

/**
 * Represents the rules that are related to solitaire any piles.
 * Is an abstract class since currently all pile types have same logic.
 */

public abstract class ASolitaireRules {

  /**
   * Checks if move is valid.
   *
   * @param next Card that will be placed on top
   * @param current Card that will be below next card
   * @param desType PileType of destination pile
   * @return whether or not the move is valid
   */

  boolean placeOnTop(ICard next, ICard current, PileType desType) {
    switch (desType) {
      case OPEN:
        return this.placeOnOpen(next, current);
      case CASCADE:
        return this.placeOnCascade(next, current);
      case FOUNDATION:
        return this.placeOnFoundation(next, current);
      default: {
        return false;
      }
    }
  }

  /**
   * Checks if move is valid if the destination is a foundation pile.
   *
   * @param next Card that will be placed on top
   * @param current Card that will be below next card
   * @return whether or not the move is valid
   */

  boolean placeOnFoundation(ICard next, ICard current) {
    if (next == null) {
      return false;
    }
    else if (current == null && next.getValue() == Value.ACE.getValue()) {
      return true;
    }
    else if (current == null) {
      return false;
    }
    else {
      return next.getValue() == current.getValue() + 1
          && next.getSuite() == current.getSuite();
    }
  }

  /**
   * Checks if move is valid if the destination is a cascade pile.
   *
   * @param next Card that will be placed on top
   * @param current Card that will be below next card
   * @return whether or not the move is valid
   */

  // changed to protected so possible to be accessed by the ComplexSolitaireRules class

  protected boolean placeOnCascade(ICard next, ICard current) {
    try {
      return (next != null
          && current != null
          && (next.getValue() == current.getValue() - 1
          && !this.getColor(next).equals(this.getColor(current))))
          || placeOnOpen(next, current);
    }
    catch (Exception e) {
      return false;
    }
  }

  /**
   * Checks if move is valid if the destination is a open pile.
   *
   * @param next Card that will be placed on top
   * @param current Card that will be below next card
   * @return whether or not the move is valid
   */

  boolean placeOnOpen(ICard next, ICard current) {
    return current == null && next != null;
  }

  /**
   * Get the color of card based on suite.
   *
   * @param card Card to get color of
   * @return whether or not the move is valid
   */

  String getColor(ICard card) {
    if (card.getSuite().equals(Suite.DIAMOND.getSuite())
        || card.getSuite().equals(Suite.HEART.getSuite())) {
      return "red";
    }
    else if (card.getSuite().equals(Suite.CLUBS.getSuite())
        || card.getSuite().equals(Suite.SPADE.getSuite())) {
      return "black";
    }
    else {
      throw new IllegalArgumentException("Invalid Card Suite");
    }
  }
}
