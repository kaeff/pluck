package net.kaeff.pluck;

import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * Use the static `pluck()` function to create a `Function` that represents a method call on an object.
 */
final class PluckByMethodName {

    private PluckByMethodName() {
    }

    /**
     * @see net.kaeff.pluck.Pluck#pluck(Class, String)
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
