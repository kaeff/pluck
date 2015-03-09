package net.kaeff.pluck;

/**
 * Signals invalid use of {@link net.kaeff.pluck.Pluck#pluck}.
 */
public class InvalidPluckException extends RuntimeException {
    InvalidPluckException(final Throwable cause) {
        super(cause);
    }
}
