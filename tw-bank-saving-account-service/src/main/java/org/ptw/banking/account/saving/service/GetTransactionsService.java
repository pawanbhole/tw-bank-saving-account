package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Transactions;

public interface GetTransactionsService {
	public Transactions execute(String accountId, Integer offset, Integer limit);
}