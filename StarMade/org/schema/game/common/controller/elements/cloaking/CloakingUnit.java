/*  1:   */package org.schema.game.common.controller.elements.cloaking;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementCollection;
/*  4:   */import q;
/*  5:   */
/*  7:   */public class CloakingUnit
/*  8:   */  extends ElementCollection
/*  9:   */{
/* 10:10 */  q significator = new q();
/* 11:   */  
/* 12:   */  float cloak;
/* 13:   */  
/* 15:   */  public q getSignificator()
/* 16:   */  {
/* 17:17 */    return this.significator;
/* 18:   */  }
/* 19:   */  
/* 20:   */  public void refreshCloakingCapabilities() {
/* 21:21 */    this.cloak = Math.max(0.0F, getMax().c - getMin().c);
/* 22:22 */    this.cloak += Math.max(0.0F, getMax().a - getMin().a);
/* 23:23 */    this.cloak += Math.max(0.0F, getMax().b - getMin().b);
/* 24:24 */    float f = (float)Math.pow(size(), 1.25D);
/* 25:   */    
/* 26:26 */    this.cloak += f;
/* 27:27 */    this.cloak = Math.max(1.0F, this.cloak);
/* 28:   */  }
/* 29:   */  
/* 35:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 36:   */  {
/* 37:37 */    if ((paramInt1 <= this.significator.a) && 
/* 38:38 */      (paramInt2 <= this.significator.b) && 
/* 39:39 */      (paramInt3 < this.significator.c)) {
/* 40:40 */      this.significator.b(paramInt1, paramInt2, paramInt3);
/* 41:   */    }
/* 42:   */  }
/* 43:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.cloaking.CloakingUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */