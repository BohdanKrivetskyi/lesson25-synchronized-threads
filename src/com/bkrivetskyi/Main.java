package com.bkrivetskyi;

import com.bkrivetskyi.FibonacciTask.FibonacciRunnable;
import com.bkrivetskyi.FibonacciTask.FibonacciThread;
import com.bkrivetskyi.taskPoint.*;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
       // new Main().task1();
       // new Main().task2();
        new Main().task3();
       // new Main().task4();
    }

    public void task1() {
        Point point = new Point();
        PointSynchronMethod synchronMethod = new PointSynchronMethod();
        PointSynchronBlock1 synchronBlock1 = new PointSynchronBlock1();
        PointSynchronBlock2 synchronBlock2 = new PointSynchronBlock2();
        PointSynchronMetodX synchronMethodX = new PointSynchronMetodX();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Future<String> result = null;
        for (int i = 0; i < 2000; i++) {
            result = executor.submit(() -> {
                point.move(1, 1);
                synchronMethod.move(1, 1);
                synchronBlock1.move(1, 1);
                PointSynchronBlock2.move(synchronBlock2, 1, 1);
                PointSynchronMetodX.move(synchronMethodX, 1, 1);
                return "";
            });
        }
        try {
            result.get(3, TimeUnit.SECONDS);
            System.out.println("Final result synchronized  " + point.getX() + " : " + point.getY());
            System.out.println("Final result synchronized method  " + synchronMethod.getX() + " : " + synchronMethod.getY());
            System.out.println("Final result synchronized block #1  " + synchronBlock1.getX() + " : " + synchronBlock1.getY());
            System.out.println("Final result synchronized block #2  " + synchronBlock2.getX() + " : " + synchronBlock2.getY());
            System.out.println("Final result synchronized method X  " + synchronMethodX.getX() + " : " + synchronMethodX.getY());
        } catch (InterruptedException e) {
            System.out.println("Current thread has been interrupted/canceled");
            result.cancel(true);
            System.out.println("Point move has been cancelled");
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.err.println("Internal exception: " + e.getMessage());
            e.printStackTrace();
        } catch (TimeoutException e) {
            result.cancel(true);
            System.out.println("Point move has timed out and canceled");
            System.out.println("Last result synchronized  " + point.getX() + " : " + point.getY());
            System.out.println("Last result synchronized method  " + synchronMethod.getX() + " : " + synchronMethod.getY());
            System.out.println("Last result synchronized block #1  " + synchronBlock1.getX() + " : " + synchronBlock1.getY());
            System.out.println("Last result synchronized block #2  " + synchronBlock2.getX() + " : " + synchronBlock2.getY());
            System.out.println("Last result synchronized method X  " + synchronMethodX.getX() + " : " + synchronMethodX.getY());
            e.printStackTrace();
        }
        executor.shutdown();
    }

    private void task2() {
        FibonacciThread thread = new FibonacciThread(11);
        thread.start();
        try {
            Thread.sleep(3000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Current thread has been interrupted/cancelled");
            e.printStackTrace();
        }
    }

    private void task3() {
        FibonacciThread thread = new FibonacciThread(333);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<?> result = executor.submit(thread);
        try {
            result.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Current thread has been interrupted/canceled. ");
            result.cancel(true);
        } catch (TimeoutException e) {
            result.cancel(true);
            System.out.println("Thread has timed out and cancelled");
        }
        executor.shutdown();
    }

    private void task4() {
        FibonacciRunnable task = new FibonacciRunnable(555);
        Thread thread = new Thread(task);
        thread.start();
        try {
            Thread.sleep(3000);
            thread.interrupt();
        } catch (InterruptedException e) {
            System.out.println("Current thread has been interrupted/cancelled");
            e.printStackTrace();
        }
    }
}
