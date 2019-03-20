package org.ptw.banking.account.saving.service.impl;

import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.concurrent.locks.Lock;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.AmountDTO;
import org.ptw.banking.account.saving.persistance.lock.LockManager;
import org.ptw.banking.account.saving.persistance.store.AccountStore;
import org.ptw.banking.account.saving.service.CreateAccountService;
import org.ptw.banking.account.saving.service.mapper.AccountMapper;
import org.ptw.banking.account.saving.service.model.Account;
import org.ptw.banking.account.saving.service.model.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountServiceImpl implements CreateAccountService {
	
	@Autowired
	protected AccountStore accountStore;
	
	@Autowired
	protected AccountMapper accountMapper;
	
	@Autowired
	protected LockManager lockManager;
	
	public Account execute(AccountRequest accountRequest) {
		String accountId = accountStore.generateId();
		Lock accountLock = lockManager.writeLock(accountId);
		try {
			accountLock.lock();
			AccountDTO accountDTO = new AccountDTO();
			AmountDTO amount = new AmountDTO();
			amount.setValue(0);
			accountDTO.setCurrency(Currency.getInstance(accountRequest.getCurrency()));
			accountDTO.setBalance(amount);
			accountDTO.setId(accountId);
			accountDTO.setCreationDate(OffsetDateTime.now());
			accountStore.createAccount(accountDTO);
			return accountMapper.map(accountDTO);
		} finally {
			accountLock.unlock();
		}
	}
}