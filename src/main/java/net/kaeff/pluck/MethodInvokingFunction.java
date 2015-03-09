package net.kaeff.pluck;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * Represents a method call on an object.
 *
 * @param <P> Type of the plucked class
 * @param <O> Output type of the resulting function.
 */
class MethodInvokingFunction<P, O> implements Function<P, O> {
    private final Method pluckedMethod;

    MethodInvokingFunction(final Method pluckedMethod) {
        this.pluckedMethod = pluckedMethod;
    }

    @Override
    public O apply(final P pluckedObject) {
        return unsafeInvoke(pluckedObject);
    }

    @SuppressWarnings("unchecked")
    private O unsafeInvoke(final P pluckedObject) {
        try {
            // TODO make sure pluckedMethod has correct return type
            return (O) pluckedMethod.invoke(pluckedObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO handle!
            return null;
        }
    }
}
