# Thread lifecycle
![image](https://github.com/user-attachments/assets/ea661afc-d5f9-45cb-a697-e4dbcf2bce06)

## Thread lifecycle methods
~~~
Package java.lang
public class Thread implements Runnable {
    public void start();
    public void run();
    public void stop(); //deprecated (internal race condition)
    public void resume(); // deprecated
    public void suspend(); // deprecated
    public static void sleep(long millis);
    public static void sleep(long millis, int nanos);
    public boolean isAlive();
    public void interrupt();
    public boolean isInterrupted();
    public static boolean interrupted();
    public void join();
}
~~~

## Stop a thread
Setting a flag: Loop will exit (and thread will stop) on the next iteration after done is set to true
~~~
public void run () {
  while (!done ){
     //...
  }
}
~~~
Interrupting: 
- As above, when isInterrupted() is called for this thread, it will stop
- Except that if the thread is sleeping /waiting will throw InterruptedException and immediately return from run()

~~~
public void run() {
  while (!isInterrupted()){
    //...
  }
}
~~~
