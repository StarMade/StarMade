package org.schema.game.common.controller.elements;

import class_48;
import org.schema.game.common.controller.elements.lift.LiftCollectionManager;

public abstract interface LiftContainerInterface
  extends PowerManagerInterface
{
  public abstract LiftCollectionManager getLiftManager();
  
  public abstract void handleClientRemoteLift(class_48 paramclass_48);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.LiftContainerInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */