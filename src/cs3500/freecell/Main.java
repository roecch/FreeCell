package cs3500.freecell;

import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.io.InputStreamReader;

/**
 * Class to manually run the controller.
 */

public class Main {

  /**
   * Method to manually run the controller.
   */

  public static void main(String[] args) {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    Appendable out = System.out;
    Readable in = new InputStreamReader(System.in);

    FreecellController<ICard> controller = new SimpleFreecellController(model, in, out);
    controller.playGame(model.getDeck(), 4, 4, false);
  }
}
