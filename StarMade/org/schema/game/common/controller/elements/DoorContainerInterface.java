package org.schema.game.common.controller.elements;

import class_48;
import org.schema.game.common.controller.elements.door.DoorCollectionManager;

public abstract interface DoorContainerInterface
  extends PowerManagerInterface
{
  public abstract DoorCollectionManager getDoorManager();
  
  public abstract void handleClientRemoteDoor(class_48 paramclass_48);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.DoorContainerInterface
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */