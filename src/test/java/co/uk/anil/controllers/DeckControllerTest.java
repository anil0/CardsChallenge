package co.uk.anil.controllers;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.uk.anil.model.Card;

/**
 * Created by anil on 14/03/2018.
 */
public class DeckControllerTest
{
    private DeckController deckController = null;


    @Before
    public void setUp() throws Exception
    {
        deckController = DeckController.getInstance();
    }


    @Test
    public void testSettingUpDeck() throws Exception
    {
        final ArrayList<Card> deck = deckController.setupDeck();

        //test deck has 52 cards and check the first card is the two of clubs
        Assert.assertEquals( "The List should contain 52 Card elements", 52, deck.size() );

        final String card = deck.get( 0 ).getValue() + " " + deck.get( 0 ).getSuit();
        Assert.assertEquals( "Outcome should be: TWO CLUBS", "TWO CLUBS", card );
    }


    @Test
    public void testShuffleDeck() throws Exception
    {
        final ArrayList<Card> deck = deckController.setupDeck();
        final ArrayList<Card> unshuffledDeck = deckController.setupDeck();
        deckController.shuffleDeck( unshuffledDeck );

        Assert.assertNotEquals( "List elements should be different", deck.toArray(), unshuffledDeck.toArray() );
    }


    @Test
    public void testDrawAndRemoveCardsFromDeck() throws Exception
    {
        final ArrayList<Card> deck = deckController.setupDeck();
        final ArrayList<Card> hand = new ArrayList<>();

        deckController.drawAndRemoveCardFromDeck( 5, hand, deck );

        Assert.assertEquals( "Outcome should be a deck of 47 cards", 47, deck.size() );
        Assert.assertEquals( "Outcome should be a hand of 5 cards", 5, hand.size() );
    }


    @Test
    public void testSortingCardsInHand() throws Exception
    {
        final ArrayList<Card> originalHand = new ArrayList<>();
        final ArrayList<Card> sortedHand = new ArrayList<>();
        ArrayList<Card> deck = deckController.setupDeck();

        deckController.drawAndRemoveCardFromDeck( 5, originalHand, deck );

        //reset deck so same cards are pulled for the other comparison hand
        deck = deckController.setupDeck();

        deckController.drawAndRemoveCardFromDeck( 5, sortedHand, deck );

        //emulate a shuffled hand of different cards from deck
        deckController.shuffleDeck( originalHand );
        deckController.shuffleDeck( sortedHand );
        //sort those cards
        deckController.sortCards( sortedHand );

        Assert.assertNotEquals( "List elements should be different", originalHand.toArray(), sortedHand.toArray() );
    }
}
