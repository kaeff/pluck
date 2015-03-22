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
     * @see net.kaeff.pluck.PluckDsl#pluck(Object)
     */
    @SuppressWarnings("unused")
    public static <P, O> Function<P, O> pluck(final O pluckedMethod) {
        final Invocation invocation = new MockUtil().getMockHandler(lastMock)
                .getInvocationContainer().getInvocations().get(0);
        return new MethodInvokingFunction<>(invocation.getMethod());
    }

    /**
     * @see net.kaeff.pluck.PluckDsl#from(Class)
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
