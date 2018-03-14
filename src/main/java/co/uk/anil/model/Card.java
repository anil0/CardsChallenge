package co.uk.anil.model;

/**
 * Created by anil on 12/03/2018.
 */
public class Card
{
    private Suit suit;
    private Value value;


    public Card( Suit suit, Value value )
    {
        this.suit = suit;
        this.value = value;
    }


    public Value getValue()
    {
        return value;
    }


    public Suit getSuit()
    {
        return suit;
    }
}
