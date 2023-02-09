package cs3500.freecell.model.hw04;

import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw02.Suite;
import cs3500.freecell.model.hw02.Value;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the methods of the ComplexFreecellModel Class:
 * ensure that ComplexFreecellModel methods behave correctly.
 */

public class ComplexFreecellModelTest {
  FreecellModel<ICard> freecellShuffle = new ComplexFreecellModel();
  FreecellModel<ICard> freecellNoShuffle = new ComplexFreecellModel();
  FreecellModel<ICard> notStartedFreecell = new ComplexFreecellModel();
  FreecellModel<ICard> freecellNoShuffleForMulti = new ComplexFreecellModel();

  @Before
  public void setUp() {
    freecellShuffle.startGame(freecellShuffle.getDeck(), 4, 4, true);
    freecellNoShuffle.startGame(freecellNoShuffle.getDeck(), 4, 12, false);
    freecellNoShuffleForMulti.startGame(freecellNoShuffleForMulti.getDeck(), 8, 9, false);
    // set up possible multiple card move
    freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 5, PileType.OPEN, 0);
    freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 4, PileType.OPEN, 1);
    freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 3, PileType.OPEN, 2);
    freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 2, PileType.CASCADE, 7);
    freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 7);
    freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 5, PileType.OPEN, 3);
    freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 4, PileType.OPEN, 4);
    freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 3, PileType.OPEN, 5);
    /*
    * State of freecellNoShuffleForMulti after setup
    *
    * F1:
    * F2:
    * F3:
    * F4:
    * O1: 6♣
    * O2: J♠
    * O3: 3♠
    * O4: 8♣
    * O5: K♠
    * O6: 5♠
    * O7:
    * O8:
    * C1: A♦, 9♦, 4♥, Q♥, 7♠, 2♣, 10♣
    * C2: 2♦, 10♦, 5♥, K♥, 8♠, 3♣, J♣
    * C3: 3♦, J♦, 6♥, A♠, 9♠, 4♣, Q♣
    * C4: 4♦, Q♦, 7♥, 2♠, 10♠, 5♣, K♣
    * C5: 5♦, K♦
    * C6: 6♦, A♥, 9♥, 4♠, Q♠
    * C7: 7♦, 2♥, 10♥
    * C8: 8♦, 3♥, J♥, 6♠, A♣, 9♣, 8♥, 7♣
    */
  }

  /**
   * Tests for move if player tries to move too many cards.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveTooManyCards() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 6, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 5, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 6, PileType.CASCADE, 6);
  }

  /**
   * Tests for move if player tries to move valid number of cards but to invalid cascade pile card.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveInvalidPlace() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 5);
  }

  /**
   * Tests for move if player tries to move the exact number of cards allowed to move.
   */

  @Test
  public void moveExactValidNumOfCardsToValidPlace() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 2, PileType.CASCADE, 1);
    assertEquals(11, freecellNoShuffleForMulti.getNumCardsInCascadePile(1));
  }

  /**
   * Tests for move if player tries to move less than number of cards allowed to move.
   */

  @Test
  public void moveLessValidNumOfCardsToValidPlace() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    assertEquals(6, freecellNoShuffleForMulti.getNumCardsInCascadePile(6));
  }

  /**
   * Tests for move if player tries to move the multiple cards in foundation piles.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveMultipleFromFoundation() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 7, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 6, PileType.FOUNDATION, 0);
    this.freecellNoShuffleForMulti.move(PileType.FOUNDATION, 0, 0, PileType.FOUNDATION, 1);
  }

  /**
   * Tests for move if player tries to move the multiple cards to open piles.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveMultipleToOpen() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.OPEN, 7);
  }

  /**
   * Tests for move if player tries to move the multiple cards to foundation piles.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveMultipleToFoundation() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.FOUNDATION, 0);
  }

  /**
   * Tests for valid move if player tries to move with no empty open and one empty cascade.
   * (allowed to only move two).
   */

  @Test
  public void moveNoEmptyOpenOneEmptyCascadeValidMove() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 1, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 0, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 7, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    assertEquals(5, freecellNoShuffleForMulti.getNumCardsInCascadePile(6));
  }

  /**
   * Tests for invalid move if player tries to move with no empty open and one empty cascade.
   * Enters move that would move too many cards.
   * (allowed to only move two).
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveNoEmptyOpenOneEmptyCascadeInvalidNumberOfCardsMoved() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 1, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 0, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 6, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
  }

  /**
   * Tests for valid move if player tries to move with no empty open and one empty cascade.
   * Enters move that would move to wrong position.
   * (allowed to only move two).
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveNoEmptyOpenOneEmptyCascadeInvalidPlacement() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 1, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 1, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 6, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 0);
  }

  /**
   * Tests for valid move if player tries to move with no empty cascade and one empty open.
   * (allowed to only move two).
   */

  @Test
  public void moveNoEmptyCascadeOneEmptyOpenValidMove() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 6, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 7, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    assertEquals(5, freecellNoShuffleForMulti.getNumCardsInCascadePile(6));
  }

  /**
   * Tests for invalid move if player tries to move with no empty cascade and one empty open.
   * Enters move that would move too many cards.
   * (allowed to only move two).
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveNoEmptyCascadeOneEmptyOpenInvalidNumberOfCardsMoved() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 6, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 5, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
  }

  /**
   * Tests for valid move if player tries to move with no empty cascade and one empty open.
   * Enters move that would move to wrong position.
   * (allowed to only move two).
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveNoEmptyCascadeOneEmptyOpenInvalidPlacement() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 0, 6, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 7, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 9, PileType.CASCADE, 6);
  }

  /**
   * Tests for move if player tries to move with at least 2 empty open.
   */

  @Test
  public void moveTwoEmptyOpenValidMove() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 2, PileType.CASCADE, 1);
    assertEquals(11, freecellNoShuffleForMulti.getNumCardsInCascadePile(1));
  }

  /**
   * Tests for move if player tries to move with at least 2 empty open.
   * Enters move that would move too many cards.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveTwoEmptyOpenInvalidNumberOfCardsMove() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 4);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 3, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 0, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 5);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 2, PileType.CASCADE, 1);
  }

  /**
   * Tests for move if player tries to move with at least 2 empty open.
   * Enters move that would be invalid placement.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveTwoEmptyOpenInvalidPlacement() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 2, PileType.CASCADE, 2);
  }

  /**
   * Tests for move if player tries to move with at least 2 empty cascade and no empty open.
   * Also tests for multi move after multi move.
   */

  @Test
  public void moveTwoEmptyCascadeNoEmptyOpenValidMove() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 4);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 3, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 0, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 2, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 1, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 1);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 0, PileType.CASCADE, 7);
    this.freecellNoShuffleForMulti.move(PileType.OPEN, 5, 0, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 2, PileType.CASCADE, 1);
    assertEquals(13, freecellNoShuffleForMulti.getNumCardsInCascadePile(1));
  }

  /**
   * Tests for move if player tries to move with at least 2 empty cascade.
   * Enters move that would move too many cards.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveTwoEmptyCascadeInvalidNumberOfCardsMove() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 4);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 3, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 0, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 2, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 1, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 1);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 0, PileType.CASCADE, 7);
    this.freecellNoShuffleForMulti.move(PileType.OPEN, 5, 0, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 3, 6, PileType.OPEN, 5);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 2, PileType.CASCADE, 1);
  }

  /**
   * Tests for move if player tries to move with at least 2 empty cascade.
   * Enters move that would be invalid placement.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveTwoEmptyCascadeInvalidPlacement() {
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 5, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 4);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 3, PileType.OPEN, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 2, PileType.CASCADE, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION, 0);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 5, 0, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 2, PileType.OPEN, 8);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 1, PileType.OPEN, 7);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 7, 4, PileType.FOUNDATION, 1);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 4, 0, PileType.CASCADE, 7);
    this.freecellNoShuffleForMulti.move(PileType.OPEN, 5, 0, PileType.CASCADE, 6);
    this.freecellNoShuffleForMulti.move(PileType.CASCADE, 6, 2, PileType.CASCADE, 0);
  }

  ///
  // All tests below are similar to the tests in the SimpleFreecellModelTest
  // but applied to the ComplexFreecellModel
  ///

  /**
   * Tests for the getDeck method.
   */

  @Test
  public void getDeck() {
    assertEquals(52, freecellShuffle.getDeck().size());
  }

  /**
   * Tests for the startGame method with all valid parameters.
   */

  @Test
  public void startGameValid() {
    notStartedFreecell.startGame(notStartedFreecell.getDeck(),
        8, 5, true);
    assertEquals(5, notStartedFreecell.getNumOpenPiles());
  }

  /**
   * Tests for the startGame method to restart game.
   */

  @Test
  public void startGameValidRestart() {
    freecellShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 0);
    int testNumInPile = freecellShuffle.getNumCardsInCascadePile(0);
    freecellShuffle.startGame(freecellShuffle.getDeck(), 4, 4, true);
    assertEquals(false, testNumInPile == freecellShuffle.getNumCardsInCascadePile(0));
  }

  /**
   * Tests for the startGame method to shuffle deck for game.
   */

  @Test
  public void startGameValidShuffleTrue() {
    int sameInCascade = 52;
    for (int i = 0; i < 52; i++) {
      if (!freecellShuffle.getCascadeCardAt(i / 13, i % 13)
          .equals(freecellNoShuffle.getCascadeCardAt(i / 13, i % 13))) {
        sameInCascade--;
      }
    }
    assertEquals(true, sameInCascade != 52);
  }


  /**
   * Tests for the startGame method with invalid empty deck.
   */

  @Test (expected = IllegalArgumentException.class)
  public void startGameInvalidDeckWrongSize() {
    new SimpleFreecellModel().startGame(new ArrayList<ICard>(), 4, 4, true);
  }

  /**
   * Tests for the startGame method with invalid deck with dupe card.
   */

  @Test (expected = IllegalArgumentException.class)
  public void startGameInvalidDeckDupeCard() {
    List<ICard> inValDeck = new ArrayList<ICard>();
    inValDeck = freecellNoShuffle.getDeck();
    inValDeck.remove(51);
    inValDeck.add(new Card(Value.ACE, Suite.DIAMOND));
    new SimpleFreecellModel().startGame(inValDeck, 3, 4, true);
  }

  /**
   * Tests for the startGame method with invalid deck with invalid card.
   */

  @Test (expected = NullPointerException.class)
  public void startGameInvalidNumCascadeInvalidCard() {
    List<ICard> inValDeck = new ArrayList<ICard>();
    inValDeck = freecellNoShuffle.getDeck();
    inValDeck.remove(51);
    inValDeck.add(new Card(null, null));
    new SimpleFreecellModel().startGame(inValDeck, 3, 4, true);
  }

  /**
   * Tests for the startGame method with invalid numOpenPiles.
   */

  @Test (expected = IllegalArgumentException.class)
  public void startGameInvalidNumOpen() {
    new SimpleFreecellModel().startGame(freecellShuffle.getDeck(), 4, -1, true);
  }

  /**
   * Tests for the startGame method with invalid numCascadePiles.
   */

  @Test (expected = IllegalArgumentException.class)
  public void startGameInvalidNumCascade() {
    new SimpleFreecellModel().startGame(freecellShuffle.getDeck(), -1, 4, true);
  }

  /**
   * Tests for the move method for invalid move of cascade to cascade.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardFromCascadeToCascadeInvalid() {
    this.freecellShuffle.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 1);
  }

  /**
   * Tests for the move method for invalid move to full open pile destination.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardToFullOpen() {
    this.freecellShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 1);
    this.freecellShuffle.move(PileType.CASCADE, 0, 11, PileType.OPEN, 1);
  }

  /**
   * Tests for the move method for valid move to empty open pile destination.
   */

  @Test
  public void moveCardToEmptyOpen() {
    this.freecellShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 1);
    assertEquals(1, this.freecellShuffle.getNumCardsInOpenPile(1));
  }

  /**
   * Tests for the move method for valid move of cascade to cascade.
   */

  @Test
  public void moveCardFromCascadeToCascadeValid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 8, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 7, PileType.OPEN, 5);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 6, PileType.OPEN, 6);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 3);
    assertEquals(new Card(Value.TEN, Suite.CLUBS), this.freecellNoShuffle.getCascadeCardAt(3, 6));
  }

  /**
   * Tests for the move method for valid move of cascade to empty foundation.
   */

  @Test
  public void moveCardFromCascadeToFoundationEmptyValid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    assertEquals(1, this.freecellNoShuffle.getNumCardsInFoundationPile(1));
  }

  /**
   * Tests for the move method for invalid move of cascade to empty foundation.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardToFoundationEmptyInvalid() {
    this.freecellShuffle.move(PileType.CASCADE, 0, 12, PileType.FOUNDATION, 1);
  }

  /**
   * Tests for the move method for invalid move of cascade to non-empty foundation.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardToFoundationNotEmptyInvalid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 8, PileType.FOUNDATION, 1);
  }

  /**
   * Tests for the move method for valid move of cascade to non-empty foundation.
   */

  @Test
  public void moveCardToFoundationNotEmptyValid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 11, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 1);
    assertEquals(2, this.freecellNoShuffle.getNumCardsInFoundationPile(1));
  }

  /**
   * Tests for the move method for valid move of open to cascade.
   */

  @Test
  public void moveCardFromOpenToCascadeValid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 8, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 7, PileType.OPEN, 5);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 6, PileType.OPEN, 6);
    this.freecellNoShuffle.move(PileType.OPEN, 4, 0, PileType.CASCADE, 3);
    assertEquals(7, this.freecellNoShuffle.getNumCardsInCascadePile(3));
  }

  /**
   * Tests for the move method for invalid move of open to cascade.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardFromOpenToCascadeInvalid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 8, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 7, PileType.OPEN, 5);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 6, PileType.OPEN, 6);
    this.freecellNoShuffle.move(PileType.OPEN, 4, 1, PileType.CASCADE, 2);
  }

  /**
   * Tests for the move method for valid move of open to empty open.
   */

  @Test
  public void moveCardFromOpenToOpenValid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    assertEquals(new Card(Value.KING, Suite.CLUBS), this.freecellNoShuffle.getOpenCardAt(1));
  }

  /**
   * Tests for the move method for valid move of open to non-empty open.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardFromOpenToOpenInvalid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
  }

  /**
   * Tests for the move method for valid move of open to empty foundation.
   */

  @Test
  public void moveCardFromOpenToFoundationValid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 1);
    assertEquals(1, this.freecellNoShuffle.getNumCardsInFoundationPile(1));
  }

  /**
   * Tests for the move method for invalid move of open to empty foundation.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardFromOpenToFoundationInvalid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 1);
  }

  /**
   * Tests for the move method of foundation to cascade.
   * Move that would be valid if source is not foundation.
   * Cannot take card out of foundation pile.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardFromFoundationToCascadeValidButStillInvalidMove() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 11, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.FOUNDATION, 1, 0, PileType.CASCADE, 0);
  }

  /**
   * Tests for the move method for invalid move of foundation to cascade.
   * Cannot take card out of foundation pile.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardFromFoundationToCascadeInvalid() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.FOUNDATION, 1, 0, PileType.CASCADE, 1);
  }

  /**
   * Tests for the move method of foundation to open.
   * Move that would be valid if source is not foundation.
   * Cannot take card out of foundation pile.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardFromFoundationToOpenEmpty() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.FOUNDATION, 1, 0, PileType.OPEN, 1);
  }

  /**
   * Tests for the move method for invalid move of foundation to non-empty open.
   * Cannot take card out of foundation pile.
   */

  @Test (expected = IllegalArgumentException.class)
  public void moveCardFromFoundationToOpenNotEmpty() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 0, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.FOUNDATION, 1, 0, PileType.OPEN, 1);
  }

  /**
   * Tests for the isGameOver method for not moved game.
   */

  @Test
  public void isGameOverNotOver() {
    assertEquals(false, this.freecellShuffle.isGameOver());
  }

  /**
   * Tests for the isGameOver method for moved game.
   */

  @Test
  public void isGameOverNotOverPostMoveFromCascade() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    assertEquals(false, this.freecellNoShuffle.isGameOver());
  }

  /**
   * Tests for the isGameOver method for game state where foundation pile is not empty.
   */

  @Test
  public void isGameOverNotOverPostFoundationNotEmpty() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 11, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 1);
    assertEquals(false, this.freecellNoShuffle.isGameOver());
  }

  /**
   * Tests for the isGameOver method for completed game.
   */

  @Test
  public void isGameOverFinished() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 11, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 12, PileType.OPEN, 5);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 11, PileType.OPEN, 6);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 10, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 12, PileType.OPEN, 7);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 11, PileType.OPEN, 8);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 10, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 4, 0, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 6, 0, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 8, 0, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 5, 0, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 7, 0, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 1);

    this.freecellNoShuffle.move(PileType.CASCADE, 2, 9, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 8, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 7, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 6, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 8, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 7, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 6, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 9, PileType.OPEN, 5);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 8, PileType.OPEN, 6);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 7, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 9, PileType.OPEN, 7);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 8, PileType.OPEN, 8);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 7, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 4, 0, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 6, 0, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 8, 0, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 5, 0, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 7, 0, PileType.FOUNDATION, 0);
    this.freecellNoShuffle.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 0);

    this.freecellNoShuffle.move(PileType.CASCADE, 1, 6, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 5, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 4, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 3, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 5, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 4, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 5, PileType.OPEN, 5);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 4, PileType.OPEN, 6);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 3, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 6, PileType.OPEN, 7);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 5, PileType.OPEN, 8);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 4, 0, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 6, 0, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 8, 0, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 5, 0, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 7, 0, PileType.FOUNDATION, 2);
    this.freecellNoShuffle.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 2);

    this.freecellNoShuffle.move(PileType.CASCADE, 0, 3, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 2, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 1, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 2, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 1, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 1, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 2, PileType.OPEN, 5);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 1, PileType.OPEN, 6);
    this.freecellNoShuffle.move(PileType.CASCADE, 2, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 2, PileType.OPEN, 7);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 1, PileType.OPEN, 8);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 4, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 6, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 8, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 5, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 7, 0, PileType.FOUNDATION, 3);
    this.freecellNoShuffle.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 3);

    assertEquals(true, this.freecellNoShuffle.isGameOver());
  }

  /**
   * Tests for the getNumCardsInFoundationPile for empty foundation pile index.
   */

  @Test
  public void getNumCardsInFoundationPile() {
    assertEquals(0, this.freecellShuffle.getNumCardsInFoundationPile(1));
  }

  /**
   * Tests for the getNumCardsInFoundationPile for non-empty foundation pile index.
   */

  @Test
  public void getNumCardsInFoundationPileNotEmpty() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 11, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 1);
    assertEquals(2, this.freecellNoShuffle.getNumCardsInFoundationPile(1));
  }

  /**
   * Tests for the getNumCardsInCascadePile for non-empty cascade pile index.
   */

  @Test
  public void getNumCardsInCascadePile() {
    assertEquals(13, this.freecellShuffle.getNumCardsInCascadePile(0));
  }

  /**
   * Tests for the getNumCardsInCascadePile for cascade after move has been made.
   */

  @Test
  public void getNumCardsInCascadePilePostMove() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    assertEquals(9, this.freecellNoShuffle.getNumCardsInCascadePile(3));
  }

  /**
   * Tests for the getNumCascadePiles after game has started.
   */

  @Test
  public void getNumCascadePiles() {
    assertEquals(4, this.freecellShuffle.getNumCascadePiles());
  }

  /**
   * Tests for the getNumCascadePiles before game has started.
   */

  @Test
  public void getNumCascadePilesGameNotStart() {
    assertEquals(-1, this.notStartedFreecell.getNumCascadePiles());
  }

  /**
   * Tests for the getNumCardsInOpenPile of empty open pile.
   */

  @Test
  public void getNumCardsInOpenPileEmpty() {
    assertEquals(0, this.freecellShuffle.getNumCardsInOpenPile(1));
  }

  /**
   * Tests for the getNumCardsInOpenPile of non-empty open pile.
   */

  @Test
  public void getNumCardsInOpenPileNotEmpty() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    assertEquals(1, this.freecellNoShuffle.getNumCardsInOpenPile(0));
  }

  /**
   * Tests for the getNumOpenPiles after game has started.
   */

  @Test
  public void getNumOpenPiles() {
    assertEquals(4, this.freecellShuffle.getNumOpenPiles());
  }

  /**
   * Tests for the getNumOpenPiles before game has started.
   */

  @Test
  public void getNumOpenPilesGameNotStart() {
    assertEquals(-1, this.notStartedFreecell.getNumOpenPiles());
  }

  /**
   * Tests for the getFoundationCardAt with invalid pile index.
   */

  @Test (expected = IllegalArgumentException.class)
  public void getFoundationCardWrongPileIndex() {
    freecellShuffle.getFoundationCardAt(5, 0);
  }

  /**
   * Tests for the getFoundationCardAt with invalid card index.
   */

  @Test (expected = IllegalArgumentException.class)
  public void getFoundationCardWrongCardIndex() {
    freecellShuffle.getFoundationCardAt(2, 1);
  }

  /**
   * Tests for the getFoundationCardAt with no card in choosen pile.
   */

  @Test (expected = IllegalArgumentException.class)
  public void getFoundationCardEmpty() {
    freecellShuffle.getFoundationCardAt(0, 0);
  }

  /**
   * Tests for the getFoundationCardAt with one card.
   */

  @Test
  public void getFoundationCardValidFirst() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    assertEquals(new Card(Value.ACE, Suite.CLUBS), freecellNoShuffle.getFoundationCardAt(1, 0));
  }

  /**
   * Tests for the getFoundationCardAt with at least one card.
   */

  @Test
  public void getFoundationCardValidSecond() {
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.freecellNoShuffle.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 3);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 11, PileType.OPEN, 4);
    this.freecellNoShuffle.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 1);
    assertEquals(new Card(Value.TWO, Suite.CLUBS), freecellNoShuffle.getFoundationCardAt(1, 1));
  }

  /**
   * Tests for the getCascadeCardAt with invalid pile index.
   */

  @Test (expected = IllegalArgumentException.class)
  public void getCascadeCardWrongPileIndex() {
    freecellShuffle.getCascadeCardAt(5, 1);
  }

  /**
   * Tests for the getCascadeCardAt with invalid card index.
   */

  @Test (expected = IllegalArgumentException.class)
  public void getCascadeCardWrongCardIndex() {
    freecellShuffle.getCascadeCardAt(2, 13);
  }

  /**
   * Tests for the getCascadeCardAt with all valid parameters.
   */

  @Test
  public void getCascadeCardAtValid() {
    assertEquals(new Card(Value.TEN, Suite.CLUBS), freecellNoShuffle.getCascadeCardAt(0, 12));
  }

  /**
   * Tests for the getCascadeCardAt with all valid parameters and a move has been made in pile.
   */

  @Test
  public void getCascadeCardAtValidPostMove() {
    freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 1);
    assertEquals(new Card(Value.SIX, Suite.CLUBS), freecellNoShuffle.getCascadeCardAt(0, 11));
  }

  /**
   * Tests for the getOpenCardAt with an empty open pile at index.
   */

  @Test
  public void getOpenCardAtEmpty() {
    assertEquals(null, freecellShuffle.getOpenCardAt(3));
  }

  /**
   * Tests for the getOpenCardAt with an non-empty open pile at index.
   */

  @Test
  public void getOpenCardAtNonEmpty() {
    freecellNoShuffle.move(PileType.CASCADE, 0, 12, PileType.OPEN, 1);
    assertEquals(new Card(Value.TEN, Suite.CLUBS), freecellNoShuffle.getOpenCardAt(1));
  }
}