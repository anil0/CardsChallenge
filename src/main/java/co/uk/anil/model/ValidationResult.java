package co.uk.anil.model;

/**
 * Created by anil on 14/03/2018.
 */
public class ValidationResult {

    private int numOfCardsToDraw;
    private String errors;

    public ValidationResult(int numOfCardsToDraw, String errors) {
        this.numOfCardsToDraw = numOfCardsToDraw;
        this.errors = errors;
    }

    public int getNumOfCardsToDraw() {
        return numOfCardsToDraw;
    }

    public String getErrors() {
        return errors;
    }
}
