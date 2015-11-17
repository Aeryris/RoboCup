package localhosts;

import java.util.HashMap;
import java.util.Map;

interface TeamInterface{
	
}

public class Team implements TeamInterface{
	
	/**
	 * Singleton static variable
	 */
	private static Team instance = null;
	
	//Player.class
	public HashMap<Integer, Player> players = new HashMap<Integer, Player>();
	
	/**
	 * Id of a player who has seen the ball last
	 */
	public int lastPlayerIdSeenBall;
	
	/**
	 * HashMap of team members for each role for quick access 
	 */
	public HashMap<PlayerRole, int[]> teamRolePlayers = new HashMap<PlayerRole, int[]>();
	
	
	
	

	protected Team() {
		this.teamRolePlayers.put(PlayerRole.Attacker, new int[]{2, 3, 7, 8});
		this.teamRolePlayers.put(PlayerRole.Defender, new int[]{9, 10, 11});
		this.teamRolePlayers.put(PlayerRole.MidFielders, new int[]{4, 5, 6});
	}
	
	public void autoAssignPlayerRole(int playerId){
		for (Map.Entry<PlayerRole, int[]> entry : teamRolePlayers.entrySet())
		{
			PlayerRole role = entry.getKey();
    		int[] i = entry.getValue();
    		for(int id : i){
    			if(playerId == id){
    				players.get(playerId).setRole(role);
    				System.out.println("######## Set Role: " + role + " playerID: " + playerId );
    			}
    				
    		}
		}
	}
	
	

	public boolean checkPlayerRole(int playerId, PlayerRole role){
		for (int id : this.teamRolePlayers.get(role)) {
			if(id == playerId) return true;
		}
		
		return false;
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
	
	public void play(){
		
	}
	
	
	
	
	
	
	
	
}
