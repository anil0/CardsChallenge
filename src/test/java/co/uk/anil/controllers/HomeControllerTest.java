package co.uk.anil.controllers;

import org.junit.*;

/**
 * Created by anil on 14/03/2018.
 */

public class HomeControllerTest
{
    private static final String INVALID_NUMBER_ERROR = "Please enter a valid number";
    private HomeController homeController = null;

    @Before
    public void setUp() throws Exception
    {
        homeController = new HomeController();
    }

    @Test
    public void testValidNumberOfCardsToDrawEntered() throws Exception
    {
        final int numOfCardsToDraw = homeController.validateCardsDrawn("4").getNumOfCardsToDraw();
        final String errors = homeController.validateCardsDrawn("4").getErrors();

        //check for valid entry
        Assert.assertEquals("Outcome should have been 4", 4, numOfCardsToDraw);
        Assert.assertEquals("Outcome should have been null", null, errors);
    }

    @Test
    public void testNumberOfCardsToDrawIsOutOfBounds() throws Exception
    {
        final int numOfCardsToDraw = homeController.validateCardsDrawn("54").getNumOfCardsToDraw();
        final String errors = homeController.validateCardsDrawn("54").getErrors();

        //check with out of bounds number - 1 - 52 allowed only
        Assert.assertEquals("Outcome should have been 0", 0, numOfCardsToDraw);
        Assert.assertEquals("Outcome should have been: " + INVALID_NUMBER_ERROR, INVALID_NUMBER_ERROR, errors);
    }

    @Test
    public void testNumberOfCardsToDrawEnteredShowsErrorIfDouble() throws Exception
    {
        final int numOfCardsToDraw = homeController.validateCardsDrawn("5.0").getNumOfCardsToDraw();
        final String errors = homeController.validateCardsDrawn("5.0").getErrors();

        //check with a double
        Assert.assertEquals("Outcome should have been 0", 0, numOfCardsToDraw);
        Assert.assertEquals("Outcome should have been: " + INVALID_NUMBER_ERROR, INVALID_NUMBER_ERROR, errors);
    }

    @Test
    public void testNonNumberForNumberOfCardsToDraw() throws Exception
    {
        final int numOfCardsToDraw = homeController.validateCardsDrawn("fsdfsfsdf").getNumOfCardsToDraw();
        final String errors = homeController.validateCardsDrawn("fsdfsfsdf").getErrors();

        //check with a non number
        Assert.assertEquals("Outcome should have been 0", 0, numOfCardsToDraw);
        Assert.assertEquals("Outcome should have been: " + INVALID_NUMBER_ERROR, INVALID_NUMBER_ERROR, errors);
    }
}
