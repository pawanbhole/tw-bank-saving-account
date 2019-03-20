package org.ptw.banking.account.saving.persistance.dto;

import java.time.OffsetDateTime;

public class TransactionDTO {
	private String id;
	private AmountDTO amount;
	private OffsetDateTime transactionDate;
	private String status;
	private String reasonCode;
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AmountDTO getAmount() {
		return amount;
	}

	public void setAmount(AmountDTO amount) {
		this.amount = amount;
	}

	public OffsetDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(OffsetDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TransactionDTO [id=" + id + ", amount=" + amount + ", transactionDate=" + transactionDate + ", status="
				+ status + ", reasonCode=" + reasonCode + ", type=" + type + "]";
	}

}
