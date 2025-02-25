// The goal is to find the next greater element for each number in nums1 based on its position in nums2.
// Since nums1 is a subset of nums2, we first map each number in nums1 to its index for quick lookup.
// We then use a monotonic decreasing stack to efficiently find the next greater element in nums2.
// As we iterate through nums2, whenever the current element is greater than the element at the top of the stack,
// it means we have found the next greater element for the number at the top of the stack.
// We update the result array accordingly using the index mapping we built from nums1.
// If the current element exists in nums1 (i.e., it's in our map), we push it onto the stack to later find its next greater element.

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Use a stack to maintain a decreasing sequence from nums2.
        Stack<Integer> stack = new Stack<>();
        // Initialize the output array with default value -1 for each element.
        int[] output = new int[nums1.length];
        // Map to store the index of each element in nums1.
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.fill(output, -1);
        
        // Build the map from value to index for nums1.
        // This allows us to quickly update the corresponding output element when we find the next greater number.
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }

        // Traverse nums2 to determine the next greater element for numbers in nums1.
        for (int i = 0; i < nums2.length; i++) {
            // If current number in nums2 is greater than the number on top of the stack,
            // it means we've found the next greater element for that number.
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                // Pop the number from the stack and use the map to find its index in nums1.
                int index = map.get(stack.pop());
                // Update the output for that number with the current number from nums2.
                output[index] = nums2[i];
            }
            // Only push the current number to the stack if it is present in nums1,
            // because we only care about numbers whose next greater element is needed.
            if (map.get(nums2[i]) != null)
                stack.push(nums2[i]);
        }

        // Return the final array of next greater elements for each number in nums1.
        return output;
    }
}
