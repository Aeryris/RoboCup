package localhosts;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.github.robocup_atan.atan.model.ActionsPlayer;
import com.github.robocup_atan.atan.model.ControllerPlayer;
import com.github.robocup_atan.atan.model.enums.Errors;
import com.github.robocup_atan.atan.model.enums.Flag;
import com.github.robocup_atan.atan.model.enums.Line;
import com.github.robocup_atan.atan.model.enums.Ok;
import com.github.robocup_atan.atan.model.enums.PlayMode;
import com.github.robocup_atan.atan.model.enums.RefereeMessage;
import com.github.robocup_atan.atan.model.enums.ServerParams;
import com.github.robocup_atan.atan.model.enums.ViewAngle;
import com.github.robocup_atan.atan.model.enums.ViewQuality;
import com.github.robocup_atan.atan.model.enums.Warning;

public class PlayerController implements ControllerPlayer {
	
	private ActionsPlayer player;
	public String type;
	private static Logger log = Logger.getLogger(PlayerController.class);
	
	public static Team team = Team.getTeam();
	
	
	/**
	 * Direction
	 */
	private double ballDirection;
	
	/**
	 * Distance
	 */
	private double ballDistance;
	private double ownGoalDistance;
	private double oponentGoalDistance;
	
	/**
	 * See
	 */
	private boolean canSeeOwnGoal;
	private boolean canSeeOponentGoal;
	private boolean canSeeGoal;
	private boolean canSeeBall;
	private boolean canSeeNothing;
	double canSeePlayerDirection;
	
	private boolean beforeKickOff = true;
	
	boolean canOpponentSide = true;
	double opponentSideDistance;
	double opponentSideDirection;
	
	boolean canSeeOwnSide = true;
	double ownSideDistance;
	double ownSideDirection;
	
	double opponentGoalDistance;
	double ownPlayerDistance;
	
	boolean hasTheBall;
	
	boolean heardMessageStartChase;

	
	
	SeeBall ballInfo = new SeeBall();
	
	int[] seeOwnPlayers;
	
	double closestOwnPlayerDistance = 1000;
	int closestOwnPlayerId = 0;
	double closestOwnPlayerDirection = 0;
	
	int seeOwnPlayer = 0;
	boolean bSeeOwnPlayer;
	boolean canSeeOwnPlayer = false;
	int numberOfOwnPlayerSearches = 0;
	

	@Override
	public void preInfo() {
		this.setType("Player");
		
		this.ballDirection = 0;
		this.ballDistance = 1000;
		this.ownGoalDistance = 1000;
		this.oponentGoalDistance = 1000;
		
		this.canSeeGoal = false;
		this.canSeeOponentGoal = false;
		this.canSeeOwnGoal = false;
		this.canSeeBall = false;
		this.canSeeNothing = false;
		
		
	
	}

	@Override
	public void postInfo() {
		if (this.beforeKickOff)
			return;

		try {
			int playerId = this.getPlayer().getNumber();
			
			if(team.get(playerId).getRole() == PlayerRole.Attacker){
				if(canSeeBall){
					if(hasTheBall()){
						
					}else{
						runForBall();
					}
					
				}else{
					lookAround();
					
					if(canSeeBall){
						runForBall();
					}else{
						
					}
					
					
				}
			}
			
			
			

		} catch (Exception e) {

		}

	}
	
	public boolean hasTheBall(){
		if(ballDistance <= 0.7){
			return true;
		}
		return false;
	}
	
	public void findTheBallAndGetIt(){
		if(canSeeBall){
			runForBall();
		}else{
			lookAround();
		}
	}
	
	public void lookAround(){
		getPlayer().turn(40);
	}
	
	public void runForBall(){
		if(ballDistance > 4){
			getPlayer().turn(ballDirection);
			getPlayer().dash(100);
		}
	}
	
	public boolean seeOpponentSide(){
		if (canOpponentSide && opponentGoalDistance > 45){
			return true;
		}
		return false;
	}
	
	public boolean seeOwnSide() {
		if (canSeeOwnSide && ownGoalDistance < 50) {
			return true;
		}
		return false;
	}
	
	public boolean seeOwnSupportPlayer(){
		if(bSeeOwnPlayer){
			
			return true;
		}
		return false;
	}
	
	public void lookForSupport(){
		if(!bSeeOwnPlayer){
			numberOfOwnPlayerSearches++;
		}else{
			getPlayer().turn(40);
		}
	}
	
	public void runInDirection(double direction){
		getPlayer().turn(direction);
		getPlayer().dash(100);
	}
	
	public void dribbleInDirection(double direction){
		getPlayer().turn(direction);
		getPlayer().kick(20, direction);
		getPlayer().dash(20);
	}
	
	/**
	 * Check whether the ball is in a kick able distance
	 * @return boolean
	 */
	public boolean ballInKickableDistance(){
		if(ballDistance < 1){
			return true;
		}else{
			return false;
		}
	}
	
	public double lookForPlayer(int seePlayerId, int playerToLookFor, int whoShouldLook){
		System.out.println("LookForPlayer: " + seePlayerId + " -> " + playerToLookFor + " -> " + whoShouldLook);
		if(getPlayer().getNumber() == whoShouldLook){
			if(seePlayerId == playerToLookFor){
				return 1;
			}else{
				System.out.println("Cant see: "+playerToLookFor + " so turn" );
				getPlayer().turn(45);
			}
		}
		
		return 0;
	}
	
	public void getTheBall(){
		turnTowardsBall();
		getPlayer().dash(100);
	}
	
	public void kickBall(int playerId){
		if(ballInfo.distance < 5 && ballInfo.distance != 0.0){
			//getPlayer().kick(100, -180);
			//System.out.println("KickBall "+ playerId);
		}
	}
	
	public void kickTowards(int playerId, double direction, int power){
		if(ballInfo.distance < 0.7 && ballInfo.distance != 0.0){
			//getPlayer().kick(power, direction);
			System.out.println("KickTowards "+ playerId + " dir: " + direction + " power: " + power);
		}
	}
	
	public void turnTowardsBall(){
		System.out.println("TurnTowardsBall: " + ballInfo.direction);
		getPlayer().turn(ballInfo.direction);
	}
	
	private void postInfoAttackers(){
		
		int playerId = this.getPlayer().getNumber();
		
		System.out.println("#####################################################");
		System.out.println("#####################################################");
		System.out.println("#####################################################");
		System.out.println(team.lastPlayerIdSeenBall);
		System.out.println("#####################################################");
		System.out.println("#####################################################");
		System.out.println("#####################################################");
		
		
		if(team.get(playerId).getBall() == null){
			System.out.println("####################################################################################GET BALL NULL" + playerId);
			//return;
		}
		
		if(playerId == 8){
			
			//System.out.println("####DISTANCE8: " + team.get(playerId).getBall().distance);
			team.get(playerId).getController().getPlayer().dash(55);
			team.get(playerId).getController().getPlayer().turn(team.get(playerId).getBall().direction);
			if(team.get(playerId).getBall().distance < 1){
				
				team.get(playerId).getController().getPlayer().kick(100, -80);
				team.get(playerId).getController().getPlayer().dash(0);
			}else{
				
			}
		}
		
		//Middle attacker
		if(playerId == 4){
			
			
			//System.out.println("####DISTANCE4: " + team.get(playerId).getBall().distance);
			if(team.get(playerId).getBall().distance < 1){
				team.get(playerId).getController().getPlayer().kick(100, -90);
				team.get(playerId).getController().getPlayer().dash(0);
			}else{
				team.get(playerId).getController().getPlayer().dash(100);
			}
			
			
			
		}
		
		
	}
	
	private void postInfoDefenders(){
		
	}
	
	private void postInfoMidFielders(){
		
	}
	

	@Override
	public ActionsPlayer getPlayer() {
		return this.player;
	}

	@Override
	public void setPlayer(ActionsPlayer c) {
		this.player = c;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String newType) {
		this.type = newType;
	}

