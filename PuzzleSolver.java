
public class PuzzleSolver{
    public static void main(String[] args) {
//        Ask user for which solved state to use
//        Initialize board with the solved state
//        Ask user for how many moves to randomize the board
//        Randomize board
//        Solve  board
//        Show steps for solving board


        Graph g = new Graph(Solution.Middle_Blank);
        g.printGraph();
        g.moveNumber(0,0,0,1);
        g.printGraph();
        g.moveNumber(0,0,2,2);
        g.printGraph();

        for(Pair<Integer,Integer> p : g.getNeighbors(1,1)){
            System.out.println(g.getNumber(p));
        }
    }
}