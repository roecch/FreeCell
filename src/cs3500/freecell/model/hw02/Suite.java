package cs3500.freecell.model.hw02;

/**
 * This is the enum representing all of the possible suites that a card can have.
 * Used to create all the possible combination of cards in a deck of cards.
 */

public enum Suite {
  DIAMOND("♦"), HEART("♥"), SPADE("♠"), CLUBS("♣");
  private final String suite;

  /**
   * Constructor for Value Enum.
   *
   * @param suite String corresponding to Suite
   * @throws IllegalArgumentException if suite is null
   */

  Suite(String suite) {
    if (suite == null) {
      throw new IllegalArgumentException("Invalid suite");
    }
    this.suite = suite;
  }

  /**
   * Called to retrieve the suite of suite.
   *
   * @return String of the value
   */

  String getSuite() {
    return this.suite;
  }

  /**
   * String based on what suite is called.
   *
   * @return String corresponding to suite
   */

  @Override
  public String toString() {
    return this.suite;
  }

  /**
   * Ensures that the given Suite is not null and matches one of the suites created in Enum.
   *
   * @param givenS Suite to check if valid
   * @return whether or not given suite is valid
   */

  public boolean isValid(Suite givenS) {
    if (givenS == null) {
      return false;
    }

    for (Suite s : Suite.values()) {
      if (s.suite == givenS.suite) {
        return true;
      }
    }
    return false;
  }
}
