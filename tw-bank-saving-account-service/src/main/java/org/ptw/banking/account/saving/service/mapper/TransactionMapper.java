package org.ptw.banking.account.saving.service.mapper;

import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.service.model.Transaction;

/**
 * 
 * Mapper to map Transaction to/from TransactionDTO
 * @author pawan
 *
 */
public interface TransactionMapper {
	/**
	 * @param from org.ptw.banking.account.saving.service.model.Transaction
	 * @return org.ptw.banking.account.saving.persistance.dto.TransactionDTO
	 */
	TransactionDTO map(Transaction from);
	/**
	 * @param from org.ptw.banking.account.saving.persistance.dto.TransactionDTO
	 * @return org.ptw.banking.account.saving.service.model.Transaction
	 */
	Transaction map(TransactionDTO from);
	/**
	 * @param from org.ptw.banking.account.saving.service.model.Transaction
	 * @param to org.ptw.banking.account.saving.persistance.dto.TransactionDTO
	 */
	void map(Transaction from, TransactionDTO to);
	/**
	 * @param from org.ptw.banking.account.saving.persistance.dto.TransactionDTO
	 * @param to org.ptw.banking.account.saving.service.model.Transaction
	 */
	void map(TransactionDTO from, Transaction to);
}
