package cs3500.freecell.model.hw02;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This class represents a simple freecell model must support to return various aspects of its
 * state. Follows the method of the FreecellModel and FreecellModelState classes.
 */

public class SimpleFreecellModel implements FreecellModel<ICard> {

  private final Random rand = new Random();

  private final List<ArrayList<ICard>> openPiles;
  // change from private to protected so can be accessed by the ComplexFreecellModel class
  protected final List<ArrayList<ICard>> cascadePiles;
  private final List<ArrayList<ICard>> foundationPiles;
  private int maxOpen = -1;
  private int maxCascade = -1;

  /**
   * The constructor of the SimpleFreecellModel class. Initializes the 2D ArrayLists which is used
   * to play the freecell model.
   */

  public SimpleFreecellModel() {
    this.openPiles = new ArrayList<>();
    this.cascadePiles = new ArrayList<>();
    this.foundationPiles = new ArrayList<>();
  }

  @Override
  public List<ICard> getDeck() {
    ArrayList<ICard> deck = new ArrayList<>();
    //goes through all values and suite and matches them up to get every card combination
    for (Suite s : Suite.values()) {
      for (Value v : Value.values()) {
        deck.add(new Card(v, s));
      }
    }
    return deck;
  }

  /**
   * Returns a shuffled version of given deck. Ensure deck id valid while doing so.
   *
   * @return the new deck of cards as a list
   */

  private ArrayList<ICard> shuffleDeck(List<ICard> deck) {
    List<ICard> copyDeck = new ArrayList<>(deck);

    if (!this.isValidDeck(deck)) {
      throw new IllegalArgumentException("Invalid Deck");
    }

    ArrayList<ICard> newShuffledDeck = new ArrayList<>();
    while (copyDeck.size() > 0) {
      newShuffledDeck.add(copyDeck.remove(rand.nextInt(copyDeck.size())));
    }

    return newShuffledDeck;
  }

