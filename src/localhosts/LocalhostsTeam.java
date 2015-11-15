package localhosts;

import org.apache.log4j.Logger;

import com.github.robocup_atan.atan.model.AbstractTeam;
import com.github.robocup_atan.atan.model.ControllerCoach;
import com.github.robocup_atan.atan.model.ControllerPlayer;

public class LocalhostsTeam extends AbstractTeam {
	
	/**
	 * Team name
	 */
	public String teamName;
	
	/**
	 * Server port
	 */
	public int port;
	
	/**
	 * Server host name
	 */
	public String hostname;
	
	/**
	 * Add coach
	 */
	public boolean hasCoach;
	
	/**
	 * Logger
	 */
	 private static Logger log = Logger.getLogger(LocalhostsTeam.class);
	
	/**
	 * Constructor
	 * @param teamName
	 * @param port
	 * @param hostname
	 * @param hasCoach
	 */
	public LocalhostsTeam(String teamName, int port, String hostname, boolean hasCoach) {
		super(teamName, port, hostname, hasCoach);
		this.teamName = teamName;
		this.port = port;
		this.hostname = hostname;
		this.hasCoach = hasCoach;
	}

	@Override
	public ControllerPlayer getNewControllerPlayer(int i) {
		if(Config.debug)
			log.debug("getNewControllerPlayer: " + i);
	
		if(i == 0)
			return new Goalie();
		else
			return new PlayerController();
		
	}

	@Override
	public ControllerCoach getNewControllerCoach() {
		return new Coach();
	}

}
