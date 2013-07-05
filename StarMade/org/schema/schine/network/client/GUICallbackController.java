/*    */ package org.schema.schine.network.client;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import xp;
/*    */ import ys;
/*    */ import yz;
/*    */ 
/*    */ public class GUICallbackController
/*    */ {
/* 11 */   private final ArrayList guiCallbacks = new ArrayList();
/* 12 */   private final ArrayList callingGUIElements = new ArrayList();
/*    */ 
/*    */   public void addCallback(ys paramys, yz paramyz)
/*    */   {
/*    */     int i;
/* 16 */     if (((
/* 16 */       i = this.guiCallbacks.indexOf(paramys)) < 0) || 
/* 16 */       (i != this.callingGUIElements.indexOf(paramyz))) {
/* 17 */       this.guiCallbacks.add(paramys);
/* 18 */       this.callingGUIElements.add(paramyz);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void execute(xp paramxp) {
/* 23 */     assert (this.guiCallbacks.size() == this.callingGUIElements.size());
/* 24 */     for (int i = 0; i < this.guiCallbacks.size(); i++) {
/* 25 */       ys localys = (ys)this.guiCallbacks.get(i);
/* 26 */       yz localyz = (yz)this.callingGUIElements.get(i);
/* 27 */       localys.a(localyz, paramxp);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void reset() {
/* 32 */     this.guiCallbacks.clear();
/* 33 */     this.callingGUIElements.clear();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.schine.network.client.GUICallbackController
 * JD-Core Version:    0.6.2
 */