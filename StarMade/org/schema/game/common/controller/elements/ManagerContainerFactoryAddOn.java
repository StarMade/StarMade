/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import java.io.PrintStream;
/*  4:   */import java.util.ArrayList;
/*  5:   */import java.util.Collection;
/*  6:   */import java.util.HashSet;
/*  7:   */import java.util.List;
/*  8:   */import java.util.Set;
/*  9:   */import mf;
/* 10:   */import org.schema.game.common.controller.SegmentController;
/* 11:   */import org.schema.game.common.controller.elements.factory.FactoryCollectionManager;
/* 12:   */import org.schema.game.common.controller.elements.factory.FactoryElementManager;
/* 13:   */import org.schema.game.common.data.element.BlockFactory;
/* 14:   */import org.schema.game.common.data.element.ElementInformation;
/* 15:   */import org.schema.game.common.data.element.ElementKeyMap;
/* 16:   */import xq;
/* 17:   */
/* 18:   */public class ManagerContainerFactoryAddOn
/* 19:   */{
/* 20:   */  public static final float TIME_STEP = 5.0F;
/* 21:   */  private float accumulated;
/* 22:22 */  private final java.util.HashMap map = new java.util.HashMap();
/* 23:   */  private SegmentController segmentController;
/* 24:   */  private boolean initialized;
/* 25:   */  
/* 26:   */  public void initialize(ArrayList paramArrayList, SegmentController paramSegmentController) {
/* 27:27 */    for (java.util.Iterator localIterator = ElementKeyMap.getFactorykeyset().iterator(); localIterator.hasNext();) {
/* 28:28 */      ElementInformation localElementInformation = ElementKeyMap.getInfo(((Short)localIterator.next()).shortValue());
/* 29:29 */      assert (localElementInformation.getFactory() != null);
/* 30:   */      
/* 32:32 */      ManagerModuleCollection localManagerModuleCollection = new ManagerModuleCollection(new FactoryElementManager(paramSegmentController, localElementInformation.getId(), localElementInformation.getFactory().enhancer), localElementInformation.getId(), localElementInformation.getFactory().enhancer);
/* 33:   */      
/* 39:39 */      paramArrayList.add(localManagerModuleCollection);
/* 40:40 */      this.map.put(Short.valueOf(localElementInformation.getId()), localManagerModuleCollection);
/* 41:   */    }
/* 42:   */    
/* 45:45 */    this.segmentController = paramSegmentController;
/* 46:46 */    this.initialized = true; }
/* 47:   */  
/* 48:48 */  private final java.util.HashMap changedSet = new java.util.HashMap();
/* 49:   */  
/* 50:   */  public void update(xq paramxq, boolean paramBoolean) {
/* 51:51 */    assert (this.initialized);
/* 52:   */    
/* 53:53 */    this.accumulated += paramxq.a();
/* 54:54 */    paramxq = 0;
/* 55:55 */    Object localObject1; Object localObject2; while (this.accumulated > 5.0F) {
/* 56:56 */      for (paramBoolean = this.map.values().iterator(); paramBoolean.hasNext();)
/* 57:   */      {
/* 58:58 */        for (localObject2 = (localObject1 = (ManagerModuleCollection)paramBoolean.next()).getCollectionManagers().iterator(); ((java.util.Iterator)localObject2).hasNext();) {
/* 59:59 */          ((FactoryCollectionManager)((java.util.Iterator)localObject2).next()).manufractureStep((FactoryElementManager)((ManagerModuleCollection)localObject1).getElementManager(), this.changedSet);
/* 60:   */        }
/* 61:   */      }
/* 62:62 */      if (paramxq > 2) {
/* 63:63 */        this.accumulated = 0.0F;
/* 64:64 */        break;
/* 65:   */      }
/* 66:66 */      paramxq++;
/* 67:67 */      this.accumulated -= 5.0F;
/* 68:   */    }
/* 69:69 */    if (this.accumulated > 200.0F) {
/* 70:70 */      System.err.println("[FACTORY] WARNING: " + this.segmentController + " accumulated too much time: " + this.accumulated);
/* 71:   */    }
/* 72:72 */    for (paramBoolean = this.changedSet.entrySet().iterator(); paramBoolean.hasNext();) {
/* 73:73 */      if (!((HashSet)(localObject1 = (java.util.Map.Entry)paramBoolean.next()).getValue()).isEmpty())
/* 74:   */      {
/* 75:75 */        (localObject2 = new HashSet()).addAll((Collection)((java.util.Map.Entry)localObject1).getValue());
/* 76:76 */        ((mf)((java.util.Map.Entry)localObject1).getKey()).a((Collection)localObject2);
/* 77:77 */        ((HashSet)((java.util.Map.Entry)localObject1).getValue()).clear();
/* 78:78 */        return;
/* 79:   */      }
/* 80:   */    }
/* 81:   */  }
/* 82:   */  
/* 88:   */  public float getAccumulated()
/* 89:   */  {
/* 90:90 */    return this.accumulated;
/* 91:   */  }
/* 92:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerContainerFactoryAddOn
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */