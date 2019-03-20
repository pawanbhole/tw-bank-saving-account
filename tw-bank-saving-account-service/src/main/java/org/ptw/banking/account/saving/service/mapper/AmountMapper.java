package org.ptw.banking.account.saving.service.mapper;

import org.ptw.banking.account.saving.persistance.dto.AmountDTO;
import org.ptw.banking.account.saving.service.model.Amount;

public interface AmountMapper {
	AmountDTO map(Amount from);
	Amount map(AmountDTO from);
	void map(Amount from, AmountDTO to);
	void map(AmountDTO from, Amount to);
}
