package com.bkrivetskyi.taskPoint;

public class PointSynchronMetodX {

    private int x;
    private int y;

    public static synchronized void move(PointSynchronMetodX synchronMethodX, int dx, int dy) {
        synchronMethodX.x += dx;
        synchronMethodX.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
