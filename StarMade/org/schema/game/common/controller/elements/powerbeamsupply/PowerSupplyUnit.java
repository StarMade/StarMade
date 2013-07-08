/*  1:   */package org.schema.game.common.controller.elements.powerbeamsupply;
/*  2:   */
/*  3:   */import org.schema.common.FastMath;
/*  4:   */import org.schema.game.common.data.element.CustomOutputUnit;
/*  5:   */import q;
/*  6:   */
/*  9:   */public class PowerSupplyUnit
/* 10:   */  extends CustomOutputUnit
/* 11:   */{
/* 12:   */  private float drainSupplyFactor;
/* 13:   */  
/* 14:   */  public float getSupplySpeedFactor()
/* 15:   */  {
/* 16:16 */    return this.drainSupplyFactor;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public q getSignificator()
/* 20:   */  {
/* 21:21 */    return this.significator;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public void refreshSupplyCapabilities() {
/* 25:25 */    this.drainSupplyFactor = (7.0F / FastMath.b(Math.max(0.0F, size()), 1.2F));
/* 26:   */  }
/* 27:   */  
/* 28:28 */  private q significator = new q(-2147483648, -2147483648, -2147483648);
/* 29:   */  
/* 37:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 38:   */  {
/* 39:39 */    if (paramInt3 > this.significator.c) {
/* 40:40 */      this.significator.b(paramInt1, paramInt2, paramInt3);
/* 41:   */    }
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */