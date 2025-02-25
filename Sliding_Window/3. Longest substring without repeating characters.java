class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0)
            return 0;

        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int longest = 0;
        int currentLength = 0;

        while(right < s.length()){
            char c = s.charAt(right);
            if(set.contains(c)){ // if we've see a repeated character
                while (s.charAt(left) != c) {
                    set.remove(s.charAt(left));
                    left++;
                    currentLength--;
                }
                left++;
            }else{ // if we see a new character, add it to the current substring
                set.add(c);
                currentLength++;
                longest = Math.max(longest, currentLength);
            }
            right++;
        }
        return longest;
    }
}`