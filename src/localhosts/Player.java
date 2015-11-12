package localhosts;

public class Player{
	
	int playerId;
	
	PlayerRole role;
	
	boolean isInComfortZone;
	boolean hasBall;
	boolean isClosestToBall;
	
	boolean isInOpponentGoalArea;
	boolean isInOwnGoalArea;
	
	double distanceToBall;
	double distanceToOpponentGoal;
	double distanceToOwnGoal;
	
	int kickCount;
	int dashCount;
	int passCount;
	
	boolean canSeeBall;
	boolean isClearAhead;
	boolean isClearBehind;

	int[] teamMembersAhead;
	int[] teamMembersBehind;
	
	int[] opponentsAhead;
	int[] opponentsBehind;
	
	public PlayerController controller;
	
	
	Player(PlayerController controller,int playerId){
		this.controller = controller;
		this.playerId = playerId;
		this.defaultSettings();
	}
	
	Player(int playerId, PlayerRole role){
		this.playerId = playerId;
		this.role = role;
		this.defaultSettings();
	}
	
	Player(int playerId){
		this.playerId = playerId;
		this.defaultSettings();
	}
	
	public void setRole(PlayerRole role){
		this.role = role;
	}
	
	public PlayerRole getRole(){
		return this.role;
	}
	
	public PlayerController getController(){
		return this.controller;
	}
	
	private void defaultSettings(){
		this.isInComfortZone = true;
		this.hasBall = false;
		this.isClosestToBall = false;
		
		this.isInOpponentGoalArea = false;
		this.isInOwnGoalArea = false;
		
		this.distanceToBall = 1000;
		this.distanceToOpponentGoal = 1000;
		this.distanceToOwnGoal = 1000;
		
		this.kickCount = 0;
		this.dashCount = 0;
		this.passCount = 0;
	}
	
}
