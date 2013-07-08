package org.schema.game.common.controller.elements.thrust;

import class_218;
import class_227;
import class_295;
import class_371;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;

public class ThrusterCollectionManager
  extends ElementCollectionManager
{
  private float totalThrust;
  
  public ThrusterCollectionManager(SegmentController paramSegmentController)
  {
    super((short)8, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  public float getTotalThrust()
  {
    return this.totalThrust;
  }
  
  protected Class getType()
  {
    return ThrusterUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshMaxThrust();
    if (!getSegmentController().isOnServer())
    {
      Iterator localIterator = ((class_371)getSegmentController().getState()).a27().a100().field_98.values().iterator();
      while (localIterator.hasNext()) {
        ((class_295)localIterator.next()).e();
      }
    }
  }
  
  private void refreshMaxThrust()
  {
    setTotalThrust(0.0F);
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      ThrusterUnit localThrusterUnit;
      (localThrusterUnit = (ThrusterUnit)localIterator.next()).refreshThrusterCapabilities();
      setTotalThrust(getTotalThrust() + localThrusterUnit.thrust);
    }
  }
  
  public void setTotalThrust(float paramFloat)
  {
    this.totalThrust = paramFloat;
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */