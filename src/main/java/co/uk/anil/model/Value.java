package co.uk.anil.model;

/**
 * Created by anil on 12/03/2018.
 */
public enum Value
{
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    private int value;


    Value( int value )
    {
        this.value = value;
    }


    public int getValue()
    {
        return this.value;
    }
}
