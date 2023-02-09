package cs3500.freecell.model.hw02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the methods of the Utils class: ensure that utils methods behave correctly.
 */

public class UtilsTest {
  Utils testUtils = new Utils();

  /**
   * Tests for checkAtLeast if min is neg.
   */

  @Test
  public void checkAtLeastMoreThanNeg() {
    assertEquals(0, testUtils.checkAtLeast(0, -1, "Num not at least min"));
  }

  /**
   * Tests for checkAtLeast if min is positive integer.
   */

  @Test
  public void checkAtLeastPosInt() {
    assertEquals(5, testUtils.checkAtLeast(5, 0, "Num not at least min"));
  }

  /**
   * Tests for checkAtLeast if min is same as num.
   */

  @Test
  public void checkAtLeastSame() {
    assertEquals(25, testUtils.checkAtLeast(25, 25, "Num not at least min"));
  }

  /**
   * Tests for checkAtLeast if num is big than min.
   */

  @Test (expected = IllegalArgumentException.class)
  public void checkAtLeastError() {
    testUtils.checkAtLeast(24, 25, "Num not at least min");
  }

  /**
   * Tests for checkRangeValid.
   */

  @Test
  public void checkRangeValid() {
    assertEquals(5,
        testUtils.checkRange(5, 0, 7, "Num not at least min"));
  }

  /**
   * Tests for checkRangeValid if min is neg.
   */

  @Test
  public void checkRangeValidWNeg() {
    assertEquals(0,
        testUtils.checkRange(0, -1, 5, "Num not at least min"));
  }

  /**
   * Tests for checkRangeValid if min and num are equal.
   */

  @Test
  public void checkRangeSameMin() {
    assertEquals(5,
        testUtils.checkRange(5, 5, 14, "Num not at least min"));
  }

  /**
   * Tests for checkRangeValid if max and num are equal.
   */

  @Test (expected = IllegalArgumentException.class)
  public void checkRangeSameMax() {
    testUtils.checkRange(31, 24, 31,"Num not at least min");
  }

  /**
   * Tests for checkRangeValid if num is less than num.
   */

  @Test (expected = IllegalArgumentException.class)
  public void checkRangeTooSmall() {
    testUtils.checkRange(4, 24, 31,"Num not at least min");
  }

  /**
   * Tests for checkRangeValid if num is greater than max.
   */

  @Test (expected = IllegalArgumentException.class)
  public void checkRangeTooBig() {
    testUtils.checkRange(78, 24, 31,"Num not at least min");
  }

}