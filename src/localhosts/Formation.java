package localhosts;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.github.robocup_atan.atan.model.enums.PlayMode;

public class Formation {
	
	public Team team;
	
	private static Logger log = Logger.getLogger(Formation.class);
	
	Formation(Team _team, int playerId, PlayMode playMode){
		team = _team;
		this.positionByPlayMode(playMode, playerId);
	}
	
	Formation(int playerId, PlayMode playMode){
		team = Team.getTeam();
		this.positionByPlayMode(playMode, playerId);
	}
	
	public void positionByPlayMode(PlayMode playMode, int playerId){
		switch(playMode){
		case BEFORE_KICK_OFF:
			this.beforeKickOff(playerId);
			break;
		}
	}
	
	public void beforeKickOff(int playerId) {
		this.positionPlayers(playerId);
	}
	
	
	public void positionPlayers(int playerId) {
		switch (playerId) {
		
		// Attackers
		case 2:
		case 3:
		case 7:
		case 8:
			this.positionAttackers(playerId);
			//team.get(playerId).setRole(PlayerRole.Attacker);
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

		HashMap<String, Integer> position = new HashMap<String, Integer>();
		
		switch(playerId){
		case 2:
			position.put("x", -10);
			position.put("y", 10);
			break;
		case 3:
			position.put("x", -10);
			position.put("y", -10);
			break;
		case 7:
			position.put("x", -12);
			position.put("y", 23);
			break;
		case 8:
			position.put("x", -12);
			position.put("y", -24);
			break;
		}
		
		team.get(playerId).setRole(PlayerRole.Attacker);
		team.get(playerId).getController().getPlayer().move(position.get("x"), position.get("y"));
		
	}
	
	public void positionMidfielders(int playerId){
		
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		
		switch(playerId){
		case 4:
			position.put("x", -10);//-13
			position.put("y", 0);
			break;
		case 5:
			position.put("x", -23);
			position.put("y", 12);
			break;
		case 6:
			position.put("x", -23);
			position.put("y", -12);
			break;
		}
		
		team.get(playerId).setRole(PlayerRole.MidFielders);
		team.get(playerId).getController().getPlayer().move(position.get("x"), position.get("y"));
	}
	
	public void positionDefenders(int playerId){
		
		HashMap<String, Integer> position = new HashMap<String, Integer>();
		
		switch(playerId){
		case 9:
			position.put("x", -30);
			position.put("y", 0);
			break;
		case 10:
			position.put("x", -40);
			position.put("y", 10);
			break;
		case 11:
			position.put("x", -40);
			position.put("y", -10);
			break;
		}
		
		team.get(playerId).setRole(PlayerRole.MidFielders);
		team.get(playerId).getController().getPlayer().move(position.get("x"), position.get("y"));
	}

}
