package cs3500.freecell.model.hw04;

import cs3500.freecell.model.hw02.ASolitaireRules;
import cs3500.freecell.model.hw02.ICard;

/**
 * Represents the rules that are related to solitaire any piles.
 * Since the ComplexFreecell move method is only different when both piles are cascade,
 * the only method needed is to check if a cascade card can go on a cascade card.
 */

public class ComplexSolitaireRules extends ASolitaireRules {

  /**
   * Checks if move is valid if the destination is a cascade pile.
   *
   * @param next Card that will be placed on top
   * @param current Card that will be below next card
   * @return whether or not the move is valid
   */

  protected boolean placeOnCascade(ICard next, ICard current) {
    return super.placeOnCascade(next, current);
  }
}
