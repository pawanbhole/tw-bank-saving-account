package org.ptw.banking.account.saving.service.test;


import static org.junit.Assert.assertFalse;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ptw.banking.account.saving.service.model.Balance;
import org.ptw.banking.account.saving.service.test.configuration.SavingAccountTestConfiguration;
import org.ptw.banking.account.saving.service.test.mock.AccountMockUtil;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SavingAccountTestConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class GetBalanceServiceTest extends SavingAccountServiceTestBase {

	private Callable<Balance> createGetBalanceCallableTask() {
		return new Callable<Balance>() {
			@Override
			public Balance call() throws Exception {
				return getBalanceService.execute(AccountMockUtil.DEFAULT_ACCOUNT_ID);
			}
		};
	}
	
	@Test
	public void testConcurentGetBalanceCalls_NoLockEachOther() throws InterruptedException, ExecutionException {
		Future<Balance> firstCallFeature = taskExecutor.submit(createGetBalanceCallableTask());
		Thread.sleep(5000);
		Future<Balance> secondCallFeature = taskExecutor.submit(createGetBalanceCallableTask());
		
		Balance balanceFromSecondCall = secondCallFeature.get();
		assertFalse("First call should be pending.", firstCallFeature.isDone());
		isBalanceMatchAccountDTO(balanceFromSecondCall, AccountMockUtil.getAccount(AccountMockUtil.DEFAULT_ACCOUNT_ID));
		Balance balanceFromFirstCall = secondCallFeature.get();
		isBalanceMatchAccountDTO(balanceFromFirstCall, AccountMockUtil.getAccount(AccountMockUtil.DEFAULT_ACCOUNT_ID));
	}
}