package localhosts;

import com.github.robocup_atan.atan.model.enums.Flag;

public class Player{
	
	/**
	 * Player id
	 * Assigned when client-server connection is made
	 */
	public int playerId;
	
	/**
	 * Player role
	 *  Attacker,
	 *  Goalie,
	 *  Defender,
	 *  MidFielders
	 */
	public PlayerRole role;
	
	/**
	 * Whether is user has some safe space near him to receive or kick the ball,
	 *  with the least chance of loosing the ball
	 */
	public boolean isInComfortZone;
	
	/**
	 * True is user has the ball
	 */
	public boolean hasBall;
	
	/**
	 * True if user is closest to the ball
	 */
	public boolean isClosestToBall;
	
	/**
	 * True if user can see the ball
	 */
	public boolean canSeeBall;
	
	/**
	 * True if user is in the opponent goal area
	 */
	public boolean isInOpponentGoalArea;
	
	/**
	 * True if user is in the own goal area
	 */
	public boolean isInOwnGoalArea;
	
	/**
	 * Distance between player and the ball
	 */
	public double distanceToBall;
	
	/**
	 * Distance between opponent goal and the player
	 */
	public double distanceToOpponentGoal;
	
	/**
	 * Distance between own goal and the player
	 */
	public double distanceToOwnGoal;
	
	/**
	 * How many times user has kicked the ball
	 */
	public int kickCount;
	
	/**
	 * How many times user has performed dash command
	 */
	public int dashCount;
	
	/**
	 * How many times user has passed the ball
	 */
	public int passCount;
	
	/**
	 * Which flag can user see
	 */
	public Flag canSeeFlag;
	
	/**
	 * Whether the path ahead is clear
	 */
	public boolean isClearAhead;
	
	/**
	 * Whether the path behind is clear
	 */
	public boolean isClearBehind;

	/**
	 * PlayerIds[] of team members ahead of the player
	 */
	public int[] teamMembersAhead;
	
	/**
	 * PlayerIds[] of team members behind of the player
	 */
	public int[] teamMembersBehind;
	
	/**
	 * PlayerIds[] of opponent team members ahead of the player
	 */
	public int[] opponentsAhead;
	
	/**
	 * PlayerIds[] of opponent team members behind of the player
	 */
	public int[] opponentsBehind;
	
	/**
	 * Gateway to the Actions.class
	 */
	public Actions actions;
	
	/**
	 * Gateway to the PlayerController.class
	 */
	public PlayerController controller;
	
	public PlayerType type;
	
	public PlayerParam param;
	
	public SenseBody body;
	
	public SeeBall ball;
	
	public boolean shouldChaseBall = false;
	
	/**
	 * Player position X
	 */
	double positionX;
	
	/**
	 * Player position Y
	 */
	double positionY;
	
	/**
	 * Create new player with the controller and playerId
	 * @param controller
	 * @param playerId
	 */
	Player(PlayerController controller,int playerId){
		this.actions = new Actions(playerId);
		this.controller = controller;
		this.playerId = playerId;
		this.defaultSettings();
	}
	
	/**
	 * Create player with the player id and player role
	 * @param playerId
	 * @param role
	 */
	Player(int playerId, PlayerRole role){
		this.actions = new Actions(playerId);
		this.playerId = playerId;
		this.role = role;
		this.defaultSettings();
	}
	
	/**
	 * Create player with player id
	 * @param playerId
	 */
	Player(int playerId){
		this.actions = new Actions(playerId);
		this.playerId = playerId;
		this.defaultSettings();
	}
	
	/**
	 * Set players role
	 * @todo When changing the role, swap positions
	 * @param role
	 */
	public void setRole(PlayerRole role){
		this.role = role;
	}
	
	/**
	 * Get user role
	 * @return PLayerRole
	 */
	public PlayerRole getRole(){
		return this.role;
	}
	
	/**
	 * Get PlayerController
	 * @return
	 */
	public PlayerController getController(){
		return this.controller;
	}
	
	/**
	 * Get Actions controller
	 * @return
	 */
	public Actions action(){
		return this.actions;
	}
	
	
	public void setPlayerType(PlayerType type){
		this.type = type;
	}
	
	public PlayerType getType(){
		return this.type;
	}
	
	public void setPlayerParam(PlayerParam param){
		this.param = param;
	}
	
	public PlayerParam getParam(){
		return this.param;
	}
	
	public void setSenseBody(SenseBody body){
		this.body = body;
	}
	
	public SenseBody getBody(){
		return this.body;
	}
	
	public void setSeeBall(SeeBall ball){
		this.ball = ball;
	}
	
	public SeeBall getBall(){
		return this.ball;
	}
	
	
	/**
	 * Set default dummy settings
	 */
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
