package org.ptw.banking.account.saving.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.locks.Lock;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.AmountDTO;
import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.persistance.lock.LockManager;
import org.ptw.banking.account.saving.persistance.store.AccountStore;
import org.ptw.banking.account.saving.persistance.store.TransactionStore;
import org.ptw.banking.account.saving.service.AccountTransactionService;
import org.ptw.banking.account.saving.service.TallyTransactionAndBalanceService;
import org.ptw.banking.account.saving.service.exception.DataIntegrityException;
import org.ptw.banking.account.saving.service.mapper.AccountMapper;
import org.ptw.banking.account.saving.service.mapper.AmountMapper;
import org.ptw.banking.account.saving.service.mapper.TransactionMapper;
import org.ptw.banking.account.saving.service.model.Amount;
import org.ptw.banking.account.saving.service.model.Balance;
import org.ptw.banking.account.saving.service.model.Transaction;
import org.ptw.banking.account.saving.service.model.Transaction.TypeEnum;
import org.ptw.banking.account.saving.service.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

	@Autowired
	protected AccountStore accountStore;

	@Autowired
	protected TransactionStore transactionStore;

	@Autowired
	protected AccountMapper accountMapper;

	@Autowired
	protected TransactionMapper transactionMapper;

	@Autowired
	protected AmountMapper amountMapper;
	
	@Autowired
	protected TallyTransactionAndBalanceService tallyTransactionAndBalanceService;
	
	@Autowired
	protected LockManager lockManager;
	
	@Override
	public TransactionResponse execute(String accountId, Amount amount, TypeEnum type) {
		Lock accountLock = lockManager.writeLock(accountId);
		try {
			accountLock.lock();
			TransactionResponse transactionResponse;
			AccountDTO accountDTO = accountStore.getAccount(accountId);
			TransactionDTO transactionDTO = new TransactionDTO();
			transactionDTO.setAmount(amountMapper.map(amount));
			transactionDTO.setTransactionDate(OffsetDateTime.now());
			transactionDTO.setId(transactionStore.generateId());
			transactionDTO.setStatus(Transaction.StatusEnum.SUCCESSFUL.name());
			transactionDTO.setType(type.name());
			double newBalanceValue = accountDTO.getBalance().getValue();
			switch (type) {
			case DEPOSITE:
				newBalanceValue += transactionDTO.getAmount().getValue();
				break;
			case WITHDRAW:
				newBalanceValue -= transactionDTO.getAmount().getValue();
				break;
			default:
			}
			AmountDTO amountDTO = accountDTO.getBalance();
			amountDTO.setValue(newBalanceValue);
			
	
			List<TransactionDTO> transactionDTOs = transactionStore.getTransactions(accountId);
			transactionDTOs.add(transactionDTO);
			if(tallyTransactionAndBalanceService.execute(accountDTO, transactionDTOs)) {
				accountStore.updateAccount(accountDTO);
				transactionStore.addTransaction(accountId, transactionDTO);
			} else {
				throw new DataIntegrityException("New balance of account does not tally with the transactions.");
			}
			
			transactionResponse = new TransactionResponse();
			Balance newBalance = new Balance();
			Amount newBalanceAmount = amountMapper.map(amountDTO);
			newBalanceAmount.setCurrency(accountDTO.getCurrency().getCurrencyCode());
			newBalance.setAmount(newBalanceAmount);
			newBalance.setTimestamp(OffsetDateTime.now());
			transactionResponse.setBalance(newBalance);
			transactionResponse.setTransaction(transactionMapper.map(transactionDTO));
			return transactionResponse;
		} finally {
			accountLock.unlock();
		}
	}
}