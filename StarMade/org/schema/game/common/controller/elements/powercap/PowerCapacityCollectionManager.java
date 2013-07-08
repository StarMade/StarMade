package org.schema.game.common.controller.elements.powercap;

import class_798;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;

public class PowerCapacityCollectionManager
  extends ElementCollectionManager
{
  private double maxPower;
  
  public PowerCapacityCollectionManager(SegmentController paramSegmentController)
  {
    super((short)331, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  public double getMaxPower()
  {
    return this.maxPower;
  }
  
  protected Class getType()
  {
    return PowerCapacityUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshMaxPower();
  }
  
  private void refreshMaxPower()
  {
    this.maxPower = 0.0D;
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      double d = Math.pow(((PowerCapacityUnit)localIterator.next()).size(), 1.75D);
      this.maxPower += d;
    }
    ((PowerManagerInterface)((class_798)getSegmentController()).a()).getPowerAddOn().setMaxPower(this.maxPower);
  }
  
  public void setMaxPower(double paramDouble)
  {
    this.maxPower = paramDouble;
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */