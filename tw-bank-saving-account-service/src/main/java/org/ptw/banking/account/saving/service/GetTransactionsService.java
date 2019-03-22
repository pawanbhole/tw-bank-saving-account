package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Transactions;
/**
 * Service to handle Account transactions.
 * 
 * @author pawan
 *
 */
public interface GetTransactionsService {
	/**
	 * Returns the account transactions with pagination.
	 * @param accountId
	 * @param offset Start index zero based.
	 * @param limit Maximum transaction to be returned.
	 * @return Transactions
	 */
	public Transactions execute(String accountId, Integer offset, Integer limit);
}