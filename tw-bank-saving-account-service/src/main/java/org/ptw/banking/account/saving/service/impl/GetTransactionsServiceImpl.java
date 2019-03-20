package org.ptw.banking.account.saving.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.persistance.lock.LockManager;
import org.ptw.banking.account.saving.persistance.store.AccountStore;
import org.ptw.banking.account.saving.persistance.store.TransactionStore;
import org.ptw.banking.account.saving.service.GetTransactionsService;
import org.ptw.banking.account.saving.service.mapper.TransactionMapper;
import org.ptw.banking.account.saving.service.model.Transaction;
import org.ptw.banking.account.saving.service.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTransactionsServiceImpl implements GetTransactionsService {
	
	@Autowired
	protected TransactionStore transactionStore; 
	
	@Autowired
	protected AccountStore accountStore;
	
	@Autowired
	protected TransactionMapper transactionMapper;
	
	@Autowired
	protected LockManager lockManager;
	
	public Transactions execute(String accountId, Integer offsetParam, Integer limitParam) {
		Lock accountLock = lockManager.readLock(accountId);
		try {
			accountLock.lock();
			Transactions transactions = new Transactions();
			List<Transaction> transactionList = null;
			AccountDTO accountDTO;
			List<TransactionDTO> transactionDTOs;
			accountDTO = accountStore.getAccount(accountId);
			transactionDTOs = transactionStore.getTransactions(accountId);
			int offset = (offsetParam != null) ? offsetParam : 0;
			if(transactionDTOs != null && transactionDTOs.size() > offset) {
				int limit = (limitParam != null) ? limitParam : (transactionDTOs.size() - offset);
				transactionList = new ArrayList<Transaction>(limit);
				String currency = accountDTO.getCurrency().getCurrencyCode();
				int lastIndex = offset + limit;
				for(int index = offset; index < transactionDTOs.size() && index < lastIndex; index++) {
					TransactionDTO transactionDTO = transactionDTOs.get(index);
					Transaction transaction = transactionMapper.map(transactionDTO);
					transaction.getAmount().setCurrency(currency);
					transactionList.add(transaction);
				}
				transactions.setTotalRecords(transactionDTOs.size());
			} else {
				transactionList = Collections.emptyList();
				transactions.setTotalRecords(0);
			}
			transactions.setTransactions(transactionList);
			return transactions;
		} finally {
			accountLock.unlock();
		}
	}
}