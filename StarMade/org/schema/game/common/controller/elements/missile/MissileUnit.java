package org.schema.game.common.controller.elements.missile;

import class_48;
import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;
import org.schema.game.common.data.element.pointeffect.PointEffect;
import org.schema.game.common.data.element.pointeffect.missile.MissileDamagePointEffect;
import org.schema.game.common.data.element.pointeffect.missile.MissileDistancePointEffect;
import org.schema.game.common.data.element.pointeffect.missile.MissileRadiusPointEffect;
import org.schema.game.common.data.element.pointeffect.missile.MissileReloadPointEffect;
import org.schema.game.common.data.element.pointeffect.missile.MissileSpeedPointEffect;

public class MissileUnit
  extends PointDistributionCustomOutputUnit
{
  private static final int DAMAGE = 0;
  private static final int DISTANCE = 1;
  private static final int RELOAD = 2;
  private static final int SPEED = 3;
  private static final int RADIUS = 4;
  private long lastShoot;
  private class_48 significator = new class_48(-2147483648, -2147483648, -2147483648);
  private class_48 minSig = new class_48(0, 0, 0);
  private class_48 maxSig = new class_48(0, 0, 0);
  
  public boolean canShoot()
  {
    return (float)(System.currentTimeMillis() - getLastShoot()) > getRelaodTime();
  }
  
  public int getBlastRadius()
  {
    return (int)getEffects()[4].getValue();
  }
  
  public float getDamage()
  {
    return getEffects()[0].getValue();
  }
  
  public float getDistance()
  {
    return getEffects()[1].getValue();
  }
  
  public long getLastShoot()
  {
    return this.lastShoot;
  }
  
  public float getRelaodTime()
  {
    return getEffects()[2].getValue() * 1000.0F;
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public float getSpeed()
  {
    return getEffects()[3].getValue() * 2.0F;
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 > this.significator.field_475)
    {
      this.minSig.b(paramInt1, paramInt2, paramInt3);
      this.maxSig.b(paramInt1, paramInt2, paramInt3);
      this.significator.field_475 = getMax().field_475;
    }
    else if (paramInt1 == this.significator.field_475)
    {
      this.minSig.b(paramInt1, Math.min(paramInt2, this.significator.field_476), Math.min(paramInt3, this.significator.field_477));
      this.maxSig.b(paramInt1, Math.max(paramInt2, this.significator.field_476), Math.max(paramInt3, this.significator.field_477));
    }
    this.significator.field_476 = (this.maxSig.field_476 - (this.maxSig.field_476 - this.minSig.field_476) / 2);
    this.significator.field_477 = (this.maxSig.field_477 - (this.maxSig.field_477 - this.minSig.field_477) / 2);
  }
  
  public String toString()
  {
    return "HeatMissileUnit [significator=" + this.significator + "]";
  }
  
  public void updateLastShoot()
  {
    this.lastShoot = System.currentTimeMillis();
  }
  
  protected PointEffect[] initializeEffects()
  {
    PointEffect[] arrayOfPointEffect;
    (arrayOfPointEffect = new PointEffect[5])[0] = new MissileDamagePointEffect(this);
    arrayOfPointEffect[1] = new MissileDistancePointEffect(this);
    arrayOfPointEffect[2] = new MissileReloadPointEffect(this);
    arrayOfPointEffect[3] = new MissileSpeedPointEffect(this);
    arrayOfPointEffect[4] = new MissileRadiusPointEffect(this);
    return arrayOfPointEffect;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */