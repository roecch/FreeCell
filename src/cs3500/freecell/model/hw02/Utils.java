package cs3500.freecell.model.hw02;

/**
 * This is a class to store utilities.
 * Used to ensure given parameters are valid with other given parameters.
 */

public class Utils {

  /**
   * Checks if value is at least the min.
   * Else returns error message.
   *
   * @param num the given num to check if it is at least min
   * @param min the given minimum
   * @param msg message to throw is num is not at least min
   * @return the int num if it is at least the min
   * @throws IllegalArgumentException if num is not at least min
   */
  public int checkAtLeast(int num, int min, String msg) {
    if (num >= min) {
      return num;
    }
    else {
      throw new IllegalArgumentException(msg);
    }
  }

  /**
   * Checks if value is at least the min and less then max.
   * Else returns error message.
   *
   * @param num the given num to check if it is at least min and less then max
   * @param minIndex the given minimum
   * @param maxIndex the given maximum
   * @param msg message to throw is num is not at least min and less then max
   * @return the int num if it is at least the min and less then max
   * @throws IllegalArgumentException if num is not at least min and less then max
   */
  public int checkRange(int num, int minIndex, int maxIndex, String msg) {
    if (this.checkAtLeast(num, minIndex, msg) < maxIndex) {
      return num;
    }
    else {
      throw new IllegalArgumentException(msg);
    }
  }
}
