package org.schema.game.common.controller.elements.jamming;

import class_227;
import class_261;
import class_371;
import class_798;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ShipManagerContainer;

public class JammingCollectionManager
  extends ElementCollectionManager
{
  private float totalJam;
  
  public JammingCollectionManager(SegmentController paramSegmentController)
  {
    super((short)15, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  public float getTotalJam()
  {
    return this.totalJam;
  }
  
  protected Class getType()
  {
    return JammingUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshMaxJam();
    if (!getSegmentController().isOnServer()) {
      ((class_371)getSegmentController().getState()).a27().a92().a17(this);
    }
    JammingElementManager localJammingElementManager;
    if ((getCollection().isEmpty()) && ((localJammingElementManager = ((ShipManagerContainer)((class_798)getSegmentController()).a()).getJammingElementManager()).isJamming())) {
      localJammingElementManager.stopJamming();
    }
  }
  
  private void refreshMaxJam()
  {
    setTotalJam(0.0F);
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      JammingUnit localJammingUnit;
      (localJammingUnit = (JammingUnit)localIterator.next()).refreshJammingCapabilities();
      setTotalJam(getTotalJam() + localJammingUnit.jam);
    }
  }
  
  public void setTotalJam(float paramFloat)
  {
    this.totalJam = paramFloat;
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.jamming.JammingCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */