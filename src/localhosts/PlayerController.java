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
	
	private boolean beforeKickOff = true;
	
	public void lookAction(){
		
	}
	
	

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
		if(this.beforeKickOff) return;
		// TODO Auto-generated method stub
		//this.getPlayer().dash(100);
		/**if(this.canSeeBall){
			//getPlayer().turn(90);
			getPlayer().dash(100);
			getPlayer().kick(100, -90);
		}else{
			getPlayer().turn(90);
			getPlayer().dash(50);
		} */
			
			
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
		
	}

	@Override
	public void infoSeeFlagOther(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void infoSeeFlagGoalOther(Flag flag, double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
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
		
	}

	@Override
	public void infoSeePlayerOwn(int number, boolean goalie, double distance, double direction, double distChange,
			double dirChange, double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeBall(double distance, double direction, double distChange, double dirChange,
			double bodyFacingDirection, double headFacingDirection) {
		// TODO Auto-generated method stub
		this.canSeeBall = true;
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
		
		/**
		 * Create team member
		 */
		Player player = new Player(this, this.getPlayer().getNumber());
		
		/**
		 * Add player to the team
		 */
		team.addTeamMember(this.getPlayer().getNumber(), player);
		
		/**
		 * Position players on the pitch
		 */
		//PositionPlayers positionPlayers = new PositionPlayers(this, playMode, this.getPlayer().getNumber());
		Formation formation = new Formation(this.getPlayer().getNumber(), playMode);
		
		
		
	}

	@Override
	public void infoHearPlayer(double direction, String message) {
		// TODO Auto-generated method stub
		
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
