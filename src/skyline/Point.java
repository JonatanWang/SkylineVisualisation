/*
 * point
 */
package skyline;

/**
 *
 * @author Wang Zheng-Yu <zhengyuw@kth.se>
 */
public class Point {

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    private int x;
    private int y;
}
