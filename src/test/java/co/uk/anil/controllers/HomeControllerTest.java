package co.uk.anil.controllers;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.uk.anil.model.Card;
import co.uk.anil.model.ValidationResult;

/**
 * Created by anil on 14/03/2018.
 */

public class HomeControllerTest
{
    private static final String INVALID_NUMBER_ERROR = "Please enter a valid number";

    private final DeckController deckController = DeckController.getInstance();
    private final ArrayList<Card> deck = deckController.setupDeck();

    private HomeController homeController = null;


    @Before
    public void setUp() throws Exception
    {
        homeController = new HomeController();
    }


    @Test
    public void testValidNumberOfCardsToDrawEntered() throws Exception
    {
        final int numOfCardsToDraw = ValidationResult.validateCardsDrawn( deck.size(), "4" ).getNumOfCardsToDraw();
        final String errors = ValidationResult.validateCardsDrawn( deck.size(), "4" ).getErrors();

        //check for valid entry
        Assert.assertEquals( "Outcome should have been 4", 4, numOfCardsToDraw );
        Assert.assertEquals( "Outcome should have been null", null, errors );
    }


    @Test
    public void testNumberOfCardsToDrawIsOutOfBounds() throws Exception
    {
        final int numOfCardsToDraw = ValidationResult.validateCardsDrawn( deck.size(), "54" ).getNumOfCardsToDraw();
        final String errors = ValidationResult.validateCardsDrawn( deck.size(), "54" ).getErrors();

        //check with out of bounds number - 1 - 52 allowed only
        Assert.assertEquals( "Outcome should have been 0", 0, numOfCardsToDraw );
        Assert.assertEquals( "Outcome should have been: " + INVALID_NUMBER_ERROR, INVALID_NUMBER_ERROR, errors );
    }


    @Test
    public void testNumberOfCardsToDrawEnteredShowsErrorIfDouble() throws Exception
    {
        final int numOfCardsToDraw = ValidationResult.validateCardsDrawn( deck.size(), "5.0" ).getNumOfCardsToDraw();
        final String errors = ValidationResult.validateCardsDrawn( deck.size(), "5.0" ).getErrors();

        //check with a double
        Assert.assertEquals( "Outcome should have been 0", 0, numOfCardsToDraw );
        Assert.assertEquals( "Outcome should have been: " + INVALID_NUMBER_ERROR, INVALID_NUMBER_ERROR, errors );
    }


    @Test
    public void testNonNumberForNumberOfCardsToDraw() throws Exception
    {
        final int numOfCardsToDraw = ValidationResult.validateCardsDrawn( deck.size(), "fsdfsfsdf" ).getNumOfCardsToDraw();
        final String errors = ValidationResult.validateCardsDrawn( deck.size(), "fsdfsfsdf" ).getErrors();

        //check with a non number
        Assert.assertEquals( "Outcome should have been 0", 0, numOfCardsToDraw );
        Assert.assertEquals( "Outcome should have been: " + INVALID_NUMBER_ERROR, INVALID_NUMBER_ERROR, errors );
    }
}
