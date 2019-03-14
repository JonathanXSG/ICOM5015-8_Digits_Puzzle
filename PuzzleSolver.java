import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PuzzleSolver{

    static private Solution selectedSolution;
    static private int randomSteps;
    static private Puzzle solvedPuzzle;
    static private Puzzle puzzleToSolve;

    public static void main(String[] args) {
//        Ask user for which solved state to use
        Scanner reader = new Scanner(System.in);
        String input = "";
        while (checkNumber(input) == 0){
            System.out.println("Select Solution type: ");
            System.out.println("   [1] Top Left Blank Space  ,  [2] Middle Blank Space");
            input= reader.nextLine();
        }

        if(Integer.parseInt(input) == 1)
            selectedSolution = Solution.Top_Left_Blank;
        else if(Integer.parseInt(input) == 2)
            selectedSolution = Solution.Middle_Blank;

//        Initialize board with the solved state
        solvedPuzzle = new Puzzle(selectedSolution);
        puzzleToSolve = new Puzzle(selectedSolution);

        input = "";
//        Ask user for how many moves to randomize the board
        while (checkNumber(input) == 0){
            System.out.println("How many random steps? ");
            input= reader.nextLine();
        }
        randomSteps = Integer.parseInt(input);

//        Randomize board
        puzzleToSolve.randomizeBoard(randomSteps);
//        Solve  board
//        Show steps for solving board

        Node lastNode = new Node();
        try {
            lastNode = aStarSolve();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while(lastNode.getParentNode() != null){
            System.out.println(lastNode.getAction());
            lastNode.getState().printBoard();
            lastNode = lastNode.getParentNode();
        }
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
            puzzleToSolve.calcHScore(solvedPuzzle);
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
            puzzleToSolve.calcHScore(solvedPuzzle);
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


    static int checkNumber(String str) {
        int result = str.matches("^\\d+$") ? Integer.parseInt(str) : 0;
        if(result < 1 || result > 2) result =0;
        return result;
    }

    static Node aStarSolve() throws Exception {
    	PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> (a.getPathCost())));
        ArrayList<Node> explored = new ArrayList<>();
        frontier.add(new Node(puzzleToSolve, null, "Starting position", puzzleToSolve.getZeroPos()));

        while(true) {
            if(frontier.isEmpty()) {
                return null;
            }
            Node node = frontier.remove();
            explored.add(node);
            if(node.getState().equals(solvedPuzzle)) {
                return node;
            }
            for(Pair<Integer, Integer> p: node.getState().getNeighborsOfZero()){
            	if(!p.equals(node.getCoordinates())) {
            		Node child = new Node(new Puzzle(node.getState()), node, "Move zero from "+node.getCoordinates()+" to "+p, p);
            		child.getState().moveZero(p);
            		child.getState().calcHScore(solvedPuzzle);
            		if(!explored.contains(child) && !frontier.contains(child)) {
            			frontier.add(child);
            		}else if(frontier.contains(child)){
            			for(Node e: frontier) {
            				if(e.equals(child)) {
            					if(e.getPathCost() > child.getPathCost()) {
            						frontier.remove(e);
            						frontier.add(child);
            					}
            				}
            				break;
            			}
            		}
            	}                
            }
        }
    }
}