package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Amount;
import org.ptw.banking.account.saving.service.model.Transaction.TypeEnum;
import org.ptw.banking.account.saving.service.model.TransactionResponse;

/**
 * Service to handle Account transactions such as DEPOSIT and WITHDRAW
 * 
 * @author pawan
 *
 */
public interface AccountTransactionService {

	/**
	 * Performs the Account transactions such as DEPOSIT and WITHDRAW based on type.
	 * @param accountId Id of the account to be DEPOSITD or WITHDRAWEN
	 * @param amount
	 * @param type Type fo transaction. e.g. DEPOSIT or WITHDRAW
	 * @return
	 */
	public TransactionResponse execute(String accountId, Amount amount, TypeEnum type);
}
