package org.ptw.banking.account.saving.service.mapper;

import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.service.model.Transaction;

public interface TransactionMapper {
	TransactionDTO map(Transaction from);
	Transaction map(TransactionDTO from);
	void map(Transaction from, TransactionDTO to);
	void map(TransactionDTO from, Transaction to);
}
