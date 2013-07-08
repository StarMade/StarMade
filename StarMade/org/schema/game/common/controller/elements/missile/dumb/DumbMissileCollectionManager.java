/*  1:   */package org.schema.game.common.controller.elements.missile.dumb;
/*  2:   */
/*  3:   */import ct;
/*  4:   */import dj;
/*  5:   */import fe;
/*  6:   */import le;
/*  7:   */import org.schema.game.common.controller.SegmentController;
/*  8:   */import org.schema.game.common.controller.elements.DistributionCollectionManager;
/*  9:   */import org.schema.game.common.controller.elements.missile.MissileUnit;
/* 10:   */
/* 11:   */public class DumbMissileCollectionManager extends DistributionCollectionManager
/* 12:   */{
/* 13:   */  public DumbMissileCollectionManager(le paramle, SegmentController paramSegmentController)
/* 14:   */  {
/* 15:15 */    super(paramle, (short)32, paramSegmentController);
/* 16:   */  }
/* 17:   */  
/* 20:   */  public int getMargin()
/* 21:   */  {
/* 22:22 */    return 0;
/* 23:   */  }
/* 24:   */  
/* 28:   */  protected Class getType()
/* 29:   */  {
/* 30:30 */    return MissileUnit.class;
/* 31:   */  }
/* 32:   */  
/* 37:   */  protected void onChangedCollection()
/* 38:   */  {
/* 39:39 */    if (!getSegmentController().isOnServer()) {
/* 40:40 */      ((ct)getSegmentController().getState()).a().a().a(this);
/* 41:   */    }
/* 42:   */  }
/* 43:   */  
/* 45:   */  public boolean needsUpdate()
/* 46:   */  {
/* 47:47 */    return false;
/* 48:   */  }
/* 49:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.missile.dumb.DumbMissileCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */