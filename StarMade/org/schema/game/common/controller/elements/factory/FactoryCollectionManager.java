package org.schema.game.common.controller.elements.factory;

import class_216;
import class_48;
import class_635;
import class_639;
import class_796;
import class_798;
import class_846;
import class_883;
import class_886;
import class_916;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.FactoryResource;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;

public class FactoryCollectionManager
  extends ControlBlockElementCollectionManager
{
  private class_48 absPos = new class_48();
  private int capability = 1;
  private final class_48 posTmp = new class_48();
  private long lastCheck;
  
  public FactoryCollectionManager(short paramShort, class_796 paramclass_796, SegmentController paramSegmentController)
  {
    super(paramclass_796, paramShort, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  protected Class getType()
  {
    return FactoryUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshEnhancerCapabiities();
  }
  
  public void refreshEnhancerCapabiities()
  {
    this.capability = 1;
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext()) {
      ((FactoryUnit)localIterator.next()).refreshFactoryCapabilities(this);
    }
  }
  
  public void manufractureStep(FactoryElementManager paramFactoryElementManager, HashMap paramHashMap)
  {
    if (!consumePower(paramFactoryElementManager)) {
      return;
    }
    if (getSegmentController().isOnServer())
    {
      class_48 localclass_48 = getControllerElement().a2(this.absPos);
      class_846 localclass_846 = paramFactoryElementManager.getControlElementMap().getControlledElements((short)32767, localclass_48);
      class_639 localclass_639;
      if ((localclass_639 = ((class_798)getSegmentController()).a().getInventory(localclass_48)) == null)
      {
        System.err.println("[SERVER][FACTORY] Exception: Factory has no own inventory: " + getSegmentController() + " -> " + localclass_48);
        return;
      }
      if (!paramFactoryElementManager.isInputFactory())
      {
        int i = paramFactoryElementManager.getProductCount();
        for (int j = 0; j < i; j++)
        {
          HashSet localHashSet;
          if ((localHashSet = (HashSet)paramHashMap.get(localclass_639)) == null)
          {
            localHashSet = new HashSet();
            paramHashMap.put(localclass_639, localHashSet);
          }
          handleProduct(localclass_846, paramFactoryElementManager, j, localclass_639, paramHashMap, localHashSet);
        }
      }
    }
  }
  
  private void handleProduct(class_846 paramclass_846, FactoryElementManager paramFactoryElementManager, int paramInt, class_639 paramclass_639, HashMap paramHashMap, HashSet paramHashSet)
  {
    paramclass_846 = paramclass_846.field_1094.iterator();
    Object localObject;
    while (paramclass_846.hasNext())
    {
      class_916 localclass_916 = (class_916)paramclass_846.next();
      this.posTmp.b(localclass_916.field_1139, localclass_916.field_1140, localclass_916.field_1141);
      class_639 localclass_639;
      int i;
      class_796 localclass_796;
      if ((localclass_639 = ((class_798)getSegmentController()).a().getInventory(this.posTmp)) != null)
      {
        if ((localObject = (HashSet)paramHashMap.get(localclass_639)) == null)
        {
          localObject = new HashSet();
          paramHashMap.put(localclass_639, localObject);
        }
        for (localclass_916 : paramFactoryElementManager.getInputType(paramInt))
        {
          int k;
          if ((k = localclass_639.b7(localclass_916.type)) >= getCount(localclass_916)) {
            try
            {
              localclass_639.a43(localclass_916.type, (Collection)localObject);
              k = localclass_639.b8(localclass_916.type, k - getCount(localclass_916));
              ((HashSet)localObject).add(Integer.valueOf(k));
              i = paramclass_639.b8(localclass_916.type, getCount(localclass_916));
              paramHashSet.add(Integer.valueOf(i));
            }
            catch (NoSlotFreeException localNoSlotFreeException1)
            {
              localclass_796 = null;
              localNoSlotFreeException1.printStackTrace();
            }
          }
        }
      }
      else if (System.currentTimeMillis() - this.lastCheck > 1000L)
      {
        try
        {
          if (((localclass_796 = getSegmentController().getSegmentBuffer().a9(this.posTmp, true)) == null) || (localclass_796.a9() != 212)) {
            System.err.println("[FACTORY] " + getSegmentController() + ": no inventory found at " + i + "; ControllerPos: " + getControllerPos() + "; NOW loaded supposed inventory position (Unsave Point): " + localclass_796);
          }
        }
        catch (IOException localIOException)
        {
          localclass_796 = null;
          localIOException.printStackTrace();
        }
        catch (InterruptedException localInterruptedException)
        {
          localclass_796 = null;
          localInterruptedException.printStackTrace();
        }
        this.lastCheck = System.currentTimeMillis();
      }
    }
    paramclass_846 = 1;
    for (localObject : paramFactoryElementManager.getInputType(paramInt)) {
      if (paramclass_639.b7(((FactoryResource)localObject).type) < getCount((FactoryResource)localObject)) {
        paramclass_846 = 0;
      }
    }
    if (paramclass_846 != 0)
    {
      int n;
      for (localObject : paramFactoryElementManager.getInputType(paramInt))
      {
        n = paramclass_639.b7(((FactoryResource)localObject).type);
        paramclass_639.a43(((FactoryResource)localObject).type, paramHashSet);
        try
        {
          ??? = paramclass_639.b8(((FactoryResource)localObject).type, n - getCount((FactoryResource)localObject));
          paramHashSet.add(Integer.valueOf(???));
        }
        catch (NoSlotFreeException localNoSlotFreeException2)
        {
          localNoSlotFreeException2;
        }
      }
      for (localObject : paramFactoryElementManager.getOutputType(paramInt)) {
        try
        {
          n = paramclass_639.b8(((FactoryResource)localObject).type, getCount((FactoryResource)localObject));
          paramHashSet.add(Integer.valueOf(n));
        }
        catch (NoSlotFreeException localNoSlotFreeException3)
        {
          localNoSlotFreeException3;
        }
      }
    }
  }
  
  public boolean consumePower(FactoryElementManager paramFactoryElementManager)
  {
    boolean bool = (paramFactoryElementManager = ((PowerManagerInterface)paramFactoryElementManager.getManagerContainer()).getPowerAddOn()).consumePowerInstantly(this.capability * 500);
    if ((!getSegmentController().isOnServer()) && (!bool))
    {
      Transform localTransform;
      (localTransform = new Transform()).setIdentity();
      class_48 localclass_48 = getControllerElement().a2(this.absPos);
      localTransform.origin.set(localclass_48.field_475 - 8, localclass_48.field_476 - 8, localclass_48.field_477 - 8);
      getSegmentController().getWorldTransform().transform(localTransform.origin);
      class_883.field_89.add(new class_216(localTransform, "Insufficient Energy (" + (int)paramFactoryElementManager.getPower() + " / " + this.capability * 100 + ")", 1.0F, 0.1F, 0.1F));
    }
    return bool;
  }
  
  public int getCount(FactoryResource paramFactoryResource)
  {
    return paramFactoryResource.count * this.capability;
  }
  
  public void addCapability(int paramInt)
  {
    this.capability += paramInt;
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.factory.FactoryCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */