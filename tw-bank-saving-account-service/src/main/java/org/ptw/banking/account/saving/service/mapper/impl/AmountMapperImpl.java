package org.ptw.banking.account.saving.service.mapper.impl;

import java.math.BigDecimal;

import org.ptw.banking.account.saving.persistance.dto.AmountDTO;
import org.ptw.banking.account.saving.service.mapper.AmountMapper;
import org.ptw.banking.account.saving.service.model.Amount;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AmountMapperImpl implements AmountMapper {

	@Override
	public void map(Amount from, AmountDTO to) {
		to.setValue(from.getValue().doubleValue());
	}

	@Override
	public void map(AmountDTO from, Amount to) {
		to.setValue(BigDecimal.valueOf(from.getValue()));
	}

	@Override
	public AmountDTO map(Amount from) {
		AmountDTO to = new AmountDTO();
		this.map(from, to);
		return to;
	}

	@Override
	public Amount map(AmountDTO from) {
		Amount to = new Amount();
		this.map(from, to);
		return to;
	}
}
