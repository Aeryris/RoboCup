package localhosts;

import java.util.HashMap;

import com.github.robocup_atan.atan.model.ActionsCoach;
import com.github.robocup_atan.atan.model.ControllerCoach;
import com.github.robocup_atan.atan.model.enums.Errors;
import com.github.robocup_atan.atan.model.enums.Ok;
import com.github.robocup_atan.atan.model.enums.PlayMode;
import com.github.robocup_atan.atan.model.enums.RefereeMessage;
import com.github.robocup_atan.atan.model.enums.ServerParams;
import com.github.robocup_atan.atan.model.enums.Warning;

public class Coach implements ControllerCoach {
	
	private ActionsCoach coach;

	@Override
	public void infoSeePlayerOwn(int number, boolean goalie, double x, double y, double deltaX, double deltaY,
			double bodyAngle, double neckAngle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeePlayerOther(int number, boolean goalie, double x, double y, double deltaX, double deltaY,
			double bodyAngle, double neckAngle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeGoalOwn(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeGoalOther(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoSeeBall(double x, double y, double deltaX, double deltaY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearTeamNames(String teamWest, String teamEast) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearReferee(RefereeMessage refereeMessage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void infoHearPlayMode(PlayMode playMode) {
		// TODO Auto-generated method stub
		
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
	public void infoPlayerType(int id, double playerSpeedMax, double staminaIncMax, double playerDecay,
			double inertiaMoment, double dashPowerRate, double playerSize, double kickableMargin, double kickRand,
			double extraStamina, double effortMax, double effortMin) {
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

	@Override
	public void setCoach(ActionsCoach c) {
		this.coach = c;
	}

	@Override
	public ActionsCoach getCoach() {
		return this.coach;
	}

}
