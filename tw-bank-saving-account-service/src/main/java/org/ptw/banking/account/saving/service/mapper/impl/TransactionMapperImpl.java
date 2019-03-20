package org.ptw.banking.account.saving.service.mapper.impl;

import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.service.mapper.AmountMapper;
import org.ptw.banking.account.saving.service.mapper.TransactionMapper;
import org.ptw.banking.account.saving.service.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TransactionMapperImpl implements TransactionMapper {
	
	@Autowired
	protected AmountMapper amountMapper;

	@Override
	public void map(Transaction from, TransactionDTO to) {
		to.setId(from.getId());
		to.setStatus(from.getStatus().name());
		if(from.getReasonCode() != null) {
			to.setReasonCode(from.getReasonCode().name());
		}
		to.setAmount(amountMapper.map(from.getAmount()));
		to.setTransactionDate(from.getTransactionDate());
		to.setType(from.getType().name());
	}

	@Override
	public void map(TransactionDTO from, Transaction to) {
		to.setId(from.getId());
		to.setStatus(Transaction.StatusEnum.valueOf(from.getStatus()));
		if(from.getReasonCode() != null) {
			to.setReasonCode(Transaction.ReasonCodeEnum.valueOf(from.getReasonCode()));
		}
		to.setAmount(amountMapper.map(from.getAmount()));
		to.setTransactionDate(from.getTransactionDate());
		to.setType(Transaction.TypeEnum.valueOf(from.getType()));
	}

	@Override
	public Transaction map(TransactionDTO from) {
		Transaction to = new Transaction();
		this.map(from, to);
		return to;
	}

	@Override
	public TransactionDTO map(Transaction from) {
		TransactionDTO to = new TransactionDTO();
		this.map(from, to);
		return to;
	}
}