	@Override
	public void infoSeeFlagRight(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagLeft(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagOwn(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		canSeeOwnSide = true;
		canOpponentSide = false;
		ownSideDistance = distance;
		ownSideDirection = direction;
	}

	@Override
	public void infoSeeFlagOther(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
//		if(getPlayer().getNumber() == 4 
//				|| getPlayer().getNumber() == 3 || getPlayer().getNumber() == 2)
//		System.out.println("CanSeeFlag PlayerId: " + getPlayer().getNumber() + " -> " + flag);
//		
		
		//canSeeOwnSide = true;
		//ownSideDistance = distance;
		//ownSideDirection = direction;
		
		canOpponentSide = true;
		canSeeOwnSide = false;
		
		
		opponentSideDirection = direction;
		opponentSideDistance = distance;
		
		
	}

	@Override
	public void infoSeeFlagCenter(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagCornerOwn(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagCornerOther(Flag flag, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagPenaltyOwn(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagPenaltyOther(Flag flag, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeFlagGoalOwn(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		canSeeOwnSide = true;
		canOpponentSide = false;
		ownGoalDistance = distance;
	}

	@Override
	public void infoSeeFlagGoalOther(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		//team.get(this.getPlayer().getNumber()).getController().
		canOpponentSide = true;
		canSeeOwnSide = false;
		opponentGoalDistance = distance;
//		if(getPlayer().getNumber() == 4 
//		|| getPlayer().getNumber() == 3 || getPlayer().getNumber() == 2)
//System.out.println("CanSeeFlag PlayerId: " + getPlayer().getNumber() + " -> " + flag + " --> " + distance);

	}

	@Override
	public void infoSeeLine(Line line, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeePlayerOther(int number, boolean goalie, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		System.out.println("OtherPlayer: " + number);
	}

	@Override
	public void infoSeePlayerOwn(int number, boolean goalie, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
		//closestOwnPlayerDistance = 1000;
		//closestOwnPlayerId = number;
		if(distance < closestOwnPlayerDistance){
			closestOwnPlayerDistance = distance;
			closestOwnPlayerId = number;
			closestOwnPlayerDirection = direction;
			
		}
		
		seeOwnPlayer = number;
		canSeePlayerDirection = direction;
		canSeeOwnPlayer = true;
		ownPlayerDistance = distance;
		
		if(getPlayer().getNumber() == 4){
			//System.out.println("CanSeeOwnPlayer: 4 -> " + number);
		}
		
		//if(ballInfo.distance < 0.7 && ballInfo.distance != 0.0)
		//	getPlayer().kick(100, direction);
	    
		
		
		
		
		
		int playerId = getPlayer().getNumber();
		//if(playerId == 4)
		//	System.out.println("SeeOwnPlayer: " + playerId +  " -> " + number + " = " + distance + " ~ " + direction);
		
		/**
		 * [*]
		 * [1]
		 * [2] -> findBall() ? LookAroud (canSeeBall = true) : AskWhoCanSeeTheBall()->6->chasePlayerId(6)
		 * [3]
		 * [4]
		 * [5]
		 * [6] canSeeBall = true
		 * [7]
		 * [8]
		 * [9]
		 * [10]
		 */
				
	}

	@Override
	public void infoSeeBall(double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub

		
		canSeeBall = true;
		ballDirection = direction;
		ballDistance = distance;
		
		
		
		
		
		
		
		
		
		
		
		/**
		int playerId = this.getPlayer().getNumber();
		
		canSeeBall = true;
		
		ballInfo.distance = distance;
		ballInfo.direction = direction;
		ballInfo.distChange = distChange;
		ballInfo.dirChange = dirChange;
		ballInfo.bodyFacingDirection = bodyFacingDirection;
		ballInfo.headFacingDirection = headFacingDirection;
		
		this.ballDirection = direction;
		this.ballDistance = distance;]
		*/
		/*
		
		
		SeeBall ball;
		
		if(team.get(playerId).getBall() == null){
			ball = new SeeBall();
		}else{
			ball = team.get(playerId).getBall();
		}
		
		
		ball.distance = distance;
		ball.direction = direction;
		ball.distChange = distChange;
		ball.dirChange = dirChange;
		ball.bodyFacingDirection = bodyFacingDirection;
		ball.headFacingDirection = headFacingDirection;
		
		ballInfo = ball;
		team.get(playerId).setSeeBall(ball);
		//team.get(playerId).canSeeBall = true;
		 * 
		 */
		
		//team.lastPlayerIdSeenBall = playerId;	
		
	}

	@Override
	public void infoHearReferee(RefereeMessage refereeMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearPlayMode(PlayMode playMode) {
		this.pause(1000);
		
		if(playMode == PlayMode.KICK_OFF_L
				||
		   playMode == PlayMode.KICK_OFF_R 
		        ||
		   playMode == PlayMode.KICK_OFF_OWN 
		   		||
		   playMode == PlayMode.KICK_OFF_OTHER){
			this.beforeKickOff = false;
		}
		
		int playerId = this.getPlayer().getNumber();
		
		/**
		 * Create team member
		 */
		Player player = new Player(this, playerId);
		
		/**
		 * Add player to the team
		 */
		team.addTeamMember(playerId, player);
		
		/**
		 * Set player role
		 */
		team.autoAssignPlayerRole(playerId);
		
		/**
		 * Position players on the pitch
		 */
		//PositionPlayers positionPlayers = new PositionPlayers(this, playMode, this.getPlayer().getNumber());
		Formation formation = new Formation(playerId, playMode);
		
		if(playerId == 2){
			getPlayer().turn(-15);
		}
		if(playerId == 3){
			getPlayer().turn(15);
		}
		
		
	}

	@Override
	public void infoHearPlayer(double direction, String message) {
		// TODO Auto-generated method stub
		
		//Messages.inbox().hear(this.getPlayer().getNumber(), message);
		//team.hear(this.getPlayer().getNumber(), message);
		
		if(message.equals("p9"))
			heardMessageStartChase = true;
		
	}

	@Override
	public void infoHearError(Errors error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearOk(Ok ok) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearWarning(Warning warning) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSenseBody(ViewQuality viewQuality, ViewAngle viewAngle, double stamina, double unknown,
			double effort, double speedAmount, double speedDirection, double headAngle, int kickCount, int dashCount,
			int turnCount, int sayCount, int turnNeckCount, int catchCount, int moveCount, int changeViewCount) {
		// TODO Auto-generated method stub
		
		try{
			SenseBody sense = new SenseBody();
			sense.viewQuality = viewQuality;
			sense.viewAngle = viewAngle;
			sense.stamina = stamina;
			sense.unknown = unknown;
			sense.effort = effort;
			sense.speedAmount = speedAmount;
			sense.speedDirection = speedDirection;
			sense.headAngle = headAngle;
			sense.kickCount = kickCount;
			sense.dashCount = dashCount;
			sense.turnCount = turnCount;
			sense.sayCount = sayCount;
			sense.turnNeckCount = turnNeckCount;
			sense.catchCount = catchCount;
			sense.moveCount = moveCount;
			sense.changeViewCount = changeViewCount;
			
			int playerId = this.getPlayer().getNumber();
			team.get(playerId).setSenseBody(sense);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		
		
	}

	@Override
	public void infoCPTOwn(int unum, int type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoCPTOther(int unum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoPlayerType(int id, double playerSpeedMax, double staminaIncMax, double playerDecay,
			double inertiaMoment, double dashPowerRate, double playerSize, double kickableMargin, double kickRand,
			double extraStamina, double effortMax, double effortMin) {
		// TODO Auto-generated method stub
		
		PlayerType type = new PlayerType();
		type.id = id;
		type.playerSpeedMax = playerSpeedMax;
		type.staminaIncMax = staminaIncMax;
		type.playerDecay = playerDecay;
		type.inertiaMoment = inertiaMoment;
		type.dashPowerRate = dashPowerRate;
		type.playerSize = playerSize;
		type.kickableMargin = kickableMargin;
		type.kickRand = kickRand;
		type.extraStamina = extraStamina;
		type.effortMax = effortMax;
		type.effortMin = effortMin;
		
		int playerId = this.getPlayer().getNumber();
		
		team.get(playerId).setPlayerType(type);
		
		
	}

	@Override
	public void infoPlayerParam(double allowMultDefaultType, double dashPowerRateDeltaMax, double dashPowerRateDeltaMin,
			double effortMaxDeltaFactor, double effortMinDeltaFactor, double extraStaminaDeltaMax,
			double extraStaminaDeltaMin, double inertiaMomentDeltaFactor, double kickRandDeltaFactor,
			double kickableMarginDeltaMax, double kickableMarginDeltaMin, double newDashPowerRateDeltaMax,
			double newDashPowerRateDeltaMin, double newStaminaIncMaxDeltaFactor, double playerDecayDeltaMax,
			double playerDecayDeltaMin, double playerTypes, double ptMax, double randomSeed,
			double staminaIncMaxDeltaFactor, double subsMax) {
		// TODO Auto-generated method stub
		
		PlayerParam param = new PlayerParam();
		param.allowMultDefaultType = allowMultDefaultType;
		param.dashPowerRateDeltaMax = dashPowerRateDeltaMax;
		param.dashPowerRateDeltaMin = dashPowerRateDeltaMin;
		param.effortMaxDeltaFactor =  effortMaxDeltaFactor;
		param.effortMinDeltaFactor = effortMinDeltaFactor;
		param.extraStaminaDeltaMax = extraStaminaDeltaMax;
		param.extraStaminaDeltaMin = extraStaminaDeltaMin;
		param.inertiaMomentDeltaFactor = inertiaMomentDeltaFactor;
		param.kickRandDeltaFactor = kickRandDeltaFactor;
		param.kickableMarginDeltaMax = kickableMarginDeltaMax;
		param.kickableMarginDeltaMin = kickableMarginDeltaMin;
		param.newDashPowerRateDeltaMax = newDashPowerRateDeltaMax;
		param.newDashPowerRateDeltaMin = newDashPowerRateDeltaMin;
		param.newStaminaIncMaxDeltaFactor = newStaminaIncMaxDeltaFactor;
		param.playerDecayDeltaMax = playerDecayDeltaMax;
		param.playerDecayDeltaMin = playerDecayDeltaMin;
		param.playerTypes = playerTypes;
		param.ptMax = ptMax;
		param.randomSeed = randomSeed;
		param.staminaIncMaxDeltaFactor = staminaIncMaxDeltaFactor;
		param.subsMax = subsMax;
		
		int playerId = this.getPlayer().getNumber();
		
		team.get(playerId).setPlayerParam(param);
		
		
	}

	@Override
	public void infoServerParam(HashMap<ServerParams, Object> info) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	/**
     * Pause the thread.
     * @param ms How long to pause the thread for (in ms).
     */
    private synchronized void pause(int ms) {
        try {
            this.wait(ms);
        } catch (InterruptedException ex) {
            log.warn("Interrupted Exception ", ex);
        }
    }
    
    

}
