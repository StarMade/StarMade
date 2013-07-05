/*    */ package org.schema.game.common.controller.elements.door;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import javax.vecmath.Vector3f;
/*    */ import ka;
/*    */ import le;
/*    */ import org.schema.game.common.data.element.ElementCollection;
/*    */ import q;
/*    */ import s;
/*    */ 
/*    */ public class DoorUnit extends ElementCollection
/*    */ {
/* 13 */   private q significator = new q();
/* 14 */   private Vector3f topMin = new Vector3f();
/* 15 */   private Vector3f topMax = new Vector3f();
/*    */   private boolean active;
/*    */ 
/*    */   public void activate(le paramle, boolean paramBoolean)
/*    */   {
/* 22 */     paramle = new q();
/* 23 */     for (Iterator localIterator = getNeighboringCollection().iterator(); localIterator.hasNext(); ) {
/* 24 */       getPosFromIndex(((Long)localIterator.next()).longValue(), 
/* 24 */         paramle);
/*    */       s locals;
/* 25 */       (
/* 26 */         locals = new s())
/* 26 */         .a(paramle.a, paramle.b, paramle.c, paramBoolean ? 2 : -2);
/* 27 */       ((ka)getController()).getBlockActivationBuffer().enqueue(locals);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void cleanUp()
/*    */   {
/* 41 */     super.cleanUp();
/*    */   }
/*    */ 
/*    */   public q getSignificator() {
/* 45 */     return this.significator;
/*    */   }
/*    */ 
/*    */   public boolean isActive() {
/* 49 */     return this.active;
/*    */   }
/*    */ 
/*    */   public void refreshDoorCapabilities()
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void significatorUpdate(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 64 */     this.significator.a = (getMax().a - (getMax().a - getMin().a) / 2);
/* 65 */     this.significator.b = getMax().b;
/* 66 */     this.significator.c = (getMax().c - (getMax().c - getMin().c) / 2);
/*    */ 
/* 68 */     this.topMin.set(getMin().a, getMax().b, getMin().c);
/* 69 */     this.topMax.set(getMax().a, getMax().b, getMax().c);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.door.DoorUnit
 * JD-Core Version:    0.6.2
 */