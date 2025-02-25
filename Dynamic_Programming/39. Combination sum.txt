class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //recursion: hashmap, linikedlist of current combination, list of all found combinations, 
        // The trick to this problem is finding a way to look for all possible number combinations, select the combinations that sum to 'target', but to also make sure that duplicate combinations don't get put into the solution (e.g "2,2,3" and "2,3,2" are the same combination and cannot be added to the solution together.)
        // To avoid duplicate solutions without having to sort the current combinations and check the list of all valid combinations we've found with a hashmap, we need to implement a decision tree that ensures no recursion calls can ever get to a position where they can pick the same combination.
        // The solution is a decision tree that has two options: we can pick the first number available in the array, or we can choose to not pick the first number. When we choose to not pick the first number, we have to ensure that all subsequent recursion calls that stem from the current decision never pick the first number in the array again. We achieve this by sending off a new recursion where the first number available in the array is the second number available from the decision above.

        // look at current combination's sum, check if (target - sum) is in hashmap. If it does exist, add (target - sum) to the current combination, and add the current combination to the list of all found combinations.
        // if we can find a number that makes the current combo a valid combo, then the current recursion ends because it's impossible to add another number to the current combination and still have our current combination be valid.
        // if (target - sum) isn't in "candidates", then we need to continue the recursion. Add one number from "candidates" to the current combo and send the recursion. Repeat for every distinct number in "candidates".
        List<List<Integer>> allCombinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();

        findAllCombinations(candidates, allCombinations, currentCombination, target, 0, 0);
        
        return allCombinations;
    }

    private void findAllCombinations(int[] candidates, List<List<Integer>> allCombinations, List<Integer>currentCombination,  int target, int currentSum, int i){
        //System.out.println(Arrays.toString(currentCombination.toArray()) + currentSum);
        if(i >= candidates.length || currentSum > target)
            return;
        if(currentSum == target){
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }
        
        currentCombination.add(candidates[i]);
        findAllCombinations(candidates, allCombinations, currentCombination, target, currentSum+candidates[i], i);
        currentCombination.remove(currentCombination.size()-1);
        findAllCombinations(candidates, allCombinations, currentCombination, target, currentSum, i+1);
        
    }
}