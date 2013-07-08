package org.schema.game.common.controller.elements;

import class_48;
import class_941;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.world.Segment;

public class ManagerModuleSingle
  extends ManagerModuleControllable
{
  private final UsableControllableSingleElementManager elementManager;
  
  public ManagerModuleSingle(UsableControllableSingleElementManager paramUsableControllableSingleElementManager, short paramShort1, short paramShort2)
  {
    super(paramUsableControllableSingleElementManager, paramShort2, paramShort1);
    this.elementManager = paramUsableControllableSingleElementManager;
  }
  
  public void addControlledBlock(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    getCollectionManager().addModded(paramclass_482);
    this.elementManager.onControllerChange();
  }
  
  public void addElement(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    getCollectionManager().addModded(ElementCollection.getIndex(paramByte1, paramByte2, paramByte3, paramSegment));
  }
  
  public void clear()
  {
    getCollectionManager().clear();
  }
  
  public ElementCollectionManager getCollectionManager()
  {
    return getElementManager().getCollection();
  }
  
  public UsableControllableSingleElementManager getElementManager()
  {
    return this.elementManager;
  }
  
  public void removeControllerBlock(class_48 paramclass_481, class_48 paramclass_482, short paramShort)
  {
    getCollectionManager().remove(paramclass_482);
    this.elementManager.onControllerChange();
  }
  
  public void removeElement(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    getCollectionManager().remove(ElementCollection.getIndex(paramByte1, paramByte2, paramByte3, paramSegment));
  }
  
  public void update(class_941 paramclass_941, long paramLong)
  {
    ElementCollectionManager localElementCollectionManager = this.elementManager.getCollection();
    assert (localElementCollectionManager != null);
    localElementCollectionManager.updateStructure(paramLong);
    if (localElementCollectionManager.needsUpdate()) {
      localElementCollectionManager.update(paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerModuleSingle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */