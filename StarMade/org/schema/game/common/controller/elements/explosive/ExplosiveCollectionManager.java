package org.schema.game.common.controller.elements.explosive;

import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;

public class ExplosiveCollectionManager
  extends ElementCollectionManager
{
  private float totalExplosive;
  
  public ExplosiveCollectionManager(SegmentController paramSegmentController)
  {
    super((short)14, paramSegmentController);
  }
  
  public int getMargin()
  {
    return 0;
  }
  
  public float getTotalExplosive()
  {
    return this.totalExplosive;
  }
  
  protected Class getType()
  {
    return ExplosiveUnit.class;
  }
  
  protected void onChangedCollection()
  {
    refreshMaxExplosive();
  }
  
  private void refreshMaxExplosive()
  {
    setTotalExplosive(0.0F);
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      ExplosiveUnit localExplosiveUnit;
      (localExplosiveUnit = (ExplosiveUnit)localIterator.next()).refreshExplosiveCapabilities();
      setTotalExplosive(getTotalExplosive() + localExplosiveUnit.explosive);
    }
  }
  
  public void setTotalExplosive(float paramFloat)
  {
    this.totalExplosive = paramFloat;
  }
  
  public boolean needsUpdate()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */