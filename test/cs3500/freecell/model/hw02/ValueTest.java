package cs3500.freecell.model.hw02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the methods of the Value Enum: ensure that Value methods behave correctly.
 */


public class ValueTest {

  /**
   * Test getValue for ace value.
   */

  @Test
  public void testGetValueAce() {
    assertEquals(1, Value.ACE.getValue());
  }

  /**
   * Test getValue for two value.
   */

  @Test
  public void testGetValueTwo() {
    assertEquals(2, Value.TWO.getValue());
  }

  /**
   * Test getValue for three value.
   */

  @Test
  public void testGetValueThree() {
    assertEquals(3, Value.THREE.getValue());
  }

  /**
   * Test getValue for four value.
   */

  @Test
  public void testGetValueFour() {
    assertEquals(4, Value.FOUR.getValue());
  }

  /**
   * Test getValue for five value.
   */

  @Test
  public void testGetValueFive() {
    assertEquals(5, Value.FIVE.getValue());
  }

  /**
   * Test getValue for ten value.
   */

  @Test
  public void testGetValueTen() {
    assertEquals(10, Value.TEN.getValue());
  }

  /**
   * Test getValue for Jack value.
   */

  @Test
  public void testGetValueJack() {
    assertEquals(11, Value.JACK.getValue());
  }

  /**
   * Test getValue for Queen value.
   */

  @Test
  public void testGetValueQueen() {
    assertEquals(12, Value.QUEEN.getValue());
  }

  /**
   * Test getValue for King value.
   */

  @Test
  public void testGetValueKing() {
    assertEquals(13, Value.KING.getValue());
  }

  /**
   * Test toString for ace value.
   */

  @Test
  public void testToStringAce() {
    assertEquals("A", Value.ACE.toString());
  }

  /**
   * Test toString for six value.
   */

  @Test
  public void testToStringSix() {
    assertEquals("6", Value.SIX.toString());
  }

  /**
   * Test toString for seven value.
   */

  @Test
  public void testToStringSeven() {
    assertEquals("7", Value.SEVEN.toString());
  }

  /**
   * Test toString for eight value.
   */

  @Test
  public void testToStringEight() {
    assertEquals("8", Value.EIGHT.toString());
  }

  /**
   * Test toString for nine value.
   */

  @Test
  public void testToStringNine() {
    assertEquals("9", Value.NINE.toString());
  }

  /**
   * Test toString for ten value.
   */

  @Test
  public void testToStringTen() {
    assertEquals("10", Value.TEN.toString());
  }

  /**
   * Test toString for Jack value.
   */

  @Test
  public void testToStringJack() {
    assertEquals("J", Value.JACK.toString());
  }

  /**
   * Test toString for Queen value.
   */

  @Test
  public void testToStringQueen() {
    assertEquals("Q", Value.QUEEN.toString());
  }

  /**
   * Test toString for King value.
   */

  @Test
  public void testToStringKing() {
    assertEquals("K", Value.KING.toString());
  }

  /**
   * Test isValid for King value.
   */

  @Test
  public void testIsValidKing() {
    assertEquals(true, Value.KING.isValid(Value.KING));
  }

  /**
   * Test isValid for Ace value.
   */

  @Test
  public void testIsValidAce() {
    assertEquals(true, Value.ACE.isValid(Value.ACE));
  }

  /**
   * Test isValid for five value.
   */

  @Test
  public void testIsValidFive() {
    assertEquals(true, Value.FIVE.isValid(Value.FIVE));
  }

  /**
   * Test isValid for Null value.
   */

  @Test
  public void testIsValidNull() {
    assertEquals(false, Value.TEN.isValid(null));
  }
}