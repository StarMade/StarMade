package org.schema.game.common.controller.elements.shield;

import class_48;
import org.schema.game.common.data.element.ElementCollection;

public class ShieldUnit
  extends ElementCollection
{
  boolean dirty;
  private int shields = 200;
  private long lastHit;
  class_48 significator = new class_48();
  
  public int getRechargeAmount()
  {
    synchronized (this)
    {
      if ((System.currentTimeMillis() - this.lastHit > 10000L) && (System.currentTimeMillis() - this.lastHit > 1000L))
      {
        float f = Math.max(1.0F, 1.6F);
        return (int)Math.min(Math.ceil(getShields() + f), 200.0D) - getShields();
      }
      this.dirty = true;
    }
    return 0;
  }
  
  public int getShields()
  {
    return this.shields;
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public void hit(int paramInt)
  {
    synchronized (this)
    {
      this.lastHit = System.currentTimeMillis();
      setShields(Math.max(0, getShields() - paramInt));
      this.dirty = true;
      return;
    }
  }
  
  public void incRecharge(int paramInt)
  {
    setShields(getShields() + paramInt);
  }
  
  public void setShields(int paramInt)
  {
    this.shields = paramInt;
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 <= this.significator.field_475) && (paramInt2 <= this.significator.field_476) && (paramInt3 < this.significator.field_477)) {
      this.significator.b(paramInt1, paramInt2, paramInt3);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.shield.ShieldUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */