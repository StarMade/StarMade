package org.schema.game.common.controller.elements.door;

import class_46;
import class_48;
import class_753;
import class_796;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import java.util.Collection;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.element.ElementCollection;

public class DoorUnit
  extends ElementCollection
{
  private class_48 significator = new class_48();
  private Vector3f topMin = new Vector3f();
  private Vector3f topMax = new Vector3f();
  private boolean active;
  
  public void activate(class_796 paramclass_796, boolean paramBoolean)
  {
    paramclass_796 = new class_48();
    Iterator localIterator = getNeighboringCollection().iterator();
    while (localIterator.hasNext())
    {
      getPosFromIndex(((Long)localIterator.next()).longValue(), paramclass_796);
      class_46 localclass_46;
      (localclass_46 = new class_46()).a(paramclass_796.field_475, paramclass_796.field_476, paramclass_796.field_477, paramBoolean ? 2 : -2);
      ((class_753)getController()).getBlockActivationBuffer().enqueue(localclass_46);
    }
  }
  
  public void cleanUp()
  {
    super.cleanUp();
  }
  
  public class_48 getSignificator()
  {
    return this.significator;
  }
  
  public boolean isActive()
  {
    return this.active;
  }
  
  public void refreshDoorCapabilities() {}
  
  protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
  {
    this.significator.field_475 = (getMax().field_475 - (getMax().field_475 - getMin().field_475) / 2);
    this.significator.field_476 = getMax().field_476;
    this.significator.field_477 = (getMax().field_477 - (getMax().field_477 - getMin().field_477) / 2);
    this.topMin.set(getMin().field_475, getMax().field_476, getMin().field_477);
    this.topMax.set(getMax().field_475, getMax().field_476, getMax().field_477);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.door.DoorUnit
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */