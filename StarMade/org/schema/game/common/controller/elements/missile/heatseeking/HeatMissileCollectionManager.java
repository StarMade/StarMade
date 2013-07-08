/*  1:   */package org.schema.game.common.controller.elements.missile.heatseeking;
/*  2:   */
/*  3:   */import ct;
/*  4:   */import dj;
/*  5:   */import fe;
/*  6:   */import le;
/*  7:   */import org.schema.game.common.controller.SegmentController;
/*  8:   */import org.schema.game.common.controller.elements.DistributionCollectionManager;
/*  9:   */import org.schema.game.common.controller.elements.missile.MissileUnit;
/* 10:   */
/* 11:   */public class HeatMissileCollectionManager
/* 12:   */  extends DistributionCollectionManager
/* 13:   */{
/* 14:   */  public HeatMissileCollectionManager(le paramle, SegmentController paramSegmentController)
/* 15:   */  {
/* 16:16 */    super(paramle, (short)40, paramSegmentController);
/* 17:   */  }
/* 18:   */  
/* 21:   */  public int getMargin()
/* 22:   */  {
/* 23:23 */    return 0;
/* 24:   */  }
/* 25:   */  
/* 26:   */  protected Class getType()
/* 27:   */  {
/* 28:28 */    return MissileUnit.class;
/* 29:   */  }
/* 30:   */  
/* 36:   */  protected void onChangedCollection()
/* 37:   */  {
/* 38:38 */    if (!getSegmentController().isOnServer()) {
/* 39:39 */      ((ct)getSegmentController().getState()).a().a().a(this);
/* 40:   */    }
/* 41:   */  }
/* 42:   */  
/* 45:   */  public boolean needsUpdate()
/* 46:   */  {
/* 47:47 */    return false;
/* 48:   */  }
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.heatseeking.HeatMissileCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */