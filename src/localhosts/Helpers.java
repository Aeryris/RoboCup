package localhosts;

enum PlayerRole{
	Attacker,
	Goalie,
	Defender,
	MidFielders
}

interface HelpersInterface{
	
	int[] getPlayersClosestToOpponentsGoal();
	int[] getPlayersClosestToOwnGoal();
	int[] getPlayersClosestToBall();
	
	
}

public class Helpers {
	
	

}
