package ru.agolovin;

/**
 * @author agolovin (agolovin@list.ru)
 * @version $Id$
 * @since 0.1
 */

public class Lock {

    /**
     * Lock monitor.
     */
    private boolean flag = false;

    /**
     * Lock object.
     *
     * @return boolean result.
     * @throws InterruptedException e
     */
    private boolean lock() throws InterruptedException {
        synchronized (this) {
            while (flag) {
                this.wait();
            }
            this.flag = true;
            return true;
        }
    }

    /**
     * Unlock object.
     *
     * @return boolean result.
     */
    private boolean unLock() {
        synchronized (this) {
            this.flag = false;
            notify();
        }
        return true;
    }
}
