package cs3500.freecell.view;

import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the methods of the FreecellTextView Class:
 * ensure that FreecellTextView methods behave correctly.
 */

public class FreecellTextViewTest {
  FreecellModel<ICard> model = new SimpleFreecellModel();

  /**
   * Sets model prior to every test.
   */

  @Before
  public void setUp() {
    model.startGame(model.getDeck(), 4, 8, false);
  }

  /**
   * Test constructor to make sure throws exception if given null.
   */

  @Test (expected = IllegalArgumentException.class)
  public void freecellTextViewNullConstructor() {
    new FreecellTextView(null);
  }

  /**
   * Test for toString if model has not started.
   */

  @Test
  public void testToStringNotStarted() {
    assertEquals("", new FreecellTextView(new SimpleFreecellModel()).toString());
  }

  /**
   * Test for toString if model has not started.
   */

  @Test
  public void testToStringError() {
    FreecellModel<ICard> testModel = new SimpleFreecellModel();
    try {
      testModel.startGame(testModel.getDeck(), 1, 2, false);
    }
    catch (IllegalArgumentException e) {
      assertEquals("", new FreecellTextView(testModel).toString());
    }
  }

  /**
   * Test for toString if model has started but has no moves.
   */

  @Test
  public void testToStringStartedNoChange() {
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1:\nO2:\nO3:\nO4:\nO5:\nO6:\nO7:\nO8:\nC1: "
        + "A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣, 10♣\nC2: 2♦, 6♦, 10♦, A♥, 5♥, "
        + "9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\nC3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠, "
        + "4♣, 8♣, Q♣\nC4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣",
        new FreecellTextView(model).toString());
  }

  /**
   * Test for toString if model has started and moved card from cascade to open.
   */

  @Test
  public void testToStringCascadeToOpen() {
    model.move(PileType.CASCADE, 0, 12, PileType.OPEN, 1);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1:\nO2: 10♣\nO3:\nO4:\nO5:\nO6:\nO7:\nO8:\nC1: "
            + "A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣\nC2: 2♦, 6♦, 10♦, A♥, 5♥, "
            + "9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\nC3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠, "
            + "4♣, 8♣, Q♣\nC4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣",
        new FreecellTextView(model).toString());
  }

  /**
   * Test for toString if model has started and moved card from cascade to cascade.
   */

  @Test
  public void testToStringCascadeToCascade() {
    this.model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.model.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.model.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.model.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    this.model.move(PileType.CASCADE, 3, 8, PileType.OPEN, 4);
    this.model.move(PileType.CASCADE, 3, 7, PileType.OPEN, 5);
    this.model.move(PileType.CASCADE, 3, 6, PileType.OPEN, 6);
    this.model.move(PileType.CASCADE, 0, 12, PileType.CASCADE, 3);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1: K♣\nO2: 9♣\nO3: 5♣\nO4: A♣\nO5: 10♠\nO6: "
            + "6♠\nO7: 2♠\nO8:\nC1: A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣\nC2: 2♦, "
            + "6♦, 10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\nC3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, "
            + "A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣\nC4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 10♣",
        new FreecellTextView(model).toString());
  }

  /**
   * Test for toString if model has started and moved card from cascade to foundation.
   */

  @Test
  public void testToStringCascadeToFoundation() {
    this.model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.model.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.model.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.model.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    assertEquals("F1:\nF2: A♣\nF3:\nF4:\nO1: K♣\nO2: 9♣\nO3: 5♣\nO4:\nO5:\nO6:\nO7:\nO8:\nC1: "
            + "A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣, 10♣\nC2: 2♦, 6♦, 10♦, A♥, 5♥, "
            + "9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\nC3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠, "
            + "4♣, 8♣, Q♣\nC4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠",
        new FreecellTextView(model).toString());
  }

  /**
   * Test for toString if model has started and moved card from open to foundation.
   */

  @Test
  public void testToStringOpenToFoundation() {
    this.model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.model.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.model.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.model.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    this.model.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 1);
    assertEquals("F1:\nF2: A♣\nF3:\nF4:\nO1: K♣\nO2: 9♣\nO3: 5♣\nO4:\nO5:\nO6:\nO7:\nO8:\nC1: "
            + "A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣, 10♣\nC2: 2♦, 6♦, 10♦, A♥, 5♥, "
            + "9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\nC3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠, "
            + "4♣, 8♣, Q♣\nC4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠",
        new FreecellTextView(model).toString());
  }

  /**
   * Test for toString if model has started and moved card from open to cascade.
   */

  @Test
  public void testToStringOpenToCascade() {
    this.model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.model.move(PileType.CASCADE, 3, 11, PileType.OPEN, 1);
    this.model.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    this.model.move(PileType.CASCADE, 3, 9, PileType.OPEN, 3);
    this.model.move(PileType.CASCADE, 3, 8, PileType.OPEN, 4);
    this.model.move(PileType.CASCADE, 3, 7, PileType.OPEN, 5);
    this.model.move(PileType.CASCADE, 3, 6, PileType.OPEN, 6);
    this.model.move(PileType.OPEN, 4, 0, PileType.CASCADE, 3);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1: K♣\nO2: 9♣\nO3: 5♣\nO4: A♣\nO5:\nO6: 6♠\nO7: "
            + "2♠\nO8:\nC1: A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣, 10♣\nC2: 2♦, 6♦, "
            + "10♦, A♥, 5♥, 9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\nC3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, "
            + "5♠, 9♠, K♠, 4♣, 8♣, Q♣\nC4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 10♠",
        new FreecellTextView(model).toString());
  }

  /**
   * Test for toString if model has started and moved card from cascade to open.
   */

  @Test
  public void testToStringOpenToOpen() {
    this.model.move(PileType.CASCADE, 3, 12, PileType.OPEN, 0);
    this.model.move(PileType.OPEN, 0, 0, PileType.OPEN, 1);
    assertEquals("F1:\nF2:\nF3:\nF4:\nO1:\nO2: K♣\nO3:\nO4:\nO5:\nO6:\nO7:\nO8:\nC1: "
            + "A♦, 5♦, 9♦, K♦, 4♥, 8♥, Q♥, 3♠, 7♠, J♠, 2♣, 6♣, 10♣\nC2: 2♦, 6♦, 10♦, A♥, 5♥, "
            + "9♥, K♥, 4♠, 8♠, Q♠, 3♣, 7♣, J♣\nC3: 3♦, 7♦, J♦, 2♥, 6♥, 10♥, A♠, 5♠, 9♠, K♠, "
            + "4♣, 8♣, Q♣\nC4: 4♦, 8♦, Q♦, 3♥, 7♥, J♥, 2♠, 6♠, 10♠, A♣, 5♣, 9♣",
        new FreecellTextView(model).toString());
  }
}