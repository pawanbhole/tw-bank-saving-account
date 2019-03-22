package org.ptw.banking.account.saving.service;

import java.util.List;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;


/**
 * Service to tally Account transactions with current balance.
 * 
 * @author pawan
 *
 */
public interface TallyTransactionAndBalanceService {

	/**
	 * It checks if the current balance is in sync with all transactions.
	 * @param accountDTO
	 * @param transactionDTOs
	 * @return boolean true or false. True if balance and transactions are in sync else false.
	 */
	public boolean execute(AccountDTO accountDTO, List<TransactionDTO> transactionDTOs);
}
