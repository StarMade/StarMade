package org.schema.game.common.controller.elements;

import org.schema.game.common.controller.elements.door.DoorCollectionManager;
import q;

public abstract interface DoorContainerInterface
  extends PowerManagerInterface
{
  public abstract DoorCollectionManager getDoorManager();
  
  public abstract void handleClientRemoteDoor(q paramq);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.DoorContainerInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */