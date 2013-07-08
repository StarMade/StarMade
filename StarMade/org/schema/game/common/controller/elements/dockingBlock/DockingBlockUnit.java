/*  1:   */package org.schema.game.common.controller.elements.dockingBlock;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementCollection;
/*  4:   */import q;
/*  5:   */
/*  6:   */public class DockingBlockUnit
/*  7:   */  extends ElementCollection
/*  8:   */{
/*  9: 9 */  private q significator = new q();
/* 10:   */  
/* 11:   */  public q getSignificator()
/* 12:   */  {
/* 13:13 */    return this.significator;
/* 14:   */  }
/* 15:   */  
/* 21:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 22:   */  {
/* 23:23 */    this.significator.a = getMax().a;
/* 24:24 */    this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 25:25 */    this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/* 26:   */  }
/* 27:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.dockingBlock.DockingBlockUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */