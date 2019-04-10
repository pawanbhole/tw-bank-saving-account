package org.ptw.banking.account.saving.service.test.mock;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.AmountDTO;
import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.service.model.Transaction.TypeEnum;

public class AccountMockUtil {
	
	private static Map<String, AccountDTO> accountsMap = new ConcurrentHashMap<String, AccountDTO>();
	private static Map<String, List<TransactionDTO>> transactionsMap = new ConcurrentHashMap<String, List<TransactionDTO>>();
	public static final String DEFAULT_ACCOUNT_ID = "1";
	public static final String DEFAULT_CURRENCY = "USD";
	
	static {
		accountsMap.put(DEFAULT_ACCOUNT_ID, createDummyAccount(DEFAULT_ACCOUNT_ID, 0));
		transactionsMap.put(DEFAULT_ACCOUNT_ID, new ArrayList<TransactionDTO>());
	}
	
	public static AccountDTO getAccount(String accountId) {
		return accountsMap.get(accountId);
	}
	

	public static void updateAccount(String accountId, AccountDTO accountDTO) {
		accountsMap.put(accountId, accountDTO);
	}
	
	public static List<TransactionDTO> getTransactions(String accountId) {
		return transactionsMap.get(accountId);
	}
	

	public static void addTransaction(String accountId, TransactionDTO transactionDTO) {
		List<TransactionDTO> transactionDTOs = getTransactions(accountId);
		transactionDTOs.add(transactionDTO);
		transactionsMap.put(accountId, transactionDTOs);
	}

	private static AccountDTO createDummyAccount(String accountId, double balance) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setCreationDate(OffsetDateTime.now());
		accountDTO.setCurrency(Currency.getInstance(DEFAULT_CURRENCY));
		accountDTO.setId(accountId);
		accountDTO.setBalance(createAmount(0));
		return accountDTO;
	}

	private static AmountDTO createAmount(double amount) {
		AmountDTO amountDTO = new AmountDTO();
		amountDTO.setValue(amount);
		return amountDTO;
	}
	

	public static AccountDTO cloneAccountDTO(AccountDTO original) {
		AccountDTO clonedAccountDTO = new AccountDTO();
		clonedAccountDTO.setCreationDate(original.getCreationDate());
		clonedAccountDTO.setCurrency(original.getCurrency());
		clonedAccountDTO.setId(original.getId());
		AmountDTO clonedAmount = new AmountDTO();
		clonedAmount.setValue(original.getBalance().getValue());
		clonedAccountDTO.setBalance(clonedAmount);
		return clonedAccountDTO;
	}
	
	public static List<TransactionDTO> cloneTransactionList(List<TransactionDTO> transactionDTOs) {
		return new ArrayList<TransactionDTO>(transactionDTOs);
	}
	
	public static double calculateNewBalance(double balanceBefore, double transactionAmount, TypeEnum transactionType) {
		double newBalance = balanceBefore;
		switch (transactionType) {
		case DEPOSIT:
			newBalance += transactionAmount;
			break;
		case WITHDRAW:
			newBalance -= transactionAmount;
			break;
		}
		return newBalance;
	}
}
