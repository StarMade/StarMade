/*  1:   */package org.schema.game.common.controller.elements.shield;
/*  2:   */
/*  3:   */import org.schema.game.common.data.element.ElementCollection;
/*  4:   */import q;
/*  5:   */
/*  6:   */public class ShieldUnit
/*  7:   */  extends ElementCollection
/*  8:   */{
/*  9:   */  boolean dirty;
/* 10:10 */  private int shields = 200;
/* 11:   */  private long lastHit;
/* 12:12 */  q significator = new q();
/* 13:   */  
/* 14:   */  public int getRechargeAmount() {
/* 15:15 */    synchronized (this) {
/* 16:16 */      if ((System.currentTimeMillis() - this.lastHit > 10000L) && 
/* 17:17 */        (System.currentTimeMillis() - this.lastHit > 1000L))
/* 18:   */      {
/* 19:19 */        float f = Math.max(1.0F, 1.6F);
/* 20:   */        
/* 24:24 */        return (int)Math.min(Math.ceil(getShields() + f), 200.0D) - getShields();
/* 25:   */      }
/* 26:   */      
/* 27:27 */      this.dirty = true;
/* 28:   */    }
/* 29:29 */    return 0;
/* 30:   */  }
/* 31:   */  
/* 33:   */  public int getShields()
/* 34:   */  {
/* 35:35 */    return this.shields;
/* 36:   */  }
/* 37:   */  
/* 39:   */  public q getSignificator()
/* 40:   */  {
/* 41:41 */    return this.significator;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public void hit(int paramInt) {
/* 45:45 */    synchronized (this) {
/* 46:46 */      this.lastHit = System.currentTimeMillis();
/* 47:47 */      setShields(Math.max(0, getShields() - paramInt));
/* 48:   */      
/* 50:50 */      this.dirty = true;
/* 51:51 */      return;
/* 52:   */    }
/* 53:   */  }
/* 54:   */  
/* 55:55 */  public void incRecharge(int paramInt) { setShields(getShields() + paramInt); }
/* 56:   */  
/* 60:   */  public void setShields(int paramInt)
/* 61:   */  {
/* 62:62 */    this.shields = paramInt;
/* 63:   */  }
/* 64:   */  
/* 70:   */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/* 71:   */  {
/* 72:72 */    if ((paramInt1 <= this.significator.a) && (paramInt2 <= this.significator.b) && (paramInt3 < this.significator.c)) {
/* 73:73 */      this.significator.b(paramInt1, paramInt2, paramInt3);
/* 74:   */    }
/* 75:   */  }
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.shield.ShieldUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */