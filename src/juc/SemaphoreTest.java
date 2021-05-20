package juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    //停车场同时容纳的车辆7
    private static Semaphore semaphore = new Semaphore(7);

    public SemaphoreTest() {
        System.out.println("你妹的");
    }

    {
        System.out.println("我是你爹");
    }

    public static void main(String[] args) {
        new SemaphoreTest();
        new SemaphoreTest();

        //模拟10辆车进入停车场
        for(int i=0; i<10; i++) {

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("====" + Thread.currentThread().getName() + "来到停车场");
                        if (semaphore.availablePermits() == 0) {
                            System.out.println("车位不足，请耐心等待");
                        }
                        semaphore.acquire();//获取令牌尝试进入停车场
                        System.out.println(Thread.currentThread().getName() + "成功进入停车场");
                        Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                        System.out.println(Thread.currentThread().getName() + "驶出停车场");
                        semaphore.release();//释放令牌，腾出停车场车位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, i + "号车");

            thread.start();
        }
    }
}
