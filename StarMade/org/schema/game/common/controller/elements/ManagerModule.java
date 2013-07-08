/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementInformation;
/*  4:   */import org.schema.game.common.data.element.ElementKeyMap;
/*  5:   */import q;
/*  6:   */import xq;
/*  7:   */
/* 11:   */public abstract class ManagerModule
/* 12:   */{
/* 13:   */  private final UsableElementManager elementManager;
/* 14:   */  private final short elementID;
/* 15:   */  private ManagerModule next;
/* 16:   */  
/* 17:   */  public ManagerModule(UsableElementManager paramUsableElementManager, short paramShort)
/* 18:   */  {
/* 19:19 */    this.elementManager = paramUsableElementManager;
/* 20:20 */    this.elementID = paramShort;
/* 21:   */  }
/* 22:   */  
/* 26:   */  public void addControlledBlock(q paramq1, q paramq2, short paramShort) {}
/* 27:   */  
/* 31:   */  public void clear() {}
/* 32:   */  
/* 35:   */  public short getElementID()
/* 36:   */  {
/* 37:37 */    return this.elementID;
/* 38:   */  }
/* 39:   */  
/* 40:   */  public UsableElementManager getElementManager()
/* 41:   */  {
/* 42:42 */    return this.elementManager;
/* 43:   */  }
/* 44:   */  
/* 47:   */  public void removeControllerBlock(q paramq1, q paramq2, short paramShort) {}
/* 48:   */  
/* 51:   */  public String toString()
/* 52:   */  {
/* 53:53 */    return "(" + getElementManager().getClass().getSimpleName() + ": " + ElementKeyMap.getInfo(this.elementID).getName() + ")";
/* 54:   */  }
/* 55:   */  
/* 58:   */  public abstract void update(xq paramxq, long paramLong);
/* 59:   */  
/* 61:   */  public ManagerModule getNext()
/* 62:   */  {
/* 63:63 */    return this.next;
/* 64:   */  }
/* 65:   */  
/* 69:   */  public void setNext(ManagerModule paramManagerModule)
/* 70:   */  {
/* 71:71 */    this.next = paramManagerModule;
/* 72:   */  }
/* 73:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModule
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */