package cs3500.freecell.model.hw02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the methods of the Suite Enum: ensure that Suite methods behave correctly.
 */

public class SuiteTest {

  /**
   * Test getSuite for Diamond suite.
   */

  @Test
  public void testGetSuiteDiamond() {
    assertEquals("♦", Suite.DIAMOND.getSuite());
  }

  /**
   * Test getSuite for Heart suite.
   */

  @Test
  public void testGetSuiteHeart() {
    assertEquals("♥", Suite.HEART.getSuite());
  }

  /**
   * Test getSuite for Spade suite.
   */

  @Test
  public void testGetSuiteSpade() {
    assertEquals("♠", Suite.SPADE.getSuite());
  }

  /**
   * Test getSuite for Clubs suite.
   */

  @Test
  public void testGetSuiteClubs() {
    assertEquals("♣", Suite.CLUBS.getSuite());
  }

  /**
   * Test toString for Diamond suite.
   */

  @Test
  public void testToStringDiamond() {
    assertEquals("♦", Suite.DIAMOND.getSuite());
  }

  /**
   * Test toString for Heart suite.
   */

  @Test
  public void testToStringHeart() {
    assertEquals("♥", Suite.HEART.getSuite());
  }

  /**
   * Test toString for Spade suite.
   */

  @Test
  public void testToStringSpade() {
    assertEquals("♠", Suite.SPADE.getSuite());
  }

  /**
   * Test toString for Clubs suite.
   */

  @Test
  public void testToStringClubs() {
    assertEquals("♣", Suite.CLUBS.getSuite());
  }

  /**
   * Test isValid for Diamond suite.
   */

  @Test
  public void testIsValidDiamond() {
    assertEquals(true, Suite.HEART.isValid(Suite.DIAMOND));
  }

  /**
   * Test isValid for Heart suite.
   */

  @Test
  public void testIsValidHeart() {
    assertEquals(true, Suite.SPADE.isValid(Suite.HEART));
  }

  /**
   * Test isValid for Spade suite.
   */

  @Test
  public void testIsValidSpade() {
    assertEquals(true, Suite.CLUBS.isValid(Suite.SPADE));
  }

  /**
   * Test isValid for Clubs suite.
   */

  @Test
  public void testIsValidClubs() {
    assertEquals(true, Suite.DIAMOND.isValid(Suite.CLUBS));
  }

  /**
   * Test isValid for Null suite.
   */

  @Test
  public void testIsValidNull() {
    assertEquals(false, Suite.DIAMOND.isValid(null));
  }
}