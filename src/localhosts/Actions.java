/**
 * @author Bartlomiej Kliszczyk
 */
package localhosts;

interface ActionsInterface{
	
	/**
	 * Check whether the player can receive the ball safely
	 * @TODO Introduce probability
	 * @param playerId
	 * @return boolean
	 */
	boolean isInComfortZone(int playerId);
	
	/**
	 * Find team members in comfort zone
	 * @return int
	 */
	int findSupportTeamMember();
	
	/**
	 * Look around
	 * Ask Team if whether anyone can see the ball, if yes send message to team members
	 *  new Message.Ball->Pos(x, y)
	 */
	void findBall();
	
	/**
	 * Dash in the direction of other team member
	 * @param playerId
	 */
	void dashAheadOTeamMember(int playerId);
	
	/**
	 * Pass to the team member
	 * @param playerId
	 */
	void passToTeamMember(int playerId);
	
	/**
	 * Quick pass to team members 
	 * 1. Loop
	 *  Ask player[i] if this.isInComfortZone
	 * 	 if YES -> Pass
	 *   if NO -> Skip -> ask next team member on the list  
	 *   if NOONE isInComfortZone @todo ??
	 * @param playerIds
	 * @param reverse - reverse the order of the array
	 */
	void passChainToTeamMembers(int[] playerIds, boolean reverse);
	
	/**
	 * Pass ahead
	 */
	void passAhead();
	
	/**
	 * Pass back
	 */
	void passBack();
	
	/**
	 * Pass ahead in the direction of playerId 
	 * @param playerId
	 */
	void passAhead(int playerId);
	
	/**
	 * Pass back in the direction of playerId 
	 * @param playerId
	 */
	void passBack(int playerId);
	
	/**
	 * Track players position by PlayerId
	 * @param playerId
	 */
	void trackPlayer(int playerId);
	
	/**
	 * Track the position of the player with the ball
	 * @param playerId
	 */
	void trackPlayerWithBall(int playerId);
	
	/**
	 * Predict balls position
	 */
	void predictBallPosition();
	
	/**
	 * Find the ball -> findBall
	 * Inform team members of the balls position -> Message
	 * Find findTeamMembersClosestToPosition -> (result of findBall)
	 * Choose numberOfChasers closest to the balls position
	 * Move Player(playerIds) -> to balls position
	 * @param playerIds
	 */
	void chaseBall(int[] playerIds, int numberOfChasers);
	
	/**
	 * Find team members closest to the given position
	 * @param x
	 * @param y
	 * @return playerIds[]
	 */
	int[] findTeamMembersClosestToPosition(double x, double y);
	
	/**
	 * Hear messages
	 * @return int
	 */
	int hearMessages();
	
	/**
	 * Perform action with nothing can be seen 
	 */
	void seeNothing();
	
	/**
	 * Perform action when opponent is seen
	 */
	void seeOpponent();
	
	/**
	 * Dash in direction
	 * @param xy
	 */
	public void dashInDirection(int xy, int power);
	public void dashInDirection(double xy, int power);
	
	/**
	 * Turn in direction
	 * @param xy
	 */
	void turnInDirection(int xy);
	void turnInDirection(double xy);
	
	
	
}

public class Actions implements ActionsInterface{
	
	/**
	 * Player ID
	 */
	public int currentPlayerId;
	
	/**
	 * Team
	 */
	public Team team = Team.getTeam();
	
	/**
	 * Constructor
	 * @param playerId
	 */
	Actions(int playerId){
		currentPlayerId = playerId;
	}

	/**
	 * Check whether the player can receive the ball safely
	 * @TODO Introduce probability
	 * @param playerId
	 * @return boolean
	 */
	@Override
	public boolean isInComfortZone(int playerId) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Find team members in comfort zone
	 * @return int
	 */
	@Override
	public int findSupportTeamMember() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Look around
	 * Ask Team if whether anyone can see the ball, if yes send message to team members
	 *  new Message.Ball->Pos(x, y)
	 */
	@Override
	public void findBall() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Dash in the direction of other team member
	 * @param playerId
	 */
	@Override
	public void dashAheadOTeamMember(int playerId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Pass to the team member
	 * @param playerId
	 */
	@Override
	public void passToTeamMember(int playerId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Quick pass to team members 
	 * 1. Loop
	 *  Ask player[i] if this.isInComfortZone
	 * 	 if YES -> Pass
	 *   if NO -> Skip -> ask next team member on the list  
	 *   if NOONE isInComfortZone @todo ??
	 * @param playerIds
	 * @param reverse - reverse the order of the array
	 */
	@Override
	public void passChainToTeamMembers(int[] playerIds, boolean reverse) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Pass ahead
	 */
	@Override
	public void passAhead() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Pass back
	 */
	@Override
	public void passBack() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Pass ahead in the direction of playerId 
	 * @param playerId
	 */
	@Override
	public void passAhead(int playerId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Pass back in the direction of playerId 
	 * @param playerId
	 */
	@Override
	public void passBack(int playerId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Track players position by PlayerId
	 */
	@Override
	public void trackPlayer(int playerId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Track the position of the player with the ball
	 * @param playerId
	 */
	@Override
	public void trackPlayerWithBall(int playerId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Predict ball position
	 * kick * velocity
	 */
	@Override
	public void predictBallPosition() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Find the ball -> findBall
	 * Inform team members of the balls position -> Message
	 * Find findTeamMembersClosestToPosition -> (result of findBall)
	 * Choose numberOfChasers closest to the balls position
	 * Move Player(playerIds) -> to balls position
	 * @param playerIds
	 */
	@Override
	public void chaseBall(int[] playerIds, int numberOfChasers) {
		// TODO Auto-generated method stub
		
		
		for (int i = 0; i < playerIds.length; i++) {
			int ids = playerIds[i];
			//team.get(ids).getController().getPlayer()
		}
		
		
	}

	/**
	 * Find team members closest to the given position
	 * @param x
	 * @param y
	 * @return playerIds[]
	 */
	@Override
	public int[] findTeamMembersClosestToPosition(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Hear messages
	 * @return int
	 */
	@Override
	public int hearMessages() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Perform action with nothing can be seen 
	 */
	@Override
	public void seeNothing() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Perform action when opponent is seen
	 */
	@Override
	public void seeOpponent() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Dash in direction
	 * @param xy
	 */
	@Override
	public void dashInDirection(int xy, int power) {
		// TODO Auto-generated method stub
		this.turnInDirection(xy);
		team.get(currentPlayerId).getController().getPlayer().dash(power);
		
	}
	
	public void dashInDirection(double xy, int power) {
		// TODO Auto-generated method stub
		this.turnInDirection(xy);
		team.get(currentPlayerId).getController().getPlayer().dash(power);
		
	}

	/**
	 * Turn in direction
	 * @param xy
	 */
	@Override
	public void turnInDirection(int xy) {
		// TODO Auto-generated method stub
		team.get(currentPlayerId).getController().getPlayer().dash((int) xy);
	}
	
	public void turnInDirection(double xy) {
		// TODO Auto-generated method stub
		team.get(currentPlayerId).getController().getPlayer().dash((int) xy);
	}
	
	public void turnTowardsBall(double direction){
		System.out.println("TurnTowardsBall PlayerId: " + currentPlayerId + " direction: " + direction);
		team.get(currentPlayerId).getController().getPlayer().turn(direction);
	}
	
	public void turnTowardsBall(double direction, int playerId){
		System.out.println("TurnTowardsBall PlayerId: " + currentPlayerId + " direction: " + direction);
		team.get(playerId).getController().getPlayer().turn(direction);
	}
	
	

}
