
public class PuzzleSolver{

    static private Solution selectedSolution;
    static private int randomSteps;
    static private Puzzle solvedPuzzle;
    static private Puzzle puzzleToSolve;

    public static void main(String[] args) {
//        Ask user for which solved state to use
        selectedSolution = Solution.Middle_Blank;

//        Initialize board with the solved state
        solvedPuzzle = new Puzzle(selectedSolution);
        puzzleToSolve = new Puzzle(selectedSolution);

//        Ask user for how many moves to randomize the board
        randomSteps = 20;

//        Randomize board
        puzzleToSolve.randomizeBoard(randomSteps);
//        Solve  board
//        Show steps for solving board

        example();
    }
    static void example (){

//        Getting the zero position and getting values of positions
        System.out.println(puzzleToSolve.getZeroPos()+ "   "+puzzleToSolve.getValueInPos(puzzleToSolve.getZeroPos()));

//        Example of looking for the neighbors of the zero
        for(Pair<Integer,Integer> p : puzzleToSolve.getNeighborsOfZero()){
            System.out.println("Coordinates: " + p.posX + "," + p.posY + " value: "+ puzzleToSolve.getValueInPos(p));
        }

//        Example of moving the zero
        puzzleToSolve.printBoard();
        try {
            puzzleToSolve.calcGScore(solvedPuzzle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("initial h(x) cost " +  puzzleToSolve.gethCost());
        System.out.println("Initial g(x) cost " +  puzzleToSolve.getgCost());
        System.out.println("Initial zero position: "+puzzleToSolve.getZeroPos());
        try {
            puzzleToSolve.moveZero(puzzleToSolve.getNeighborsOfZero().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("New zero position: "+puzzleToSolve.getZeroPos());
        puzzleToSolve.printBoard();
        try {
            puzzleToSolve.calcGScore(solvedPuzzle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("h(x) cost " +  puzzleToSolve.gethCost());
        System.out.println("g(x) cost " +  puzzleToSolve.getgCost());

        System.out.println();
        System.out.println("Neighbors of zero: ");
        for(Pair<Integer,Integer> p : puzzleToSolve.getNeighborsOfZero()){
            System.out.println("Coordinates: " + p.posX + "," + p.posY + " value: "+ puzzleToSolve.getValueInPos(p));
        }

        System.out.println("Coordinates of number 2: "+ puzzleToSolve.findNumber(2));
        try {
            System.out.println("Manhattan distance of number 2: "+ puzzleToSolve.findManhattanDistance(solvedPuzzle, puzzleToSolve.findNumber(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}