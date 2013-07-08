/*  1:   */package org.schema.game.common.controller;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementInformation;
/*  4:   */
/*  6:   */public class CannotBeControlledException
/*  7:   */  extends Exception
/*  8:   */{
/*  9:   */  private static final long serialVersionUID = 4188138482695970846L;
/* 10:   */  public final ElementInformation a;
/* 11:   */  public final ElementInformation b;
/* 12:   */  
/* 13:   */  public CannotBeControlledException(ElementInformation paramElementInformation1, ElementInformation paramElementInformation2)
/* 14:   */  {
/* 15:15 */    this.b = paramElementInformation1;
/* 16:16 */    this.a = paramElementInformation2;
/* 17:   */  }
/* 18:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.CannotBeControlledException
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */