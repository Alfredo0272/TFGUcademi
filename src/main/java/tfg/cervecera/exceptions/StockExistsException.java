package tfg.cervecera.exceptions;


public class StockExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockExistsException(String message) {
        super(message);
    }
}