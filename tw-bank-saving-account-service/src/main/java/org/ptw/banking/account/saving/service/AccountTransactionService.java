package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Amount;
import org.ptw.banking.account.saving.service.model.Transaction.TypeEnum;
import org.ptw.banking.account.saving.service.model.TransactionResponse;

public interface AccountTransactionService {

	public TransactionResponse execute(String accountId, Amount amount, TypeEnum type);
}
