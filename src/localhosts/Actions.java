/**
 * @author Bartlomiej Kliszczyk
 */
package localhosts;

interface ActionsInterface{
	
	/**
	 * Check whether the player can receive the ball safely
	 * @TODO Introduce probability
	 * @param playerId
	 * @return
	 */
	boolean isInComfortZone(int playerId);
	
	/**
	 * Find team members in comfort zone
	 * @return
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
	 * Track player
	 * @param playerId
	 */
	void trackPlayer(int playerId);
	
	/**
	 * Track who's got the ball
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
	
	
	
}

public class Actions implements ActionsInterface{

	@Override
	public boolean isInComfortZone(int playerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int findSupportTeamMember() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void findBall() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dashAheadOTeamMember(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passToTeamMember(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passChainToTeamMembers(int[] playerIds, boolean reverse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passAhead() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passBack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passAhead(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passBack(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trackPlayer(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trackPlayerWithBall(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void predictBallPosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chaseBall(int[] playerIds, int numberOfChasers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] findTeamMembersClosestToPosition(double x, double y) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
