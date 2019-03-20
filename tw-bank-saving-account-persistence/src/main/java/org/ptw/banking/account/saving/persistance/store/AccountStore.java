package org.ptw.banking.account.saving.persistance.store;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.exception.ItemAlreadyExistException;
import org.ptw.banking.account.saving.persistance.exception.ItemNotFoundException;

public interface AccountStore extends Store {
	void createAccount(AccountDTO account) throws ItemAlreadyExistException;
	AccountDTO getAccount(String accountId) throws ItemNotFoundException;
	void updateAccount(AccountDTO account) throws ItemNotFoundException;
}
