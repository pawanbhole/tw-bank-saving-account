package org.ptw.banking.account.saving.persistance.dto;

public class AmountDTO {
	@Override
	public String toString() {
		return "AmountDTO [value=" + value + "]";
	}

	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
}
