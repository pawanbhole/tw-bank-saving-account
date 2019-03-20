package org.ptw.banking.account.saving.service.mapper;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.service.model.Account;

public interface AccountMapper {
	void map(Account from, AccountDTO to);
	void map(AccountDTO from, Account to);
	Account map(AccountDTO from);
	AccountDTO map(Account from);
}
