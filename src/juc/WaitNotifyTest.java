package juc;

public class WaitNotifyTest {

    Object lockObj;

    static class CountThread extends Thread{
        Object lockObj;
        String threadName;

        public CountThread() {
        }

        public CountThread(Object lockObj, String threadName) {
            super(threadName);
            this.lockObj = lockObj;
            this.threadName = threadName;
        }

        @Override
        public void run() {
            int i = 1;
            while(true){
                synchronized (lockObj){
                    System.out.println(getName() + ":" + i);
                    try {
                        lockObj.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                i++;
            }
        }
    }


    static class NotifyThread extends Thread{
        Object lockObj;

        public NotifyThread(Object lockObj) {
            this.lockObj = lockObj;
        }

        @Override
        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockObj){
                System.out.println("notifyAll()执行完毕");
                lockObj.notifyAll();
            }
        }
    }


    public static void main(String[] args){
        Object lockObj = new Object();
        CountThread countThread1 = new CountThread(lockObj, "1号");
        CountThread countThread2 = new CountThread(lockObj, "2号");
        countThread1.start();
        countThread2.start();
        NotifyThread notifyThread = new NotifyThread(lockObj);
        notifyThread.start();
    }

}
