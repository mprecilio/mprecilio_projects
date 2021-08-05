package project_1_v2.utilities;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class EventLogger {
	public static final Logger loggy = Logger.getLogger(EventLogger.class);
	{
		loggy.setLevel(Level.ALL);
	}

}
