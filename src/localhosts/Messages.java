package localhosts;

import java.util.HashMap;

public class Messages {
	
	//HashMap <PlayerId, String message>
	public HashMap<Integer, String> sentMessages = new HashMap<Integer, String>();
		
	//HashMap <PlayerId, String message>
	public HashMap<Integer, String> heardMessages = new HashMap<Integer, String>();
	
	private static Messages instance = null;
	
	public Team team = Team.getTeam();
	
	protected Messages(){
		
	}
	
	public static Messages inbox(){
		if(instance == null){
			instance = new Messages();
		}		
		return instance;
	}
	
	public void takeAction(int playerId, String msg){
		String message = this.heardMessages.get(playerId);
		
		switch (message) {
		case "SeeBall":
			team.get(playerId).getController().getPlayer().bye();
			
			team.get(playerId).action().findSupportTeamMember();
			
			break;

		default:
			break;
		}
		
	}
	
	public void say(int playerId, String msg){
		sentMessages.put(playerId, msg); //Keep track of sent messages
		//this.get(playerId).getController().getPlayer().say(msg); //Actual message sent
	}
	
	public void hear(int playerId, String msg){
		heardMessages.put(playerId, msg);
		
		this.takeAction(playerId, msg);
	}

}
