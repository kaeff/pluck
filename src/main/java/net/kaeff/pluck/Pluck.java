package net.kaeff.pluck;

import java.lang.reflect.Method;
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
        try {
            final Method pluckedMethod = pluckedClass.getDeclaredMethod(methodName);
            return new MethodInvokingFunction<>(pluckedMethod);
        } catch (NoSuchMethodException e) {
            // TODO handle!
            throw new InvalidPluckException(e);
        }
    }
}
