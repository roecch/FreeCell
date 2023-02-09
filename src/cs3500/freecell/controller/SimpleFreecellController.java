package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for the simple freecell game controller.
 */

public class SimpleFreecellController implements FreecellController<ICard> {

  private final FreecellModel<ICard> model;
  private final Scanner scanner;
  private final FreecellView view;

  /**
   * Constructor for SimpleFreecellController.
   * @param model the freecell model
   * @param rd the Readable object
   * @param ap the Appendable object
   */

  public SimpleFreecellController(FreecellModel<ICard> model, Readable rd, Appendable ap) {
    if (rd == null || ap == null || model == null) {
      throw new IllegalArgumentException("Invalid parameter for Freecell Controller Constructor");
    }

    this.model = model;
    this.scanner = new Scanner(rd);
    this.view = new FreecellTextView(model, ap);
  }

  /**
   * Start and play a new game of freecell with the provided deck. This deck should be used as-is.
   * This method returns only when the game is over (either by winning or by quitting)
   *
   * @param deck        the deck to be used to play this game
   * @param numCascades the number of cascade piles
   * @param numOpens    the number of open piles
   * @param shuffle     shuffle the deck if true, false otherwise
   * @throws IllegalStateException    if writing to the Appendable object used by it fails
   * @throws IllegalArgumentException if the model or deck provided to it are null
   */

  @Override
  public void playGame(List<ICard> deck, int numCascades, int numOpens, boolean shuffle) {

    if (deck == null) {
      throw new IllegalArgumentException("Invalid Deck: null deck");
    }

    try {
      this.model.startGame(deck, numCascades, numOpens, shuffle);
    }
    catch (IllegalArgumentException e) {
      this.catchRenderMessageIO("Could not start game.");
      return;
    }

    this.catchRenderBoardIO();
    this.catchRenderMessageIO("\n");
    this.catchRenderMessageIO("Enter Moves.\n");
    this.createMoveBasedOnInput();

    if (this.model.isGameOver()) {
      this.catchRenderMessageIO("\nGame over.");
    }
  }

  /**
   * Plays the game by allowing player to enter parts of move.
   * Stops when game is over or when q is entered
   */

  private void createMoveBasedOnInput() {
    PileType srcPileLetter = null;
    int srcPileNum = -1;
    int cardIndex = -1;
    PileType destPileLetter = null;
    int destPileNum = -1;
    String curr;

    while (!this.model.isGameOver()) {

      try {
        curr = this.scanner.next();
      }
      catch (NoSuchElementException e) {
        throw new IllegalStateException("Readable Ran Out");
      }

      if (curr.contains("q") || curr.contains("Q")) {
        this.catchRenderMessageIO("Game quit prematurely.");
        return;
      }

      if (srcPileLetter == null && srcPileNum == -1 && !curr.equals("")) {
        try {
          srcPileLetter = this.getPileTypeFromInput(curr.substring(0, 1));
          srcPileNum = this.getIndexFromInput(curr.substring(1));
        }
        catch (IllegalArgumentException e) {
          this.catchRenderMessageIO("Invalid Source. Please re-enter.\n");
        }
      }
      else if (cardIndex == -1 && !curr.equals("")) {
        try {
          cardIndex = this.getIndexFromInput(curr);
        }
        catch (IllegalArgumentException e) {
          this.catchRenderMessageIO("Invalid Card Index. Please re-enter.\n");
        }
      }
      else if (destPileLetter == null && destPileNum == -1 && !curr.equals("")) {
        try {
          destPileLetter = this.getPileTypeFromInput(curr.substring(0, 1));
          destPileNum = this.getIndexFromInput(curr.substring(1));
        }
        catch (IllegalArgumentException e) {
          this.catchRenderMessageIO("Invalid Destination. Please re-enter.\n");
        }
      }

      if (srcPileLetter != null && srcPileNum != -1 && cardIndex != -1
          && destPileLetter != null && destPileNum != -1) {
        try {
          this.model
              .move(srcPileLetter, srcPileNum, cardIndex, destPileLetter, destPileNum);
          this.catchRenderBoardIO();
          this.catchRenderMessageIO("\n");
        }
        catch (IllegalArgumentException e) {
          this.catchRenderMessageIO("Invalid move. Try again. " + e.getMessage() + "\n");
        }

        srcPileLetter = null;
        srcPileNum = -1;
        cardIndex = -1;
        destPileLetter = null;
        destPileNum = -1;
      }
    }
  }

  /**
   * Gets PileType corresponding to the given string or
   * renders message if the string does not match any pile type.
   * @param stringPile the string of the pile type
   */

  private PileType getPileTypeFromInput(String stringPile) {
    PileType pileType;

    switch (stringPile) {
      case "C": {
        pileType = PileType.CASCADE;
        break;
      }
      case "F": {
        pileType = PileType.FOUNDATION;
        break;
      }
      case "O": {
        pileType = PileType.OPEN;
        break;
      }
      default: {
        throw new IllegalArgumentException("");
      }
    }
    return pileType;
  }

  /**
   * Gets index corresponding to the given string or
   * renders message if index is invalid (less then 1).
   * @param stringIndex the string of the pile type
   */

  private int getIndexFromInput(String stringIndex) {
    try {
      int index = Integer.parseInt(stringIndex);
      if (index >= 1) {
        return index - 1;
      }
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("");
    }
    return -1;
  }

  /**
   * Prints the current state of the game to the Appendable object.
   * @throws IllegalStateException if the Appendable object used fails
   */

  private void catchRenderBoardIO() {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed");
    }
  }

  /**
   * Prints the message to the Appendable object.
   * @param mes the String to send to Appendable object
   * @throws IllegalStateException if the Appendable object used fails
   */

  private void catchRenderMessageIO(String mes) {
    try {
      this.view.renderMessage(mes);
    }
    catch (IOException e) {
      throw new IllegalStateException("Appendable failed");
    }
  }
}