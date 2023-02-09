package cs3500.freecell.model.hw02;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the methods of the Card Class: ensure that Card methods behave correctly.
 * Does not check for null cards since a card with null cannot be initialized
 */

public class CardTest {
  Card aceSpade;
  Card threeDia;
  Card jackClub;
  Card kingHeart;

  @Before
  public void setUp() {
    aceSpade = new Card(Value.ACE, Suite.SPADE);
    threeDia = new Card(Value.THREE, Suite.DIAMOND);
    jackClub = new Card(Value.JACK, Suite.CLUBS);
    kingHeart = new Card(Value.KING, Suite.HEART);
  }

  /**
   * Test getSuite for Spade string.
   */

  @Test
  public void getSuiteSpade() {
    assertEquals("♠", this.aceSpade.getSuite());
  }

  /**
   * Test getSuite for Diamond string.
   */

  @Test
  public void getSuiteDiamond() {
    assertEquals("♦", this.threeDia.getSuite());
  }

  /**
   * Test getSuite for Clubs string.
   */

  @Test
  public void getSuiteClubs() {
    assertEquals("♣", this.jackClub.getSuite());
  }

  /**
   * Test getSuite for Heart string.
   */

  @Test
  public void getSuiteHeart() {
    assertEquals("♥", this.kingHeart.getSuite());
  }

  /**
   * Test getValue for an ace value.
   */

  @Test
  public void getValueAce() {
    assertEquals(1, this.aceSpade.getValue());
  }

  /**
   * Test getValue for a number value.
   */

  @Test
  public void getValueThree() {
    assertEquals(3, this.threeDia.getValue());
  }

  /**
   * Test getValue for a face value.
   */

  @Test
  public void getValueFace() {
    assertEquals(11, this.jackClub.getValue());
  }

  /**
   * Test isValid for an valid card.
   */

  @Test
  public void isValidCardValid() {
    assertEquals(true, this.kingHeart.isValidCard());
  }

  /**
   * Test toString for an number card.
   */

  @Test
  public void testToStringNum() {
    assertEquals("3♦", this.threeDia.toString());
  }

  /**
   * Test toString for a face card.
   */

  @Test
  public void testToStringFace() {
    assertEquals("K♥", this.kingHeart.toString());
  }

  /**
   * Test toString for an ace card.
   */

  @Test
  public void testToStringAce() {
    assertEquals("A♠", this.aceSpade.toString());
  }

  /**
   * Test toString for an spade card since other suite were tested in past tests.
   */

  @Test
  public void testToStringOtherSuite() {
    assertEquals("J♣", this.jackClub.toString());
  }
}