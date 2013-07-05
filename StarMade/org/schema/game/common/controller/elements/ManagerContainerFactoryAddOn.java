/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ import mf;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.factory.FactoryCollectionManager;
/*    */ import org.schema.game.common.controller.elements.factory.FactoryElementManager;
/*    */ import org.schema.game.common.data.element.BlockFactory;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ import xq;
/*    */ 
/*    */ public class ManagerContainerFactoryAddOn
/*    */ {
/*    */   public static final float TIME_STEP = 5.0F;
/*    */   private float accumulated;
/* 22 */   private final HashMap map = new HashMap();
/*    */   private SegmentController segmentController;
/*    */   private boolean initialized;
/* 48 */   private final HashMap changedSet = new HashMap();
/*    */ 
/*    */   public void initialize(ArrayList paramArrayList, SegmentController paramSegmentController)
/*    */   {
/* 27 */     for (Iterator localIterator = ElementKeyMap.getFactorykeyset().iterator(); localIterator.hasNext(); ) {
/* 28 */       ElementInformation localElementInformation = ElementKeyMap.getInfo(((Short)localIterator.next()).shortValue());
/*    */ 
/* 29 */       assert (localElementInformation.getFactory() != null);
/*    */ 
/* 32 */       ManagerModuleCollection localManagerModuleCollection = new ManagerModuleCollection(new FactoryElementManager(paramSegmentController, localElementInformation.getId(), localElementInformation.getFactory().enhancer), localElementInformation.getId(), localElementInformation.getFactory().enhancer);
/*    */ 
/* 39 */       paramArrayList.add(localManagerModuleCollection);
/* 40 */       this.map.put(Short.valueOf(localElementInformation.getId()), localManagerModuleCollection);
/*    */     }
/*    */ 
/* 45 */     this.segmentController = paramSegmentController;
/* 46 */     this.initialized = true;
/*    */   }
/*    */ 
/*    */   public void update(xq paramxq, boolean paramBoolean)
/*    */   {
/* 51 */     assert (this.initialized);
/*    */ 
/* 53 */     this.accumulated += paramxq.a();
/* 54 */     paramxq = 0;
/*    */     Object localObject1;
/*    */     Object localObject2;
/* 55 */     while (this.accumulated > 5.0F) {
/* 56 */       for (paramBoolean = this.map.values().iterator(); paramBoolean.hasNext(); )
/*    */       {
/* 58 */         for (localObject2 = (
/* 58 */           localObject1 = (ManagerModuleCollection)paramBoolean.next())
/* 58 */           .getCollectionManagers().iterator(); ((Iterator)localObject2).hasNext(); ) ((FactoryCollectionManager)((Iterator)localObject2).next())
/* 59 */             .manufractureStep((FactoryElementManager)((ManagerModuleCollection)localObject1).getElementManager(), this.changedSet);
/*    */       }
/*    */ 
/* 62 */       if (paramxq > 2) {
/* 63 */         this.accumulated = 0.0F;
/* 64 */         break;
/*    */       }
/* 66 */       paramxq++;
/* 67 */       this.accumulated -= 5.0F;
/*    */     }
/* 69 */     if (this.accumulated > 200.0F) {
/* 70 */       System.err.println("[FACTORY] WARNING: " + this.segmentController + " accumulated too much time: " + this.accumulated);
/*    */     }
/* 72 */     for (paramBoolean = this.changedSet.entrySet().iterator(); paramBoolean.hasNext(); )
/* 73 */       if (!((HashSet)(
/* 73 */         localObject1 = (Map.Entry)paramBoolean.next())
/* 73 */         .getValue()).isEmpty()) {
/* 74 */         (
/* 75 */           localObject2 = new HashSet())
/* 75 */           .addAll((Collection)((Map.Entry)localObject1).getValue());
/* 76 */         ((mf)((Map.Entry)localObject1).getKey()).a((Collection)localObject2);
/* 77 */         ((HashSet)((Map.Entry)localObject1).getValue()).clear();
/* 78 */         return;
/*    */       }
/*    */   }
/*    */ 
/*    */   public float getAccumulated()
/*    */   {
/* 90 */     return this.accumulated;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ManagerContainerFactoryAddOn
 * JD-Core Version:    0.6.2
 */