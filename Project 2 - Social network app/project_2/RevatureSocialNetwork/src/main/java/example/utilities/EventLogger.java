package example.utilities;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * @author Matthew Precilio
 * 
 * This class is responsible for holding the Logger object.
 * Use the static member "loggy" for all logging.
 */

public class EventLogger {
	
	public static final Logger loggy = Logger.getLogger(EventLogger.class);
	{
		loggy.setLevel(Level.ALL);
	}
	
}
