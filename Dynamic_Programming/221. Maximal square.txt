// for this question, in order to find the maximal square of the current square in the matrix, check the maximal square that can be made from the square to the right and the maximal square that can be made from the square directly below.
// Compare the two maximal squares that can be made from the right and bottom squares and take the minimum (int min)
// Then, check to see if the square that is 'min' units away diagonally from the current square is a 1. If it is, then the maximal square that can be made from the current square is min+1. If not, then the maximal square that can be made from the current square is 'min'
class Solution {
    public int maximalSquare(char[][] matrix) {
        int[][] visited = new int[matrix.length][matrix[0].length];
        int maxSize = 0;
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix[0].length; j++){
                // we skip all zeros
                int currentSize = findMax(matrix, visited, i, j);
                //System.out.println(currentSize);
                maxSize = Math.max(maxSize, currentSize);
            }
        }

        //System.out.println(Arrays.deepToString(visited));

        return maxSize*maxSize;
    }

    private int findMax(char[][] matrix, int[][] visited, int i, int j){
        if(i >= matrix.length || j >= matrix[0].length)
            return -1;
        if(visited[i][j] != 0){
            return visited[i][j];
        }
        if(matrix[i][j] == '0'){
            visited[i][j] = -1;
            return -1;
        }
        
        int rightMax = findMax(matrix, visited, i, j+1);
        int belowMax = findMax(matrix, visited, i+1, j);
        int min = Math.min(rightMax, belowMax);
        //System.out.println(min + " " + i + " " + j);
        //min = Math.max(1, min);

        // if one of the squares to the right or below the current square is a 0, then the maximum square the current square can make is 1
        if(min == -1){
            visited[i][j] = 1;
            return 1;
        }else{ // if the right and below squares are non-zero, then check if the square that is 'min' diagonal units away from the current square
            if(i + min < matrix.length && j + min < matrix[0].length && matrix[i+min][j+min] == '1'){
                visited[i][j] = min+1;
                return min+1;
            }else{
                visited[i][j] = min;
                return min;
            }
        }
    }
}