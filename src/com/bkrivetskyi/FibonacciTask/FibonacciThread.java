package com.bkrivetskyi.FibonacciTask;

import java.math.BigInteger;
import java.util.concurrent.*;

public class FibonacciThread extends Thread {

    private int number;

    public FibonacciThread(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Calculation Fibonacci start" + number);
            if (number <= 1) System.out.println(BigInteger.valueOf(number));

            BigInteger previous = BigInteger.ZERO, next = BigInteger.ONE, sum;

            for (int i = 2; i <= number; i++) {
                sum = previous;
                previous = next;
                next = sum.add(previous);
                System.out.println("Sum= " + sum + " + " + previous);
            }

            System.out.println("Result: " + next);
        }
    }
}
