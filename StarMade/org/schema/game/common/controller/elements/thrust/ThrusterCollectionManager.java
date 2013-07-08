/*  1:   */package org.schema.game.common.controller.elements.thrust;
/*  2:   */
/*  3:   */import ct;
/*  4:   */import dj;
/*  5:   */import eG;
/*  6:   */import er;
/*  7:   */import java.util.Collection;
/*  8:   */import java.util.Iterator;
/*  9:   */import java.util.List;
/* 10:   */import java.util.Map;
/* 11:   */import org.schema.game.common.controller.SegmentController;
/* 12:   */import org.schema.game.common.controller.elements.ElementCollectionManager;
/* 13:   */
/* 14:   */public class ThrusterCollectionManager extends ElementCollectionManager
/* 15:   */{
/* 16:   */  private float totalThrust;
/* 17:   */  
/* 18:   */  public ThrusterCollectionManager(SegmentController paramSegmentController)
/* 19:   */  {
/* 20:20 */    super((short)8, paramSegmentController);
/* 21:   */  }
/* 22:   */  
/* 23:   */  public int getMargin()
/* 24:   */  {
/* 25:25 */    return 0;
/* 26:   */  }
/* 27:   */  
/* 30:   */  public float getTotalThrust()
/* 31:   */  {
/* 32:32 */    return this.totalThrust;
/* 33:   */  }
/* 34:   */  
/* 37:   */  protected Class getType()
/* 38:   */  {
/* 39:39 */    return ThrusterUnit.class;
/* 40:   */  }
/* 41:   */  
/* 45:   */  protected void onChangedCollection()
/* 46:   */  {
/* 47:47 */    refreshMaxThrust();
/* 48:48 */    if (!getSegmentController().isOnServer()) {
/* 49:49 */      for (Iterator localIterator = ((ct)getSegmentController().getState()).a().a().a.values().iterator(); localIterator.hasNext(); ((er)localIterator.next()).e()) {}
/* 50:   */    }
/* 51:   */  }
/* 52:   */  
/* 57:   */  private void refreshMaxThrust()
/* 58:   */  {
/* 59:59 */    setTotalThrust(0.0F);
/* 60:60 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 61:   */      ThrusterUnit localThrusterUnit;
/* 62:62 */      (localThrusterUnit = (ThrusterUnit)localIterator.next()).refreshThrusterCapabilities();
/* 63:   */      
/* 64:64 */      setTotalThrust(getTotalThrust() + localThrusterUnit.thrust);
/* 65:   */    }
/* 66:   */  }
/* 67:   */  
/* 70:   */  public void setTotalThrust(float paramFloat)
/* 71:   */  {
/* 72:72 */    this.totalThrust = paramFloat;
/* 73:   */  }
/* 74:   */  
/* 75:   */  public boolean needsUpdate()
/* 76:   */  {
/* 77:77 */    return false;
/* 78:   */  }
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.thrust.ThrusterCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */