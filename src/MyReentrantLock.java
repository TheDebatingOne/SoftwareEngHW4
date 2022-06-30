public class MyReentrantLock implements Lock {
    private boolean locked;
    @Override
    public void acquire() {
        while (!this.locked); //wait
        this.locked = true;
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
