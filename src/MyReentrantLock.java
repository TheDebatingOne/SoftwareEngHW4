import java.util.concurrent.atomic.AtomicBoolean;

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
        if (owner == Thread.currentThread() || locked.compareAndSet(false, true)) {
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
            locked.set(false); //releasing
            owner = null;
        }
    }

    @Override
    public void close() throws Exception {
        this.release();
    }

}
