/**  
* <p>Title: TestThread.java</p>
* <p>Description: </p> 
* @author pxx 
* @date 2019年4月1日  
*/  
package ms.subojetstudy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;


/**  
* <p>Title: TestThread</p>  
* <p>Description: </p>  
* @author pxx  
* @date 2019年4月1日  
*/
public class TestThread {
	
//	public static void main1(String[] args) {
//		//Java的并发是抢占式
//		for (int i = 0; i < 10; i++) {
//			Thread thread = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					System.out.println("我叫" + Thread.currentThread().getName() + "，我超喜欢沉默王二的写作风格");
//				}
//			});
//			thread.start();
//		}
//	}
//	
//	public static void main2(String[] args) {
//		//阿里巴巴java开发手册中不让用
//		ExecutorService executorService = Executors.newCachedThreadPool();
//		for (int i = 0; i < 10; i++) {
//			Runnable r = new Runnable() {
//				@Override
//				public void run() {
//					System.out.println("我叫" + Thread.currentThread().getName() + "，我超喜欢沉默王二的写作风格");
//				}
//			};
//			executorService.execute(r);
//		}
//		executorService.shutdown();
//	}

	public static int count = 0; 
	public static int getCount() {
		return count;    
	} 
//	public synchronized static void addCount() {
//		count++;    
//	} 
	public static void addCount() {
		// 上锁
//		Lock writeLock = Locker.INSTANCE.writeLock();
//		writeLock.lock();
		count++;
		// 释放锁
//		writeLock.unlock();
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = new ThreadPoolExecutor(10, 1000, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10)); 
		for (int i = 0; i < 1000; i++) {  
			Runnable r = new Runnable() { 
				@Override 
				public void run() {
					TestThread.addCount(); 
				}            
			};           
			executorService.execute(r);  
		}        
		executorService.shutdown();
		System.out.println(TestThread.count);
//		try {
//			Class<?> forName = Class.forName("ms.subojetstudy.TestThread");
//			ClassLoader classLoader = forName.getClassLoader();
//			System.out.println("获取类的全限定名称  "+classLoader.toString());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		TestThread testThread = new TestThread();
//		Class<? extends TestThread> class1 = testThread.getClass();
//		ClassLoader classLoader = class1.getClassLoader();
//		System.out.println("对象的getClass()   "+classLoader.toString());
	}
}
