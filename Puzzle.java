import java.util.ArrayList;
import java.util.Random;

public class Puzzle{
    private Graph currentBoard;
    private Solution solutionType;
    private int gCost = 0;
    private int hCost = 0;

    /**
     * Default constructor for the puzzle.
     * @param solution The solved state selected.
     */
    public Puzzle(Solution solution){
        this.currentBoard = new Graph(solution);
        this.solutionType = solution;
    }

    /**
     * Constructor for creating a copy of a puzzle.
     * It copies the board from the parent and the gCost.
     * @param parentPuzzle Puzzle object to copy
     */
    public Puzzle(Puzzle parentPuzzle){
        this.currentBoard = new Graph(parentPuzzle.currentBoard);
        this.solutionType = parentPuzzle.getSolutionType();
        this.gCost = parentPuzzle.gCost;
    }

    /**
     * Method for randomizing the board by performing random movements on the board
     * @param randomSteps number of random movements to perform
     */
    public void randomizeBoard(int randomSteps){
        Random rand = new Random();
        ArrayList<Pair<Integer, Integer>> neighbors;
        for(int i=0; i<randomSteps; i++){
            neighbors = currentBoard.getZeroNeighbors();
            currentBoard.moveNumber(currentBoard.getZeroPos(),neighbors.get(rand.nextInt(neighbors.size())));
        }
    }

    /**
     * \Method finding the Manhattan distance from the coordinate given to the solved position
     * @param solvedPuzzle Puzzle object of the solved state
     * @param coordinates Coordinates of the desired number to find it's distance
     * @return the Manhattan Distance calculated
     * @throws Exception if the value of the coordinate doesn't exist in the solved puzzle
     */
    public int findManhattanDistance(Puzzle solvedPuzzle, Pair<Integer,Integer> coordinates) throws Exception {
        int numberForSearch = currentBoard.getNumber(coordinates);
        Pair<Integer,Integer> solvedCoordinates = solvedPuzzle.findNumber(numberForSearch);

        if(solvedCoordinates == null)
            throw new Exception("Invalid number in findManhattanDistance: "+ numberForSearch + " from coordinates: "+ coordinates);

        return calcManhattanDistance(coordinates, solvedCoordinates);
    }

    /**
     * Method for calculating the Manhattan distance
     * @param pos1 coordinates of one place in the board
     * @param pos2 second coordinate of place in board
     * @return the Manhattan distance between the two coordinates given
     */
    private int calcManhattanDistance(Pair<Integer,Integer> pos1, Pair<Integer,Integer> pos2){
        return Math.abs(pos1.posX - pos2.posX) + Math.abs(pos1.posY - pos2.posY);
    }


    public void calcGScore(Puzzle solvedPuzzle) throws Exception {
        hCost = 0;
        for (int i = 0; i < currentBoard.getSize(); i++)
            for (int j = 0; j < currentBoard.getSize(); j++)
                hCost += findManhattanDistance(solvedPuzzle, new Pair<>(j, i));
    }

    /**
     * Wrapper method for finding the coordinates of the given number, on the board
     * @param number integer to look for in the board
     * @return coordinates of the number inside board, null if number isn't found
     */
    public Pair<Integer,Integer> findNumber(int number){
        return currentBoard.findNumber(number);
    }

    /**
     * Wrapper method for moving the zero or blank space.
     * The method checks if the position is a valid neighbor, also increments the gCost by one (1)
     * @param coordinates Valid neighbor positions to move the zero to
     */
    public void moveZero(Pair<Integer,Integer> coordinates) throws Exception {
        if(!currentBoard.getZeroNeighbors().contains(coordinates))
            throw new Exception("moveZero: Coordinates given are not neighbors of the zero: "+coordinates);
        currentBoard.moveNumber(currentBoard.getZeroPos(),coordinates);
        gCost++;
    }

    /**
     * Wrapper method for getting the positions of the zero or blank space
     * @return Coordinates of the zero in the board
     */
    public Pair<Integer,Integer> getZeroPos(){
        return currentBoard.getZeroPos();
    }

    /**
     * Wrapper for getting the value in the coordinate specified
     * @param coordinates the coordinate of the space to get the value from
     * @return  the integer value from the coordinates in the board
     */
    public int getValueInPos(Pair<Integer,Integer> coordinates){
        return currentBoard.getNumber(coordinates);
    }

    /**
     * Wrapper method for getting all valid neighbors coordinates of the zero
     * @return arraylist of valid neighbor coordinates
     */
    public ArrayList<Pair<Integer, Integer>> getNeighborsOfZero(){
        return currentBoard.getZeroNeighbors();
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public Solution getSolutionType() {
        return solutionType;
    }

    public void printBoard(){
        currentBoard.printGraph();
    }


}