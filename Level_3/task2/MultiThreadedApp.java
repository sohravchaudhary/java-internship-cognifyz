class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class WorkerThread extends Thread {
    private Counter counter;

    public WorkerThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}

public class MultiThreadedApp {
    public static void main(String[] args) {
        Counter counter = new Counter();

        WorkerThread[] threads = new WorkerThread[5];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new WorkerThread(counter);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final counter value: " + counter.getCount());
    }
}
