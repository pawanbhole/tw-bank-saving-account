package org.ptw.banking.account.saving.persistance.store;

public interface Store {
	String generateId();
	boolean exists(String accountId);
}
