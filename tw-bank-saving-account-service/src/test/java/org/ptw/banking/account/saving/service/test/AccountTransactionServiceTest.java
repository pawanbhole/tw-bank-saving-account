package org.ptw.banking.account.saving.service.test;


import static org.junit.Assert.assertFalse;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ptw.banking.account.saving.service.model.Amount;
import org.ptw.banking.account.saving.service.model.Transaction.TypeEnum;
import org.ptw.banking.account.saving.service.model.TransactionResponse;
import org.ptw.banking.account.saving.service.test.configuration.SavingAccountTestConfiguration;
import org.ptw.banking.account.saving.service.test.mock.AccountMockUtil;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SavingAccountTestConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountTransactionServiceTest extends SavingAccountServiceTestBase {

	private RecursiveTask<TransactionResponse> createAccountTransactionTask(double amountValue, TypeEnum transactionType) {
		return new RecursiveTask<TransactionResponse>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected TransactionResponse compute() {
				Amount amount = new Amount();
				amount.setValue(BigDecimal.valueOf(amountValue));
				return accountTransactionService.execute(AccountMockUtil.DEFAULT_ACCOUNT_ID, amount, transactionType);
			}
		};
	}
	
	@Test
	public void testConcurentTransactions_SecondWaitForFirst() throws InterruptedException, ExecutionException {
		double balanceBeforeDeposit = AccountMockUtil.getAccount(AccountMockUtil.DEFAULT_ACCOUNT_ID).getBalance().getValue();
		RecursiveTask<TransactionResponse> depositTask = createAccountTransactionTask(AMOUNT_TO_DEPOSIT, TypeEnum.DEPOSIT);
		forkJoinPool.execute(depositTask); 
		Thread.sleep(2000);

		RecursiveTask<TransactionResponse> withdrawTask = createAccountTransactionTask(AMOUNT_TO_WITHDRAW, TypeEnum.WITHDRAW);
		forkJoinPool.execute(withdrawTask);

		TransactionResponse depositTransactionResponse = depositTask.get();
		isTransactionResponseMatchWithMock(depositTransactionResponse, balanceBeforeDeposit, AMOUNT_TO_DEPOSIT, TypeEnum.DEPOSIT);
		
		assertFalse("Second call should be pending.", withdrawTask.isDone());
		
		TransactionResponse withdrawTransactionResponse = withdrawTask.get();
		isTransactionResponseMatchWithMock(withdrawTransactionResponse, depositTransactionResponse.getBalance().getAmount().getValue().doubleValue(), AMOUNT_TO_WITHDRAW, TypeEnum.WITHDRAW);
	}
	
}