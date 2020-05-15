package space.zhdanov.laboratory.carshop.entities.dto.v1;

public class ExceptionMessage {
    private String message;
    private String exception;
    private Long timestamp;

    public ExceptionMessage(String message, String exception, Long timestamp) {
        this.message = message;
        this.exception = exception;
        this.timestamp = timestamp;
    }

    public ExceptionMessage(Throwable exc, String message) {
        if (message == null)
            this.message = exc.getMessage();
        else
            this.message = message;
        this.exception = exc.getClass().getSimpleName();
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
