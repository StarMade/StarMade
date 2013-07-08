package org.schema.game.common.controller.elements.powercap;

import class_48;
import org.schema.game.common.data.element.ElementCollection;

public class PowerCapacityUnit
  extends ElementCollection
{
  private class_48 significator = new class_48();
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    this.significator.field_475 = (getMax().field_475 - (getMax().field_475 - getMin().field_475) / 2);
    this.significator.field_476 = (getMax().field_476 - (getMax().field_476 - getMin().field_476) / 2);
    this.significator.field_477 = (getMax().field_477 - (getMax().field_477 - getMin().field_477) / 2);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.powercap.PowerCapacityUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */