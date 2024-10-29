public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int x = nums1.length;
        int y = nums2.length;
        int low = 0, high = x;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            // Handle edge cases for partitions
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if we have found the correct partitions
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // If combined length is even
                if ((x + y) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } 
            // Move towards the left side in nums1
            else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } 
            // Move towards the right side in nums1
            else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted or have invalid lengths");
    }

    public static void main(String[] args) {
        testMedian(new int[]{1, 3}, new int[]{2});    // Output: 2.0
        testMedian(new int[]{1, 2}, new int[]{3, 4}); // Output: 2.5
    }

    private static void testMedian(int[] nums1, int[] nums2) {
        System.out.println("Median is: " + findMedianSortedArrays(nums1, nums2));
    }
}
