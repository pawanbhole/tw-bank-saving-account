package org.ptw.banking.account.saving.persistance.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.ptw.banking.account.saving.persistance.exception.ItemAlreadyExistException;
import org.ptw.banking.account.saving.persistance.exception.ItemNotFoundException;
import org.ptw.banking.account.saving.persistance.repository.Repository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author pawan
 * Implementation for Repository which maintains the data in-memory
 * @param <K> Key
 * @param <V> Item
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ImMemoryRepository<K, V> implements Repository<K, V>{

	private Map<K, V> objectStore;

	public ImMemoryRepository() {
		objectStore = new HashMap<K, V>();
	}
	
	@Override
	synchronized public void add(K key, V value) throws ItemAlreadyExistException {
		if(this.exists(key)) {
			throw new ItemAlreadyExistException("Resource with id '"+key+"' already exist.");
		} else {
			objectStore.put(key, value);
		}
	}

	@Override
	synchronized public void update(K key, V value) throws ItemNotFoundException {
		if(this.exists(key)) {
			objectStore.put(key, value);
		} else {
			throw new ItemNotFoundException("Resource with id '"+key+"' not found.");
		}
	}

	@Override
	synchronized public void remove(K key) throws ItemNotFoundException {
		if(this.exists(key)) {
			objectStore.remove(key);
		} else {
			throw new ItemNotFoundException("Resource with id '"+key+"' not found.");
		}
	}

	@Override
	synchronized public V get(K key) throws ItemNotFoundException {
		if(this.exists(key)) {
			return objectStore.get(key);
		} else {
			throw new ItemNotFoundException("Resource with id '"+key+"' not found.");
		}
	}
	
	@Override
	synchronized public boolean exists(K key) {
		return objectStore.containsKey(key);
	}
}
