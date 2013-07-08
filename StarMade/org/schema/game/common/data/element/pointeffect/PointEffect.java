package org.schema.game.common.data.element.pointeffect;

import class_69;
import class_79;
import class_80;
import java.util.Observable;
import org.schema.game.common.data.element.PointDistributionUnit;

public abstract class PointEffect
  extends Observable
  implements class_80
{
  public static final int DAMAGE_EFFECT_ID = 0;
  public static final int DISTANCE_EFFECT_ID = 1;
  public static final int RELOAD_EFFECT_ID = 2;
  public static final int SPEED_EFFECT_ID = 3;
  public static final int RADIUS_EFFECT_ID = 4;
  private int distribution = 0;
  protected PointDistributionUnit unit;
  private int pointsSpend;
  protected float value;
  private boolean recalc = true;
  
  public PointEffect(PointDistributionUnit paramPointDistributionUnit)
  {
    this.unit = paramPointDistributionUnit;
  }
  
  public void decreaseDist(int paramInt)
  {
    if (this.distribution > 0)
    {
      this.distribution = Math.max(this.distribution - paramInt, 0);
      this.unit.sendDistChange(this);
    }
  }
  
  public void flagRecalculate()
  {
    this.recalc = true;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    assert (((Integer)paramclass_69[0].a4()).intValue() == getPointsSpend());
    setDistribution(((Integer)paramclass_69[1].a4()).intValue());
  }
  
  public int getDistribution()
  {
    return this.distribution;
  }
  
  public abstract int getEffectId();
  
  public abstract String getName();
  
  public int getPointsSpend()
  {
    return this.pointsSpend;
  }
  
  public String getUniqueIdentifier()
  {
    return "effect" + getEffectId();
  }
  
  public float getValue()
  {
    if (this.recalc)
    {
      float f = this.value;
      recalculate();
      if (this.value != f)
      {
        setChanged();
        notifyObservers();
      }
      this.recalc = false;
    }
    return this.value;
  }
  
  public void increaseDist(int paramInt)
  {
    int i;
    if ((i = this.unit.getAvailableDist()) > 0)
    {
      if (paramInt > i) {
        paramInt = i;
      }
      this.distribution += paramInt;
      this.unit.sendDistChange(this);
    }
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  protected abstract void recalculate();
  
  public void reset()
  {
    setPointsSpend(0);
  }
  
  public void setDistribution(int paramInt)
  {
    this.distribution = paramInt;
  }
  
  public void setPointsSpend(int paramInt)
  {
    int i = this.pointsSpend;
    this.pointsSpend = paramInt;
    if (paramInt != i) {
      flagRecalculate();
    }
    setChanged();
    notifyObservers();
    assert (getPointsSpend() <= this.unit.getMaxPoints()) : (getPointsSpend() + " / " + this.unit.getMaxPoints() + " on " + this);
  }
  
  public String toString()
  {
    return getName() + "[" + this.distribution + "%](" + this.pointsSpend + ")";
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_551, "id", Integer.valueOf(getEffectId()));
    class_69 localclass_692 = new class_69(class_79.field_551, "dist", Integer.valueOf(getDistribution()));
    return new class_69(class_79.field_561, getClass().getSimpleName(), new class_69[] { localclass_691, localclass_692, new class_69(class_79.field_548, null, null) });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.PointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */