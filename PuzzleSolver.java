import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * Main class for solving the problem
 * @author Víctor Fermin 801-12-2550,
 *      Jonathan X. Santiago Gonzalez 802-14-7616,
 *      Eduardo A. Perez Vega 802-14-5780
 *
 */
public class PuzzleSolver{

    static private Solution selectedSolution;
    static private int randomSteps;
    static private Puzzle solvedPuzzle;
    static private Puzzle puzzleToSolve;
    static private int steps = 0;

    public static void main(String[] args) {
//        Ask user for which solved state to use
        Scanner reader = new Scanner(System.in);
        String input = "";
        while (checkNumber(input) == 0){
            System.out.println("Select Solution type: ");
            System.out.println("   [1] Top Left Blank Space  ,  [2] Middle Blank Space  ,  [3] Bottom Right Blank Space");
            input= reader.nextLine();
        }

        if(Integer.parseInt(input) == 1)
            selectedSolution = Solution.Top_Left_Blank;
        else if(Integer.parseInt(input) == 2)
            selectedSolution = Solution.Middle_Blank;
        else if(Integer.parseInt(input) == 3)
            selectedSolution = Solution.Bottom_Right_blank;

//        Initialize board with the solved state
        solvedPuzzle = new Puzzle(selectedSolution);
        puzzleToSolve = new Puzzle(selectedSolution);

        input = "";
//        Ask user for how many moves to randomize the board
        while (checkNumber2(input) == 0){
            System.out.println("How many random steps? ");
            input= reader.nextLine();
        }
        randomSteps = Integer.parseInt(input);

//        Randomize board
        puzzleToSolve.randomizeBoard(randomSteps);

//        Solve  board
        Node lastNode = new Node();
        try {
            lastNode = aStarSolve();
		} catch (Exception e) {
			e.printStackTrace();
		}

//		Show steps for solving board
        recursivePrintTree(lastNode);
        System.out.println("Number of step taken: " +steps);
    }

    static int checkNumber(String str) {
        int result = str.matches("^\\d+$") ? Integer.parseInt(str) : 0;
        if(result < 1 || result > 3) result =0;
        return result;
    }

    static int checkNumber2(String str) {
        int result = str.matches("^\\d+$") ? Integer.parseInt(str) : 0;
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
                System.out.println("Number of nodes expanded: "+(frontier.size()+explored.size()));
                System.out.println("Number of nodes explored: "+explored.size());
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
    
    static void recursivePrintTree(Node node) {
        steps++;
    	if(!(node.getParentNode() == null)) {
    		recursivePrintTree(node.getParentNode());
    	}
    	System.out.println(node.getAction() + "   g(x)= "+node.getState().getgCost() + " h(x)= "+node.getState().gethCost());
        node.getState().printBoard();
    }
}