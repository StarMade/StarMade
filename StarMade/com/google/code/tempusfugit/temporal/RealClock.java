package com.google.code.tempusfugit.temporal;

import java.util.Date;

public class RealClock
  implements Clock
{
  public static RealClock now()
  {
    return new RealClock();
  }
  
  public static RealClock today()
  {
    return new RealClock();
  }
  
  public Date create()
  {
    return new Date();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.google.code.tempusfugit.temporal.RealClock
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */