class Solution {
    public String triangleType(int[] nums) {
        if (!isValidTriangle(nums)) {
            return "none";
        }
        
        if (nums[0] == nums[1] && nums[1] == nums[2]) {
            return "equilateral";
        } else if (nums[0] == nums[1] || nums[1] == nums[2] || nums[0] == nums[2]) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }
    
    private boolean isValidTriangle(int[] sides) {
        return (sides[0] + sides[1] > sides[2]) && 
               (sides[0] + sides[2] > sides[1]) && 
               (sides[1] + sides[2] > sides[0]);
    }
}