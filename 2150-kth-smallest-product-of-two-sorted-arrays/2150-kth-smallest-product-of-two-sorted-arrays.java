class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        // ì´ì§ íìì ë²ì ì¤ì 
        long minProduct = Long.MAX_VALUE;
        long maxProduct = Long.MIN_VALUE;
        
        // ë¤ ëª¨ìë¦¬ ê³±ì íì¸íì¬ ìµì/ìµëê° ì°¾ê¸°
        long[] products = {
            (long)nums1[0] * nums2[0],
            (long)nums1[0] * nums2[nums2.length - 1],
            (long)nums1[nums1.length - 1] * nums2[0],
            (long)nums1[nums1.length - 1] * nums2[nums2.length - 1]
        };
        
        for (long product : products) {
            minProduct = Math.min(minProduct, product);
            maxProduct = Math.max(maxProduct, product);
        }
        
        long left = minProduct;
        long right = maxProduct;
        
        // ì´ì§ íì
        while (left < right) {
            long mid = left + (right - left) / 2;
            
            long count = countProductsLessOrEqual(nums1, nums2, mid);
            
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    // target ì´íì¸ ê³±ì ê°ìë¥¼ ì¸ë í¨ì
    private long countProductsLessOrEqual(int[] nums1, int[] nums2, long target) {
        long count = 0;
        
        for (int num1 : nums1) {
            count += countForSingleNum1(nums2, target, num1);
        }
        
        return count;
    }
    
    // í¹ì  nums1[i]ì ëí´ nums1[i] * nums2[j] <= targetì¸ jì ê°ì
    private long countForSingleNum1(int[] nums2, long target, int num1) {
        if (num1 == 0) {
            // 0 * nums2[j] = 0ì´ë¯ë¡ target >= 0ì´ë©´ ëª¨ë  nums2 ìì ê°ë¥
            return target >= 0 ? nums2.length : 0;
        }
        
        if (num1 > 0) {
            // ìì * nums2[j] <= target
            // nums2[j] <= target / num1
            return countLessOrEqual(nums2, (double) target / num1);
        } else {
            // ìì * nums2[j] <= target
            // nums2[j] >= target / num1 (ë¶ë±í¸ ë°©í¥ ë°ë)
            return countGreaterOrEqual(nums2, (double) target / num1);
        }
    }
    
    // nums2ìì threshold ì´íì¸ ììì ê°ì
    private long countLessOrEqual(int[] nums2, double threshold) {
        int left = 0, right = nums2.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums2[mid] <= threshold) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    // nums2ìì threshold ì´ìì¸ ììì ê°ì
    private long countGreaterOrEqual(int[] nums2, double threshold) {
        int left = 0, right = nums2.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums2[mid] >= threshold) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return nums2.length - left;
    }
}