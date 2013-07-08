/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import Aj;
/*  4:   */import ct;
/*  5:   */import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*  6:   */import java.util.ArrayList;
/*  7:   */import java.util.Iterator;
/*  8:   */import java.util.List;
/*  9:   */import le;
/* 10:   */import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
/* 11:   */import org.schema.game.common.controller.SegmentController;
/* 12:   */import org.schema.game.common.data.element.ElementCollection;
/* 13:   */import org.schema.game.common.data.element.PointDistributionTagDummy;
/* 14:   */import q;
/* 15:   */import xq;
/* 16:   */
/* 17:   */public abstract class DistributionCollectionManager extends ControlBlockElementCollectionManager
/* 18:   */{
/* 19:   */  public static final String TAG_ID = "D";
/* 20:   */  
/* 21:   */  public DistributionCollectionManager(le paramle, short paramShort, SegmentController paramSegmentController)
/* 22:   */  {
/* 23:23 */    super(paramle, paramShort, paramSegmentController);
/* 24:   */  }
/* 25:   */  
/* 33:   */  public void updateStructure(long paramLong)
/* 34:   */  {
/* 35:35 */    if ((!getContainer().getInitialPointDists().isEmpty()) && (
/* 36:36 */      (getSegmentController().isOnServer()) || (((ct)getSegmentController().getState()).a().containsKey(getSegmentController().getId()))))
/* 37:   */    {
/* 38:   */      PointDistributionTagDummy localPointDistributionTagDummy;
/* 39:39 */      for (int i = 0; i < getContainer().getInitialPointDists().size(); i++) {
/* 40:40 */        localPointDistributionTagDummy = (PointDistributionTagDummy)getContainer().getInitialPointDists().get(i);
/* 41:41 */        if (getControllerPos().equals(localPointDistributionTagDummy.getControllerPos())) {
/* 42:42 */          for (org.schema.game.common.data.element.PointDistributionUnit localPointDistributionUnit : getCollection()) {
/* 43:   */            try {
/* 44:44 */              if (((localPointDistributionUnit instanceof org.schema.game.common.data.element.PointDistributionUnit)) && (localPointDistributionUnit.getId() != null) && (localPointDistributionUnit.getId().a(new q()).equals(localPointDistributionTagDummy.getIdPos()))) {
/* 45:45 */                ((org.schema.game.common.data.element.PointDistributionUnit)localPointDistributionUnit).applyDummy(localPointDistributionTagDummy);
/* 46:   */                
/* 47:47 */                getContainer().getInitialPointDists().remove(i);
/* 48:48 */                i--;
/* 49:49 */                break;
/* 50:   */              }
/* 51:   */            }
/* 52:   */            catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {}
/* 53:   */          }
/* 54:   */        }
/* 55:   */      }
/* 56:   */    }
/* 57:   */    
/* 60:60 */    super.updateStructure(paramLong);
/* 61:   */  }
/* 62:   */  
/* 66:   */  public void update(xq paramxq)
/* 67:   */  {
/* 68:68 */    super.update(paramxq);
/* 69:   */  }
/* 70:   */  
/* 71:   */  public void sendDistribution()
/* 72:   */  {
/* 73:73 */    for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext();) {
/* 74:74 */      ((org.schema.game.common.data.element.PointDistributionUnit)localIterator.next()).sendAllDistChange();
/* 75:   */    }
/* 76:   */  }
/* 77:   */  
/* 78:   */  public Ah toDistributionTagStructure() {
/* 79:79 */    Ah[] arrayOfAh = new Ah[getCollection().size() + 1];
/* 80:   */    
/* 81:81 */    for (int i = 0; i < arrayOfAh.length - 1; i++) {
/* 82:82 */      arrayOfAh[i] = ((org.schema.game.common.data.element.PointDistributionUnit)getCollection().get(i)).toTagStructure();
/* 83:   */    }
/* 84:84 */    arrayOfAh[(arrayOfAh.length - 1)] = new Ah(Aj.a, null, null);
/* 85:85 */    return new Ah(Aj.n, "D", arrayOfAh);
/* 86:   */  }
/* 87:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.DistributionCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */