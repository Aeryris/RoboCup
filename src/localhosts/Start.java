package localhosts;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.github.robocup_atan.atan.model.AbstractTeam;

public class Start {
	
	public static void main(String[] args) {
        BasicConfigurator.configure();
        List<Logger> loggers = Collections.<Logger>list(LogManager.getCurrentLoggers());
        loggers.add(LogManager.getRootLogger());
        for ( Logger logger : loggers ) {
            logger.setLevel(Level.OFF);
        }
        AbstractTeam team = null;
        if (args.length == 0) {
            team = new LocalhostsTeam(Config.teamName, Config.serverPort, Config.hostName, Config.hasCoach);
        } else {
            Integer val      = new Integer(args[0]);
            int     port     = val.intValue();
            String  hostname = args[1];
            team = new LocalhostsTeam(Config.teamName, port, hostname, true);
        }
        team.connectAll();
    }

}
