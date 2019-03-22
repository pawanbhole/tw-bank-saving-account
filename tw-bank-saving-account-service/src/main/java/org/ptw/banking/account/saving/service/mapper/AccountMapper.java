package org.ptw.banking.account.saving.service.mapper;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.service.model.Account;

/**
 * 
 * Mapper to map Account to/from AccountDTO
 * 
 * @author pawan
 *
 */
public interface AccountMapper {
	/**
	 * Maps the org.ptw.banking.account.saving.service.model.Account to org.ptw.banking.account.saving.persistance.dto.AccountDTO
	 * 
	 * @param from org.ptw.banking.account.saving.service.model.Account
	 * @param to org.ptw.banking.account.saving.persistance.dto.AccountDTO
	 */
	void map(Account from, AccountDTO to);

	/**
	 * Maps the org.ptw.banking.account.saving.persistance.dto.AccountDTO to org.ptw.banking.account.saving.service.model.Account
	 * 
	 * @param from org.ptw.banking.account.saving.persistance.dto.AccountDTO
	 * @param to org.ptw.banking.account.saving.service.model.Account
	 */
	void map(AccountDTO from, Account to);

	/**
	 * @param from org.ptw.banking.account.saving.persistance.dto.AccountDTO
	 * @return org.ptw.banking.account.saving.service.model.Account
	 */
	Account map(AccountDTO from);

	/**
	 * @param from org.ptw.banking.account.saving.service.model.Account
	 * @return org.ptw.banking.account.saving.persistance.dto.AccountDTO
	 */
	AccountDTO map(Account from);
}
