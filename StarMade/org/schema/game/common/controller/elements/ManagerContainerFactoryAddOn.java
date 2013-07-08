package org.schema.game.common.controller.elements;

import class_639;
import class_941;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.factory.FactoryCollectionManager;
import org.schema.game.common.controller.elements.factory.FactoryElementManager;
import org.schema.game.common.data.element.BlockFactory;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

public class ManagerContainerFactoryAddOn
{
  public static final float TIME_STEP = 5.0F;
  private float accumulated;
  private final HashMap map = new HashMap();
  private SegmentController segmentController;
  private boolean initialized;
  private final HashMap changedSet = new HashMap();
  
  public void initialize(ArrayList paramArrayList, SegmentController paramSegmentController)
  {
    Iterator localIterator = ElementKeyMap.getFactorykeyset().iterator();
    while (localIterator.hasNext())
    {
      ElementInformation localElementInformation = ElementKeyMap.getInfo(((Short)localIterator.next()).shortValue());
      assert (localElementInformation.getFactory() != null);
      ManagerModuleCollection localManagerModuleCollection = new ManagerModuleCollection(new FactoryElementManager(paramSegmentController, localElementInformation.getId(), localElementInformation.getFactory().enhancer), localElementInformation.getId(), localElementInformation.getFactory().enhancer);
      paramArrayList.add(localManagerModuleCollection);
      this.map.put(Short.valueOf(localElementInformation.getId()), localManagerModuleCollection);
    }
    this.segmentController = paramSegmentController;
    this.initialized = true;
  }
  
  public void update(class_941 paramclass_941, boolean paramBoolean)
  {
    assert (this.initialized);
    this.accumulated += paramclass_941.a();
    paramclass_941 = 0;
    Object localObject1;
    Object localObject2;
    while (this.accumulated > 5.0F)
    {
      paramBoolean = this.map.values().iterator();
      while (paramBoolean.hasNext())
      {
        localObject2 = (localObject1 = (ManagerModuleCollection)paramBoolean.next()).getCollectionManagers().iterator();
        while (((Iterator)localObject2).hasNext()) {
          ((FactoryCollectionManager)((Iterator)localObject2).next()).manufractureStep((FactoryElementManager)((ManagerModuleCollection)localObject1).getElementManager(), this.changedSet);
        }
      }
      if (paramclass_941 > 2)
      {
        this.accumulated = 0.0F;
        break;
      }
      paramclass_941++;
      this.accumulated -= 5.0F;
    }
    if (this.accumulated > 200.0F) {
      System.err.println("[FACTORY] WARNING: " + this.segmentController + " accumulated too much time: " + this.accumulated);
    }
    paramBoolean = this.changedSet.entrySet().iterator();
    while (paramBoolean.hasNext()) {
      if (!((HashSet)(localObject1 = (Map.Entry)paramBoolean.next()).getValue()).isEmpty())
      {
        (localObject2 = new HashSet()).addAll((Collection)((Map.Entry)localObject1).getValue());
        ((class_639)((Map.Entry)localObject1).getKey()).a56((Collection)localObject2);
        ((HashSet)((Map.Entry)localObject1).getValue()).clear();
        return;
      }
    }
  }
  
  public float getAccumulated()
  {
    return this.accumulated;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerContainerFactoryAddOn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */