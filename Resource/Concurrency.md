- Concurrency refers to the ability of a program to execute multiple tasks simultaneously.
- It enables the efficient use of system resources like CPUs and helps in building responsive, high-performance applications.

Java provides robust support for concurrency through threads, the **java.util.concurrent** package, and advanced tools for synchronization and parallel processing.

## Thread
- A thread is a lightweight unit of a process.
- Java's Thread class and Runnable interface allow the creation and management of threads.
- A program with multiple threads can perform tasks concurrently.

~~~
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running!");
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start(); // Starts the thread
    }
}
~~~

## Multithreading
- Multithreading refers to running multiple threads simultaneously.
- Each thread runs independently, sharing the same memory space

~~~
class MyRunnable implements Runnable {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable());
        Thread thread2 = new Thread(new MyRunnable());

        thread1.start();
        thread2.start();
    }
}
~~~

## Synchronization
- Ensures that only one thread can access a critical section of code at a time.
- Prevents race conditions and data inconsistency

~~~
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
~~~

## Concurrency Utilities
The java.util.concurrent package provides high-level abstractions for concurrency:

- Executor Framework: For managing thread pools and executing tasks.
~~~
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.execute(() -> System.out.println("Task 1"));
executor.execute(() -> System.out.println("Task 2"));
executor.shutdown();
~~~

- Locks: Fine-grained control over synchronization
~~~
ReentrantLock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
~~~

- Concurrent Collections: Thread-safe data structures like ConcurrentHashMap and CopyOnWriteArrayList.
- CountDownLatch, CyclicBarrier, Semaphore: Tools for synchronization and coordination between threads
