/**  
* <p>Title: Locker.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年4月1日  
*/  
package ms.subojetstudy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**  
* <p>Title: Locker</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年4月1日  
*/
public enum Locker {
	INSTANCE;
	
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();

	public Lock writeLock() {
		return lock.writeLock();
	}

}
