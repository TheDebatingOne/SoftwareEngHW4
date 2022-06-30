import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock {
    private AtomicBoolean locked;
    @Override
    public void acquire() {
        while (!this.locked.get()); //wait
        this.locked.set(true);
    }

    @Override
    public boolean tryAcquire() {
        return false;
    }

    @Override
    public void release() {

    }

    @Override
    public void close() throws Exception {

    }
}
