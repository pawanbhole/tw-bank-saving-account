package org.ptw.banking.account.saving.service.impl;

import java.util.List;

import org.ptw.banking.account.saving.persistance.dto.AccountDTO;
import org.ptw.banking.account.saving.persistance.dto.TransactionDTO;
import org.ptw.banking.account.saving.service.TallyTransactionAndBalanceService;
import org.springframework.stereotype.Service;


@Service
public class TallyTransactionAndBalanceServiceImpl implements TallyTransactionAndBalanceService{

	@Override
	public boolean execute(AccountDTO accountDTO, List<TransactionDTO> transactionDTOs) {
		double expectedBalance = 0;
		for(int index = 0; index < transactionDTOs.size() ; index++) {
			TransactionDTO transactionDTO = transactionDTOs.get(index);
			switch (transactionDTO.getType()) {
			case "DEPOSIT":
				expectedBalance += transactionDTO.getAmount().getValue();
				break;
			case "WITHDRAW":
				expectedBalance -= transactionDTO.getAmount().getValue();
				break;
			}
		}
		return expectedBalance == accountDTO.getBalance().getValue();
	}
}
