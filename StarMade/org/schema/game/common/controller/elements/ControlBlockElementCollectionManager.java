package org.schema.game.common.controller.elements;

import class_371;
import class_48;
import class_69;
import class_79;
import class_796;
import class_846;
import class_916;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.ArrayList;
import java.util.Iterator;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ElementCollection;

public abstract class ControlBlockElementCollectionManager
  extends ElementCollectionManager
{
  private final class_796 controllerElement;
  private class_48 tmp = new class_48();
  
  public ControlBlockElementCollectionManager(class_796 paramclass_796, short paramShort, SegmentController paramSegmentController)
  {
    super(paramShort, paramSegmentController);
    this.controllerElement = paramclass_796;
    pieceRefresh();
  }
  
  public boolean equals(Object paramObject)
  {
    return ((ControlBlockElementCollectionManager)paramObject).controllerElement.equals(this.controllerElement);
  }
  
  public boolean equalsControllerPos(class_48 paramclass_48)
  {
    return (paramclass_48 != null) && (getControllerElement().a1(paramclass_48));
  }
  
  public class_796 getControllerElement()
  {
    return this.controllerElement;
  }
  
  public class_48 getControllerPos()
  {
    assert (this.controllerElement.a1(this.tmp)) : (this.tmp + ": " + this.controllerElement.a2(new class_48()));
    return this.tmp;
  }
  
  public int hashCode()
  {
    return getControllerElement().hashCode();
  }
  
  protected void pieceRefresh()
  {
    this.controllerElement.a12();
    getControllerElement().a2(this.tmp);
  }
  
  public void updateStructure(long paramLong)
  {
    if ((hasMetaData()) && (!getContainer().getInitialBlockMetaData().isEmpty()) && ((getSegmentController().isOnServer()) || (((class_371)getSegmentController().getState()).a7().containsKey(getSegmentController().getId())))) {
      for (int i = 0; i < getContainer().getInitialBlockMetaData().size(); i++)
      {
        BlockMetaDataDummy localBlockMetaDataDummy = (BlockMetaDataDummy)getContainer().getInitialBlockMetaData().get(i);
        if (getControllerPos().equals(localBlockMetaDataDummy.getControllerPos()))
        {
          applyMetaData(localBlockMetaDataDummy);
          getContainer().getInitialPointDists().remove(i);
          i--;
        }
      }
    }
    super.updateStructure(paramLong);
  }
  
  protected boolean hasMetaData()
  {
    return false;
  }
  
  protected void applyMetaData(BlockMetaDataDummy paramBlockMetaDataDummy) {}
  
  public void refreshControlled(ControlElementMap paramControlElementMap)
  {
    paramControlElementMap = paramControlElementMap.getControlledElements(getEnhancerClazz(), getControllerPos()).field_1094.iterator();
    while (paramControlElementMap.hasNext())
    {
      class_916 localclass_916 = (class_916)paramControlElementMap.next();
      doAdd(ElementCollection.getIndex(localclass_916.field_1139, localclass_916.field_1140, localclass_916.field_1141));
    }
  }
  
  public class_69 toTagStructure()
  {
    return new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_558, null, getControllerPos()), toTagStructurePriv(), new class_69(class_79.field_548, null, null) });
  }
  
  protected class_69 toTagStructurePriv()
  {
    return new class_69(class_79.field_549, null, Byte.valueOf((byte)0));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ControlBlockElementCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */