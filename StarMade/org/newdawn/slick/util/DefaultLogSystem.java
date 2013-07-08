package org.newdawn.slick.util;

import java.io.PrintStream;
import java.util.Date;

public class DefaultLogSystem
  implements LogSystem
{
  public static PrintStream out = System.out;
  
  public void error(String message, Throwable local_e)
  {
    error(message);
    error(local_e);
  }
  
  public void error(Throwable local_e)
  {
    out.println(new Date() + " ERROR:" + local_e.getMessage());
    local_e.printStackTrace(out);
  }
  
  public void error(String message)
  {
    out.println(new Date() + " ERROR:" + message);
  }
  
  public void warn(String message)
  {
    out.println(new Date() + " WARN:" + message);
  }
  
  public void info(String message)
  {
    out.println(new Date() + " INFO:" + message);
  }
  
  public void debug(String message)
  {
    out.println(new Date() + " DEBUG:" + message);
  }
  
  public void warn(String message, Throwable local_e)
  {
    warn(message);
    local_e.printStackTrace(out);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.DefaultLogSystem
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */