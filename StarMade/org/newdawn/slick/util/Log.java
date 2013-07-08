/*   1:    */package org.newdawn.slick.util;
/*   2:    */
/*   3:    */import java.security.AccessController;
/*   4:    */import java.security.PrivilegedAction;
/*   5:    */
/*  11:    */public final class Log
/*  12:    */{
/*  13: 13 */  private static boolean verbose = true;
/*  14:    */  
/*  15: 15 */  private static boolean forcedVerbose = false;
/*  16:    */  
/*  20:    */  private static final String forceVerboseProperty = "org.newdawn.slick.forceVerboseLog";
/*  21:    */  
/*  25:    */  private static final String forceVerbosePropertyOnValue = "true";
/*  26:    */  
/*  30: 30 */  private static LogSystem logSystem = new DefaultLogSystem();
/*  31:    */  
/*  44:    */  public static void setLogSystem(LogSystem system)
/*  45:    */  {
/*  46: 46 */    logSystem = system;
/*  47:    */  }
/*  48:    */  
/*  55:    */  public static void setVerbose(boolean v)
/*  56:    */  {
/*  57: 57 */    if (forcedVerbose)
/*  58: 58 */      return;
/*  59: 59 */    verbose = v;
/*  60:    */  }
/*  61:    */  
/*  64:    */  public static void checkVerboseLogSetting()
/*  65:    */  {
/*  66:    */    try
/*  67:    */    {
/*  68: 68 */      AccessController.doPrivileged(new PrivilegedAction() {
/*  69:    */        public Object run() {
/*  70: 70 */          String val = System.getProperty("org.newdawn.slick.forceVerboseLog");
/*  71: 71 */          if ((val != null) && (val.equalsIgnoreCase("true"))) {
/*  72: 72 */            Log.setForcedVerboseOn();
/*  73:    */          }
/*  74:    */          
/*  75: 75 */          return null;
/*  76:    */        }
/*  77:    */      });
/*  78:    */    }
/*  79:    */    catch (Throwable e) {}
/*  80:    */  }
/*  81:    */  
/*  87:    */  public static void setForcedVerboseOn()
/*  88:    */  {
/*  89: 89 */    forcedVerbose = true;
/*  90: 90 */    verbose = true;
/*  91:    */  }
/*  92:    */  
/*  98:    */  public static void error(String message, Throwable e)
/*  99:    */  {
/* 100:100 */    logSystem.error(message, e);
/* 101:    */  }
/* 102:    */  
/* 107:    */  public static void error(Throwable e)
/* 108:    */  {
/* 109:109 */    logSystem.error(e);
/* 110:    */  }
/* 111:    */  
/* 116:    */  public static void error(String message)
/* 117:    */  {
/* 118:118 */    logSystem.error(message);
/* 119:    */  }
/* 120:    */  
/* 125:    */  public static void warn(String message)
/* 126:    */  {
/* 127:127 */    logSystem.warn(message);
/* 128:    */  }
/* 129:    */  
/* 135:    */  public static void warn(String message, Throwable e)
/* 136:    */  {
/* 137:137 */    logSystem.warn(message, e);
/* 138:    */  }
/* 139:    */  
/* 144:    */  public static void info(String message)
/* 145:    */  {
/* 146:146 */    if ((verbose) || (forcedVerbose)) {
/* 147:147 */      logSystem.info(message);
/* 148:    */    }
/* 149:    */  }
/* 150:    */  
/* 155:    */  public static void debug(String message)
/* 156:    */  {
/* 157:157 */    if ((verbose) || (forcedVerbose)) {
/* 158:158 */      logSystem.debug(message);
/* 159:    */    }
/* 160:    */  }
/* 161:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.Log
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */