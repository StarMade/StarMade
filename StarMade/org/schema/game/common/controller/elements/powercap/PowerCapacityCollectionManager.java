/*  1:   */package org.schema.game.common.controller.elements.powercap;
/*  2:   */
/*  3:   */import java.util.List;
/*  4:   */import ld;
/*  5:   */import org.schema.game.common.controller.SegmentController;
/*  6:   */import org.schema.game.common.controller.elements.ElementCollectionManager;
/*  7:   */import org.schema.game.common.controller.elements.PowerAddOn;
/*  8:   */import org.schema.game.common.controller.elements.PowerManagerInterface;
/*  9:   */
/* 10:   */public class PowerCapacityCollectionManager extends ElementCollectionManager
/* 11:   */{
/* 12:   */  private double maxPower;
/* 13:   */  
/* 14:   */  public PowerCapacityCollectionManager(SegmentController paramSegmentController)
/* 15:   */  {
/* 16:16 */    super((short)331, paramSegmentController);
/* 17:   */  }
/* 18:   */  
/* 21:   */  public int getMargin()
/* 22:   */  {
/* 23:23 */    return 0;
/* 24:   */  }
/* 25:   */  
/* 28:   */  public double getMaxPower()
/* 29:   */  {
/* 30:30 */    return this.maxPower;
/* 31:   */  }
/* 32:   */  
/* 34:   */  protected Class getType()
/* 35:   */  {
/* 36:36 */    return PowerCapacityUnit.class;
/* 37:   */  }
/* 38:   */  
/* 42:   */  protected void onChangedCollection()
/* 43:   */  {
/* 44:44 */    refreshMaxPower();
/* 45:   */  }
/* 46:   */  
/* 47:   */  private void refreshMaxPower() {
/* 48:48 */    this.maxPower = 0.0D;
/* 49:49 */    for (java.util.Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 50:50 */      double d = Math.pow(((PowerCapacityUnit)localIterator.next()).size(), 1.75D);
/* 51:51 */      this.maxPower += d;
/* 52:   */    }
/* 53:   */    
/* 54:54 */    ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn().setMaxPower(this.maxPower);
/* 55:   */  }
/* 56:   */  
/* 60:   */  public void setMaxPower(double paramDouble)
/* 61:   */  {
/* 62:62 */    this.maxPower = paramDouble;
/* 63:   */  }
/* 64:   */  
/* 68:   */  public boolean needsUpdate()
/* 69:   */  {
/* 70:70 */    return false;
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */