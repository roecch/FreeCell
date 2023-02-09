package cs3500.freecell.model.hw02;

import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.PileType;
import org.junit.Test;

/**
 * Tests for the methods of the ASolitaireRules Class:
 * ensure that ASolitaireRules methods behave correctly.
 */

public class ASolitaireRulesTest {

  /**
   * Test if choosen next card is null.
   */

  @Test
  public void placeOnTopNullNext() {
    assertEquals(false, new SolitaireCascadeRules().placeOnTop(null,
        new Card(Value.ACE, Suite.SPADE), PileType.CASCADE));
  }

  /**
   * Tests for if switch case FOUNDATION works.
   */

  @Test
  public void placeOnTopFoundation() {
    assertEquals(true,
        new SolitaireCascadeRules().placeOnTop(new Card(Value.TWO, Suite.SPADE),
            new Card(Value.ACE, Suite.SPADE), PileType.FOUNDATION));
  }

  /**
   * Tests for if switch case CASCADE works.
   */

  @Test
  public void placeOnTopCascade() {
    assertEquals(true,
        new SolitaireCascadeRules().placeOnTop(new Card(Value.ACE, Suite.CLUBS),
            new Card(Value.TWO, Suite.HEART), PileType.CASCADE));
  }

  /**
   * Tests for if switch case OPEN works.
   */

  @Test
  public void placeOnTopOpen() {
    assertEquals(true,
        new SolitaireCascadeRules().placeOnTop(new Card(Value.ACE, Suite.SPADE),
        null, PileType.OPEN));
  }

  /**
   * Tests to place card on card accurately on foundation pile.
   */

  @Test
  public void placeOnFoundationNotEmptyCanMove() {
    assertEquals(true,
        new SolitaireFoundationRules().placeOnFoundation(
            new Card(Value.TWO, Suite.DIAMOND), new Card(Value.ACE, Suite.DIAMOND)));
  }

  /**
   * Tests to place card on card with wrong value on foundation pile.
   */

  @Test
  public void placeOnFoundationNotEmptyWrongValue() {
    assertEquals(false,
        new SolitaireCascadeRules().placeOnFoundation(
            new Card(Value.THREE, Suite.DIAMOND), new Card(Value.ACE, Suite.DIAMOND)));
  }

  /**
   * Tests to place card on card with wrong suite on foundation pile.
   */

  @Test
  public void placeOnFoundationNotEmptyWrongSuite() {
    assertEquals(false,
        new SolitaireOpenRules().placeOnFoundation(
            new Card(Value.TWO, Suite.CLUBS), new Card(Value.ACE, Suite.DIAMOND)));
  }

  /**
   * Tests to place ace on empty foundation pile.
   */

  @Test
  public void placeOnFoundationEmptyFoundationPlaceAceNext() {
    assertEquals(true,
        new SolitaireFoundationRules().placeOnFoundation(new Card(Value.ACE, Suite.DIAMOND),
            null));
  }

  /**
   * Tests to place non-ace on empty foundation pile.
   */

  @Test
  public void placeOnFoundationEmptyFoundationPlaceNotAceNext() {
    assertEquals(false,
        new SolitaireFoundationRules().placeOnFoundation(new Card(Value.JACK, Suite.DIAMOND),
            null));
  }

  /**
   * Tests to place card on card with same color suite on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveSameColorSuite() {
    assertEquals(false,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.TEN, Suite.DIAMOND),
            new Card(Value.JACK, Suite.HEART)));
  }

  /**
   * Tests to place card on card with different color suite and right value on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCanMoveDiffColorSuite() {
    assertEquals(true,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.JACK, Suite.CLUBS),
            new Card(Value.QUEEN, Suite.DIAMOND)));
  }

  /**
   * Tests to place card on card with different color suite
   * and right value (wrong direction) on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveDiffColorSuite() {
    assertEquals(false,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.QUEEN, Suite.DIAMOND),
            new Card(Value.JACK, Suite.CLUBS)));
  }

  /**
   * Tests to place card on card with different color suite and wrong value on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveDiffColorWrongValue() {
    assertEquals(false,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.SEVEN, Suite.SPADE)));
  }

  /**
   * Tests to place card on card with same color suite and right value on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveSameSuiteRightValue() {
    assertEquals(false,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.QUEEN, Suite.DIAMOND)));
  }

  /**
   * Tests to place card on card with different color suite and same value on cascade pile.
   */

  @Test
  public void placeOnCascadeNotEmptyCannotMoveSameValue() {
    assertEquals(false,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.JACK, Suite.SPADE)));
  }

  /**
   * Tests to place card on empty cascade pile.
   */

  @Test
  public void placeOnCascadeEmptyCascade() {
    assertEquals(true,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            null));
  }

  /**
   * Tests to place card on card with same value and different color on open pile.
   */

  @Test
  public void placeOnOpenNotEmptySameValueDiffSuiteColor() {
    assertEquals(false,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.JACK, Suite.CLUBS)));
  }
  /**
   * Tests to place card on card with same color and different value on open pile.
   */

  @Test
  public void placeOnOpenNotEmptySameSuiteColorDiffValue() {
    assertEquals(false,
        new SolitaireFoundationRules().placeOnCascade(new Card(Value.JACK, Suite.DIAMOND),
            new Card(Value.TEN, Suite.HEART)));
  }

  /**
   * Tests to place card on empty open pile.
   */

  @Test
  public void placeOnOpenEmpty() {
    assertEquals(true,
        new SolitaireFoundationRules().placeOnOpen(new Card(Value.JACK, Suite.DIAMOND),
            null));
  }

  /**
   * Tests to getColor on card with heart suite.
   */

  @Test
  public void getColorHeart() {
    assertEquals("red",
        new SolitaireFoundationRules().getColor(new Card(Value.ACE, Suite.HEART)));
  }

  /**
   * Tests to getColor on card with diamond suite.
   */

  @Test
  public void getColorDiamond() {
    assertEquals("red",
        new SolitaireFoundationRules().getColor(new Card(Value.ACE, Suite.DIAMOND)));
  }

  /**
   * Tests to getColor on card with clubs suite.
   */

  @Test
  public void getColorClubs() {
    assertEquals("black",
        new SolitaireFoundationRules().getColor(new Card(Value.ACE, Suite.CLUBS)));
  }

  /**
   * Tests to getColor on card with spade suite.
   */

  @Test
  public void getColorSpade() {
    assertEquals("black",
        new SolitaireFoundationRules().getColor(new Card(Value.ACE, Suite.SPADE)));
  }
}