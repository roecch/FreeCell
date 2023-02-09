package cs3500.freecell.model.hw02;

import cs3500.freecell.model.PileType;

/**
 * Represents the rules that are only related to solitaire foundation piles.
 * Currently empty since the rules for foundation piles are the same for other piles.
 * Created to make future implementation of movement of solitaire easier.
 */

public class SolitaireFoundationRules extends ASolitaireRules {

  /**
   * Checks if move is valid.
   * Since once a card is in foundation pile it cannot be removed,
   * this always returns false.
   *
   * @param next Card that will be placed on top
   * @param current Card that will be below next card
   * @param desType PileType of destination pile
   * @return whether or not the move is valid
   */
  boolean placeOnTop(ICard next, ICard current, PileType desType) {
    return false;
  }
}
