package hashing.hashTables;

/**
 * Not enough space inside the table
 * @author minhhoang
 *
 */
public class TableFullException extends Exception {
	
	public TableFullException() { }
	public TableFullException(String message) {
		super(message);
	}
	
}
