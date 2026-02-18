package tfg.cervecera.exceptions;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDataException() {
        super("Datos no inválidos");
    }
	
	public InvalidDataException(String message) {
		super(message);
	}

}
