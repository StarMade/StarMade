/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import xq;
/*  4:   */
/*  8:   */public class ManagerModuleControllable
/*  9:   */  extends ManagerModule
/* 10:   */{
/* 11:   */  private final short controllerID;
/* 12:   */  
/* 13:   */  public ManagerModuleControllable(UsableElementManager paramUsableElementManager, short paramShort1, short paramShort2)
/* 14:   */  {
/* 15:15 */    super(paramUsableElementManager, paramShort1);
/* 16:16 */    this.controllerID = paramShort2;
/* 17:   */  }
/* 18:   */  
/* 20:   */  public short getControllerID()
/* 21:   */  {
/* 22:22 */    return this.controllerID;
/* 23:   */  }
/* 24:   */  
/* 25:   */  public void update(xq paramxq, long paramLong) {}
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleControllable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */