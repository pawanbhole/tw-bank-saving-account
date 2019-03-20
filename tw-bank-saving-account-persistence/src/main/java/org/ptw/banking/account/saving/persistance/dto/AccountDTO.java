package org.ptw.banking.account.saving.persistance.dto;

import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.List;

public class AccountDTO {
	private String id;
	private Currency currency;
	private AmountDTO balance;
	private List<TransactionDTO> transactions;
	private OffsetDateTime creationDate;
	private String status;

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public AmountDTO getBalance() {
		return balance;
	}

	public void setBalance(AmountDTO balance) {
		this.balance = balance;
	}

	public List<TransactionDTO> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionDTO> transactions) {
		this.transactions = transactions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccountDTO [id=" + id + ", currency=" + currency + ", balance=" + balance + ", transactions="
				+ transactions + ", creationDate=" + creationDate + ", status=" + status + "]";
	}

}