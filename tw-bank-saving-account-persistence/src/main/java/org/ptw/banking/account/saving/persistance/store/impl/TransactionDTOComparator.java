package org.ptw.banking.account.saving.persistance.store.impl;

import java.util.Comparator;

import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.springframework.stereotype.Component;

@Component
public class TransactionDTOComparator implements Comparator<TransactionDTO> {

	@Override
	public int compare(TransactionDTO o1, TransactionDTO o2) {
		int difference = (int) (o1.getTransactionDate().compareTo(o2.getTransactionDate()));
		if (difference == 0) {
			difference = o1.getId().compareTo(o2.getId());
		}
		return difference;
	}
}