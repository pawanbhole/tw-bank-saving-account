package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Account;
import org.ptw.banking.account.saving.service.model.AccountRequest;

public interface CreateAccountService {
	public Account execute(AccountRequest account);
}