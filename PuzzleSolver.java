
public class PuzzleSolver{
    public static void main(String[] args) {
//        Ask user for which solved state to use
//        Initialize board with the solved state
//        Ask user for how many moves to randomize the board
//        Randomize board
//        Solve  board
//        Show steps for solving board

        Solution selectedSolution = Solution.Middle_Blank;
        int randomSteps = 20;

        Puzzle solvedPuzzle = new Puzzle(selectedSolution);
        Puzzle puzzleToSolve = new Puzzle(selectedSolution);
        puzzleToSolve.randomizeBoard(randomSteps);

//        Getting the zero position and getting values of positions
        System.out.println(puzzleToSolve.getZeroPos()+ "   "+puzzleToSolve.getValueInPos(puzzleToSolve.getZeroPos()));

//        Example of looking for the neighbors of the zero
        for(Pair<Integer,Integer> p : puzzleToSolve.getNeighborsOfZero()){
            System.out.println("Coordinates: " + p.posX + "," + p.posY + " value: "+ puzzleToSolve.getValueInPos(p));
        }

//        Example of moving the zero
        puzzleToSolve.printBoard();
        puzzleToSolve.moveZero(puzzleToSolve.getNeighborsOfZero().get(0));
        System.out.println(puzzleToSolve.getZeroPos());
        puzzleToSolve.printBoard();

        for(Pair<Integer,Integer> p : puzzleToSolve.getNeighborsOfZero()){
            System.out.println("Coordinates: " + p.posX + "," + p.posY + " value: "+ puzzleToSolve.getValueInPos(p));
        }

        System.out.println(puzzleToSolve.findNumber(2));
        try {
            System.out.println(puzzleToSolve.findManhattanDistance(solvedPuzzle, puzzleToSolve.findNumber(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}