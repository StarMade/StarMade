package org.newdawn.slick.util;

public abstract interface LogSystem
{
  public abstract void error(String paramString, Throwable paramThrowable);

  public abstract void error(Throwable paramThrowable);

  public abstract void error(String paramString);

  public abstract void warn(String paramString);

  public abstract void warn(String paramString, Throwable paramThrowable);

  public abstract void info(String paramString);

  public abstract void debug(String paramString);
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.LogSystem
 * JD-Core Version:    0.6.2
 */