package net.kaeff.pluck;

import org.mockito.Mockito;
import org.mockito.internal.invocation.Invocation;
import org.mockito.internal.util.MockUtil;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.MethodInvocationReport;

import java.util.function.Function;

/**
 * Provides an DSL-style API to create plucks in a typesafe manner.
*/
final class PluckDsl {

    private static Object lastMock;

    private PluckDsl() {

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
    @SuppressWarnings("unused")
    public static <P, O> Function<P, O> pluck(final O pluckedMethod) {
        final Invocation invocation = new MockUtil().getMockHandler(lastMock)
                .getInvocationContainer().getInvocations().get(0);
        return new MethodInvokingFunction<>(invocation.getMethod());
    }

    /**
     * Generates a double. The method called on the double will become the plucked method.
     * @param pluckedClass Type token for the class to pluck.
     * @param <P>          Type of the plucked class. Input type of the resulting function.
     * @return             Plucking double
     */
    public static <P> P from(final Class<P> pluckedClass) {
        final P mock = Mockito.mock(pluckedClass);
        rememberLastMock(mock);
        return mock;
    }

    private static void rememberLastMock(final Object mock) {
        new MockUtil().getMockHandler(mock).getMockSettings().getInvocationListeners().add(new InvocationListener() {
            @Override
            public void reportInvocation(final MethodInvocationReport methodInvocationReport) {
                lastMock = mock;
            }
        });
    }
}
