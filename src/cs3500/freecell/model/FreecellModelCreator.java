package cs3500.freecell.model;

import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.ComplexFreecellModel;

/**
 * This is the factory class for Freecell models.
 */
public class FreecellModelCreator {

  /**
   * This is the enum representing all of the possible gameTypes of a FreecellModel.
   */

  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Returns the Freecellmodel corresponding to the given type.
   *
   * @param type GameType
   * @return Freecellmodel corresponding to the given type
   */

  public static FreecellModel create(GameType type) {
    try {
      FreecellModel modelType;

      switch (type) {
        case SINGLEMOVE: {
          modelType = new SimpleFreecellModel();
          break;
        }
        case MULTIMOVE: {
          modelType = new ComplexFreecellModel();
          break;
        }
        default:
          throw new IllegalStateException("");
      }
      return modelType;
    }
    catch (Exception e) {
      throw new IllegalArgumentException("Invalid GameType");
    }
  }

}