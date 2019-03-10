
public class Node {
	
	private int [][] state;
	private Node parentNode;
	private String actionTaken;
	private int pathCost;
	

	public Node(int[][]currentState, Node parent, String action, int cost) {
		// TODO Auto-generated constructor stub
		state = currentState;
		parentNode = parent;
		actionTaken = action;
		pathCost = cost;
	}
	
	public Node getParentNode(){
		return parentNode;
	}
	
	public void setParentNode(Node parent){
		parentNode = parent;
	}
	
	public int[][] getState(){
		return state;
	}
	
	public void printState(){
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
	}
	
	public void setState(int[][] state){
		state = state;
	}
	
	public int getPathCost(){
		return pathCost;
	}
	
	public void setPathCost(int cost){
		pathCost = cost;
	}
	
	public String getAction(){
		return actionTaken;
	}
	
	public void setAction(String action){
		actionTaken = action;
	}

}
