/*  1:   */package org.schema.game.common.controller.elements.powerbeamdrain;
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
/* 16:   */public class PowerDrainBeamCollectionManager
/* 17:   */  extends ControlBlockElementCollectionManager
/* 18:   */  implements jq
/* 19:   */{
/* 20:   */  private final PowerDrainBeamHandler handler;
/* 21:   */  
/* 22:   */  public PowerDrainBeamCollectionManager(le paramle, SegmentController paramSegmentController)
/* 23:   */  {
/* 24:24 */    super(paramle, (short)333, paramSegmentController);
/* 25:   */    
/* 26:26 */    this.handler = new PowerDrainBeamHandler(paramSegmentController, this);
/* 27:   */  }
/* 28:   */  
/* 33:   */  public PowerDrainBeamHandler getHandler()
/* 34:   */  {
/* 35:35 */    return this.handler;
/* 36:   */  }
/* 37:   */  
/* 40:   */  public int getMargin()
/* 41:   */  {
/* 42:42 */    return 0;
/* 43:   */  }
/* 44:   */  
/* 45:   */  protected Class getType()
/* 46:   */  {
/* 47:47 */    return PowerDrainUnit.class;
/* 48:   */  }
/* 49:   */  
/* 50:   */  protected void onChangedCollection()
/* 51:   */  {
/* 52:52 */    refreshBeamCapabiities();
/* 53:53 */    getHandler().clearStates();
/* 54:   */    
/* 56:56 */    if (!getSegmentController().isOnServer()) {
/* 57:57 */      ((ct)getSegmentController().getState()).a().a().a(this);
/* 58:   */    }
/* 59:   */  }
/* 60:   */  
/* 61:   */  public void refreshBeamCapabiities()
/* 62:   */  {
/* 63:63 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 64:64 */      ((PowerDrainUnit)localIterator.next()).refreshDrainCapabilities();
/* 65:   */    }
/* 66:   */  }
/* 67:   */  
/* 69:   */  public boolean needsUpdate()
/* 70:   */  {
/* 71:71 */    return true;
/* 72:   */  }
/* 73:   */  
/* 74:   */  public void update(xq paramxq) {
/* 75:75 */    this.handler.update(paramxq);
/* 76:   */  }
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamdrain.PowerDrainBeamCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */