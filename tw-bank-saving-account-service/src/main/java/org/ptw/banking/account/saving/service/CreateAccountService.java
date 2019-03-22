package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Account;
import org.ptw.banking.account.saving.service.model.AccountRequest;

/**
 * Service to handle Account creation.
 * 
 * @author pawan
 *
 */
public interface CreateAccountService {
	/**
	 * Accepts the account request and return newly created account.
	 * @param account AccountRequest
	 * @return New account
	 */
	public Account execute(AccountRequest account);
}