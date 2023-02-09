package cs3500.freecell.model.hw04;

import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.hw02.Card;
import cs3500.freecell.model.hw02.Suite;
import cs3500.freecell.model.hw02.Value;
import org.junit.Test;

/**
 * Tests for the methods of the ComplexSolitaireRules class:
 * ensure that ComplexSolitaireRules methods behave correctly.
 */

public class ComplexSolitaireRulesTest {

  /**
   * Tests to place card on card with same color suite on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveSameColorSuite() {
    assertEquals(false,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.TEN, Suite.DIAMOND),
            new Card(Value.JACK, Suite.HEART)));
  }

  /**
   * Tests to place card on card with different color suite and right value on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCanMoveDiffColorSuite() {
    assertEquals(true,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.JACK, Suite.CLUBS),
            new Card(Value.QUEEN, Suite.DIAMOND)));
  }

  /**
   * Tests to place card on card with different color suite
   * and right value (wrong direction) on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveDiffColorSuite() {
    assertEquals(false,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.QUEEN, Suite.DIAMOND),
            new Card(Value.JACK, Suite.CLUBS)));
  }

  /**
   * Tests to place card on card with different color suite and wrong value on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveDiffColorWrongValue() {
    assertEquals(false,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.SEVEN, Suite.SPADE)));
  }

  /**
   * Tests to place card on card with same color suite and right value on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveSameSuiteRightValue() {
    assertEquals(false,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.QUEEN, Suite.DIAMOND)));
  }

  /**
   * Tests to place card on card with different color suite and same value on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveSameValue() {
    assertEquals(false,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.JACK, Suite.SPADE)));
  }

  /**
   * Tests to place card on empty cascade pile.
   */

  @Test
  public void placeOnCascadeEmptyCascade() {
    assertEquals(true,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            null));
  }

  /**
   * Tests to place card on card with same value and different color on open pile.
   */

  @Test
  public void placeOnOpenNotEmptySameValueDiffSuiteColor() {
    assertEquals(false,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.JACK, Suite.CLUBS)));
  }
  /**
   * Tests to place card on card with same color and different value on open pile.
   */

  @Test
  public void placeOnOpenNotEmptySameSuiteColorDiffValue() {
    assertEquals(false,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.TEN, Suite.HEART)));
  }

  /**
   * Tests to place card on empty open pile.
   */

  @Test
  public void placeOnOpenEmpty() {
    assertEquals(true,
        new ComplexSolitaireRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            null));
  }
}