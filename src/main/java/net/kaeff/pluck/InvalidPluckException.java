package net.kaeff.pluck;

/**
 * Signals invalid use of {@link PluckByMethodName#pluck}.
 */
public class InvalidPluckException extends RuntimeException {
    InvalidPluckException(final Throwable cause) {
        super(cause);
    }
}
