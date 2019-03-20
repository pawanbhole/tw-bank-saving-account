package org.ptw.banking.account.saving.persistance.store;

import java.util.List;

import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;

public interface TransactionStore  extends Store {
	void addTransaction(String accountId, TransactionDTO transactionDTO);
	void initializeForNewAccount(String accountId);
	List<TransactionDTO> getTransactions(String accountId);
}
