/*  1:   */package org.schema.game.common.controller.elements.cloaking;
/*  2:   */
/*  3:   */import ct;
/*  4:   */import dj;
/*  5:   */import fe;
/*  6:   */import java.util.Iterator;
/*  7:   */import java.util.List;
/*  8:   */import ld;
/*  9:   */import org.schema.game.common.controller.SegmentController;
/* 10:   */import org.schema.game.common.controller.elements.ElementCollectionManager;
/* 11:   */import org.schema.game.common.controller.elements.ShipManagerContainer;
/* 12:   */
/* 13:   */public class CloakingCollectionManager extends ElementCollectionManager
/* 14:   */{
/* 15:   */  private float totalCloak;
/* 16:   */  
/* 17:   */  public CloakingCollectionManager(SegmentController paramSegmentController)
/* 18:   */  {
/* 19:19 */    super((short)22, paramSegmentController);
/* 20:   */  }
/* 21:   */  
/* 22:   */  public int getMargin()
/* 23:   */  {
/* 24:24 */    return 0;
/* 25:   */  }
/* 26:   */  
/* 29:   */  public float getTotalCloak()
/* 30:   */  {
/* 31:31 */    return this.totalCloak;
/* 32:   */  }
/* 33:   */  
/* 36:   */  protected Class getType()
/* 37:   */  {
/* 38:38 */    return CloakingUnit.class;
/* 39:   */  }
/* 40:   */  
/* 42:   */  protected void onChangedCollection()
/* 43:   */  {
/* 44:44 */    refreshMaxCloak();
/* 45:   */    
/* 46:46 */    if (!getSegmentController().isOnServer()) {
/* 47:47 */      ((ct)getSegmentController().getState()).a().a().a(this);
/* 48:   */    }
/* 49:   */    
/* 50:50 */    if (getCollection().isEmpty())
/* 51:   */    {
/* 52:   */      CloakingElementManager localCloakingElementManager;
/* 53:53 */      if ((localCloakingElementManager = ((ShipManagerContainer)((ld)getSegmentController()).a()).getCloakElementManager()).isCloaked()) {
/* 54:54 */        localCloakingElementManager.stopCloak();
/* 55:   */      }
/* 56:   */    }
/* 57:   */  }
/* 58:   */  
/* 59:   */  private void refreshMaxCloak()
/* 60:   */  {
/* 61:61 */    setTotalCloak(0.0F);
/* 62:62 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 63:   */      CloakingUnit localCloakingUnit;
/* 64:64 */      (localCloakingUnit = (CloakingUnit)localIterator.next()).refreshCloakingCapabilities();
/* 65:   */      
/* 66:66 */      setTotalCloak(getTotalCloak() + localCloakingUnit.cloak);
/* 67:   */    }
/* 68:   */  }
/* 69:   */  
/* 72:   */  public void setTotalCloak(float paramFloat)
/* 73:   */  {
/* 74:74 */    this.totalCloak = paramFloat;
/* 75:   */  }
/* 76:   */  
/* 78:   */  public boolean needsUpdate()
/* 79:   */  {
/* 80:80 */    return false;
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.cloaking.CloakingCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */