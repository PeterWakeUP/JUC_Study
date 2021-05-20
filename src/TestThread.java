public class TestThread {

    public static void main(String[] args) {
        /*Thread thread = new Thread();
        thread.setPriority(Thread.MAX_PRIORITY);
        System.out.println(thread.getName());*/
        int i = 0;
        i = i++;
        System.out.println(i);

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}


class A{

    public static void say(){

    }

    private void hello(){

    }
}