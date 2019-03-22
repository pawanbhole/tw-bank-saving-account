package org.ptw.banking.account.saving.persistance.store;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.exception.ItemAlreadyExistException;
import org.ptw.banking.account.saving.persistance.exception.ItemNotFoundException;

/** 
 * Store for maintaining Accounts by account Id
 * @author pawan
 * 
 */
public interface AccountStore extends Store {
	/** Create the account.
	 * @param account
	 * @throws ItemAlreadyExistException If item with specified key already exist.
	 */
	void createAccount(AccountDTO account) throws ItemAlreadyExistException;
	
	/** Returns the account by accountId.
	 * @param accountId
	 * @return
	 * @throws ItemNotFoundException If item with specified key not exist.
	 */
	AccountDTO getAccount(String accountId) throws ItemNotFoundException;
	
	/** Updates the account.
	 * @param account
	 * @throws ItemNotFoundException If item with specified key not exist.
	 */
	void updateAccount(AccountDTO account) throws ItemNotFoundException;
}
