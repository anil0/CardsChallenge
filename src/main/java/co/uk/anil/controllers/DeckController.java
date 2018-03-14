package co.uk.anil.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import co.uk.anil.model.Card;
import co.uk.anil.model.Suit;
import co.uk.anil.model.Value;

/**
 * Created by anil on 13/03/2018.
 */
public class DeckController
{
    private final static Object SINGLETON = new Object();
    private static DeckController instance;

    DeckController() {
    }

    public static DeckController getInstance()
    {
        synchronized (SINGLETON)
        {
            if (instance == null)
            {
                instance = new DeckController();
            }
        }

        return instance;
    }

    public ArrayList<Card> setupDeck()
    {
        ArrayList<Card> deck = new ArrayList<>();

        for (Suit suit: Suit.values())
        {
            for (Value value: Value.values())
            {
                deck.add(new Card(suit, value));
            }
        }

        return deck;
    }

    public void shuffleDeck(List<Card> cardsList)
    {
        Collections.shuffle(cardsList);
    }

    public void sortCards(List<Card> cardsList)
    {
        //uses the enum default ordering to sort automatically, also uses chained sorting by doing one and then the other sort after
        cardsList.sort(Comparator.comparing(Card::getSuit).thenComparing(Card::getValue));
    }

    public void drawAndRemoveCardFromDeck(int numOfCards, ArrayList<Card> hand, ArrayList<Card> deck)
    {
        int topCard = 0;

        for (int i = 0; i < numOfCards; i++)
        {
            //get the top card and then remove it from list
            hand.add(deck.get(topCard));
            deck.remove(topCard);
        }
    }
}
