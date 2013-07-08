/*  1:   */package org.schema.game.common.controller.elements.repair;
/*  2:   */
/*  3:   */import org.schema.common.FastMath;
/*  4:   */import org.schema.game.common.data.element.CustomOutputUnit;
/*  5:   */import q;
/*  6:   */
/*  9:   */public class RepairUnit
/* 10:   */  extends CustomOutputUnit
/* 11:   */{
/* 12:   */  private float repairSpeedFactor;
/* 13:   */  
/* 14:   */  public float getRepairSpeedFactor()
/* 15:   */  {
/* 16:16 */    return this.repairSpeedFactor;
/* 17:   */  }
/* 18:   */  
/* 19:   */  public q getSignificator()
/* 20:   */  {
/* 21:21 */    return this.significator;
/* 22:   */  }
/* 23:   */  
/* 24:   */  public void refreshHarvestCapabilities() {
/* 25:25 */    this.repairSpeedFactor = (3.0F / FastMath.b(Math.max(0.0F, size()), 1.13F));
/* 26:   */  }
/* 27:   */  
/* 28:28 */  private q significator = new q(-2147483648, -2147483648, -2147483648);
/* 29:29 */  private q minSig = new q(0, 0, 0);
/* 30:30 */  private q maxSig = new q(0, 0, 0);
/* 31:   */  
/* 38:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 39:   */  {
/* 40:40 */    if (paramInt1 > this.significator.a) {
/* 41:41 */      this.minSig.b(paramInt1, paramInt2, paramInt3);
/* 42:42 */      this.maxSig.b(paramInt1, paramInt2, paramInt3);
/* 43:43 */      this.significator.a = getMax().a;
/* 44:44 */    } else if (paramInt1 == this.significator.a) {
/* 45:45 */      this.minSig.b(paramInt1, Math.min(paramInt2, this.significator.b), Math.min(paramInt3, this.significator.c));
/* 46:46 */      this.maxSig.b(paramInt1, Math.max(paramInt2, this.significator.b), Math.max(paramInt3, this.significator.c));
/* 47:   */    }
/* 48:   */    
/* 50:50 */    this.significator.b = (this.maxSig.b - (this.maxSig.b - this.minSig.b) / 2);
/* 51:51 */    this.significator.c = (this.maxSig.c - (this.maxSig.c - this.minSig.c) / 2);
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */