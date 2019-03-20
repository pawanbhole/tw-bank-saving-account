package org.ptw.banking.account.saving.service;

import java.util.List;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;

public interface TallyTransactionAndBalanceService {

	public boolean execute(AccountDTO accountDTO, List<TransactionDTO> transactionDTOs);
}
