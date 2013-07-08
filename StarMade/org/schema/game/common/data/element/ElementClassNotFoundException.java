/*  1:   */package org.schema.game.common.data.element;
/*  2:   */
/*  4:   */public class ElementClassNotFoundException
/*  5:   */  extends RuntimeException
/*  6:   */{
/*  7:   */  private static final long serialVersionUID = 1177407588913436017L;
/*  8:   */  
/*  9:   */  public ElementClassNotFoundException(short paramShort)
/* 10:   */  {
/* 11:11 */    super("class for type " + paramShort + " not found");
/* 12:   */  }
/* 13:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.ElementClassNotFoundException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */