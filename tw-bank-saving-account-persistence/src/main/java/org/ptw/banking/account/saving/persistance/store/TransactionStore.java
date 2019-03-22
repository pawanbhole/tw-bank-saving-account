package org.ptw.banking.account.saving.persistance.store;

import java.util.List;

import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.persistance.exception.ItemAlreadyExistException;
import org.ptw.banking.account.saving.persistance.exception.ItemNotFoundException;

/** 
 * Store for maintaining Transactions by account Id
 * @author pawan
 * 
 */
public interface TransactionStore  extends Store {
	/** Adds the transaction to list.
	 * @param account
	 * @throws ItemAlreadyExistException If item with specified key already exist.
	 */
	void addTransaction(String accountId, TransactionDTO transactionDTO);
	
	/** initialize the empty transaction list for new account.
	 * @param accountId
	 * @return
	 * @throws ItemNotFoundException If item with specified key not exist.
	 */
	void initializeForNewAccount(String accountId);
	
	/** Returns the transactions by accountId.
	 * @param account
	 * @throws ItemNotFoundException If item with specified key not exist.
	 */
	List<TransactionDTO> getTransactions(String accountId);
}
