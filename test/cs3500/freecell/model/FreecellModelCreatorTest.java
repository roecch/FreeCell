package cs3500.freecell.model;

import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.ComplexFreecellModel;
import org.junit.Test;

/**
 * Tests for the methods of the FreecellModelCreator class:
 * ensure that FreecellModelCreator methods behave correctly.
 */

public class FreecellModelCreatorTest {
  FreecellModelCreator creator = new FreecellModelCreator();

  /**
   * Tests create method for ability to return SimpleFreecellModel.
   */

  @Test
  public void createSingleModel() {
    assertEquals(true, creator.create(GameType.SINGLEMOVE) instanceof SimpleFreecellModel);
  }

  /**
   * Tests create method for ability to return ComplexFreecellModel.
   */

  @Test
  public void createMultiModel() {
    assertEquals(true, creator.create(GameType.MULTIMOVE) instanceof ComplexFreecellModel);
  }

  /**
   * Tests create method for ability to return Exception if given invalid GameType.
   */

  @Test (expected = IllegalArgumentException.class)
  public void createInvalidGameType() {
    creator.create(null);
  }

}