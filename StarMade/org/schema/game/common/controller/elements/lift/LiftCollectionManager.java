package org.schema.game.common.controller.elements.lift;

import class_46;
import class_48;
import class_941;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.NTReceiveInterface;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.network.objects.NetworkLiftInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public class LiftCollectionManager
  extends ElementCollectionManager
  implements NTReceiveInterface
{
  private final ArrayList toActivate = new ArrayList();
  
  public LiftCollectionManager(SegmentController paramSegmentController)
  {
    super((short)113, paramSegmentController);
  }
  
  public void activate(class_48 paramclass_48, boolean paramBoolean)
  {
    System.err.println("CHECKING FOR LIFT UNIT IN " + getCollection().size() + " COLLECTIONS");
    long l = ElementCollection.getIndex(paramclass_48);
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      LiftUnit localLiftUnit;
      if ((localLiftUnit = (LiftUnit)localIterator.next()).getNeighboringCollection().contains(Long.valueOf(l))) {
        if (paramBoolean)
        {
          System.err.println("ACTIVATING LIFT " + paramclass_48 + " " + getSegmentController().getState() + " " + getSegmentController());
          System.err.println("ACTIVATING " + localLiftUnit + "; this unit size " + localLiftUnit.getNeighboringCollection().size() + " / units " + getCollection().size());
          localLiftUnit.activate();
        }
        else
        {
          localLiftUnit.deactivate();
        }
      }
    }
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  protected Class getType()
  {
    return LiftUnit.class;
  }
  
  protected void onChangedCollection() {}
  
  public void update(class_941 paramclass_941)
  {
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext()) {
      ((LiftUnit)localIterator.next()).update(paramclass_941);
    }
    if (!this.toActivate.isEmpty()) {
      synchronized (this.toActivate)
      {
        paramclass_941 = (class_46)this.toActivate.remove(0);
        activate(new class_48(paramclass_941.field_467, paramclass_941.field_468, paramclass_941.field_469), paramclass_941.field_470 != 0);
        return;
      }
    }
  }
  
  public void updateFromNT(NetworkObject paramNetworkObject)
  {
    RemoteBuffer localRemoteBuffer;
    Iterator localIterator = (localRemoteBuffer = ((NetworkLiftInterface)paramNetworkObject).getLiftActivate()).getReceiveBuffer().iterator();
    while (localIterator.hasNext())
    {
      class_46 localclass_46 = ((RemoteVector4i)localIterator.next()).getVector();
      synchronized (this.toActivate)
      {
        this.toActivate.add(localclass_46);
      }
      if (getSegmentController().isOnServer()) {
        localRemoteBuffer.add(new RemoteVector4i(localclass_46, paramNetworkObject));
      }
    }
  }
  
  public boolean needsUpdate()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.lift.LiftCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */