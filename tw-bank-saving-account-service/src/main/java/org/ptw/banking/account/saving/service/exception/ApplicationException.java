package org.ptw.banking.account.saving.service.exception;

/**
 * Exception for any application lever exception.
 * @author pawan
 *
 */
public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(Throwable e) {
		super(e);
	}

	public ApplicationException(String message, Throwable e) {
		super(message, e);
	}

	public ApplicationException(String message) {
		super(message);
	}

}
