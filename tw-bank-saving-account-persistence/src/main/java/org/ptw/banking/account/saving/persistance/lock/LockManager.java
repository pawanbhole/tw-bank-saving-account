package org.ptw.banking.account.saving.persistance.lock;

import java.util.concurrent.locks.Lock;

public interface LockManager {
	Lock readLock(String key);
	Lock writeLock(String key);
}
