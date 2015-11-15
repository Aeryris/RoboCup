package localhosts;

import java.util.HashMap;

interface TeamInterface{
	
}

public class Team implements TeamInterface{
	
	//Player.class
	public HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	
	private static Team instance = null;
	
	

	protected Team() {
		
	}

	public static Team getTeam() {
		if (instance == null) {
			instance = new Team();
		}
		return instance;
	}
	
	public void addTeamMember(int playerId, Player player){
		players.put(playerId, player);
	}
	
	public Player get(int playerId){
		return players.get(playerId);
	}
	
	
}
