package io.github.xfuns.java.expection;


/**
 * UtilsException
 *
 * @author smallmenu
 */
public class FunException extends RuntimeException {
    private static final long serialVersionUID = 2253489756088246525L;

    public FunException() {
        super();
    }

    public FunException(final Throwable e) {
        super(e);
    }

    public FunException(final String message) {
        super(message);
    }

    public FunException(final String message, final Throwable e) {
        super(message, e);
    }
}
