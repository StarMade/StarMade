/*   1:    */package org.schema.game.common.data.element.pointeffect;
/*   2:    */
/*   3:    */import Ah;
/*   4:    */import Aj;
/*   5:    */import Ak;
/*   6:    */import java.util.Observable;
/*   7:    */import org.schema.game.common.data.element.PointDistributionUnit;
/*   8:    */
/*  10:    */public abstract class PointEffect
/*  11:    */  extends Observable
/*  12:    */  implements Ak
/*  13:    */{
/*  14:    */  public static final int DAMAGE_EFFECT_ID = 0;
/*  15:    */  public static final int DISTANCE_EFFECT_ID = 1;
/*  16:    */  public static final int RELOAD_EFFECT_ID = 2;
/*  17:    */  public static final int SPEED_EFFECT_ID = 3;
/*  18:    */  public static final int RADIUS_EFFECT_ID = 4;
/*  19: 19 */  private int distribution = 0;
/*  20:    */  protected PointDistributionUnit unit;
/*  21:    */  private int pointsSpend;
/*  22:    */  protected float value;
/*  23: 23 */  private boolean recalc = true;
/*  24:    */  
/*  25: 25 */  public PointEffect(PointDistributionUnit paramPointDistributionUnit) { this.unit = paramPointDistributionUnit; }
/*  26:    */  
/*  27:    */  public void decreaseDist(int paramInt)
/*  28:    */  {
/*  29: 29 */    if (this.distribution > 0) {
/*  30: 30 */      this.distribution = Math.max(this.distribution - paramInt, 0);
/*  31: 31 */      this.unit.sendDistChange(this);
/*  32:    */    }
/*  33:    */  }
/*  34:    */  
/*  35:    */  public void flagRecalculate() {
/*  36: 36 */    this.recalc = true;
/*  37:    */  }
/*  38:    */  
/*  42:    */  public void fromTagStructure(Ah paramAh)
/*  43:    */  {
/*  44: 44 */    paramAh = (Ah[])paramAh.a();
/*  45: 45 */    assert (((Integer)paramAh[0].a()).intValue() == getPointsSpend());
/*  46: 46 */    setDistribution(((Integer)paramAh[1].a()).intValue());
/*  47:    */  }
/*  48:    */  
/*  53:    */  public int getDistribution()
/*  54:    */  {
/*  55: 55 */    return this.distribution;
/*  56:    */  }
/*  57:    */  
/*  59:    */  public abstract int getEffectId();
/*  60:    */  
/*  62:    */  public abstract String getName();
/*  63:    */  
/*  64:    */  public int getPointsSpend()
/*  65:    */  {
/*  66: 66 */    return this.pointsSpend;
/*  67:    */  }
/*  68:    */  
/*  72:    */  public String getUniqueIdentifier()
/*  73:    */  {
/*  74: 74 */    return "effect" + getEffectId();
/*  75:    */  }
/*  76:    */  
/*  77:    */  public float getValue()
/*  78:    */  {
/*  79: 79 */    if (this.recalc) {
/*  80: 80 */      float f = this.value;
/*  81: 81 */      recalculate();
/*  82: 82 */      if (this.value != f) {
/*  83: 83 */        setChanged();
/*  84: 84 */        notifyObservers();
/*  85:    */      }
/*  86:    */      
/*  87: 87 */      this.recalc = false;
/*  88:    */    }
/*  89: 89 */    return this.value;
/*  90:    */  }
/*  91:    */  
/*  93:    */  public void increaseDist(int paramInt)
/*  94:    */  {
/*  95:    */    int i;
/*  96: 96 */    if ((i = this.unit.getAvailableDist()) > 0) {
/*  97: 97 */      if (paramInt > i) {
/*  98: 98 */        paramInt = i;
/*  99:    */      }
/* 100:100 */      this.distribution += paramInt;
/* 101:101 */      this.unit.sendDistChange(this);
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 108:    */  public boolean isVolatile()
/* 109:    */  {
/* 110:110 */    return false;
/* 111:    */  }
/* 112:    */  
/* 113:    */  protected abstract void recalculate();
/* 114:    */  
/* 115:115 */  public void reset() { setPointsSpend(0); }
/* 116:    */  
/* 120:    */  public void setDistribution(int paramInt)
/* 121:    */  {
/* 122:122 */    this.distribution = paramInt;
/* 123:    */  }
/* 124:    */  
/* 128:    */  public void setPointsSpend(int paramInt)
/* 129:    */  {
/* 130:130 */    int i = this.pointsSpend;
/* 131:131 */    this.pointsSpend = paramInt;
/* 132:132 */    if (paramInt != i) {
/* 133:133 */      flagRecalculate();
/* 134:    */    }
/* 135:135 */    setChanged();
/* 136:136 */    notifyObservers();
/* 137:    */    
/* 138:138 */    assert (getPointsSpend() <= this.unit.getMaxPoints()) : (getPointsSpend() + " / " + this.unit.getMaxPoints() + " on " + this);
/* 139:    */  }
/* 140:    */  
/* 141:    */  public String toString()
/* 142:    */  {
/* 143:143 */    return getName() + "[" + this.distribution + "%](" + this.pointsSpend + ")";
/* 144:    */  }
/* 145:    */  
/* 149:    */  public Ah toTagStructure()
/* 150:    */  {
/* 151:151 */    Ah localAh1 = new Ah(Aj.d, "id", Integer.valueOf(getEffectId()));
/* 152:152 */    Ah localAh2 = new Ah(Aj.d, "dist", Integer.valueOf(getDistribution()));
/* 153:153 */    return new Ah(Aj.n, getClass().getSimpleName(), new Ah[] { localAh1, localAh2, new Ah(Aj.a, null, null) });
/* 154:    */  }
/* 155:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.pointeffect.PointEffect
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */