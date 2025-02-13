import java.util.PriorityQueue;

class Solution {
    public int minOperations(int[] nums, int k) {
        // Use long to handle large numbers.
        PriorityQueue<Long> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer((long) num);
        }
        
        int operations = 0;
        while (heap.peek() < k) {
            // If less than two elements remain, the operation cannot be performed.
            if (heap.size() < 2) {
                return -1; // According to problem constraints, this should never happen.
            }
            long x = heap.poll();
            long y = heap.poll();
            long newNum = 2 * x + y;
            heap.offer(newNum);
            operations++;
        }
        return operations;
    }
}
