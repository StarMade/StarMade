package org.schema.game.common.controller.elements;

import org.schema.game.common.controller.elements.lift.LiftCollectionManager;
import q;

public abstract interface LiftContainerInterface
  extends PowerManagerInterface
{
  public abstract LiftCollectionManager getLiftManager();
  
  public abstract void handleClientRemoteLift(q paramq);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.LiftContainerInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */