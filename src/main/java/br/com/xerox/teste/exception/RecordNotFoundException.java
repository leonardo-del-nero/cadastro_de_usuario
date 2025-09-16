package br.com.xerox.teste.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super("Record not found");
    }
    
    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
