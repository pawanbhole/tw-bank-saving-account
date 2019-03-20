package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Amount;
import org.ptw.banking.account.saving.service.model.TransactionResponse;

public interface WithdrawAccountService {

	public TransactionResponse execute(String accountId, Amount amount);
}
