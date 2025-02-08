import java.util.*;

class NumberContainers {
    // Map to store the current number at each index.
    private Map<Integer, Integer> indexToNumber;
    // Map to store, for each number, a sorted set of indices that contain that number.
    private Map<Integer, TreeSet<Integer>> numberToIndices;

    public NumberContainers() {
        indexToNumber = new HashMap<>();
        numberToIndices = new HashMap<>();
    }
    
    public void change(int index, int number) {
        // If this index was previously assigned a number, remove it from the old mapping.
        if (indexToNumber.containsKey(index)) {
            int oldNumber = indexToNumber.get(index);
            // If the number is not changing, we can return immediately.
            if (oldNumber == number) {
                return;
            }
            TreeSet<Integer> oldSet = numberToIndices.get(oldNumber);
            oldSet.remove(index);
            // Clean up if there are no indices left for the old number.
            if (oldSet.isEmpty()) {
                numberToIndices.remove(oldNumber);
            }
        }
        
        // Update the index-to-number mapping.
        indexToNumber.put(index, number);
        // Add the index to the set corresponding to the new number.
        numberToIndices.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }
    
    public int find(int number) {
        // If the number isn't assigned to any index, return -1.
        if (!numberToIndices.containsKey(number)) {
            return -1;
        }
        TreeSet<Integer> indices = numberToIndices.get(number);
        if (indices.isEmpty()) {
            return -1;
        }
        // Return the smallest index that has the given number.
        return indices.first();
    }
}
