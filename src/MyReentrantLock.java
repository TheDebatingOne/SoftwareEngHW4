import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Thread;

public class MyReentrantLock implements Lock, AutoCloseable{
    private AtomicBoolean locked; //the status of the lock
    private Thread owner; //the owner of the lock (aka the thread that locked him)
    private int lockedness;

    public MyReentrantLock(){
        owner = null;
        locked = new AtomicBoolean(false);
        lockedness = 0;
    }

    @Override
    public void acquire() {
        while (!tryAcquire()) //wait until not locked
            Thread.yield();
    }

    @Override
    public boolean tryAcquire() {
        if (Thread.currentThread().equals(owner) || locked.compareAndSet(false, true)) {
            owner = Thread.currentThread();
            lockedness++;
            return true;
        }
        return false;
    }

    @Override
    public void release() {
        if (lockedness == 0 || !Thread.currentThread().equals(owner))
            throw new IllegalReleaseAttempt();
        lockedness--;
        if (lockedness == 0) {
            owner = null;
            locked.set(false); //releasing

        }
    }
    @Override
    public void close() {
        this.release();
    }

}
