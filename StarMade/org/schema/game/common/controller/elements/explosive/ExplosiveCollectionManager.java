/*  1:   */package org.schema.game.common.controller.elements.explosive;
/*  2:   */
/*  3:   */import java.util.Iterator;
/*  4:   */import java.util.List;
/*  5:   */import org.schema.game.common.controller.SegmentController;
/*  6:   */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  7:   */
/* 10:   */public class ExplosiveCollectionManager
/* 11:   */  extends ElementCollectionManager
/* 12:   */{
/* 13:   */  private float totalExplosive;
/* 14:   */  
/* 15:   */  public ExplosiveCollectionManager(SegmentController paramSegmentController)
/* 16:   */  {
/* 17:17 */    super((short)14, paramSegmentController);
/* 18:   */  }
/* 19:   */  
/* 20:   */  public int getMargin()
/* 21:   */  {
/* 22:22 */    return 0;
/* 23:   */  }
/* 24:   */  
/* 27:   */  public float getTotalExplosive()
/* 28:   */  {
/* 29:29 */    return this.totalExplosive;
/* 30:   */  }
/* 31:   */  
/* 34:   */  protected Class getType()
/* 35:   */  {
/* 36:36 */    return ExplosiveUnit.class;
/* 37:   */  }
/* 38:   */  
/* 41:   */  protected void onChangedCollection()
/* 42:   */  {
/* 43:43 */    refreshMaxExplosive();
/* 44:   */  }
/* 45:   */  
/* 46:   */  private void refreshMaxExplosive()
/* 47:   */  {
/* 48:48 */    setTotalExplosive(0.0F);
/* 49:49 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 50:   */      ExplosiveUnit localExplosiveUnit;
/* 51:51 */      (localExplosiveUnit = (ExplosiveUnit)localIterator.next()).refreshExplosiveCapabilities();
/* 52:   */      
/* 53:53 */      setTotalExplosive(getTotalExplosive() + localExplosiveUnit.explosive);
/* 54:   */    }
/* 55:   */  }
/* 56:   */  
/* 59:   */  public void setTotalExplosive(float paramFloat)
/* 60:   */  {
/* 61:61 */    this.totalExplosive = paramFloat;
/* 62:   */  }
/* 63:   */  
/* 64:   */  public boolean needsUpdate()
/* 65:   */  {
/* 66:66 */    return false;
/* 67:   */  }
/* 68:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */