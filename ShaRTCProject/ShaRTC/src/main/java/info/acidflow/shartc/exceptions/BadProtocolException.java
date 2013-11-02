package info.acidflow.shartc.exceptions;

/**
 * Created by acidflow on 02/11/13.
 */
public class BadProtocolException extends Exception {

    public BadProtocolException() {
        super();
    }

    public BadProtocolException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public BadProtocolException(String detailMessage) {
        super(detailMessage);
    }

    public BadProtocolException(Throwable throwable) {
        super(throwable);
    }
}
