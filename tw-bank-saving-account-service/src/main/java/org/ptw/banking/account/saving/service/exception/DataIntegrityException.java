package org.ptw.banking.account.saving.service.exception;

/**
 * Exception if data not in sync. E.g. if balance is not in sync with transactions.
 * @author pawan
 *
 */
public class DataIntegrityException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataIntegrityException() {
		super();
	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityException(String message) {
		super(message);
	}

	public DataIntegrityException(Throwable cause) {
		super(cause);
	}
}