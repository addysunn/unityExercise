package backend;

import java.util.HashMap;

public class ThreadConcurrency {
    /**
     *
     * Here is the implementation of two threads sharing one HashMap and trying to update key "k1"
     * Because the second thread updates after a minute First thread has completed its task
     * it would be able to Update the value of the key "k1".
     * By definition, the put command replaces the previous value associated with the given key in the map
     * The difference is created when we use the join() method , without it we will be able to
     * see the actual value of the Map. It randomly prints the value when either of the Threads process first.
     * */

    private static HashMap<String, String> map = new HashMap<>();

    public static class ThreadOne implements Runnable {

        @Override
        public void run() {
            map.put("k1", "v1");
            try {
                Thread.sleep(120000);
            }catch(Exception e)
            {}
        }
    }

    public static class ThreadTwo implements Runnable {

        @Override
        public void run() {
            map.put("k1", "v2");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());

        one.start();
        two.start();

        one.join();
        two.join();
        System.out.println("Output: " + map.get("k1"));

    }
}