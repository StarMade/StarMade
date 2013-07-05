/*    */ package org.schema.game.common.controller.elements.powercap;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import ld;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*    */ import org.schema.game.common.controller.elements.PowerAddOn;
/*    */ import org.schema.game.common.controller.elements.PowerManagerInterface;
/*    */ 
/*    */ public class PowerCapacityCollectionManager extends ElementCollectionManager
/*    */ {
/*    */   private double maxPower;
/*    */ 
/*    */   public PowerCapacityCollectionManager(SegmentController paramSegmentController)
/*    */   {
/* 16 */     super((short)331, paramSegmentController);
/*    */   }
/*    */ 
/*    */   public int getMargin()
/*    */   {
/* 23 */     return 0;
/*    */   }
/*    */ 
/*    */   public double getMaxPower()
/*    */   {
/* 30 */     return this.maxPower;
/*    */   }
/*    */ 
/*    */   protected Class getType()
/*    */   {
/* 36 */     return PowerCapacityUnit.class;
/*    */   }
/*    */ 
/*    */   protected void onChangedCollection()
/*    */   {
/* 44 */     refreshMaxPower();
/*    */   }
/*    */ 
/*    */   private void refreshMaxPower() {
/* 48 */     this.maxPower = 0.0D;
/* 49 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) {
/* 50 */       double d = Math.pow(((PowerCapacityUnit)localIterator.next())
/* 50 */         .size(), 1.75D);
/* 51 */       this.maxPower += d;
/*    */     }
/*    */ 
/* 54 */     ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn().setMaxPower(this.maxPower);
/*    */   }
/*    */ 
/*    */   public void setMaxPower(double paramDouble)
/*    */   {
/* 62 */     this.maxPower = paramDouble;
/*    */   }
/*    */ 
/*    */   public boolean needsUpdate()
/*    */   {
/* 70 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powercap.PowerCapacityCollectionManager
 * JD-Core Version:    0.6.2
 */