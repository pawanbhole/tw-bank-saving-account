package org.ptw.banking.account.saving.persistance.repository;

import org.ptw.banking.account.saving.persistance.exception.ItemAlreadyExistException;
import org.ptw.banking.account.saving.persistance.exception.ItemNotFoundException;

public interface Repository<K, V>  {
	
	/** Adds the item to the store which can be retrieved with specified key.
	 * @param key
	 * @param value
	 * @throws ItemAlreadyExistException If item with specified key already exist.
	 */
	void add(K key, V value) throws ItemAlreadyExistException;
	
	/** Remove the item from store having  specified key.
	 * @param key
	 * @throws ItemNotFoundException
	 */
	void remove(K key) throws ItemNotFoundException;
	
	/** Returns the item with specified key from store.
	 * @param key
	 * @return Item
	 * @throws ItemNotFoundException If item with specified key not exist.
	 */
	V get(K key) throws ItemNotFoundException;
	
	/** Returns true if item with specified key exists, else returns false.
	 * @param key
	 * @return boolean true/false
	 */
	boolean exists(K key);

	/** Updates the item.
	 * @param key
	 * @param value
	 * @throws ItemNotFoundException If item with specified key not exist.
	 */
	void update(K key, V value) throws ItemNotFoundException;
}
