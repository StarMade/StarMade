package org.schema.game.common.controller.elements;

import org.schema.game.common.controller.elements.power.PowerCollectionManager;
import org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager;

public abstract interface PowerManagerInterface
{
  public abstract PowerCollectionManager getPowerManager();
  
  public abstract PowerCapacityCollectionManager getPowerCapacityManager();
  
  public abstract PowerAddOn getPowerAddOn();
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.PowerManagerInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */