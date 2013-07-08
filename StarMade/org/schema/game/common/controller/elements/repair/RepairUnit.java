package org.schema.game.common.controller.elements.repair;

import class_48;
import org.schema.common.FastMath;
import org.schema.game.common.data.element.CustomOutputUnit;

public class RepairUnit
  extends CustomOutputUnit
{
  private float repairSpeedFactor;
  private class_48 significator = new class_48(-2147483648, -2147483648, -2147483648);
  private class_48 minSig = new class_48(0, 0, 0);
  private class_48 maxSig = new class_48(0, 0, 0);
  
  public float getRepairSpeedFactor()
  {
    return this.repairSpeedFactor;
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public void refreshHarvestCapabilities()
  {
    this.repairSpeedFactor = (3.0F / FastMath.b2(Math.max(0.0F, size()), 1.13F));
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */