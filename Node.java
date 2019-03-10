
public class Node {
	
	//private int [][] state;
	private Graph state;
	private Node parentNode;
	private String actionTaken;
	private int pathCost;
	private Pair<Integer,Integer> coordinatesMove;
	

	public Node(Graph currentState, Node parent, String action, int cost, Pair<Integer,Integer> coordinates) {
		// TODO Auto-generated constructor stub
		state = currentState;
		parentNode = parent;
		actionTaken = action;
		pathCost = cost;
		coordinatesMove = coordinates;
	}
	
	public Node getParentNode(){
		return parentNode;
	}
	
	public void setParentNode(Node parent){
		parentNode = parent;
	}
	
	public Graph getState(){
		return state;
	}
	
	
	public void setState(Graph stateSet){
		state = stateSet;
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
	
	public Pair<Integer, Integer> getCoordinates(){
		return coordinatesMove;
	}
	
	public void setCoordinates(Pair<Integer, Integer> coordinates){
		coordinatesMove = coordinates;
	}

}
