/*
 * Skyline by Divide & Conquer
 */
package skyline;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Wang Zheng-Yu <zhengyuw@kth.se>
 */
public class Model {
    
    // wrapper
    public List<int[]> skyline(int[][] houses) {
		
        if (houses.length == 0) {
            return new LinkedList<>();
        }     
        return skyline(houses, 0, houses.length - 1);
    }

    /**
     * D & C
     * 1. divide houses recursively into left & right
     * 2. solve left & right
     * 3. merge sub-result of left & right
     * @param houses
     * @param first
     * @param last
     * @return 
     */
    private LinkedList<int[]> skyline(int[][] houses, int first, int last) {
        
        if (first < last) {
            int mid = first + (last - first) / 2;  // divide in middle
            return merge(skyline(houses, first, mid), skyline(houses, mid + 1, last));
	} else {
                // first == last
        	LinkedList<int[]> result = new LinkedList<>();
                        
		result.add(new int[] { houses[first][0], houses[first][1] });   // left, height
                result.add(new int[] { houses[first][2], houses[first][1] });   // right, height
                result.add(new int[] { houses[first][2], 0 });                  // right, 0:ground
        	return result;
            }
	}

    /**
     * method comparing adjacent two houses
     * @param left house in left list
     * @param right house  in right list
     * @return 
     */
    private LinkedList<int[]> merge(LinkedList<int[]> left, LinkedList<int[]> right) {
		
        LinkedList<int[]> result = new LinkedList<>();
        
            int height1 = 0, height2 = 0; // y
            while (left.size() > 0 && right.size() > 0) {
		int x = 0, y = 0; // x => x1, y => height
                // if left.x1 < right.x1, draw from left house, right house's left side overlapsed
		if (left.getFirst()[0] < right.getFirst()[0]) {
			x = left.getFirst()[0];
			height1 = left.getFirst()[1];
			y = Math.max(height1, height2);
			left.removeFirst();
		} 
                // if right.x1 < left.x1, draw from right house, left house's right side overlapsed
                else if (right.getFirst()[0] < left.getFirst()[0]) {
			x = right.getFirst()[0];
			height2 = right.getFirst()[1];
			y = Math.max(height1, height2);
			right.removeFirst();
		} 
                // if left.x1 == right.x1, compare heights of left & right, draw the higher one.
                else {
			x = left.getFirst()[0];
			height1 = left.getFirst()[1];
			height2 = right.getFirst()[1];
			y = Math.max(height1, height2);
			left.removeFirst();
			right.removeFirst();
		}
                // set in points
		if (result.isEmpty() || y != result.getLast()[1]) {
			result.add(new int[] { x, y });
		}
	}
	result.addAll(left);
	result.addAll(right);
	return result;
    }
}