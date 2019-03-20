package org.ptw.banking.account.saving.service.mapper.impl;

import java.time.OffsetDateTime;
import java.util.Currency;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.AmountDTO;
import org.ptw.banking.account.saving.service.mapper.AccountMapper;
import org.ptw.banking.account.saving.service.mapper.AmountMapper;
import org.ptw.banking.account.saving.service.model.Account;
import org.ptw.banking.account.saving.service.model.Amount;
import org.ptw.banking.account.saving.service.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AccountMapperImpl implements AccountMapper {

	@Autowired
	protected AmountMapper amountMapper;

	@Override
	public void map(Account from, AccountDTO to) {
		to.setId(from.getId());
		Amount balance = from.getBalance().getAmount();
		if (balance != null) {
			to.setCurrency(Currency.getInstance(balance.getCurrency()));
			to.setBalance(amountMapper.map(balance));
		}
		to.setCreationDate(from.getCreationDate());
	}

	@Override
	public void map(AccountDTO from, Account to) {
		to.setId(from.getId());
		AmountDTO amountDTO = from.getBalance();
		if (amountDTO != null) {
			Balance balance = new Balance();
			balance.setAmount(amountMapper.map(amountDTO));
			balance.getAmount().setCurrency(from.getCurrency().getCurrencyCode());
			balance.setTimestamp(OffsetDateTime.now());
			to.setBalance(balance);
		}
		to.setCreationDate(from.getCreationDate());
	}

	@Override
	public Account map(AccountDTO from) {
		Account to = new Account();
		this.map(from, to);
		return to;
	}

	@Override
	public AccountDTO map(Account from) {
		AccountDTO to = new AccountDTO();
		this.map(from, to);
		return to;
	}

}
