class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int digit : digits) {
            freq[digit]++;
        }
        
        List<Integer> result = new ArrayList<>();
        
        for (int num = 100; num <= 998; num += 2) {
            int d1 = num / 100;
            int d2 = (num / 10) % 10;
            int d3 = num % 10;
            
            int[] currFreq = freq.clone();
            
            if (currFreq[d1] > 0) {
                currFreq[d1]--;
                if (currFreq[d2] > 0) {
                    currFreq[d2]--;
                    if (currFreq[d3] > 0) {
                        result.add(num);
                    }
                }
            }
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}