class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean dp[] = new Boolean[s.length()];
        return find(s, 0, wordDict, dp);
    }

    public boolean find(String s, int ind, List<String> wordDict, Boolean dp[]) {
        if (ind == s.length()) return true; 

        if (dp[ind] != null) return dp[ind]; 

        for (String word : wordDict) {
            if (s.startsWith(word, ind)) { 
                if (find(s, ind + word.length(), wordDict, dp)) {
                    return dp[ind] = true;
                }
            }
        }
        return dp[ind] = false;
    }
}