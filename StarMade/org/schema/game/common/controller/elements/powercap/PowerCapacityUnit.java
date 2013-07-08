/*  1:   */package org.schema.game.common.controller.elements.powercap;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementCollection;
/*  4:   */import q;
/*  5:   */
/*  8:   */public class PowerCapacityUnit
/*  9:   */  extends ElementCollection
/* 10:   */{
/* 11:11 */  private q significator = new q();
/* 12:   */  
/* 20:   */  public q getSignificator()
/* 21:   */  {
/* 22:22 */    return this.significator;
/* 23:   */  }
/* 24:   */  
/* 42:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 43:   */  {
/* 44:44 */    this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 45:45 */    this.significator.b = (getMax().b - (getMax().b - getMin().b) / 2);
/* 46:46 */    this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/* 47:   */  }
/* 48:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powercap.PowerCapacityUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */