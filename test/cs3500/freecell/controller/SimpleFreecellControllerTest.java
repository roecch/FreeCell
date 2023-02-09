package cs3500.freecell.controller;

import static org.junit.Assert.assertEquals;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.io.InputStreamReader;
import java.io.StringReader;
import org.junit.Test;

/**
 * Tests for the SimpleFreecellController class.
 */

public class SimpleFreecellControllerTest {

  FreecellModel<ICard> testModel = new SimpleFreecellModel();

  /**
   * Tests the SimpleFreecellController constructor with all valid parameters.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorValid() {
    new SimpleFreecellController(testModel, new InputStreamReader(System.in), System.out)
        .playGame(null, 4, 4, true);
  }

  /**
   * Tests the SimpleFreecellController constructor with invalid Readable.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorInvalidReadable() {
    new SimpleFreecellController(testModel, null, System.out);
  }

  /**
   * Tests the SimpleFreecellController constructor with invalid Appendable.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorInvalidAppendable() {
    new SimpleFreecellController(testModel, new InputStreamReader(System.in), null);
  }

  /**
   * Tests the SimpleFreecellController constructor with invalid model.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorInvalidModel() {
    new SimpleFreecellController(null, new InputStreamReader(System.in), System.out);
  }

  /**
   * Tests the SimpleFreecellController play game for if given null deck.
   */

  @Test (expected = IllegalArgumentException.class)
  public void testPlayGameNullDeck() {

    StringReader in = new StringReader("");
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(null, 4, 4, false);
  }

  /**
   * Tests the SimpleFreecellController play game for if given invalid game parameters.
   */

