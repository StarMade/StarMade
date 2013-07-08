/*   1:    */package org.schema.game.common.controller.elements.missile;
/*   2:    */
/*   3:    */import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;
/*   4:    */import org.schema.game.common.data.element.pointeffect.PointEffect;
/*   5:    */import org.schema.game.common.data.element.pointeffect.missile.MissileDamagePointEffect;
/*   6:    */import org.schema.game.common.data.element.pointeffect.missile.MissileDistancePointEffect;
/*   7:    */import org.schema.game.common.data.element.pointeffect.missile.MissileRadiusPointEffect;
/*   8:    */import org.schema.game.common.data.element.pointeffect.missile.MissileReloadPointEffect;
/*   9:    */import org.schema.game.common.data.element.pointeffect.missile.MissileSpeedPointEffect;
/*  10:    */import q;
/*  11:    */
/*  17:    */public class MissileUnit
/*  18:    */  extends PointDistributionCustomOutputUnit
/*  19:    */{
/*  20:    */  private static final int DAMAGE = 0;
/*  21:    */  private static final int DISTANCE = 1;
/*  22:    */  private static final int RELOAD = 2;
/*  23:    */  private static final int SPEED = 3;
/*  24:    */  private static final int RADIUS = 4;
/*  25:    */  private long lastShoot;
/*  26:    */  
/*  27:    */  public boolean canShoot()
/*  28:    */  {
/*  29: 29 */    if ((float)(System.currentTimeMillis() - getLastShoot()) > getRelaodTime()) {
/*  30: 30 */      return true;
/*  31:    */    }
/*  32: 32 */    return false;
/*  33:    */  }
/*  34:    */  
/*  38:    */  public int getBlastRadius()
/*  39:    */  {
/*  40: 40 */    return (int)getEffects()[4].getValue();
/*  41:    */  }
/*  42:    */  
/*  45:    */  public float getDamage()
/*  46:    */  {
/*  47: 47 */    return getEffects()[0].getValue();
/*  48:    */  }
/*  49:    */  
/*  50:    */  public float getDistance() {
/*  51: 51 */    return getEffects()[1].getValue();
/*  52:    */  }
/*  53:    */  
/*  57:    */  public long getLastShoot()
/*  58:    */  {
/*  59: 59 */    return this.lastShoot;
/*  60:    */  }
/*  61:    */  
/*  62: 62 */  public float getRelaodTime() { return getEffects()[2].getValue() * 1000.0F; }
/*  63:    */  
/*  64:    */  public q getSignificator()
/*  65:    */  {
/*  66: 66 */    return this.significator;
/*  67:    */  }
/*  68:    */  
/*  69: 69 */  public float getSpeed() { return getEffects()[3].getValue() * 2.0F; }
/*  70:    */  
/*  76: 76 */  private q significator = new q(-2147483648, -2147483648, -2147483648);
/*  77: 77 */  private q minSig = new q(0, 0, 0);
/*  78: 78 */  private q maxSig = new q(0, 0, 0);
/*  79:    */  
/*  86:    */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*  87:    */  {
/*  88: 88 */    if (paramInt1 > this.significator.a) {
/*  89: 89 */      this.minSig.b(paramInt1, paramInt2, paramInt3);
/*  90: 90 */      this.maxSig.b(paramInt1, paramInt2, paramInt3);
/*  91: 91 */      this.significator.a = getMax().a;
/*  92: 92 */    } else if (paramInt1 == this.significator.a) {
/*  93: 93 */      this.minSig.b(paramInt1, Math.min(paramInt2, this.significator.b), Math.min(paramInt3, this.significator.c));
/*  94: 94 */      this.maxSig.b(paramInt1, Math.max(paramInt2, this.significator.b), Math.max(paramInt3, this.significator.c));
/*  95:    */    }
/*  96:    */    
/*  98: 98 */    this.significator.b = (this.maxSig.b - (this.maxSig.b - this.minSig.b) / 2);
/*  99: 99 */    this.significator.c = (this.maxSig.c - (this.maxSig.c - this.minSig.c) / 2);
/* 100:    */  }
/* 101:    */  
/* 106:    */  public String toString()
/* 107:    */  {
/* 108:108 */    return "HeatMissileUnit [significator=" + this.significator + "]";
/* 109:    */  }
/* 110:    */  
/* 111:    */  public void updateLastShoot()
/* 112:    */  {
/* 113:113 */    this.lastShoot = System.currentTimeMillis();
/* 114:    */  }
/* 115:    */  
/* 117:    */  protected PointEffect[] initializeEffects()
/* 118:    */  {
/* 119:    */    PointEffect[] arrayOfPointEffect;
/* 120:120 */    (arrayOfPointEffect = new PointEffect[5])[0] = new MissileDamagePointEffect(this);
/* 121:121 */    arrayOfPointEffect[1] = new MissileDistancePointEffect(this);
/* 122:122 */    arrayOfPointEffect[2] = new MissileReloadPointEffect(this);
/* 123:123 */    arrayOfPointEffect[3] = new MissileSpeedPointEffect(this);
/* 124:124 */    arrayOfPointEffect[4] = new MissileRadiusPointEffect(this);
/* 125:125 */    return arrayOfPointEffect;
/* 126:    */  }
/* 127:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.MissileUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */