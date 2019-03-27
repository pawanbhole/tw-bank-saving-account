package org.ptw.banking.account.saving.resources;

import org.ptw.banking.account.saving.service.CreateAccountService;
import org.ptw.banking.account.saving.service.AccountTransactionService;
import org.ptw.banking.account.saving.service.GetBalanceService;
import org.ptw.banking.account.saving.service.GetTransactionsService;
import org.ptw.banking.account.saving.service.api.SavingAccountApi;
import org.ptw.banking.account.saving.service.model.Account;
import org.ptw.banking.account.saving.service.model.AccountRequest;
import org.ptw.banking.account.saving.service.model.Amount;
import org.ptw.banking.account.saving.service.model.Balance;
import org.ptw.banking.account.saving.service.model.Transaction.TypeEnum;
import org.ptw.banking.account.saving.service.model.TransactionResponse;
import org.ptw.banking.account.saving.service.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API implementation for Saving Account.
 * @author pawan
 *
 */
@RestController
public class SavingAccountApiImpl implements SavingAccountApi {

	@Autowired
	protected GetBalanceService getBalanceService;

	@Autowired
	protected CreateAccountService createAccountService;
	
	@Autowired
	protected AccountTransactionService accountTransactionService;
	
	@Autowired
	protected GetTransactionsService getTransactionsService;
	
	@Override
	public ResponseEntity<Balance> getBalance(@PathVariable("accountId") String accountId) {
		Balance balance = getBalanceService.execute(accountId);
		return  new ResponseEntity<>(balance, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
		Account account = createAccountService.execute(accountRequest);
		return  new ResponseEntity<>(account, HttpStatus.OK);
    }
	
	@Override
	public ResponseEntity<TransactionResponse> deposit(@PathVariable("accountId") String accountId, @RequestBody Amount amount) {
		TransactionResponse transactionResponse = accountTransactionService.execute(accountId, amount, TypeEnum.DEPOSIT);
		return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }
	
	@Override
	public ResponseEntity<TransactionResponse> withdraw(@PathVariable("accountId") String accountId, @RequestBody Amount amount) {
		TransactionResponse transactionResponse = accountTransactionService.execute(accountId, amount, TypeEnum.WITHDRAW);
		return new ResponseEntity<>(transactionResponse, HttpStatus.OK);
    }
	
	@Override
	public ResponseEntity<Transactions> getTransactions(@PathVariable("accountId") String accountId, Integer offset,  Integer limit) {
		Transactions transactions = getTransactionsService.execute(accountId,  offset,  limit);
		return  new ResponseEntity<>(transactions, HttpStatus.OK);
	}
        
}