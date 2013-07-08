package org.schema.game.common.controller.elements.explosive;

import class_48;
import org.schema.game.common.data.element.ElementCollection;

public class ExplosiveUnit
  extends ElementCollection
{
  class_48 significator = new class_48();
  float explosive;
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public void refreshExplosiveCapabilities()
  {
    this.explosive = Math.max(0.0F, getMax().field_477 - getMin().field_477);
    this.explosive += Math.max(0.0F, getMax().field_475 - getMin().field_475);
    this.explosive += Math.max(0.0F, getMax().field_476 - getMin().field_476);
    float f = (float)Math.pow(size(), 1.25D);
    this.explosive += f;
    this.explosive = Math.max(1.0F, this.explosive);
  }
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 <= this.significator.field_475) && (paramInt2 <= this.significator.field_476) && (paramInt3 < this.significator.field_477)) {
      this.significator.b(paramInt1, paramInt2, paramInt3);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */