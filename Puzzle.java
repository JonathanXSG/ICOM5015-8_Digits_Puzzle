import java.util.ArrayList;
import java.util.Random;

public class Puzzle{
    private Graph currentBoard;
    private Solution solutionType;
    private int cost = 0;

    public Puzzle(Solution solution){
        this.currentBoard = new Graph(solution);
        this.solutionType = solution;
    }

    public Puzzle(Graph graph){
        this.currentBoard = graph;
        this.solutionType = graph.getSolution();
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
            moveZero(neighbors.get(rand.nextInt(neighbors.size())));
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


    public void calcScore(Puzzle solvedPuzzle) throws Exception {
        cost = 0;
        for (int i = 0; i < currentBoard.getSize(); i++)
            for (int j = 0; j < currentBoard.getSize(); j++)
                cost += findManhattanDistance(solvedPuzzle, new Pair<>(j, i));
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
     * The method doesn't check if the position is a valid neighbor, it's left to implementation
     * @param coordinates Valid neighbor positions to move the zero to
     */
    public void moveZero(Pair<Integer,Integer> coordinates){
        currentBoard.moveNumber(currentBoard.getZeroPos(),coordinates);
    }

    public int getCost() {
        return cost;
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

    public void printBoard(){
        currentBoard.printGraph();
    }


}