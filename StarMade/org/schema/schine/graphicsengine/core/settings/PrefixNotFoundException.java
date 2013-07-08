/*  1:   */package org.schema.schine.graphicsengine.core.settings;
/*  2:   */
/*  4:   */public class PrefixNotFoundException
/*  5:   */  extends Exception
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = -8677548242927628561L;
/*  8:   */  
/*  9:   */  public PrefixNotFoundException(String paramString)
/* 10:   */  {
/* 11:11 */    super("ERROR: prefix not found: " + paramString);
/* 12:   */  }
/* 13:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.graphicsengine.core.settings.PrefixNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */