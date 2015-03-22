package net.kaeff.pluck;

import java.util.function.Function;

/**
 * Use the static `pluck()` function to create a `Function` that represents a method call on an object.
 */
public final class Pluck {

    private Pluck() {
    }

    /**
     * Plucks a method by method name.
     *
     * @param pluckedClass Type token for the class to pluck. Input type of the resulting function
     * @param methodName   Name of the method to pluck. Must be an existing, accessible method on pluckedClass
     * @param <P>          Type of the plucked class
     * @param <O>          Output type of the resulting function.
     *                     This needs to be the return type of the plucked method.
     * @return Function invoking the specified method on the input object
     */
    public static <P, O> Function<P, O> pluck(final Class<P> pluckedClass, final String methodName) {
        return PluckByMethodName.pluck(pluckedClass, methodName);
    }

    /**
     * Plucks a method using a sample invocation on a double.
     * Use {@link net.kaeff.pluck.PluckDsl#from(Class)} to generate a double. Then call a method on it
     * The method called on the double will become the plucked method.
     *
     * Example: {@code pluck(from(Person.class).getName());}
     *
     * As shown in the example, the following steps have to be performed in sequence:
     * <ol>
     *     <li>Generate a double using from</li>
     *     <li>Invoke the method to pluck on the double</li>
     *     <li>Generate the pluck function using pluck</li>
     * </ol>
     *
     * @param pluckedMethod Sample invocation using {@link net.kaeff.pluck.PluckDsl#from(Class)}. E.g.
     * @param <P>          Type of the plucked class
     * @param <O>          Output type of the resulting function.
     *                     This needs to be the return type of the plucked method.
     * @return Function invoking the specified method on the input object
     */
    public static <P, O> Function<P, O> pluck(final O pluckedMethod) {
        return PluckDsl.pluck(pluckedMethod);
    }

    /**
     * Generates a double. The method called on the double will become the plucked method.
     *
     * @param pluckedClass Type token for the class to pluck.
     * @param <P>          Type of the plucked class. Input type of the resulting function.
     * @return             Plucking double
     */
    public static <P> P from(final Class<P> pluckedClass) {
        return PluckDsl.from(pluckedClass);
    }
}
