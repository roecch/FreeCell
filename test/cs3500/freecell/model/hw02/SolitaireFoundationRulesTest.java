package cs3500.freecell.model.hw02;

import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.PileType;
import org.junit.Test;

/**
 * Tests for the methods of the SolitaireFoundationRules Class:
 * ensure that SolitaireFoundationRules methods behave correctly.
 */

public class SolitaireFoundationRulesTest {
  ASolitaireRules soliFoundRuls = new SolitaireFoundationRules();

  /**
   * Tests for placeOnTop for all null param.
   */

  @Test
  public void placeOnTopAllNullParam() {
    assertEquals(false, soliFoundRuls.placeOnTop(null, null, null));
  }

  /**
   * Tests for placeOnTop for null next card.
   */

  @Test
  public void placeOnTopNullNextCard() {
    assertEquals(false,
        soliFoundRuls.placeOnTop(null, new Card(Value.TEN, Suite.SPADE), PileType.OPEN));
  }

  /**
   * Tests for placeOnTop for null current card.
   */

  @Test
  public void placeOnTopNullCurrentCard() {
    assertEquals(false,
        soliFoundRuls.placeOnTop(new Card(Value.TEN, Suite.SPADE), null, PileType.CASCADE));
  }

  /**
   * Tests for placeOnTop for the pile type that has not been tested.
   */

  @Test
  public void placeOnTopOtherPileType() {
    assertEquals(false,
        soliFoundRuls.placeOnTop(new Card(Value.TEN, Suite.SPADE), null, PileType.CASCADE));
  }

  /**
   * Tests for placeOnTop for all valid params.
   */

  @Test
  public void placeOnTopAllValidParam() {
    assertEquals(false,
        soliFoundRuls.placeOnTop(new Card(Value.TEN, Suite.SPADE),
            new Card(Value.NINE, Suite.SPADE), PileType.CASCADE));
  }
}