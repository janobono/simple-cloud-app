package sk.janobono.sca.customerservice.common.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum ApplicationExceptionCode {
    UNKNOWN,
    CUSTOMER_NOT_FOUND;

    public String value(Object... arguments) {
        return MessageFormat.format(
                ResourceBundle.getBundle(ApplicationExceptionCode.class.getName()).getString(name()), arguments
        );
    }

    public ApplicationException exception(Object... arguments) {
        return exception(null, arguments);
    }

    public ApplicationException exception(Throwable cause, Object... arguments) {
        return new ApplicationException(value(arguments), cause, this);
    }
}
