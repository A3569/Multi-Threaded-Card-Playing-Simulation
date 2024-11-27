
## 1. wait()
- Defined in java.lang.Object.
- Causes the current thread to release the lock on the object and wait until another thread calls notify() or notifyAll() on the same object.
- Must be called inside a synchronized block or method; otherwise, it throws IllegalMonitorStateException.

~~~
public synchronized void announceBob() {
  while(!bob) {
    try {
        wait();
    } catch (InterruptedException e) {}
  }
  System.out.println("We have bob!!");
}
~~~

## 2.notify()和notifyAll()
- Also defined in java.lang.Object.
- notify(): Wakes up one thread waiting on the object's monitor.
- notifyAll(): Wakes up all threads waiting on the object's monitor.
- These methods must also be called inside a synchronized block or method

~~~
public synchronized notifyBob() {
  bob = true ;
  notifyAll ();
}

public synchronized notifyBob() {
  bob = true ;
  notify();
}
~~~

## 3. sleep()
- Defined in java.lang.Thread.
- Puts the current thread to sleep for a specified duration, without releasing any locks it holds.
- It is often used to simulate delays or pauses

~~~
public class SleepExample {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                System.out.println("Thread sleeping for 2 seconds.");
                Thread.sleep(2000); // Sleep for 2 seconds
                System.out.println("Thread awake.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}
~~~

## 4. yield()
- Defined in java.lang.Thread.
- Pauses the execution of the current thread to allow other threads of the same priority or higher to execute.
- It doesn’t release any locks and may result in the same thread continuing execution if no higher-priority threads are ready

~~~
public class YieldExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " running.");
                Thread.yield(); // Suggests giving CPU time to other threads
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " running.");
            }
        });

        t1.start();
        t2.start();
    }
}
~~~

## 5. join()
- Defined in java.lang.Thread.
- Causes the current thread to wait for another thread to finish execution.
- Useful for sequencing thread execution.

~~~
public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 running...");
            try {
                Thread.sleep(2000); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 finished.");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 running...");
        });

        t1.start();
        t1.join(); // Waits for t1 to finish
        t2.start();
    }
}
~~~

## Locks (ReentrantLock)
- From the java.util.concurrent.locks package.
- Provides more advanced locking mechanisms than synchronized.
- Allows locking and unlocking explicitly, with features like fair locks and condition variables.

Key Methods:
- lock(): Acquires the lock.
- unlock(): Releases the lock.
- tryLock(): Attempts to acquire the lock without blocking indefinitely

~~~
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private final Lock lock = new ReentrantLock();

    void safeMethod() {
        lock.lock(); // Acquires the lock
        try {
            System.out.println(Thread.currentThread().getName() + " working on resource.");
            Thread.sleep(1000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // Always release the lock
        }
    }
}

public class LockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread t1 = new Thread(resource::safeMethod);
        Thread t2 = new Thread(resource::safeMethod);

        t1.start();
        t2.start();
    }
}
~~~
