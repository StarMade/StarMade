/*   1:    */package org.schema.game.common.controller.elements.weapon;
/*   2:    */
/*   3:    */import org.schema.game.common.data.element.PointDistributionCustomOutputUnit;
/*   4:    */import org.schema.game.common.data.element.pointeffect.DamagePointEffect;
/*   5:    */import org.schema.game.common.data.element.pointeffect.DistancePointEffect;
/*   6:    */import org.schema.game.common.data.element.pointeffect.PointEffect;
/*   7:    */import org.schema.game.common.data.element.pointeffect.ReloadPointEffect;
/*   8:    */import org.schema.game.common.data.element.pointeffect.SpeedPointEffect;
/*   9:    */import q;
/*  10:    */
/*  16:    */public class WeaponUnit
/*  17:    */  extends PointDistributionCustomOutputUnit
/*  18:    */{
/*  19:    */  private static final int DAMAGE = 0;
/*  20:    */  private static final int DISTANCE = 1;
/*  21:    */  private static final int RELOAD = 2;
/*  22:    */  private static final int SPEED = 3;
/*  23:    */  private long lastShoot;
/*  24:    */  
/*  25:    */  public boolean canShoot()
/*  26:    */  {
/*  27: 27 */    if ((float)(System.currentTimeMillis() - getLastShoot()) > getRelaodTime()) {
/*  28: 28 */      return true;
/*  29:    */    }
/*  30:    */    
/*  31: 31 */    return false;
/*  32:    */  }
/*  33:    */  
/*  35:    */  public float getDamage()
/*  36:    */  {
/*  37: 37 */    return getEffects()[0].getValue();
/*  38:    */  }
/*  39:    */  
/*  40:    */  public float getDistance()
/*  41:    */  {
/*  42: 42 */    return getEffects()[1].getValue();
/*  43:    */  }
/*  44:    */  
/*  47:    */  public long getLastShoot()
/*  48:    */  {
/*  49: 49 */    return this.lastShoot;
/*  50:    */  }
/*  51:    */  
/*  54:    */  public float getPowerConsumed()
/*  55:    */  {
/*  56: 56 */    return size() * 10.0F;
/*  57:    */  }
/*  58:    */  
/*  59:    */  public float getRelaodTime()
/*  60:    */  {
/*  61: 61 */    return getEffects()[2].getValue();
/*  62:    */  }
/*  63:    */  
/*  64:    */  public q getSignificator() {
/*  65: 65 */    return this.significator;
/*  66:    */  }
/*  67:    */  
/*  68: 68 */  public float getSpeed() { return getEffects()[3].getValue(); }
/*  69:    */  
/*  70:    */  protected PointEffect[] initializeEffects()
/*  71:    */  {
/*  72:    */    PointEffect[] arrayOfPointEffect;
/*  73: 73 */    (arrayOfPointEffect = new PointEffect[4])[0] = new DamagePointEffect(this);
/*  74: 74 */    arrayOfPointEffect[1] = new DistancePointEffect(this);
/*  75: 75 */    arrayOfPointEffect[2] = new ReloadPointEffect(this);
/*  76: 76 */    arrayOfPointEffect[3] = new SpeedPointEffect(this);
/*  77: 77 */    return arrayOfPointEffect;
/*  78:    */  }
/*  79:    */  
/*  87: 87 */  private final q significator = new q(-2147483648, -2147483648, -2147483648);
/*  88:    */  
/*  95:    */  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*  96:    */  {
/*  97: 97 */    if (paramInt3 > this.significator.c) {
/*  98: 98 */      this.significator.b(paramInt1, paramInt2, paramInt3);
/*  99:    */    }
/* 100:    */  }
/* 101:    */  
/* 107:    */  public String toString()
/* 108:    */  {
/* 109:109 */    return "WeaponUnit [significator=" + this.significator + "]";
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void updateLastShoot()
/* 113:    */  {
/* 114:114 */    this.lastShoot = System.currentTimeMillis();
/* 115:    */  }
/* 116:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.weapon.WeaponUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */