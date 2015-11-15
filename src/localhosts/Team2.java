package localhosts;

import org.apache.log4j.BasicConfigurator;

import com.github.robocup_atan.atan.model.AbstractTeam;

public class Team2 {
	
	public static void main(String[] args) {
        BasicConfigurator.configure();
        AbstractTeam team = null;
        if (args.length == 0) {
            team = new LocalhostsTeam("Team2", Config.serverPort, Config.hostName, Config.hasCoach);
        } else {
            Integer val      = new Integer(args[0]);
            int     port     = val.intValue();
            String  hostname = args[1];
            team = new LocalhostsTeam(Config.teamName, port, hostname, true);
        }
        team.connectAll();
    }

}

