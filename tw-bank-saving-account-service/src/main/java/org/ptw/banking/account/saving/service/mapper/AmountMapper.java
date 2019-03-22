package org.ptw.banking.account.saving.service.mapper;

import org.ptw.banking.account.saving.persistance.dto.AmountDTO;
import org.ptw.banking.account.saving.service.model.Amount;

/**
 * 
 * Mapper to map Amount to/from AmountDTO
 * @author pawan
 *
 */
public interface AmountMapper {
	/**
	 * @param from org.ptw.banking.account.saving.service.model.Amount
	 * @return org.ptw.banking.account.saving.persistance.dto.AmountDTO
	 */
	AmountDTO map(Amount from);
	/**
	 * @param from org.ptw.banking.account.saving.persistance.dto.AmountDTO
	 * @return  org.ptw.banking.account.saving.service.model.Amount
	 */
	Amount map(AmountDTO from);
	/**
	 * @param from org.ptw.banking.account.saving.service.model.Amount
	 * @param to org.ptw.banking.account.saving.persistance.dto.AmountDTO
	 */
	void map(Amount from, AmountDTO to);
	/**
	 * @param from org.ptw.banking.account.saving.persistance.dto.AmountDTO
	 * @param to org.ptw.banking.account.saving.service.model.Amount
	 */
	void map(AmountDTO from, Amount to);
}
