package org.ptw.banking.account.saving.persistance.store.impl;

import java.util.concurrent.locks.Lock;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.exception.ItemAlreadyExistException;
import org.ptw.banking.account.saving.persistance.exception.ItemNotFoundException;
import org.ptw.banking.account.saving.persistance.lock.LockManager;
import org.ptw.banking.account.saving.persistance.repository.Repository;
import org.ptw.banking.account.saving.persistance.store.AccountStore;
import org.ptw.banking.account.saving.persistance.store.TransactionStore;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Implementation for account store.
 * @author pawan
 *
 */
@org.springframework.stereotype.Repository
public class AccountStoreImpl extends AbstractStore implements AccountStore {
	
	@Autowired
	protected Repository<String, AccountDTO> accountRepository;
	
	@Autowired
	protected TransactionStore transactionStore;
	
	@Autowired
	protected LockManager lockManager;
	
	@Override
	public void createAccount(AccountDTO account) throws ItemAlreadyExistException {
		Lock accountLock = lockManager.writeLock(account.getId());
		try {
			accountLock.lock();
			accountRepository.add(account.getId(), account);
			transactionStore.initializeForNewAccount(account.getId());
		} finally {
			accountLock.unlock();
		}
	}

	@Override
	public AccountDTO getAccount(String accountId) throws ItemNotFoundException {
		Lock accountLock = lockManager.readLock(accountId);
		try {
			accountLock.lock();
			return accountRepository.get(accountId);
		} finally {
			accountLock.unlock();
		}
	}

	@Override
	public void updateAccount(AccountDTO account) throws ItemNotFoundException {
		Lock accountLock = lockManager.writeLock(account.getId());
		try {
			accountLock.lock();
			accountRepository.update(account.getId(), account);
		} finally {
			accountLock.unlock();
		}
	}

	@Override
	public boolean exists(String accountId) {
		Lock accountLock = lockManager.readLock(accountId);
		try {
			accountLock.lock();
			return accountRepository.exists(accountId);
		} finally {
			accountLock.unlock();
		}
	}
	
	@Override
	public String generateId() {
		String id = super.generateId();
		return String.format("A%s", id);
	}
}
