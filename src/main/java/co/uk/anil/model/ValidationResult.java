package co.uk.anil.model;

/**
 * Created by anil on 14/03/2018.
 */
public class ValidationResult
{
    private int numOfCardsToDraw;
    private String errors;


    private ValidationResult( int numOfCardsToDraw, String errors )
    {
        this.numOfCardsToDraw = numOfCardsToDraw;
        this.errors = errors;
    }


    public int getNumOfCardsToDraw()
    {
        return numOfCardsToDraw;
    }


    public String getErrors()
    {
        return errors;
    }


    public static ValidationResult validateCardsDrawn( int deckSize, String numOfCards )
    {
        int numOfCardsToDraw = 0;
        String errors = null;

        try
        {
            //attempt to parse the number from user
            numOfCardsToDraw = Integer.parseInt( numOfCards );

            if( numOfCardsToDraw < 1 || numOfCardsToDraw > 52 )
            {
                numOfCardsToDraw = 0;
                errors = "Please enter a valid number";
            }

            if( numOfCardsToDraw > deckSize )
            {
                numOfCardsToDraw = 0;
                errors = "Please enter a valid number, there are " + deckSize + " cards left in the deck";
            }

            if( deckSize == 0 )
            {
                numOfCardsToDraw = 0;
                errors = "Sorry there are " + deckSize + " cards left in the deck";
            }
        }
        catch( NumberFormatException e )
        {
            errors = "Please enter a valid number";
        }

        return new ValidationResult( numOfCardsToDraw, errors );
    }
}
