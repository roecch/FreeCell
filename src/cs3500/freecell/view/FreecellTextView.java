package cs3500.freecell.view;

import cs3500.freecell.model.FreecellModelState;
import java.io.IOException;

/**
 * Represents an interface for the view of a freecell state in the form of text.
 */

public class FreecellTextView implements FreecellView {

  private final FreecellModelState<?> model;
  private Appendable ap = System.out;

  /**
   * The construtor of FreecelltextView.
   *
   * @param model the FreecellModel of ? object
   */

  public FreecellTextView(FreecellModelState model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
  }

  /**
   * The construtor of FreecelltextView.
   *
   * @param model the FreecellModel of ? object
   */

  public FreecellTextView(FreecellModelState model, Appendable ap) {
    this(model);
    if (ap == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.ap = ap;
  }

  /**
   * Returns the freecell model as a String.
   *
   * @return String of the freecell model
   */

  @Override
  public String toString() {
    try {
      return this.writeFoundationPilesString() + this.writeOpenPilesString()
          + this.writeCascadePilesString();
    }
    catch (IllegalStateException exception) {
      return "";
    }
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   * @throws IOException if transmission of the board to the provided data destination fails
   */

  @Override
  public void renderBoard() throws IOException {
    try {
      this.ap.append(this.toString());
    }
    catch (IOException e) {
      throw new IOException("Appendable failed");
    }
  }

  /**
   * Render a specific message to the provided data destination.
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.ap.append(message);
    }
    catch (IOException e) {
      throw new IOException("Appendable failed");
    }
  }

  /**
   * Returns the freecell model foundation piles as a String.
   *
   * @return String of the freecell model
   */

  private String writeFoundationPilesString() {
    String compString = "";
    for (int i = 0; i < 4; i++) {
      compString += "F" + (i + 1) + ":";
      for (int j = 0; j < model.getNumCardsInFoundationPile(i); j++) {
        if (j != 0) {
          compString += ",";
        }
        compString += " " + model.getFoundationCardAt(i, j).toString();
      }
      compString += "\n";
    }
    return compString;
  }

  /**
   * Returns the freecell model open piles as a String.
   *
   * @return String of the freecell model open piles
   */

  private String writeOpenPilesString() {
    String compString = "";
    for (int i = 0; i < model.getNumOpenPiles(); i++) {
      compString += "O" + (i + 1) + ":";
      if (model.getOpenCardAt(i) != null) {
        compString += " " + model.getOpenCardAt(i).toString();
      }
      compString += "\n";
    }
    return compString;
  }

  /**
   * Returns the freecell model cascade piles as a String.
   *
   * @return String of the freecell model cascade piles
   */

  private String writeCascadePilesString() {
    String compString = "";
    for (int i = 0; i < model.getNumCascadePiles(); i++) {
      compString += "C" + (i + 1) + ":";
      for (int j = 0; j < model.getNumCardsInCascadePile(i); j++) {
        if (j != 0) {
          compString += ",";
        }
        compString += " " + model.getCascadeCardAt(i, j).toString();
      }
      if (i != model.getNumCascadePiles() - 1) {
        compString += "\n";
      }
    }
    return compString;
  }
}
