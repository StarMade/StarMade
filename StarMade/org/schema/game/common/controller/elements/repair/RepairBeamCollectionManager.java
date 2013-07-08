/*  1:   */package org.schema.game.common.controller.elements.repair;
/*  2:   */
/*  3:   */import ct;
/*  4:   */import dj;
/*  5:   */import fe;
/*  6:   */import java.util.Iterator;
/*  7:   */import java.util.List;
/*  8:   */import jq;
/*  9:   */import le;
/* 10:   */import org.schema.game.common.controller.SegmentController;
/* 11:   */import org.schema.game.common.controller.elements.ControlBlockElementCollectionManager;
/* 12:   */import xq;
/* 13:   */
/* 14:   */public class RepairBeamCollectionManager
/* 15:   */  extends ControlBlockElementCollectionManager implements jq
/* 16:   */{
/* 17:   */  private final RepairBeamHandler handler;
/* 18:   */  
/* 19:   */  public RepairBeamCollectionManager(le paramle, SegmentController paramSegmentController)
/* 20:   */  {
/* 21:21 */    super(paramle, (short)30, paramSegmentController);
/* 22:   */    
/* 23:23 */    this.handler = new RepairBeamHandler(paramSegmentController, this);
/* 24:   */  }
/* 25:   */  
/* 31:   */  public RepairBeamHandler getHandler()
/* 32:   */  {
/* 33:33 */    return this.handler;
/* 34:   */  }
/* 35:   */  
/* 39:   */  public int getMargin()
/* 40:   */  {
/* 41:41 */    return 0;
/* 42:   */  }
/* 43:   */  
/* 45:   */  protected Class getType()
/* 46:   */  {
/* 47:47 */    return RepairUnit.class;
/* 48:   */  }
/* 49:   */  
/* 50:   */  protected void onChangedCollection()
/* 51:   */  {
/* 52:52 */    refreshRepairCapabiities();
/* 53:53 */    getHandler().clearStates();
/* 54:   */    
/* 56:56 */    if (!getSegmentController().isOnServer()) {
/* 57:57 */      ((ct)getSegmentController().getState()).a().a().a(this);
/* 58:   */    }
/* 59:   */  }
/* 60:   */  
/* 61:   */  public void refreshRepairCapabiities()
/* 62:   */  {
/* 63:63 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 64:64 */      ((RepairUnit)localIterator.next()).refreshHarvestCapabilities();
/* 65:   */    }
/* 66:   */  }
/* 67:   */  
/* 70:   */  public void update(xq paramxq)
/* 71:   */  {
/* 72:72 */    this.handler.update(paramxq);
/* 73:   */  }
/* 74:   */  
/* 75:   */  public boolean needsUpdate()
/* 76:   */  {
/* 77:77 */    return true;
/* 78:   */  }
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairBeamCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */