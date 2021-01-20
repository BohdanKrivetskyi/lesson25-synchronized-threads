package com.bkrivetskyi.taskPoint;

public class PointSynchronMethod {

    private int x;
    private int y;

    public synchronized void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
