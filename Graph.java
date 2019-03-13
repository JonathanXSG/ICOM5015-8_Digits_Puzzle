import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Data structure for holding and manipulating the board
 */
public class Graph{
    private int[][] numbers;
    private int size;
    private Solution solution;
    private Pair<Integer, Integer> zeroPos;

    /**
     * Constructor for creating a Graph object of size 3x3.
     * @param solution On of the two possible solutions.
     */
    public Graph(Solution solution){
        numbers = new int[3][3];
        this.size = 3;
        this.solution = solution;
        createBoard();
    }
    public static void arrayCopy(int[][] aSource, int[][] aDestination) {
        for (int i = 0; i < aSource.length; i++) {
            System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
        }
    }

    public Graph(Graph graph){
        numbers = new int[3][3];
        for(int i = 0; i < graph.numbers.length; i++)
            System.arraycopy(graph.numbers[i], 0, numbers[i], 0, graph.numbers[i].length);
        this.size = 3;
        this.solution = graph.solution;
        if (solution == Solution.Middle_Blank)
            zeroPos = new Pair<>(1,1);
        else
            zeroPos = new Pair<>(0,0);
    }

    private void createBoard() {
        if (solution == Solution.Middle_Blank) {
            numbers = new int[][]{{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
            zeroPos = new Pair<>(1,1);
        }
        else {
            numbers = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
            zeroPos = new Pair<>(0,0);
        }
    }

    /**
     * Method for moving a number on the board given the coordinates of the two numbers to swap.
     * @param startX    X coordinate of number to move
     * @param startY    Y coordinate of number to move
     * @param endX      X coordinate of the place to move the number to
     * @param endY      Y coordinate of the place to move the number to
     */
    public void moveNumber(int startX, int startY, int endX, int endY){
        if(numbers[startY][startX] ==0)
            zeroPos = new Pair<>(endX,endY);
        else if(numbers[endY][endX] ==0)
            zeroPos = new Pair<>(startX,startY);
        numbers[startY][startX] = (numbers[startY][startX] + numbers[endY][endX]) - (numbers[endY][endX] = numbers[startY][startX]);
    }

    /**
     * Method for moving a number on the board given the coordinates of the two numbers to swap.
     * @param startPos  Coordinates in Pair of number to move
     * @param endPos    Coordinates in Pair of the place to move the number to
     */
    public void moveNumber(Pair<Integer,Integer> startPos, Pair<Integer,Integer> endPos){
        if(startPos == zeroPos)
            zeroPos = endPos;
        else if(endPos == zeroPos)
            zeroPos = startPos;
        numbers[startPos.posY][startPos.posX] = (numbers[startPos.posY][startPos.posX] + numbers[endPos.posY][endPos.posX])
                - (numbers[endPos.posY][endPos.posX] = numbers[startPos.posY][startPos.posX]);
    }

    public Pair<Integer,Integer> findNumber(int number){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(numbers[i][j] == number){
                    return new Pair<>(j, i);
                }
            }
        }
        return null;
    }

    /**
     * Method for getting the coordinates of all valid neighbor positions.
     * @param posX  X coordinate of number to look for it's neighbors
     * @param posY  Y coordinate of number to look for it's neighbors
     * @return      An arrayList of coordinates inside the Pair object
     */
    public ArrayList<Pair<Integer,Integer>> getNeighbors(int posX, int posY){
        ArrayList<Pair<Integer,Integer>> n = new ArrayList<>();
        if(posX + 1 < size){
            n.add(new Pair<>(posX+1,posY));
        }
        if(posX - 1 >= 0){
            n.add(new Pair<>(posX-1,posY));
        }
        if(posY + 1 < size){
            n.add(new Pair<>(posX,posY+1));
        }
        if(posY - 1 >= 0){
            n.add(new Pair<>(posX,posY-1));
        }
        return n;
    }

    /**
     * Method for getting the coordinates of all valid neighbor positions around the zero or empty space
     * @return      An arrayList of coordinates inside the Pair object
     */
    public ArrayList<Pair<Integer,Integer>> getZeroNeighbors(){
        ArrayList<Pair<Integer,Integer>> n = new ArrayList<>();
        if(zeroPos.posX + 1 < size){
            n.add(new Pair<>(zeroPos.posX+1, zeroPos.posY));
        }
        if(zeroPos.posX - 1 >= 0){
            n.add(new Pair<>(zeroPos.posX-1, zeroPos.posY));
        }
        if(zeroPos.posY + 1 < size){
            n.add(new Pair<>(zeroPos.posX, zeroPos.posY+1));
        }
        if(zeroPos.posY - 1 >= 0){
            n.add(new Pair<>(zeroPos.posX, zeroPos.posY-1));
        }
        return n;
    }


    /**
     * Method for getting a number in the given coordinates
     * @param posX  X coordinate of the number to get
     * @param posY  Y coordinate of the number to get
     * @return  corresponding int value of the position specified
     */
    public int getNumber(int posX, int posY){
        return numbers[posY][posX];
    }

    /**
     * Method for getting a number in the given coordinates
     * @param coordinate  coordinates of the number to get
     * @return  corresponding int value of the position specified
     */
    public int getNumber(Pair<Integer,Integer> coordinate){
        return numbers[coordinate.posY][coordinate.posX];
    }

    public Pair<Integer, Integer> getZeroPos(){
        return zeroPos;
    }

    public Solution getSolution() {
        return solution;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return size == graph.size &&
                Arrays.equals(numbers, graph.numbers) &&
                solution == graph.solution &&
                Objects.equals(zeroPos, graph.zeroPos);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(size, solution, zeroPos);
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }

    public void printGraph(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(numbers[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

}

