package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolitateIntegerTest {
    //private static Lock lock = new ReentrantLock();

    private static volatile Integer value = 0;
    private static volatile Boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(flag);

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Accumlator(), "thread-" + i);
            list.add(t);
            t.start();
        }

        System.out.println(flag);
        for (Thread t : list) {
            t.join();
        }

        //不等于10000，即使volatile保证可见性，但value++内部是怎么操作的不清楚，有可能是赋值给其他变量自增完再赋值给value
        System.out.println(value);
        System.out.println(flag);
    }

    static class Accumlator implements Runnable {

        @Override
        public void run() {
            for (int i = 0, len = 1000; i < len; i++) {
                try {
                    //lock.lock();
                    ++value;
                    flag = true;
                }finally {
                    //lock.unlock();
                }

            }
        }
    }
}
