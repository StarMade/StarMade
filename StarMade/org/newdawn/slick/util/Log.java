package org.newdawn.slick.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

public final class Log
{
  private static boolean verbose = true;
  private static boolean forcedVerbose = false;
  private static final String forceVerboseProperty = "org.newdawn.slick.forceVerboseLog";
  private static final String forceVerbosePropertyOnValue = "true";
  private static LogSystem logSystem = new DefaultLogSystem();
  
  public static void setLogSystem(LogSystem system)
  {
    logSystem = system;
  }
  
  public static void setVerbose(boolean local_v)
  {
    if (forcedVerbose) {
      return;
    }
    verbose = local_v;
  }
  
  public static void checkVerboseLogSetting()
  {
    try
    {
      AccessController.doPrivileged(new PrivilegedAction()
      {
        public Object run()
        {
          String val = System.getProperty("org.newdawn.slick.forceVerboseLog");
          if ((val != null) && (val.equalsIgnoreCase("true"))) {
            Log.setForcedVerboseOn();
          }
          return null;
        }
      });
    }
    catch (Throwable local_e) {}
  }
  
  public static void setForcedVerboseOn()
  {
    forcedVerbose = true;
    verbose = true;
  }
  
  public static void error(String message, Throwable local_e)
  {
    logSystem.error(message, local_e);
  }
  
  public static void error(Throwable local_e)
  {
    logSystem.error(local_e);
  }
  
  public static void error(String message)
  {
    logSystem.error(message);
  }
  
  public static void warn(String message)
  {
    logSystem.warn(message);
  }
  
  public static void warn(String message, Throwable local_e)
  {
    logSystem.warn(message, local_e);
  }
  
  public static void info(String message)
  {
    if ((verbose) || (forcedVerbose)) {
      logSystem.info(message);
    }
  }
  
  public static void debug(String message)
  {
    if ((verbose) || (forcedVerbose)) {
      logSystem.debug(message);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.Log
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */