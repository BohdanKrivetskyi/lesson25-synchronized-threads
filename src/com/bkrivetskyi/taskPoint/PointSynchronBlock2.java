package com.bkrivetskyi.taskPoint;

public class PointSynchronBlock2 {
    private static final Object lock = new Object();
    private int x;
    private int y;

    public static void move(PointSynchronBlock2 synchronBlock2, int dx, int dy) {
        synchronized (lock) {
            synchronBlock2.x += dx;
            synchronBlock2.y += dy;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
