package com.bkrivetskyi.taskPoint;

public class PointSynchronBlock1 {
    private final Object lock = new Object();
    private int x;
    private int y;

    public void move(int dx, int dy) {
        synchronized (lock) {
            x += dx;
            y += dy;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
