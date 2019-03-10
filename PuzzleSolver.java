
public class PuzzleSolver{
    public static void main(String[] args) {
//        Ask user for which solved state to use
//        Initialize board with the solved state
//        Ask user for how many moves to randomize the board
//        Randomize board
//        Solve  board
//        Show steps for solving board


        Graph g = new Graph(Solution.Middle_Blank);
//        Getting the zero position
        System.out.println(g.getZeroPos());
        System.out.println(g.getNumber(g.getZeroPos()));
//        Example of moving a number
        g.printGraph();
        g.moveNumber(0,0,0,1);
        g.printGraph();
//        Example of moving the zero and getting the position of it
        System.out.println(g.getZeroPos());
        g.moveNumber(new Pair<>(0,0), g.getZeroPos());
        System.out.println(g.getZeroPos());
        g.printGraph();

//        Example of looking for the neighbors of the zero
        for(Pair<Integer,Integer> p : g.getZeroNeighbors()){
            System.out.println("Coordinates: " + p.posX + "," + p.posY + " value: "+ g.getNumber(p));
        }
    }
}