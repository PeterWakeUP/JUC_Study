package juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock rl = lock.readLock();
    private ReentrantReadWriteLock.WriteLock wl = lock.writeLock();

    public void read1() {
        try {
            rl.lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(1000*60*60);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rl.unlock();
        }
    }
    public void read2() {
        try {
            rl.lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(1000*60*60);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rl.unlock();
        }
    }

    public void read4() {
        try {
            rl.lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(1000*60*60);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rl.unlock();
        }
    }

    public void write() {
        try {
            wl.lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(1000*60*60);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wl.unlock();
        }
    }

    public static void main(String[] args) {

        final ReentrantReadWriteLockTest reentrantReadWriteLockTest = new ReentrantReadWriteLockTest();

        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                reentrantReadWriteLockTest.read1();
            }
        });
        t1.setName("t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                reentrantReadWriteLockTest.read2();
            }
        });
        t2.setName("t2");

        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                reentrantReadWriteLockTest.write();
            }
        });
        t3.setName("t3");

        Thread t4 = new Thread(new Runnable() {

            @Override
            public void run() {
                reentrantReadWriteLockTest.read4();
            }
        });
        t4.setName("t4");


        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t4.start();
    }
}
