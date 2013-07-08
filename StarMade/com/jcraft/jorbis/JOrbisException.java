/*  1:   */package com.jcraft.jorbis;
/*  2:   */
/* 12:   */public class JOrbisException
/* 13:   */  extends Exception
/* 14:   */{
/* 15:   */  private static final long serialVersionUID = 1L;
/* 16:   */  
/* 26:   */  public JOrbisException() {}
/* 27:   */  
/* 36:   */  public JOrbisException(String s)
/* 37:   */  {
/* 38:38 */    super("JOrbis: " + s);
/* 39:   */  }
/* 40:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.JOrbisException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */