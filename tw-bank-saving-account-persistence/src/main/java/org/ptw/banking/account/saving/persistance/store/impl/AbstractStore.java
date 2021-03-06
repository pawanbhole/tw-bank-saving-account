package org.ptw.banking.account.saving.persistance.store.impl;

import org.ptw.banking.account.saving.persistance.store.Store;

/**
 * Abstract class giving implementation for thread safe id generation.
 * @author pawan
 *
 */
public abstract class AbstractStore implements Store {

	private Long idSequence;

	public AbstractStore() {
		idSequence = 0L;
	}

	@Override
	public String generateId() {
		String id;
		synchronized (idSequence) {
			idSequence++;
			id = String.format("%05d", idSequence);
		}
		return id;
	}

}
