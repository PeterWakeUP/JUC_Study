package juc;

import java.util.concurrent.*;

public class FutureTest {


    public static void main(String[] args){
        try {
            ComplexTask task = new ComplexTask();
            FutureTask<Double> future = new FutureTask<Double>(task);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(future);
            Double result = future.get(1000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    static class ComplexTask implements Callable<Double> {
        @Override
        public Double call() throws InterruptedException {
            Thread.sleep(3000);
            return ThreadLocalRandom.current().nextDouble();
        }
    }
}
