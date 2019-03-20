package org.ptw.banking.account.saving.service;

import org.ptw.banking.account.saving.service.model.Balance;

public interface GetBalanceService {
	public Balance execute(String accountId);
}