/*  1:   */package org.schema.game.common.controller.elements.jamming;
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
/* 14:   */public class JammingCollectionManager
/* 15:   */  extends ElementCollectionManager
/* 16:   */{
/* 17:   */  private float totalJam;
/* 18:   */  
/* 19:   */  public JammingCollectionManager(SegmentController paramSegmentController)
/* 20:   */  {
/* 21:21 */    super((short)15, paramSegmentController);
/* 22:   */  }
/* 23:   */  
/* 24:   */  public int getMargin()
/* 25:   */  {
/* 26:26 */    return 0;
/* 27:   */  }
/* 28:   */  
/* 31:   */  public float getTotalJam()
/* 32:   */  {
/* 33:33 */    return this.totalJam;
/* 34:   */  }
/* 35:   */  
/* 38:   */  protected Class getType()
/* 39:   */  {
/* 40:40 */    return JammingUnit.class;
/* 41:   */  }
/* 42:   */  
/* 46:   */  protected void onChangedCollection()
/* 47:   */  {
/* 48:48 */    refreshMaxJam();
/* 49:   */    
/* 51:51 */    if (!getSegmentController().isOnServer()) {
/* 52:52 */      ((ct)getSegmentController().getState()).a().a().a(this);
/* 53:   */    }
/* 54:   */    
/* 55:55 */    if (getCollection().isEmpty())
/* 56:   */    {
/* 57:   */      JammingElementManager localJammingElementManager;
/* 58:58 */      if ((localJammingElementManager = ((ShipManagerContainer)((ld)getSegmentController()).a()).getJammingElementManager()).isJamming()) {
/* 59:59 */        localJammingElementManager.stopJamming();
/* 60:   */      }
/* 61:   */    }
/* 62:   */  }
/* 63:   */  
/* 64:   */  private void refreshMaxJam()
/* 65:   */  {
/* 66:66 */    setTotalJam(0.0F);
/* 67:67 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 68:   */      JammingUnit localJammingUnit;
/* 69:69 */      (localJammingUnit = (JammingUnit)localIterator.next()).refreshJammingCapabilities();
/* 70:   */      
/* 71:71 */      setTotalJam(getTotalJam() + localJammingUnit.jam);
/* 72:   */    }
/* 73:   */  }
/* 74:   */  
/* 77:   */  public void setTotalJam(float paramFloat)
/* 78:   */  {
/* 79:79 */    this.totalJam = paramFloat;
/* 80:   */  }
/* 81:   */  
/* 82:   */  public boolean needsUpdate()
/* 83:   */  {
/* 84:84 */    return false;
/* 85:   */  }
/* 86:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.jamming.JammingCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */