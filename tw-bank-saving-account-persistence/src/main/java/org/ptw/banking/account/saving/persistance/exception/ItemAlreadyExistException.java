package org.ptw.banking.account.saving.persistance.exception;

/*
 * Exception for Item already exist in store.  
 */
public class ItemAlreadyExistException extends StoreException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemAlreadyExistException() {
		super();
	}

	public ItemAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemAlreadyExistException(String message) {
		super(message);
	}

	public ItemAlreadyExistException(Throwable cause) {
		super(cause);
	}
}
