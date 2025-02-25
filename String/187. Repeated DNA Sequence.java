class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length() <= 10)
            return new ArrayList<String>();

        HashSet<String> repeatSequences = new HashSet<>(); // records all sequneces that show up more than once
        HashSet<String> sequences = new HashSet<>(); // records all 10-character long sequences in the DNA string

        for(int i = 0; i<=s.length()-10; i++){
            // analyze the current 10-character long substring
            String currentSequence = s.substring(i, i+10);
            if(!sequences.add(currentSequence)){
                repeatSequences.add(currentSequence);
            }
        }

        return new ArrayList<>(repeatSequences);
    }
}