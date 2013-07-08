package org.schema.game.common.controller.elements.weapon;

import class_48;
import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;
import org.schema.game.common.data.element.pointeffect.DamagePointEffect;
import org.schema.game.common.data.element.pointeffect.DistancePointEffect;
import org.schema.game.common.data.element.pointeffect.PointEffect;
import org.schema.game.common.data.element.pointeffect.ReloadPointEffect;
import org.schema.game.common.data.element.pointeffect.SpeedPointEffect;

public class WeaponUnit
  extends PointDistributionCustomOutputUnit
{
  private static final int DAMAGE = 0;
  private static final int DISTANCE = 1;
  private static final int RELOAD = 2;
  private static final int SPEED = 3;
  private long lastShoot;
  private final class_48 significator = new class_48(-2147483648, -2147483648, -2147483648);
  
  public boolean canShoot()
  {
    return (float)(System.currentTimeMillis() - getLastShoot()) > getRelaodTime();
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
  
  public float getPowerConsumed()
  {
    return size() * 10.0F;
  }
  
  public float getRelaodTime()
  {
    return getEffects()[2].getValue();
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public float getSpeed()
  {
    return getEffects()[3].getValue();
  }
  
  protected PointEffect[] initializeEffects()
  {
    PointEffect[] arrayOfPointEffect;
    (arrayOfPointEffect = new PointEffect[4])[0] = new DamagePointEffect(this);
    arrayOfPointEffect[1] = new DistancePointEffect(this);
    arrayOfPointEffect[2] = new ReloadPointEffect(this);
    arrayOfPointEffect[3] = new SpeedPointEffect(this);
    return arrayOfPointEffect;
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 > this.significator.field_477) {
      this.significator.b(paramInt1, paramInt2, paramInt3);
    }
  }
  
  public String toString()
  {
    return "WeaponUnit [significator=" + this.significator + "]";
  }
  
  public void updateLastShoot()
  {
    this.lastShoot = System.currentTimeMillis();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.weapon.WeaponUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */