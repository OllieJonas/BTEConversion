package uk.buildtheearth.conversionplugin.util;

public class InvalidTypeException extends RuntimeException {

    public InvalidTypeException(IllegalArgumentException e, String input, Class<?> expectedCast) {
        super("Unable to cast " + input + " to " + expectedCast.getSimpleName() + "!");
    }

    public InvalidTypeException(String input, Class<?> expectedCast) {
        super("Unable to cast " + input + " to " + expectedCast.getSimpleName() + "!");
    }

    public InvalidTypeException(Object input, Class<?> expectedCast) {
        super ("Unable to cast " + input + " to " + expectedCast.getSimpleName() + "!");
    }

}
