# Race conditions
- Threads perform non-atomic operations: Operations that involve multiple steps, such as reading, modifying, and writing a variable, can be interrupted by other threads.
- Threads lack synchronization: Shared resources are accessed without mechanisms like synchronized blocks or explicit locks.

![image](https://github.com/user-attachments/assets/a95334d8-3881-45fd-bf8d-a730d75b5ca9)

### How to Avoid Race Conditions
- Use synchronized Synchronization ensures only one thread accesses the critical section at a time

~~~
public class MyCounter {
  private int count = 0;
  public synchronized void addTwo() {
    count = count + 2;
  }
  public synchronized void subtractTwo() {
    count = count - 2;
  }
  public synchronized int countValue() {
    return count ;
  }
}

~~~

- Use Explicit Locks Using ReentrantLock provides more control over thread synchronization.
- Use Atomic Variables For simple counters or flags, Java provides Atomic classes in the java.util.concurrent.atomic package.
- Use Concurrent Data Structures The java.util.concurrent package provides thread-safe classes like ConcurrentHashMap, CopyOnWriteArrayList, etc., to handle shared data

