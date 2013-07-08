package org.schema.game.common.controller.elements.shield;

import class_371;
import class_48;
import class_52;
import class_798;
import class_941;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.HittableInterface;
import org.schema.game.common.controller.elements.NTReceiveInterface;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.schine.network.objects.NetworkObject;

public class ShieldCollectionManager
  extends ElementCollectionManager
  implements HittableInterface, NTReceiveInterface
{
  static final int SHIELD_RECHARGE_DELAY = 1000;
  static final int SHIELD_RECHARGE_AFTER_HIT = 10000;
  public static final int MAX_SHIELDS_UNIT = 200;
  public static final int RECHARGE_RATE = 2;
  private int shieldMargin = 3;
  private double shields;
  class_48 posTmp = new class_48();
  private long shieldCapacityHP;
  private long shieldRechargeRate;
  private float accPower;
  private double recovery;
  private int RECOVERY_TIME_IN_SEC = 10;
  private float POWER_CONSUME_MULT = 1.0F;
  private double initialShields;
  
  public ShieldCollectionManager(SegmentController paramSegmentController)
  {
    super((short)3, paramSegmentController);
  }
  
  public int getMargin()
  {
    return this.shieldMargin;
  }
  
  protected Class getType()
  {
    return ShieldUnit.class;
  }
  
  protected void onChangedCollection()
  {
    updateCapabilities();
  }
  
  private void updateCapabilities()
  {
    this.shieldCapacityHP = 0L;
    this.shieldRechargeRate = 0L;
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      ShieldUnit localShieldUnit = (ShieldUnit)localIterator.next();
      this.shieldCapacityHP += localShieldUnit.size() * 200;
      this.shieldRechargeRate += (localShieldUnit.size() << 1);
    }
    this.shieldCapacityHP = ((Math.pow(this.shieldCapacityHP, 0.5D) * 64.0D));
    this.shields = Math.min(this.shields, this.shieldCapacityHP);
  }
  
  public void onHit(int paramInt)
  {
    this.shields = Math.max(0.0D, this.shields - paramInt);
    if (this.shields == 0.0D)
    {
      this.recovery = this.RECOVERY_TIME_IN_SEC;
      if (getSegmentController().isClientOwnObject()) {
        ((class_371)getSegmentController().getState()).a4().b1("   !!!WARNING!!!\n\nShields DOWN\n(" + this.RECOVERY_TIME_IN_SEC + " sec recovery)");
      }
    }
  }
  
  public void update(class_941 paramclass_941)
  {
    if (getCollection().isEmpty()) {
      return;
    }
    if (this.shields >= this.shieldCapacityHP) {
      return;
    }
    if (getInitialShields() > 0.0D)
    {
      this.shields = getInitialShields();
      setInitialShields(0.0D);
    }
    if (this.recovery > 0.0D)
    {
      this.recovery -= paramclass_941.a();
      return;
    }
    PowerAddOn localPowerAddOn = ((ShieldContainerInterface)((class_798)getSegmentController()).a()).getPowerAddOn();
    this.accPower += paramclass_941.a();
    if (this.accPower > 1.0F)
    {
      if (localPowerAddOn.getPower() < this.shieldRechargeRate)
      {
        double d = localPowerAddOn.getPower();
        localPowerAddOn.consumePowerInstantly(d);
        this.shields = ((int)Math.min(this.shieldCapacityHP, this.shields + d));
      }
      else if (localPowerAddOn.consumePowerInstantly(this.shieldRechargeRate))
      {
        this.shields = ((int)Math.min(this.shieldCapacityHP, this.shields + this.shieldRechargeRate));
      }
      this.accPower -= 1.0F;
    }
  }
  
  public boolean needsUpdate()
  {
    return true;
  }
  
  public void updateFromNT(NetworkObject paramNetworkObject) {}
  
  public double getShields()
  {
    return this.shields;
  }
  
  public long getShieldCapabilityHP()
  {
    return this.shieldCapacityHP;
  }
  
  public long getShieldRechargeRate()
  {
    return this.shieldRechargeRate;
  }
  
  public void setInitialShields(double paramDouble)
  {
    this.initialShields = paramDouble;
  }
  
  public double getInitialShields()
  {
    return this.initialShields;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.shield.ShieldCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */