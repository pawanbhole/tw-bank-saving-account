package org.ptw.banking.account.saving.persistance.lock;

import java.util.concurrent.locks.Lock;

public interface LockManager {
	
	/**
	 * @param key
	 * Accepts the String key and returns the read lock object for that key
	 * @return  java.util.concurrent.locks.Lock
	 */
	Lock readLock(String key);
	
	/**
	 * @param key
	 * Accepts the String key and returns the write lock object for that key
	 * @return  java.util.concurrent.locks.Lock
	 */
	Lock writeLock(String key);
}