  @Test
  public void testPlayGameInvalidGameParam() {
    StringReader in = new StringReader("C1 13 O1");
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), -1, -1, false);
    assertEquals(true, out.indexOf("Could not start game.") != -1);
  }

  /**
   * Tests the SimpleFreecellController for if Appendable fails.
   */

  @Test (expected = IllegalStateException.class)
  public void testFailedAppendable() {
    StringReader in = new StringReader("C1 13 O1");
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

  }

  /**
   * Tests the SimpleFreecellController for an input of lowercase q to quit.
   */

  @Test
  public void testInputOfQuitLowercaseFirst() {
    StringBuilder input = new StringBuilder();
    input.append("q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals("Game quit prematurely.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of uppercase Q to quit.
   */

  @Test
  public void testInputOfQuitUppercaseFirst() {
    StringBuilder input = new StringBuilder();
    input.append("Q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals("Game quit prematurely.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of q to quit after source.
   */

  @Test
  public void testInputOfQuitSecond() {
    StringBuilder input = new StringBuilder();
    input.append("C1 q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals("Game quit prematurely.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of q to quit after source and card index.
   */

  @Test
  public void testInputOfQuitThird() {
    StringBuilder input = new StringBuilder();
    input.append("C1 13 q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals("Game quit prematurely.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of q to quit after move has been made.
   */

  @Test
  public void testInputOfQuitAfterMove() {
    StringBuilder input = new StringBuilder();
    input.append("C1 13 O1 q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals("Game quit prematurely.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input invalid source pile type.
   */

  @Test
  public void testInputOfInvalidSourcePileType() {
    StringBuilder input = new StringBuilder();
    input.append("U1 q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals(true, out.indexOf("Invalid Source. Please re-enter.") != 1);
  }

  /**
   * Tests the SimpleFreecellController for an input invalid source pile index.
   */

  @Test
  public void testInputOfInvalidSourceIndex() {
    StringBuilder input = new StringBuilder();
    input.append("C0 13 O1 q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals(true,
        out.indexOf("Invalid move. Try again. Invalid Cascade Pile Card Index") != 1);
  }

  /**
   * Tests the SimpleFreecellController for an input invalid card index.
   */

  @Test
  public void testInputOfInvalidCardIndex() {
    StringBuilder input = new StringBuilder();
    input.append("C1 0 O1 q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals(true,
        out.indexOf("Invalid move. Try again. Invalid Cascade Pile Card Index") != 1);
  }

  /**
   * Tests the SimpleFreecellController for an input invalid destination pile type.
   */

  @Test
  public void testInputOfInvalidDestinationPileType() {
    StringBuilder input = new StringBuilder();
    input.append("C1 13 Y1 q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals(true, out.indexOf("Invalid Destination. Please re-enter.") != 1);
  }

  /**
   * Tests the SimpleFreecellController for an input invalid destination pile index.
   */

  @Test
  public void testInputOfInvalidDestinationIndex() {
    StringBuilder input = new StringBuilder();
    input.append("C1 13 O5 q");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);

    assertEquals(true, out.indexOf("Invalid move. Try again. Invalid Open Pile Card Index") != 1);
  }

  /**
   * Tests the SimpleFreecellController for an input of one part of move at a time.
   */

  @Test
  public void testCompleteGameEnterMoveNotAtOnce() {
    StringBuilder input = new StringBuilder();
    input.append("C4\n13\nO1\n");
    input.append("C4\n12\nO2\n");
    input.append("C4\n11\nO3\n");
    input.append("C4\n10\nF2\n");
    input.append("C1\n13\nO4\n");
    input.append("C1\n12\nO5\n");
    input.append("C1\n11\nF2\n");
    input.append("C2\n13\nO6\n");
    input.append("C2\n12\nO7\n");
    input.append("C2\n11\nF2\n");
    input.append("C3\n13\nO8\n");
    input.append("C3\n12\nO9\n");
    input.append("C3\n11\nF2\n");
    input.append("O3\n1\nF2\n");
    input.append("O5\n1\nF2\n");
    input.append("O7\n1\nF2\n");
    input.append("O9\n1\nF2\n");
    input.append("O2\n1\nF2\n");
    input.append("O4\n1\nF2\n");
    input.append("O6\n1\nF2\n");
    input.append("O8\n1\nF2\n");
    input.append("O1\n1\nF2\n");

    input.append("C3\n10\nO1\n");
    input.append("C3\n9\nO2\n");
    input.append("C3\n8\nO3\n");
    input.append("C3\n7\nF1\n");
    input.append("C4\n9\nO4\n");
    input.append("C4\n8\nO5\n");
    input.append("C4\n7\nF1\n");
    input.append("C1\n10\nO6\n");
    input.append("C1\n9\nO7\n");
    input.append("C1\n8\nF1\n");
    input.append("C2\n10\nO8\n");
    input.append("C2\n9\nO9\n");
    input.append("C2\n8\nF1\n");
    input.append("O3\n1\nF1\n");
    input.append("O5\n1\nF1\n");
    input.append("O7\n1\nF1\n");
    input.append("O9\n1\nF1\n");
    input.append("O2\n1\nF1\n");
    input.append("O4\n1\nF1\n");
    input.append("O6\n1\nF1\n");
    input.append("O8\n1\nF1\n");
    input.append("O1\n1\nF1\n");

    input.append("C2\n7\nO1\n");
    input.append("C2\n6\nO2\n");
    input.append("C2\n5\nO3\n");
    input.append("C2\n4\nF3\n");
    input.append("C3\n6\nO4\n");
    input.append("C3\n5\nO5\n");
    input.append("C3\n4\nF3\n");
    input.append("C4\n6\nO6\n");
    input.append("C4\n5\nO7\n");
    input.append("C4\n4\nF3\n");
    input.append("C1\n7\nO8\n");
    input.append("C1\n6\nO9\n");
    input.append("C1\n5\nF3\n");
    input.append("O3\n1\nF3\n");
    input.append("O5\n1\nF3\n");
    input.append("O7\n1\nF3\n");
    input.append("O9\n1\nF3\n");
    input.append("O2\n1\nF3\n");
    input.append("O4\n1\nF3\n");
    input.append("O6\n1\nF3\n");
    input.append("O8\n1\nF3\n");
    input.append("O1\n1\nF3\n");

    input.append("C1\n4\nO1\n");
    input.append("C1\n3\nO2\n");
    input.append("C1\n2\nO3\n");
    input.append("C1\n1\nF4\n");
    input.append("C2\n3\nO4\n");
    input.append("C2\n2\nO5\n");
    input.append("C2\n1\nF4\n");
    input.append("C3\n3\nO6\n");
    input.append("C3\n2\nO7\n");
    input.append("C3\n1\nF4\n");
    input.append("C4\n3\nO8\n");
    input.append("C4\n2\nO9\n");
    input.append("C4\n1\nF4\n");
    input.append("O3\n1\nF4\n");
    input.append("O5\n1\nF4\n");
    input.append("O7\n1\nF4\n");
    input.append("O9\n1\nF4\n");
    input.append("O2\n1\nF4\n");
    input.append("O4\n1\nF4\n");
    input.append("O6\n1\nF4\n");
    input.append("O8\n1\nF4\n");
    input.append("O1\n1\nF4\n");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 12, false);

    assertEquals("Game over.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of source and card index together.
   */

  @Test
  public void testCompleteGameEnterMoveSourceAndCardIndexTogether() {
    StringBuilder input = new StringBuilder();
    input.append("C4 13\nO1\n");
    input.append("C4 12\nO2\n");
    input.append("C4 11\nO3\n");
    input.append("C4 10\nF2\n");
    input.append("C1 13\nO4\n");
    input.append("C1 12\nO5\n");
    input.append("C1 11\nF2\n");
    input.append("C2 13\nO6\n");
    input.append("C2 12\nO7\n");
    input.append("C2 11\nF2\n");
    input.append("C3 13\nO8\n");
    input.append("C3 12\nO9\n");
    input.append("C3 11\nF2\n");
    input.append("O3 1\nF2\n");
    input.append("O5 1\nF2\n");
    input.append("O7 1\nF2\n");
    input.append("O9 1\nF2\n");
    input.append("O2 1\nF2\n");
    input.append("O4 1\nF2\n");
    input.append("O6 1\nF2\n");
    input.append("O8 1\nF2\n");
    input.append("O1 1\nF2\n");

    input.append("C3 10\nO1\n");
    input.append("C3 9\nO2\n");
    input.append("C3 8\nO3\n");
    input.append("C3 7\nF1\n");
    input.append("C4 9\nO4\n");
    input.append("C4 8\nO5\n");
    input.append("C4 7\nF1\n");
    input.append("C1 10\nO6\n");
    input.append("C1 9\nO7\n");
    input.append("C1 8\nF1\n");
    input.append("C2 10\nO8\n");
    input.append("C2 9\nO9\n");
    input.append("C2 8\nF1\n");
    input.append("O3 1\nF1\n");
    input.append("O5 1\nF1\n");
    input.append("O7 1\nF1\n");
    input.append("O9 1\nF1\n");
    input.append("O2 1\nF1\n");
    input.append("O4 1\nF1\n");
    input.append("O6 1\nF1\n");
    input.append("O8 1\nF1\n");
    input.append("O1 1\nF1\n");

    input.append("C2 7\nO1\n");
    input.append("C2 6\nO2\n");
    input.append("C2 5\nO3\n");
    input.append("C2 4\nF3\n");
    input.append("C3 6\nO4\n");
    input.append("C3 5\nO5\n");
    input.append("C3 4\nF3\n");
    input.append("C4 6\nO6\n");
    input.append("C4 5\nO7\n");
    input.append("C4 4\nF3\n");
    input.append("C1 7\nO8\n");
    input.append("C1 6\nO9\n");
    input.append("C1 5\nF3\n");
    input.append("O3 1\nF3\n");
    input.append("O5 1\nF3\n");
    input.append("O7 1\nF3\n");
    input.append("O9 1\nF3\n");
    input.append("O2 1\nF3\n");
    input.append("O4 1\nF3\n");
    input.append("O6 1\nF3\n");
    input.append("O8 1\nF3\n");
    input.append("O1 1\nF3\n");

    input.append("C1 4\nO1\n");
    input.append("C1 3\nO2\n");
    input.append("C1 2\nO3\n");
    input.append("C1 1\nF4\n");
    input.append("C2 3\nO4\n");
    input.append("C2 2\nO5\n");
    input.append("C2 1\nF4\n");
    input.append("C3 3\nO6\n");
    input.append("C3 2\nO7\n");
    input.append("C3 1\nF4\n");
    input.append("C4 3\nO8\n");
    input.append("C4 2\nO9\n");
    input.append("C4 1\nF4\n");
    input.append("O3 1\nF4\n");
    input.append("O5 1\nF4\n");
    input.append("O7 1\nF4\n");
    input.append("O9 1\nF4\n");
    input.append("O2 1\nF4\n");
    input.append("O4 1\nF4\n");
    input.append("O6 1\nF4\n");
    input.append("O8 1\nF4\n");
    input.append("O1 1\nF4\n");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 12, false);

    assertEquals("Game over.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of card index and foundation together.
   */

  @Test
  public void testCompleteGameEnterCardIndexAndDestinationTogether() {
    StringBuilder input = new StringBuilder();
    input.append("C4\n13 O1\n");
    input.append("C4\n12 O2\n");
    input.append("C4\n11 O3\n");
    input.append("C4\n10 F2\n");
    input.append("C1\n13 O4\n");
    input.append("C1\n12 O5\n");
    input.append("C1\n11 F2\n");
    input.append("C2\n13 O6\n");
    input.append("C2\n12 O7\n");
    input.append("C2\n11 F2\n");
    input.append("C3\n13 O8\n");
    input.append("C3\n12 O9\n");
    input.append("C3\n11 F2\n");
    input.append("O3\n1 F2\n");
    input.append("O5\n1 F2\n");
    input.append("O7\n1 F2\n");
    input.append("O9\n1 F2\n");
    input.append("O2\n1 F2\n");
    input.append("O4\n1 F2\n");
    input.append("O6\n1 F2\n");
    input.append("O8\n1 F2\n");
    input.append("O1\n1 F2\n");

    input.append("C3\n10 O1\n");
    input.append("C3\n9 O2\n");
    input.append("C3\n8 O3\n");
    input.append("C3\n7 F1\n");
    input.append("C4\n9 O4\n");
    input.append("C4\n8 O5\n");
    input.append("C4\n7 F1\n");
    input.append("C1\n10 O6\n");
    input.append("C1\n9 O7\n");
    input.append("C1\n8 F1\n");
    input.append("C2\n10 O8\n");
    input.append("C2\n9 O9\n");
    input.append("C2\n8 F1\n");
    input.append("O3\n1 F1\n");
    input.append("O5\n1 F1\n");
    input.append("O7\n1 F1\n");
    input.append("O9\n1 F1\n");
    input.append("O2\n1 F1\n");
    input.append("O4\n1 F1\n");
    input.append("O6\n1 F1\n");
    input.append("O8\n1 F1\n");
    input.append("O1\n1 F1\n");

    input.append("C2\n7 O1\n");
    input.append("C2\n6 O2\n");
    input.append("C2\n5 O3\n");
    input.append("C2\n4 F3\n");
    input.append("C3\n6 O4\n");
    input.append("C3\n5 O5\n");
    input.append("C3\n4 F3\n");
    input.append("C4\n6 O6\n");
    input.append("C4\n5 O7\n");
    input.append("C4\n4 F3\n");
    input.append("C1\n7 O8\n");
    input.append("C1\n6 O9\n");
    input.append("C1\n5 F3\n");
    input.append("O3\n1 F3\n");
    input.append("O5\n1 F3\n");
    input.append("O7\n1 F3\n");
    input.append("O9\n1 F3\n");
    input.append("O2\n1 F3\n");
    input.append("O4\n1 F3\n");
    input.append("O6\n1 F3\n");
    input.append("O8\n1 F3\n");
    input.append("O1\n1 F3\n");

    input.append("C1\n4 O1\n");
    input.append("C1\n3 O2\n");
    input.append("C1\n2 O3\n");
    input.append("C1\n1 F4\n");
    input.append("C2\n3 O4\n");
    input.append("C2\n2 O5\n");
    input.append("C2\n1 F4\n");
    input.append("C3\n3 O6\n");
    input.append("C3\n2 O7\n");
    input.append("C3\n1 F4\n");
    input.append("C4\n3 O8\n");
    input.append("C4\n2 O9\n");
    input.append("C4\n1 F4\n");
    input.append("O3\n1 F4\n");
    input.append("O5\n1 F4\n");
    input.append("O7\n1 F4\n");
    input.append("O9\n1 F4\n");
    input.append("O2\n1 F4\n");
    input.append("O4\n1 F4\n");
    input.append("O6\n1 F4\n");
    input.append("O8\n1 F4\n");
    input.append("O1\n1 F4\n");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 12, false);

    assertEquals("Game over.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of destination and source together.
   */

  @Test
  public void testCompleteGameEnterDestinationAndSourceTogether() {
    StringBuilder input = new StringBuilder();
    input.append("C4\n13\nO1 ");
    input.append("C4\n12\nO2 ");
    input.append("C4\n11\nO3 ");
    input.append("C4\n10\nF2 ");
    input.append("C1\n13\nO4 ");
    input.append("C1\n12\nO5 ");
    input.append("C1\n11\nF2 ");
    input.append("C2\n13\nO6 ");
    input.append("C2\n12\nO7 ");
    input.append("C2\n11\nF2 ");
    input.append("C3\n13\nO8 ");
    input.append("C3\n12\nO9 ");
    input.append("C3\n11\nF2 ");
    input.append("O3\n1\nF2 ");
    input.append("O5\n1\nF2 ");
    input.append("O7\n1\nF2 ");
    input.append("O9\n1\nF2 ");
    input.append("O2\n1\nF2 ");
    input.append("O4\n1\nF2 ");
    input.append("O6\n1\nF2 ");
    input.append("O8\n1\nF2 ");
    input.append("O1\n1\nF2 ");

    input.append("C3\n10\nO1 ");
    input.append("C3\n9\nO2 ");
    input.append("C3\n8\nO3 ");
    input.append("C3\n7\nF1 ");
    input.append("C4\n9\nO4 ");
    input.append("C4\n8\nO5 ");
    input.append("C4\n7\nF1 ");
    input.append("C1\n10\nO6 ");
    input.append("C1\n9\nO7 ");
    input.append("C1\n8\nF1 ");
    input.append("C2\n10\nO8 ");
    input.append("C2\n9\nO9 ");
    input.append("C2\n8\nF1 ");
    input.append("O3\n1\nF1 ");
    input.append("O5\n1\nF1 ");
    input.append("O7\n1\nF1 ");
    input.append("O9\n1\nF1 ");
    input.append("O2\n1\nF1 ");
    input.append("O4\n1\nF1 ");
    input.append("O6\n1\nF1 ");
    input.append("O8\n1\nF1 ");
    input.append("O1\n1\nF1 ");

    input.append("C2\n7\nO1 ");
    input.append("C2\n6\nO2 ");
    input.append("C2\n5\nO3 ");
    input.append("C2\n4\nF3 ");
    input.append("C3\n6\nO4 ");
    input.append("C3\n5\nO5 ");
    input.append("C3\n4\nF3 ");
    input.append("C4\n6\nO6 ");
    input.append("C4\n5\nO7 ");
    input.append("C4\n4\nF3 ");
    input.append("C1\n7\nO8 ");
    input.append("C1\n6\nO9 ");
    input.append("C1\n5\nF3 ");
    input.append("O3\n1\nF3 ");
    input.append("O5\n1\nF3 ");
    input.append("O7\n1\nF3 ");
    input.append("O9\n1\nF3 ");
    input.append("O2\n1\nF3 ");
    input.append("O4\n1\nF3 ");
    input.append("O6\n1\nF3 ");
    input.append("O8\n1\nF3 ");
    input.append("O1\n1\nF3 ");

    input.append("C1\n4\nO1 ");
    input.append("C1\n3\nO2 ");
    input.append("C1\n2\nO3 ");
    input.append("C1\n1\nF4 ");
    input.append("C2\n3\nO4 ");
    input.append("C2\n2\nO5 ");
    input.append("C2\n1\nF4 ");
    input.append("C3\n3\nO6 ");
    input.append("C3\n2\nO7 ");
    input.append("C3\n1\nF4 ");
    input.append("C4\n3\nO8 ");
    input.append("C4\n2\nO9 ");
    input.append("C4\n1\nF4 ");
    input.append("O3\n1\nF4 ");
    input.append("O5\n1\nF4 ");
    input.append("O7\n1\nF4 ");
    input.append("O9\n1\nF4 ");
    input.append("O2\n1\nF4 ");
    input.append("O4\n1\nF4 ");
    input.append("O6\n1\nF4 ");
    input.append("O8\n1\nF4 ");
    input.append("O1\n1\nF4 ");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 12, false);

    assertEquals("Game over.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of a complete move at once.
   */

  @Test
  public void testCompleteGameEnterMoveAtOnce() {
    StringBuilder input = new StringBuilder();
    input.append("C4 13 O1\n");
    input.append("C4 12 O2\n");
    input.append("C4 11 O3\n");
    input.append("C4 10 F2\n");
    input.append("C1 13 O4\n");
    input.append("C1 12 O5\n");
    input.append("C1 11 F2\n");
    input.append("C2 13 O6\n");
    input.append("C2 12 O7\n");
    input.append("C2 11 F2\n");
    input.append("C3 13 O8\n");
    input.append("C3 12 O9\n");
    input.append("C3 11 F2\n");
    input.append("O3 1 F2\n");
    input.append("O5 1 F2\n");
    input.append("O7 1 F2\n");
    input.append("O9 1 F2\n");
    input.append("O2 1 F2\n");
    input.append("O4 1 F2\n");
    input.append("O6 1 F2\n");
    input.append("O8 1 F2\n");
    input.append("O1 1 F2\n");

    input.append("C3 10 O1\n");
    input.append("C3 9 O2\n");
    input.append("C3 8 O3\n");
    input.append("C3 7 F1\n");
    input.append("C4 9 O4\n");
    input.append("C4 8 O5\n");
    input.append("C4 7 F1\n");
    input.append("C1 10 O6\n");
    input.append("C1 9 O7\n");
    input.append("C1 8 F1\n");
    input.append("C2 10 O8\n");
    input.append("C2 9 O9\n");
    input.append("C2 8 F1\n");
    input.append("O3 1 F1\n");
    input.append("O5 1 F1\n");
    input.append("O7 1 F1\n");
    input.append("O9 1 F1\n");
    input.append("O2 1 F1\n");
    input.append("O4 1 F1\n");
    input.append("O6 1 F1\n");
    input.append("O8 1 F1\n");
    input.append("O1 1 F1\n");

    input.append("C2 7 O1\n");
    input.append("C2 6 O2\n");
    input.append("C2 5 O3\n");
    input.append("C2 4 F3\n");
    input.append("C3 6 O4\n");
    input.append("C3 5 O5\n");
    input.append("C3 4 F3\n");
    input.append("C4 6 O6\n");
    input.append("C4 5 O7\n");
    input.append("C4 4 F3\n");
    input.append("C1 7 O8\n");
    input.append("C1 6 O9\n");
    input.append("C1 5 F3\n");
    input.append("O3 1 F3\n");
    input.append("O5 1 F3\n");
    input.append("O7 1 F3\n");
    input.append("O9 1 F3\n");
    input.append("O2 1 F3\n");
    input.append("O4 1 F3\n");
    input.append("O6 1 F3\n");
    input.append("O8 1 F3\n");
    input.append("O1 1 F3\n");

    input.append("C1 4 O1\n");
    input.append("C1 3 O2\n");
    input.append("C1 2 O3\n");
    input.append("C1 1 F4\n");
    input.append("C2 3 O4\n");
    input.append("C2 2 O5\n");
    input.append("C2 1 F4\n");
    input.append("C3 3 O6\n");
    input.append("C3 2 O7\n");
    input.append("C3 1 F4\n");
    input.append("C4 3 O8\n");
    input.append("C4 2 O9\n");
    input.append("C4 1 F4\n");
    input.append("O3 1 F4\n");
    input.append("O5 1 F4\n");
    input.append("O7 1 F4\n");
    input.append("O9 1 F4\n");
    input.append("O2 1 F4\n");
    input.append("O4 1 F4\n");
    input.append("O6 1 F4\n");
    input.append("O8 1 F4\n");
    input.append("O1 1 F4\n");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 12, false);

    assertEquals("Game over.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for an input of two moves at once.
   */

  @Test
  public void testCompleteGameEnterTwoMoves() {
    StringBuilder input = new StringBuilder();
    input.append("C4 13 O1 ");
    input.append("C4 12 O2\n");
    input.append("C4 11 O3 ");
    input.append("C4 10 F2\n");
    input.append("C1 13 O4 ");
    input.append("C1 12 O5\n");
    input.append("C1 11 F2 ");
    input.append("C2 13 O6\n");
    input.append("C2 12 O7 ");
    input.append("C2 11 F2\n");
    input.append("C3 13 O8 ");
    input.append("C3 12 O9\n");
    input.append("C3 11 F2 ");
    input.append("O3 1 F2\n");
    input.append("O5 1 F2 ");
    input.append("O7 1 F2\n");
    input.append("O9 1 F2 ");
    input.append("O2 1 F2\n");
    input.append("O4 1 F2 ");
    input.append("O6 1 F2\n");
    input.append("O8 1 F2 ");
    input.append("O1 1 F2\n");

    input.append("C3 10 O1 ");
    input.append("C3 9 O2\n");
    input.append("C3 8 O3 ");
    input.append("C3 7 F1\n");
    input.append("C4 9 O4 ");
    input.append("C4 8 O5\n");
    input.append("C4 7 F1 ");
    input.append("C1 10 O6\n");
    input.append("C1 9 O7 ");
    input.append("C1 8 F1\n");
    input.append("C2 10 O8 ");
    input.append("C2 9 O9\n");
    input.append("C2 8 F1 ");
    input.append("O3 1 F1\n");
    input.append("O5 1 F1 ");
    input.append("O7 1 F1\n");
    input.append("O9 1 F1 ");
    input.append("O2 1 F1\n");
    input.append("O4 1 F1 ");
    input.append("O6 1 F1\n");
    input.append("O8 1 F1 ");
    input.append("O1 1 F1\n");

    input.append("C2 7 O1 ");
    input.append("C2 6 O2\n");
    input.append("C2 5 O3 ");
    input.append("C2 4 F3\n");
    input.append("C3 6 O4 ");
    input.append("C3 5 O5\n");
    input.append("C3 4 F3 ");
    input.append("C4 6 O6\n");
    input.append("C4 5 O7 ");
    input.append("C4 4 F3\n");
    input.append("C1 7 O8 ");
    input.append("C1 6 O9\n");
    input.append("C1 5 F3 ");
    input.append("O3 1 F3\n");
    input.append("O5 1 F3 ");
    input.append("O7 1 F3\n");
    input.append("O9 1 F3 ");
    input.append("O2 1 F3\n");
    input.append("O4 1 F3 ");
    input.append("O6 1 F3\n");
    input.append("O8 1 F3 ");
    input.append("O1 1 F3\n");

    input.append("C1 4 O1 ");
    input.append("C1 3 O2\n");
    input.append("C1 2 O3 ");
    input.append("C1 1 F4\n");
    input.append("C2 3 O4 ");
    input.append("C2 2 O5\n");
    input.append("C2 1 F4 ");
    input.append("C3 3 O6\n");
    input.append("C3 2 O7 ");
    input.append("C3 1 F4\n");
    input.append("C4 3 O8 ");
    input.append("C4 2 O9\n");
    input.append("C4 1 F4 ");
    input.append("O3 1 F4\n");
    input.append("O5 1 F4 ");
    input.append("O7 1 F4\n");
    input.append("O9 1 F4 ");
    input.append("O2 1 F4\n");
    input.append("O4 1 F4 ");
    input.append("O6 1 F4\n");
    input.append("O8 1 F4 ");
    input.append("O1 1 F4\n");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 12, false);

    assertEquals("Game over.", out.substring(out.toString().lastIndexOf("\n") + 1));
  }

  /**
   * Tests the SimpleFreecellController for Readable that ran out.
   */

  @Test (expected = IllegalStateException.class)
  public void testReadableRunOut() {
    StringBuilder input = new StringBuilder();
    input.append("C1 13 Y1");

    StringReader in = new StringReader(input.toString());
    StringBuilder out = new StringBuilder();

    FreecellController<ICard> controller = new SimpleFreecellController(testModel, in, out);
    controller.playGame(testModel.getDeck(), 4, 4, false);
  }
}