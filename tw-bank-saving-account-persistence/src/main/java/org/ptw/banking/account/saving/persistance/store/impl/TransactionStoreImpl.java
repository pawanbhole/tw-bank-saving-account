package org.ptw.banking.account.saving.persistance.store.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;

import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.persistance.lock.LockManager;
import org.ptw.banking.account.saving.persistance.repository.Repository;
import org.ptw.banking.account.saving.persistance.store.TransactionStore;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Implementation for transaction store.
 * @author pawan
 *
 */
@org.springframework.stereotype.Repository
public class TransactionStoreImpl extends AbstractStore implements TransactionStore {

	@Autowired
	protected Repository<String, List<TransactionDTO>> transactionRepository;
	
	@Autowired
	protected TransactionDTOComparator comparator;
	
	@Autowired
	protected LockManager lockManager;

	@Override
	public void addTransaction(String accountId, TransactionDTO transactionDTO) {
		Lock accountLock = lockManager.writeLock(accountId);
		try {
			accountLock.lock();
			List<TransactionDTO> transactions = transactionRepository.get(accountId);
			int position = Collections.binarySearch(transactions, transactionDTO, comparator);
			transactions.add((position + 1) * (-1), transactionDTO);
		} finally {
			accountLock.unlock();
		}
	}

	@Override
	public List<TransactionDTO> getTransactions(String accountId) {
		Lock accountLock = lockManager.readLock(accountId);
		try {
			accountLock.lock();
			return new ArrayList<TransactionDTO>(transactionRepository.get(accountId));
		} finally {
			accountLock.unlock();
		}
	}

	@Override
	public boolean exists(String accountId) {
		Lock accountLock = lockManager.readLock(accountId);
		try {
			accountLock.lock();
			return transactionRepository.exists(accountId);
		} finally {
			accountLock.unlock();
		}
	}

	@Override
	public void initializeForNewAccount(String accountId) {
		Lock accountLock = lockManager.writeLock(accountId);
		try {
			accountLock.lock();
			transactionRepository.add(accountId, new ArrayList<TransactionDTO>());
		} finally {
			accountLock.unlock();
		}
	}
	
	@Override
	public String generateId() {
		String id = super.generateId();
		return String.format("T%s", id);
	}

}
