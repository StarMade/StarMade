package org.schema.game.common.controller.elements;

import class_48;
import class_796;
import class_941;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.world.Segment;

public class ManagerModuleCollection
  extends ManagerModuleControllable
{
  private final UsableControllableElementManager elementManager;
  private final class_48 tmpAbsPos = new class_48();
  private final ObjectArrayFIFOQueue toAddControllers = new ObjectArrayFIFOQueue();
  
  public ManagerModuleCollection(UsableControllableElementManager paramUsableControllableElementManager, short paramShort1, short paramShort2)
  {
    super(paramUsableControllableElementManager, paramShort2, paramShort1);
    this.elementManager = paramUsableControllableElementManager;
  }
  
  public void addControlledBlock(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    this.elementManager.addControllerBlockIfNecessary(paramclass_481, paramclass_482, paramShort);
  }
  
  private void doControllerBlockAdds()
  {
    if (!this.toAddControllers.isEmpty()) {
      synchronized (this.toAddControllers)
      {
        while (!this.toAddControllers.isEmpty())
        {
          class_796 localclass_796;
          (localclass_796 = (class_796)this.toAddControllers.dequeue()).a2(this.tmpAbsPos);
          Object localObject2 = this.elementManager.getCollectionManagers().iterator();
          while (((Iterator)localObject2).hasNext()) {
            if (((ControlBlockElementCollectionManager)((Iterator)localObject2).next()).getControllerPos().equals(this.tmpAbsPos)) {
              return;
            }
          }
          if (((localObject2 = this.elementManager.getNewCollectionManager(localclass_796)) instanceof ControlBlockElementCollectionManager)) {
            ((ControlBlockElementCollectionManager)localObject2).refreshControlled(localclass_796.a7().a15().getControlElementMap());
          }
          getCollectionManagers().add(localObject2);
          this.elementManager.onControllerBlockAdd();
          ((ElementCollectionManager)localObject2).pieceRefresh();
        }
        return;
      }
    }
  }
  
  public void addControllerBlock(byte paramByte1, byte arg2, byte paramByte3, Segment paramSegment)
  {
    paramByte1 = new class_796(paramSegment, paramByte1, ???, paramByte3);
    synchronized (this.toAddControllers)
    {
      this.toAddControllers.enqueue(paramByte1);
      return;
    }
  }
  
  public void clear()
  {
    for (int i = 0; i < this.elementManager.getCollectionManagers().size(); i++) {
      ((ElementCollectionManager)this.elementManager.getCollectionManagers().get(i)).clear();
    }
    this.elementManager.getCollectionManagers().clear();
  }
  
  public List getCollectionManagers()
  {
    return getElementManager().getCollectionManagers();
  }
  
  public UsableControllableElementManager getElementManager()
  {
    return this.elementManager;
  }
  
  public void removeController(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    paramSegment.a13(paramByte1, paramByte2, paramByte3, this.tmpAbsPos);
    paramByte1 = this.elementManager.getCollectionManagers().iterator();
    while (paramByte1.hasNext()) {
      if ((paramByte2 = (ControlBlockElementCollectionManager)paramByte1.next()).getControllerPos().equals(this.tmpAbsPos))
      {
        paramByte2.stopUpdate();
        this.elementManager.getCollectionManagers().remove(paramByte2);
        return;
      }
    }
  }
  
  public void removeControllerBlock(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    this.elementManager.removeControllerIfNecessary(paramclass_481, paramclass_482, paramShort);
  }
  
  public void update(class_941 paramclass_941, long paramLong)
  {
    doControllerBlockAdds();
    int i = this.elementManager.getCollectionManagers().size();
    for (int j = 0; j < i; j++)
    {
      ElementCollectionManager localElementCollectionManager;
      (localElementCollectionManager = (ElementCollectionManager)this.elementManager.getCollectionManagers().get(j)).updateStructure(paramLong);
      if (localElementCollectionManager.needsUpdate()) {
        localElementCollectionManager.update(paramclass_941);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */