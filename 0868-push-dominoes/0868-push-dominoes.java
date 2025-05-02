class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] result = dominoes.toCharArray();
        
        int[] rightForce = new int[n];
        Arrays.fill(rightForce, -1);
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == 'R') {
                r = i;
            } else if (dominoes.charAt(i) == 'L') {
                r = -1;
            }
            rightForce[i] = r;
        }
        
        int[] leftForce = new int[n];
        Arrays.fill(leftForce, -1);
        int l = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') {
                l = i;
            } else if (dominoes.charAt(i) == 'R') {
                l = -1;
            }
            leftForce[i] = l;
        }
        
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == '.') {
                int rightDistance = rightForce[i] == -1 ? Integer.MAX_VALUE : i - rightForce[i];
                int leftDistance = leftForce[i] == -1 ? Integer.MAX_VALUE : leftForce[i] - i;
                
                if (rightDistance < leftDistance) {
                    result[i] = 'R';
                } else if (leftDistance < rightDistance) {
                    result[i] = 'L';
                }
            }
        }
        
        return new String(result);
    }
}