package org.ptw.banking.account.saving.service.impl;

import java.time.OffsetDateTime;
import java.util.concurrent.locks.Lock;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.AmountDTO;
import org.ptw.banking.account.saving.persistance.lock.LockManager;
import org.ptw.banking.account.saving.persistance.store.AccountStore;
import org.ptw.banking.account.saving.service.GetBalanceService;
import org.ptw.banking.account.saving.service.mapper.AmountMapper;
import org.ptw.banking.account.saving.service.model.Amount;
import org.ptw.banking.account.saving.service.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBalanceServiceImpl implements GetBalanceService {
	
	@Autowired
	protected AccountStore accountStore;
	
	@Autowired
	protected AmountMapper amountMapper;
	
	@Autowired
	protected LockManager lockManager;
	
	public Balance execute(String accountId) {
		Lock accountLock = lockManager.readLock(accountId);
		try {
			accountLock.lock();
			Balance balance = new Balance();
			AccountDTO accountDTO = accountStore.getAccount(accountId);
			AmountDTO amountDTO = accountDTO.getBalance();
			Amount amount = amountMapper.map(amountDTO);
			amount.setCurrency(accountDTO.getCurrency().getCurrencyCode());
			balance.setAmount(amount);
			balance.setTimestamp(OffsetDateTime.now());
			return balance;
		} finally {
			accountLock.unlock();
		}
	}
}