class Solution {
    public int numRabbits(int[] answers) {
        java.util.HashMap<Integer, Integer> count = new java.util.HashMap<>();
        
        for (int answer : answers) {
            count.put(answer, count.getOrDefault(answer, 0) + 1);
        }
        
        int totalRabbits = 0;
        
        for (java.util.Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int reportedSameColor = entry.getKey();
            int frequency = entry.getValue(); 
            int groupSize = reportedSameColor + 1;
            int groupsNeeded = (frequency + groupSize - 1) / groupSize;
            
            totalRabbits += groupsNeeded * groupSize;
        }
        
        return totalRabbits;
    }
}