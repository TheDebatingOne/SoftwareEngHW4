import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Thread;

public class MyReentrantLock implements Lock, AutoCloseable{
    private AtomicBoolean locked; //the status of the lock
    private Thread owner; //the owner of the lock (aka the thread that locked him)
    private int lockedness; //num of times the lock is locked

    public MyReentrantLock(){
        owner = null;
        locked = new AtomicBoolean(false);
        lockedness = 0;
    }

    /**
     * acquiring the lock. if locked - waiting until it is not locked
     */
    @Override
    public void acquire() {
        while (!tryAcquire()) //wait until not locked
            Thread.yield();
    }

    /**
     * trying to acquire the lock
     * @return whether we succeeded to acquire the lock, or not
     */
    @Override
    public boolean tryAcquire() {
        if (Thread.currentThread().equals(owner) || locked.compareAndSet(false, true)) {
            owner = Thread.currentThread();
            lockedness++;
            return true;
        }
        return false;
    }

    /**
     * releasing (opening) the lock
     */
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

    /**
     * closing the lock (
     */
    @Override
    public void close() {
        this.release();
    }

}
