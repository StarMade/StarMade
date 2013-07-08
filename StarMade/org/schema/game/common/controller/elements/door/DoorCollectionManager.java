package org.schema.game.common.controller.elements.door;

import class_48;
import class_796;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.BlockActivationListenerInterface;
import org.schema.game.common.controller.elements.ElementCollectionManager;

public class DoorCollectionManager
  extends ElementCollectionManager
  implements BlockActivationListenerInterface
{
  public DoorCollectionManager(SegmentController paramSegmentController)
  {
    super((short)122, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  protected Class getType()
  {
    return DoorUnit.class;
  }
  
  public void onActivate(class_796 paramclass_796, boolean paramBoolean)
  {
    if (getSegmentController().isOnServer())
    {
      class_48 localclass_48 = paramclass_796.a2(new class_48());
      Iterator localIterator = getCollection().iterator();
      while (localIterator.hasNext())
      {
        DoorUnit localDoorUnit;
        if ((localDoorUnit = (DoorUnit)localIterator.next()).contains(localclass_48))
        {
          localDoorUnit.activate(paramclass_796, paramBoolean);
          return;
        }
      }
    }
  }
  
  protected void onChangedCollection() {}
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.door.DoorCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */