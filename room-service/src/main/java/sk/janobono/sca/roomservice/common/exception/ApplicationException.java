package sk.janobono.sca.roomservice.common.exception;

public class ApplicationException extends RuntimeException {

    private final ApplicationExceptionCode applicationExceptionCode;

    public ApplicationException(String message, Throwable cause, ApplicationExceptionCode applicationExceptionCode) {
        super(message, cause);
        this.applicationExceptionCode = applicationExceptionCode;
    }

    public ApplicationExceptionCode getApplicationExceptionCode() {
        return applicationExceptionCode;
    }
}
