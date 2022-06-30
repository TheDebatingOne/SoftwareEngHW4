import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock {
    private AtomicBoolean locked;
    private Thread locker;
    @Override
    public void acquire() {
        while (!this.locked.get()); //wait until not locked
        this.locked.set(true);
    }

    @Override
    public boolean tryAcquire() {
    }

    @Override
    public void release() {
        if (!this.locked.get() || )
    }

    @Override
    public void close() throws Exception {

    }
}
