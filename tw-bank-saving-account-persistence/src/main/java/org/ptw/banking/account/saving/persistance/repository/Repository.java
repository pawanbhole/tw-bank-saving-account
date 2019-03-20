package org.ptw.banking.account.saving.persistance.repository;

import org.ptw.banking.account.saving.persistance.exception.ItemAlreadyExistException;
import org.ptw.banking.account.saving.persistance.exception.ItemNotFoundException;

public interface Repository<K, V>  {
	
	void add(K key, V value) throws ItemAlreadyExistException;
	
	void remove(K key) throws ItemNotFoundException;
	
	V get(K key) throws ItemNotFoundException;
	
	boolean exists(K key);

	void update(K key, V value) throws ItemNotFoundException;
}
