package org.schema.game.common.controller.elements.cloaking;

import class_227;
import class_261;
import class_371;
import class_798;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.controller.elements.ShipManagerContainer;

public class CloakingCollectionManager
  extends ElementCollectionManager
{
  private float totalCloak;
  
  public CloakingCollectionManager(SegmentController paramSegmentController)
  {
    super((short)22, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  public float getTotalCloak()
  {
    return this.totalCloak;
  }
  
  protected Class getType()
  {
    return CloakingUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshMaxCloak();
    if (!getSegmentController().isOnServer()) {
      ((class_371)getSegmentController().getState()).a27().a92().a17(this);
    }
    CloakingElementManager localCloakingElementManager;
    if ((getCollection().isEmpty()) && ((localCloakingElementManager = ((ShipManagerContainer)((class_798)getSegmentController()).a()).getCloakElementManager()).isCloaked())) {
      localCloakingElementManager.stopCloak();
    }
  }
  
  private void refreshMaxCloak()
  {
    setTotalCloak(0.0F);
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      CloakingUnit localCloakingUnit;
      (localCloakingUnit = (CloakingUnit)localIterator.next()).refreshCloakingCapabilities();
      setTotalCloak(getTotalCloak() + localCloakingUnit.cloak);
    }
  }
  
  public void setTotalCloak(float paramFloat)
  {
    this.totalCloak = paramFloat;
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.cloaking.CloakingCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */