package hr.abysalto.hiring.mid.common.exceptions;

public class InvalidIdException extends IllegalArgumentException {

    public static final String INVALID_ID_ERROR_MESSAGE = "Invalid Id provided.";

    public InvalidIdException() {
        super(INVALID_ID_ERROR_MESSAGE);
    }
}
