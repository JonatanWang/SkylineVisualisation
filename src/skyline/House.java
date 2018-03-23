/*
 * House
 */
package skyline;

/**
 *
 * @author Wang Zheng-Yu <zhengyuw@kth.se>
 */
public class House {

    //x1 left, y height, x2 right
    public House(int x1, int y, int x2) {
        this.x1 = x1;
        this.y = y;
        this.x2 = x2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }
    
    private int x1;
    private int y;
    private int x2;
    
}
