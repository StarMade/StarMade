/*  1:   */package org.newdawn.slick.util;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.Date;
/*  5:   */
/* 10:   */public class DefaultLogSystem
/* 11:   */  implements LogSystem
/* 12:   */{
/* 13:13 */  public static PrintStream out = System.out;
/* 14:   */  
/* 20:   */  public void error(String message, Throwable e)
/* 21:   */  {
/* 22:22 */    error(message);
/* 23:23 */    error(e);
/* 24:   */  }
/* 25:   */  
/* 30:   */  public void error(Throwable e)
/* 31:   */  {
/* 32:32 */    out.println(new Date() + " ERROR:" + e.getMessage());
/* 33:33 */    e.printStackTrace(out);
/* 34:   */  }
/* 35:   */  
/* 40:   */  public void error(String message)
/* 41:   */  {
/* 42:42 */    out.println(new Date() + " ERROR:" + message);
/* 43:   */  }
/* 44:   */  
/* 49:   */  public void warn(String message)
/* 50:   */  {
/* 51:51 */    out.println(new Date() + " WARN:" + message);
/* 52:   */  }
/* 53:   */  
/* 58:   */  public void info(String message)
/* 59:   */  {
/* 60:60 */    out.println(new Date() + " INFO:" + message);
/* 61:   */  }
/* 62:   */  
/* 67:   */  public void debug(String message)
/* 68:   */  {
/* 69:69 */    out.println(new Date() + " DEBUG:" + message);
/* 70:   */  }
/* 71:   */  
/* 77:   */  public void warn(String message, Throwable e)
/* 78:   */  {
/* 79:79 */    warn(message);
/* 80:80 */    e.printStackTrace(out);
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.DefaultLogSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */