package net.kaeff.pluck;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * Use the static `pluck()` function to create a `Function` that represents a method call on an object.
 */
public class Pluck {

    /**
     *
     * @param pluckedClass
     * @param methodName
     * @param <P>
     * @param <O>
     * * @return Function invoking the specified method on the input object
     */
    public static <P, O> Function<P, O> pluck(Class<P> pluckedClass, String methodName) {
        try {
            Method declaredMethod = pluckedClass.getDeclaredMethod(methodName);
            return pluckedObject -> {
                try {
                    return (O) declaredMethod.invoke(pluckedObject);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    // TODO handle!
                    e.printStackTrace();
                    return null;
                }
            };
        } catch (NoSuchMethodException e) {
            // TODO handle!
            throw new RuntimeException(e);
        }
    }
}
