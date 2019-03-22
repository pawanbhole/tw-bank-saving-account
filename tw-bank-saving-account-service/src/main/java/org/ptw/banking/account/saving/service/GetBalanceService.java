package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Balance;

/**
 * Service to handle Account balance.
 * 
 * @author pawan
 *
 */
public interface GetBalanceService {
	/**
	 * REturns the Balance for account.
	 * @param accountId
	 * @return
	 */
	public Balance execute(String accountId);
}