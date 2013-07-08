package org.schema.game.common.controller.elements;

import class_371;
import class_48;
import class_69;
import class_79;
import class_796;
import class_941;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.element.PointDistributionTagDummy;
import org.schema.game.common.data.element.PointDistributionUnit;

public abstract class DistributionCollectionManager
  extends ControlBlockElementCollectionManager
{
  public static final String TAG_ID = "D";
  
  public DistributionCollectionManager(class_796 paramclass_796, short paramShort, SegmentController paramSegmentController)
  {
    super(paramclass_796, paramShort, paramSegmentController);
  }
  
  public void updateStructure(long paramLong)
  {
    if ((!getContainer().getInitialPointDists().isEmpty()) && ((getSegmentController().isOnServer()) || (((class_371)getSegmentController().getState()).a7().containsKey(getSegmentController().getId())))) {
      for (int i = 0; i < getContainer().getInitialPointDists().size(); i++)
      {
        PointDistributionTagDummy localPointDistributionTagDummy = (PointDistributionTagDummy)getContainer().getInitialPointDists().get(i);
        if (getControllerPos().equals(localPointDistributionTagDummy.getControllerPos()))
        {
          Iterator localIterator = getCollection().iterator();
          while (localIterator.hasNext())
          {
            PointDistributionUnit localPointDistributionUnit = (PointDistributionUnit)localIterator.next();
            try
            {
              if (((localPointDistributionUnit instanceof PointDistributionUnit)) && (localPointDistributionUnit.getId() != null) && (localPointDistributionUnit.getId().a2(new class_48()).equals(localPointDistributionTagDummy.getIdPos())))
              {
                ((PointDistributionUnit)localPointDistributionUnit).applyDummy(localPointDistributionTagDummy);
                getContainer().getInitialPointDists().remove(i);
                i--;
                break;
              }
            }
            catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {}
          }
        }
      }
    }
    super.updateStructure(paramLong);
  }
  
  public void update(class_941 paramclass_941)
  {
    super.update(paramclass_941);
  }
  
  public void sendDistribution()
  {
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext()) {
      ((PointDistributionUnit)localIterator.next()).sendAllDistChange();
    }
  }
  
  public class_69 toDistributionTagStructure()
  {
    class_69[] arrayOfclass_69 = new class_69[getCollection().size() + 1];
    for (int i = 0; i < arrayOfclass_69.length - 1; i++) {
      arrayOfclass_69[i] = ((PointDistributionUnit)getCollection().get(i)).toTagStructure();
    }
    arrayOfclass_69[(arrayOfclass_69.length - 1)] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "D", arrayOfclass_69);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.DistributionCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */