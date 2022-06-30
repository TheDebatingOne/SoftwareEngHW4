import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock {
    private AtomicBoolean locked; //the status of the lock
    private Thread owner; //the owner of the lock (aka the thread that locked him)
    private int lockedness;

    @Override
    public void acquire() {
        while (!this.locked.get()); //wait until not locked
        this.locked.set(true); //locking
        this.owner = Thread.currentThread(); //setting the new owner
    }

    @Override
    public boolean tryAcquire() {
        if (this.owner !=null && this.owner == Thread.currentThread()) {
            this.lockedness++;
            return true;
        }
        if (!this.locked.getAndSet(true))
        {
            this.owner = Thread.currentThread();
            return true;
        }
        return false;
    }

    @Override
    public void release() {
        if (!this.locked.get() || !Thread.currentThread().equals(owner)) throw new IllegalReleaseAttempt();
        //TODO: maybe compare thread IDs instead of equals?
        this.locked.set(false); //releasing
        this.owner = null;
    }

    @Override
    public void close() throws Exception {
        this.release();
    }

}
