package localhosts;

import com.github.robocup_atan.atan.model.ControllerPlayer;
import com.github.robocup_atan.atan.model.enums.PlayMode;

class PositionPlayers{
	
	private ControllerPlayer controller;
	private PlayMode playMode;
	private int playerId;
	
	public static Team team;
	
	PositionPlayers(ControllerPlayer object, PlayMode playMode, int playerId){
		this.controller = object;
		this.playMode = playMode;
		this.playerId = playerId;
		this.positionByPlayMode(playMode, playerId);
	}
	
	//PositionPlayers(Team team, playMode)
	
	public void beforeKickOff(int playerId) {
		this.positionPlayers(playerId);
	}
	

	public void positionByPlayMode(PlayMode playMode, int playerId) {
		switch (playMode) {
		case BEFORE_KICK_OFF:
			this.beforeKickOff(playerId);
			break;
		case CORNER_KICK_L:
			
			break;
		case CORNER_KICK_OTHER:
			break;
		case CORNER_KICK_OWN:
			break;
		case CORNER_KICK_R:
			break;
		case FREE_KICK_FAULT_L:
			break;
		case FREE_KICK_FAULT_OTHER:
			break;
		case FREE_KICK_FAULT_OWN:
			break;
		case FREE_KICK_FAULT_R:
			break;
		case FREE_KICK_L:
			break;
		case FREE_KICK_OTHER:
			break;
		case FREE_KICK_OWN:
			break;
		case FREE_KICK_R:
			break;
		case GOAL_KICK_L:
			break;
		case GOAL_KICK_OTHER:
			break;
		case GOAL_KICK_OWN:
			break;
		case GOAL_KICK_R:
			break;
		case GOAL_L:
			break;
		case GOAL_OTHER:
			break;
		case GOAL_OWN:
			break;
		case GOAL_R:
			break;
		case KICK_IN_L:
			break;
		case KICK_IN_OTHER:
			break;
		case KICK_IN_OWN:
			break;
		case KICK_IN_R:
			break;
		case KICK_OFF_L:
			break;
		case KICK_OFF_OTHER:
			break;
		case KICK_OFF_OWN:
			break;
		case KICK_OFF_R:
			break;
		case PLAY_ON:
			break;
		case TIME_OVER:
			break;
		default:
			break;

		}
	}
	
	public void positionPlayers(int playerId) {
		switch (playerId) {
		
		// Attackers
		case 2:
		case 3:
		case 7:
		case 8:
			this.positionAttackers(playerId);
			break;

		// Midfielders
		case 4:
		case 5:
		case 6:
			this.positionMidfielders(playerId);
			break;

		// Defenders
		case 9:
		case 10:
		case 11:
			this.positionDefenders(playerId);
			break;

		}
	}
	
	
	public void positionAttackers(int playerId){
		switch(playerId){
		case 2:
			controller.getPlayer().move(-10, 10);
			break;
		case 3:
			controller.getPlayer().move(-10, -10);
			break;
		case 7:
			controller.getPlayer().move(-12, 23);
			break;
		case 8:
			controller.getPlayer().move(-12, -24);
			break;
		}
	}
	
	public void positionMidfielders(int playerId){
		switch(playerId){
		case 4:
			controller.getPlayer().move(-23, 0);
			break;
		case 5:
			controller.getPlayer().move(-23, 12);
			break;
		case 6:
			controller.getPlayer().move(-23, -12);
			break;
		}
	}
	
	public void positionDefenders(int playerId){
		switch(playerId){
		case 9:
			controller.getPlayer().move(-30, 0);
			break;
		case 10:
			controller.getPlayer().move(-40, 10);
			break;
		case 11:
			controller.getPlayer().move(-40, -10);
			break;
		}
	}

	
}