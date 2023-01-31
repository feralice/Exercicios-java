import java.util.concurrent.locks.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tarefa {

	static Lock lockUm = new ReentrantLock();
	static Lock lockDois = new ReentrantLock();
	
	public static void acquire(Lock lock1, Lock lock2) throws InterruptedException {

		while(true) {

			boolean bLock1 = false;
			boolean bLock2 = false;

			try {
				bLock1 = lock1.tryLock();
				bLock2 = lock2.tryLock();
			} finally {
				if(bLock1 && bLock2){
                    return;
                } else if (bLock1) {
                    lock1.unlock();
                } else if (bLock2) {
                    lock2.unlock();
                }
			}
		}
	}
	
	public static void release(Lock lock1, Lock lock2) {
		lock1.unlock();
		lock2.unlock();
	}
	
	static class primeiro implements Runnable {

		public void run() {

			System.out.println(Thread.currentThread().getName() + " : tentando adquirir os bloqueios");
			
			try {
				acquire(lockUm, lockDois);
				System.out.println(Thread.currentThread().getName() + " : bloqueios adquiridos");
				Thread.sleep(200);
				release(lockUm, lockDois);
			} catch (InterruptedException e) {}

			System.out.println(Thread.currentThread().getName() + " : finalizou e liberou os bloqueios");

		}
	}
	
	static class segundo implements Runnable {

		public void run() {
			System.out.println(Thread.currentThread().getName() + " : tentando adquirir os bloqueios");

			try {
			    acquire(lockDois, lockUm);
				System.out.println(Thread.currentThread().getName() + " : bloqueios adquiridos");
				Thread.sleep(1);
				release(lockDois, lockUm);
			} catch (InterruptedException e) {}
            
			System.out.println(Thread.currentThread().getName() + " : finalizou e liberou os bloqueios");
		}
	}
	
	public static void main(String arg[]) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		for(int i = 0; i < 5; i++) {
			executor.submit(new primeiro());
		}
		
		for(int i = 0; i < 5; i++) {
			executor.submit(new segundo());
		}

		executor.shutdown();
		
		try {
			executor.awaitTermination(1,TimeUnit.DAYS);
		} catch (InterruptedException ignored) {}
		
		System.out.println("Terminou");
	}

}