/*  1:   */package org.schema.game.common.controller.elements.jamming;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementCollection;
/*  4:   */import q;
/*  5:   */
/*  6:   */public class JammingUnit
/*  7:   */  extends ElementCollection
/*  8:   */{
/*  9: 9 */  q significator = new q();
/* 10:   */  
/* 11:   */  float jam;
/* 12:   */  
/* 13:   */  public q getSignificator()
/* 14:   */  {
/* 15:15 */    return this.significator;
/* 16:   */  }
/* 17:   */  
/* 18:   */  public void refreshJammingCapabilities() {
/* 19:19 */    this.jam = Math.max(0.0F, getMax().c - getMin().c);
/* 20:20 */    this.jam += Math.max(0.0F, getMax().a - getMin().a);
/* 21:21 */    this.jam += Math.max(0.0F, getMax().b - getMin().b);
/* 22:22 */    float f = (float)Math.pow(size(), 1.25D);
/* 23:   */    
/* 24:24 */    this.jam += f;
/* 25:25 */    this.jam = Math.max(1.0F, this.jam);
/* 26:   */  }
/* 27:   */  
/* 33:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 34:   */  {
/* 35:35 */    if ((paramInt1 <= this.significator.a) && 
/* 36:36 */      (paramInt2 <= this.significator.b) && 
/* 37:37 */      (paramInt3 < this.significator.c)) {
/* 38:38 */      this.significator.b(paramInt1, paramInt2, paramInt3);
/* 39:   */    }
/* 40:   */  }
/* 41:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.jamming.JammingUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */