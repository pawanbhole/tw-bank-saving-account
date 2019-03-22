package org.ptw.banking.account.saving.persistance.exception;

/*
 * Exception for Item not found in store.  
 */
public class ItemNotFoundException extends StoreException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException() {
		super();
	}

	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemNotFoundException(String message) {
		super(message);
	}

	public ItemNotFoundException(Throwable cause) {
		super(cause);
	}
}
