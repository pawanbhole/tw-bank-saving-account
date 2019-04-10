package org.ptw.banking.account.saving.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.persistance.store.AccountStore;
import org.ptw.banking.account.saving.persistance.store.TransactionStore;
import org.ptw.banking.account.saving.service.AccountTransactionService;
import org.ptw.banking.account.saving.service.GetBalanceService;
import org.ptw.banking.account.saving.service.model.Transaction.TypeEnum;
import org.ptw.banking.account.saving.service.model.Balance;
import org.ptw.banking.account.saving.service.model.TransactionResponse;
import org.ptw.banking.account.saving.service.test.mock.AccountMockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class SavingAccountServiceTestBase {
	
	@Autowired
	protected GetBalanceService getBalanceService;
	
	@Autowired
	protected AccountTransactionService accountTransactionService;

	@MockBean
	protected AccountStore accountStore;

	@MockBean
	protected TransactionStore transactionStore;

	protected ForkJoinPool forkJoinPool;
	
	protected ExecutorService taskExecutor;
	

	@Before
	public void setup() {
		forkJoinPool = new ForkJoinPool(2);
		taskExecutor = Executors.newFixedThreadPool(2);
		Mockito.reset(accountStore);
		mockAccountStoreGetAccountMethod();
		mockAccountStoreUpdateAccountMethod();
		mockTransactionStoreAddTransactiontMethod();
		mockTransactionStoreGetTransactionstMethod();
	}
	
	@After
	public void teardown() {
		forkJoinPool.shutdown();
		taskExecutor.shutdown();
	}

	protected static final double AMOUNT_TO_WITHDRAW = 5;

	protected static final double AMOUNT_TO_DEPOSIT = 10;

	protected void mockAccountStoreGetAccountMethod() {
		Mockito.when(accountStore.getAccount(Mockito.anyString())).then(new Answer<AccountDTO>() {
			private AtomicInteger callCount = new AtomicInteger(0);

			@Override
			public AccountDTO answer(InvocationOnMock invocation) throws Throwable {
				String accountId = invocation.getArgument(0);
				assertEquals(accountId, AccountMockUtil.DEFAULT_ACCOUNT_ID);
				int count = callCount.incrementAndGet();
				if (count % 2 == 1) {
					System.out.println("Sleeping for 10 seconds:"+count);
					Thread.sleep(10000);
				} else {
					Thread.sleep(2000);
					System.out.println("Sleeping for 2 seconds:"+count);
				}
				return AccountMockUtil.cloneAccountDTO(AccountMockUtil.getAccount(AccountMockUtil.DEFAULT_ACCOUNT_ID));
			}
		});
	}

	protected void mockAccountStoreUpdateAccountMethod() {

		Mockito.doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				AccountDTO accountDTO = invocation.getArgument(0);
				assertEquals(accountDTO.getId(), AccountMockUtil.DEFAULT_ACCOUNT_ID);
				AccountMockUtil.updateAccount(accountDTO.getId(), accountDTO);
				return null;
			}
		}).when(accountStore).updateAccount(Mockito.any(AccountDTO.class));

	}

	protected void mockTransactionStoreGetTransactionstMethod() {
		Mockito.when(transactionStore.getTransactions(Mockito.anyString())).then(new Answer<List<TransactionDTO>>() {

			@Override
			public List<TransactionDTO> answer(InvocationOnMock invocation) throws Throwable {
				String accountId = invocation.getArgument(0);
				assertEquals(accountId, AccountMockUtil.DEFAULT_ACCOUNT_ID);
				return AccountMockUtil
						.cloneTransactionList(AccountMockUtil.getTransactions(AccountMockUtil.DEFAULT_ACCOUNT_ID));
			}
		});

	}

	protected void mockTransactionStoreAddTransactiontMethod() {
		Mockito.doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String accountId = invocation.getArgument(0);
				assertEquals(accountId, AccountMockUtil.DEFAULT_ACCOUNT_ID);
				TransactionDTO transactionDTO = invocation.getArgument(1);
				AccountMockUtil.addTransaction(accountId, transactionDTO);
				return null;
			}
		}).when(transactionStore).addTransaction(Mockito.anyString(), Mockito.any(TransactionDTO.class));
	}

	protected void isTransactionResponseMatchWithMock(TransactionResponse transactionResponse, double balanceBefore,
			double transactionAmount, TypeEnum transactionType) throws InterruptedException, ExecutionException {
		assertNotNull("transactionResponse should not be null.", transactionResponse);
		assertNotNull("balance in transactionResponse should not be null.", transactionResponse.getBalance());
		assertNotNull("balance.amount in transactionResponse should not be null.",
				transactionResponse.getBalance().getAmount());
		assertNotNull("balance.amount.value in transactionResponse should not be null.",
				transactionResponse.getBalance().getAmount().getValue());
		assertEquals("Incorrect new balance", transactionResponse.getBalance().getAmount().getValue().doubleValue(),
				AccountMockUtil.calculateNewBalance(balanceBefore, transactionAmount, transactionType), 0.0);
	}
	

	protected void isBalanceMatchAccountDTO(Balance balance, AccountDTO accountDto) throws InterruptedException, ExecutionException {
		assertNotNull("balance should not be null.", balance);
		assertNotNull("balance amount should not be null.", balance.getAmount());
		assertNotNull("balance amount value should not be null.", balance.getAmount().getValue());
		assertEquals(balance.getAmount().getValue().doubleValue(), accountDto.getBalance().getValue(), 0.0);
	}

}