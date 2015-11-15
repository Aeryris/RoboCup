package localhosts;

import java.util.HashMap;

interface TacticsIntrface{
	
	
	
	void process();
	
}

public class Tactics {
	
	//Separator " , "

	public HashMap<String, String> tacticsMap = new HashMap<String, String>();
	
	
	public Tactics(String message) {
		
		
		
	}
	
	public void process(){
		
	}
	
	public void setup(){
		
		tacticsMap.put("SeeBall", "chaseBall, kick, ");
		
		
		
	}

}
