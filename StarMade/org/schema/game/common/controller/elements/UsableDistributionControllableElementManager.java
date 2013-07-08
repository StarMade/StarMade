package org.schema.game.common.controller.elements;

import class_69;
import class_79;
import java.util.ArrayList;
import org.schema.game.common.controller.SegmentController;

public abstract class UsableDistributionControllableElementManager
  extends UsableControllableElementManager
{
  public UsableDistributionControllableElementManager(short paramShort1, short paramShort2, SegmentController paramSegmentController)
  {
    super(paramShort1, paramShort2, paramSegmentController);
  }
  
  public class_69 toDistributionTagStructure()
  {
    class_69[] arrayOfclass_69 = new class_69[getCollectionManagers().size() + 1];
    for (int i = 0; i < getCollectionManagers().size(); i++) {
      arrayOfclass_69[i] = ((DistributionCollectionManager)getCollectionManagers().get(i)).toDistributionTagStructure();
    }
    arrayOfclass_69[getCollectionManagers().size()] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "wepContr", arrayOfclass_69);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.UsableDistributionControllableElementManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */