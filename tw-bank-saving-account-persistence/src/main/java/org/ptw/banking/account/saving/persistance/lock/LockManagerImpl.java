package org.ptw.banking.account.saving.persistance.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Component;


@Component
public class LockManagerImpl implements LockManager{

	private Map<String, ReentrantReadWriteLock> lockMap;

	public LockManagerImpl() {
		lockMap = new HashMap<String, ReentrantReadWriteLock>();
	}
	
	@Override
	public synchronized Lock readLock(String key) {
		ReentrantReadWriteLock reentrantReadWriteLock = lockMap.get(key);
		if(reentrantReadWriteLock == null) {
			reentrantReadWriteLock = new ReentrantReadWriteLock();
			lockMap.put(key, reentrantReadWriteLock);
		}
		return reentrantReadWriteLock.readLock();
	}

	@Override
	public synchronized Lock writeLock(String key) {
		ReentrantReadWriteLock reentrantReadWriteLock = lockMap.get(key);
		if(reentrantReadWriteLock == null) {
			reentrantReadWriteLock = new ReentrantReadWriteLock();
			lockMap.put(key, reentrantReadWriteLock);
		}
		return reentrantReadWriteLock.writeLock();
	}
}
