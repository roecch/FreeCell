package cs3500.freecell.model.hw04;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;

/**
 * This class represents a complex freecell model must support to return various aspects of its
 * state. Follows the method of the FreecellModel and FreecellModelState classes.
 * Extends the SimpleFreecell class since its functionality is similar.
 */

public class ComplexFreecellModel extends SimpleFreecellModel implements FreecellModel<ICard> {

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {

    if (source.equals(PileType.CASCADE) && destination.equals(PileType.CASCADE)
        && this.getNumCardsInCascadePile(pileNumber) != cardIndex + 1
        && this.checkIfMultiCardMovePoss(pileNumber, cardIndex, destPileNumber)) {

      int originalSizeOfPile = this.getNumCardsInCascadePile(pileNumber);
      for (int i = cardIndex; i < originalSizeOfPile; i++) {
        this.cascadePiles.get(destPileNumber)
            .add(this.cascadePiles.get(pileNumber).remove(cardIndex));
      }
    }
    else {
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    }
  }

  /**
   * Checks if moving multiple cards from the given pile to the destination is valid.
   *
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source pile, starting at 0
   * @param destPileNumber the pile number of the given type, starting at 0
   * @return whether multiple card move is valid
   */

  private boolean checkIfMultiCardMovePoss(int pileNumber, int cardIndex, int destPileNumber) {
    ComplexSolitaireRules rules = new ComplexSolitaireRules();
    boolean check = true;
    check &= super.getNumCardsInCascadePile(pileNumber) - cardIndex - 1 < this.numOfCardMovable();
    check &= rules.placeOnCascade(this.getCascadeCardAt(pileNumber, cardIndex),
        this.getCascadeCardAt(destPileNumber, getNumCardsInCascadePile(destPileNumber) - 1));

    for (int i = cardIndex; i < super.getNumCardsInCascadePile(pileNumber) - 1; i++) {
      check &= rules.placeOnCascade(this.getCascadeCardAt(pileNumber, i + 1),
          this.getCascadeCardAt(pileNumber, i));
    }

    return check;
  }

  /**
   * Calculates the number of cards that can be moved that once in current move.
   *
   * @return the max number of cards that can be moved
   */

  private int numOfCardMovable() {
    return (int) ((this.numOfEmptyOpen() + 1) * Math.pow(2, this.numOfEmptyCascade()));
  }

  /**
   * Calculates the number of empty open piles.
   *
   * @return the number of empty open piles
   */

  private int numOfEmptyOpen() {
    int count = 0;
    for (int i = 0; i < super.getNumOpenPiles(); i++) {
      if (super.getNumCardsInOpenPile(i) == 0) {
        count++;
      }
    }
    return count;
  }

  /**
   * Calculates the number of empty cascade piles.
   *
   * @return the number of empty cascade piles
   */

  private int numOfEmptyCascade() {
    int count = 0;
    for (int i = 0; i < super.getNumCascadePiles(); i++) {
      if (super.getNumCardsInCascadePile(i) == 0) {
        count++;
      }
    }
    return count;
  }
}