  @Override
  public void startGame(List<ICard> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {
    List<ICard> deckToUse = new ArrayList<>();

    if (shuffle) {
      List<ICard> toBeShuffledDeck = new ArrayList<ICard>();
      toBeShuffledDeck.addAll(deck);
      // check for validity in shuffleDeck
      deckToUse = this.shuffleDeck(toBeShuffledDeck);
    }
    else if (this.isValidDeck(deck)) {
      deckToUse = deck;
    }
    else {
      throw new IllegalArgumentException("Invalid deck");
    }

    //set max num for open and cascade piles
    this.maxOpen = new Utils().checkAtLeast(numOpenPiles, 1,
        "Could not start game.");
    this.maxCascade = new Utils().checkAtLeast(numCascadePiles, 4,
        "Could not start game.");

    // initiate all piles so they can have cards added and removed from them
    this.cascadePiles.clear();
    this.openPiles.clear();
    this.foundationPiles.clear();
    this.setPiles(this.cascadePiles, numCascadePiles);
    this.setPiles(this.openPiles, numOpenPiles);
    this.setPiles(this.foundationPiles, 4);

    // roundrobin deal cards
    for (int i = 0; i < 52; i++) {
      this.cascadePiles.get(i % this.maxCascade).add(deckToUse.get(i));
    }
  }

  /**
   * Sets all 2D ArrayList piles to have empty rows of certain number.
   *
   * @param piles    2D ArrayList and represents piletype
   * @param numLists number of piles in piletype
   */


  private void setPiles(List<ArrayList<ICard>> piles, int numLists) {
    for (int i = 0; i < numLists; i++) {
      piles.add(new ArrayList<>());
    }
  }

  /**
   * Ensure deck is valid. Deck is valid if deck has 52 cards, no duplicate cards, and no invalid
   * cards.
   *
   * @param deck List of ICards that represents deck of cards
   * @return boolean of whether deck is valid or not
   */

  private boolean isValidDeck(List<ICard> deck) {
    List<ICard> checkedDeck = new ArrayList<>();

    for (ICard c : deck) {
      if (!c.isValidCard() || checkedDeck.contains(c)) {
        return false;
      }
      checkedDeck.add(c);
    }

    return deck.size() == 52;
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
      int destPileNumber) {

    ASolitaireRules sourceRules = new SolitaireCascadeRules();

    ICard sourceCard =
        this.getCalledPilesInfo(source, sourceRules, pileNumber, cardIndex, true);
    ICard destCard =
        this.getCalledPilesInfo(destination, null, destPileNumber, 0, false);

    if (sourceRules.placeOnTop(sourceCard, destCard, destination)) {
      this.getPileBasedOnPileType(source).get(pileNumber).remove(cardIndex);
      this.getPileBasedOnPileType(destination).get(destPileNumber).add(sourceCard);
    }
    else {
      throw new IllegalArgumentException("Invalid Move");
    }
  }

  /**
   * Retrieves the pile that the PileType calls upon.
   *
   * @param type PileType
   * @return the pile that the PileType matches
   */

  private List<ArrayList<ICard>> getPileBasedOnPileType(PileType type) {
    switch (type) {
      case OPEN: {
        return this.openPiles;
      }
      case FOUNDATION: {
        return this.foundationPiles;
      }
      case CASCADE: {
        return this.cascadePiles;
      }
      default: {
        throw new IllegalArgumentException("Invalid PileType");
      }
    }
  }

  /**
   * Receive the last card in the pile or null if pile is empty.
   *
   * @param pile        the type of the pile see @link{PileType}
   * @param sourceRules the rules of the source pile
   * @param pileNum     the pile number of the type, starting at 0
   * @param cardIndex   the index of the card to be moved from the pile, starting at 0
   * @param sourOrDest  whether the info being received is for source or destination
   * @return the ICard which is the last card in the pile or null if pile is empty
   * @throws IllegalArgumentException if the move is not possible {@link PileType})
   * @throws IllegalStateException    if a move is attempted before the game has starts
   */

  private ICard getCalledPilesInfo(PileType pile, ASolitaireRules sourceRules,
      int pileNum, int cardIndex, boolean sourOrDest) {
    int numCards;
    switch (pile) {
      case OPEN: {
        sourceRules = new SolitaireOpenRules();
        if (cardIndex > 1 && sourOrDest) {
          throw new IllegalArgumentException("Invalid Open Pile Card Index");
        }
        return this.getOpenCardAt(pileNum);
      }
      case FOUNDATION: {
        sourceRules = new SolitaireFoundationRules();
        numCards = getNumCardsInFoundationPile(pileNum);
        if (sourOrDest) {
          if (numCards - 1 == cardIndex) {
            return this.getFoundationCardAt(pileNum, cardIndex); // returns last card in deck
          } else {
            return this.getFoundationCardAt(pileNum, numCards);
          }
        } else if (numCards == 0) {
          return null;
        } else {
          return this.getFoundationCardAt(pileNum, numCards - 1);
        }
      }
      case CASCADE: {
        numCards = getNumCardsInCascadePile(pileNum);
        if (sourOrDest) {
          if (numCards - 1 == cardIndex) {
            return this.getCascadeCardAt(pileNum, cardIndex); // returns last card in deck
          }
          else {
            return this.getCascadeCardAt(pileNum, numCards);
          }
        }
        else if (numCards == 0) {
          return null;
        }
        else {
          return this.getCascadeCardAt(pileNum, numCards - 1);
        }
      }
      default: {
        throw new IllegalArgumentException("Invalid PileType");
      }
    }
  }

  @Override
  public boolean isGameOver() {
    int cardsInFoundation = 0;
    for (ArrayList<ICard> foundationPile : this.foundationPiles) {
      cardsInFoundation += foundationPile.size();
    }
    return cardsInFoundation == 52;
  }

  @Override
  public int getNumCardsInFoundationPile(int index) {
    return this.getNumCardsInPile(this.foundationPiles, index,
        4, "Foundation Pile");
  }

  @Override
  public int getNumCascadePiles() {
    return this.maxCascade;
  }

  @Override
  public int getNumCardsInCascadePile(int index) {
    return this.getNumCardsInPile(this.cascadePiles, index,
        this.maxCascade, "Cascade Pile");
  }

  @Override
  public int getNumCardsInOpenPile(int index) {
    return this.getNumCardsInPile(this.openPiles, index,
        this.maxOpen, "Open Pile");
  }

  /**
   * Get the number of cards in a given pile.
   *
   * @param index the index of the pile, starting at 0
   * @return the number of cards in the given pile
   * @throws IllegalArgumentException if the provided index is invalid
   * @throws IllegalStateException    if the game has not started
   */

  private int getNumCardsInPile(List<ArrayList<ICard>> piles, int index, int max, String type) {
    if (this.maxCascade != -1 && this.maxOpen != -1) {
      return piles.get(new Utils().checkRange(index,
          0, max, "Invalid " + type + " Index")).size();
    }
    throw new IllegalStateException("Game has not started");
  }

  @Override
  public int getNumOpenPiles() {
    return this.maxOpen;
  }

  @Override
  public ICard getFoundationCardAt(int pileIndex, int cardIndex) {
    return this.getCardInPile(this.foundationPiles, pileIndex,
        4, cardIndex, "Foundation Pile");
  }

  @Override
  public ICard getCascadeCardAt(int pileIndex, int cardIndex) {
    return this
        .getCardInPile(this.cascadePiles, pileIndex,
            this.maxCascade, cardIndex, "Cascade Pile");
  }

  @Override
  public ICard getOpenCardAt(int pileIndex) {
    return this.getCardInPile(this.openPiles, pileIndex,
        this.maxOpen, 0, "Open Pile");
  }

  /**
   * Get the card in the given pile.
   *
   * @param piles     the 2D ArrayList of piles
   * @param pileIndex the index of the pile, starting at 0
   * @param maxPile   the max number of piles for the piles param
   * @param cardIndex the index of the card that is called
   * @return the card at the provided index
   * @throws IllegalArgumentException if the pileIndex is invalid
   * @throws IllegalStateException    if the game has not started
   */

  private ICard getCardInPile(List<ArrayList<ICard>> piles, int pileIndex,
      int maxPile, int cardIndex, String type) {
    int maxCard = this.getNumCardsInPile(piles, pileIndex, maxPile, type + " Card");
    // checks to see if cardIndex is valid in called pile
    if (cardIndex < maxCard) {
      return piles.get(pileIndex).get(cardIndex);
    } else if (type.equals("Open Pile") && maxCard == 0) {
      return null;
    } else {
      throw new IllegalArgumentException("Invalid " + type + " Card Index");
    }
  }
}
