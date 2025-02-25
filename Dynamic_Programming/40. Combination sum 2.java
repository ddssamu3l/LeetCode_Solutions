// This problem is very similar to combination sum 1. The trick here is that instead of choosing a number and not choosing a number but advancing the index to the next element, the decision tree here is: pick the first available number, and don't pick the first available number but advance the index to the next DISTINCT element (if array=[1,1,1,2,4,5], then we can choose to pick the number '1', or we can choose to not pick the number '1' but advance the index to the next DISTINCT element, which is '2').
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> allCombinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();

        findAllCombinations(candidates, allCombinations, currentCombination, 0, target, 0);
        return allCombinations;
    }
    private void findAllCombinations(int[] candidates, List<List<Integer>> allCombinations, List<Integer> currentCombination, int currentSum, int target, int i){
        //System.out.println(Arrays.toString(currentCombination.toArray()) + " " + currentSum);
        if(currentSum == target){
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }
        if(i >= candidates.length || currentSum > target)
            return;

        // choose 1, skip 1
        currentCombination.add(candidates[i]);
        findAllCombinations(candidates, allCombinations, currentCombination, currentSum+candidates[i], target, i+1);
        currentCombination.remove(currentCombination.size()-1);
        while(i < candidates.length-1 && candidates[i] == candidates[i+1])
            i++;
        findAllCombinations(candidates, allCombinations, currentCombination, currentSum, target, i+1);
    }
}
