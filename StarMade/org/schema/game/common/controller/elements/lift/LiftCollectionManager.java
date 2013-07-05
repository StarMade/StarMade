/*     */ package org.schema.game.common.controller.elements.lift;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.controller.elements.ElementCollectionManager;
/*     */ import org.schema.game.common.controller.elements.NTReceiveInterface;
/*     */ import org.schema.game.common.data.element.ElementCollection;
/*     */ import org.schema.game.network.objects.NetworkLiftInterface;
/*     */ import org.schema.schine.network.objects.NetworkObject;
/*     */ import org.schema.schine.network.objects.remote.RemoteBuffer;
/*     */ import org.schema.schine.network.objects.remote.RemoteVector4i;
/*     */ import q;
/*     */ import s;
/*     */ import xq;
/*     */ 
/*     */ public class LiftCollectionManager extends ElementCollectionManager
/*     */   implements NTReceiveInterface
/*     */ {
/*  22 */   private final ArrayList toActivate = new ArrayList();
/*     */ 
/*     */   public LiftCollectionManager(SegmentController paramSegmentController)
/*     */   {
/*  29 */     super((short)113, paramSegmentController);
/*     */   }
/*     */ 
/*     */   public void activate(q paramq, boolean paramBoolean)
/*     */   {
/*  41 */     System.err.println("CHECKING FOR LIFT UNIT IN " + getCollection().size() + " COLLECTIONS");
/*  42 */     long l = ElementCollection.getIndex(paramq);
/*  43 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); )
/*     */     {
/*     */       LiftUnit localLiftUnit;
/*  45 */       if ((
/*  45 */         localLiftUnit = (LiftUnit)localIterator.next())
/*  45 */         .getNeighboringCollection().contains(Long.valueOf(l)))
/*  46 */         if (paramBoolean) {
/*  47 */           System.err.println("ACTIVATING LIFT " + paramq + " " + getSegmentController().getState() + " " + getSegmentController());
/*  48 */           System.err.println("ACTIVATING " + localLiftUnit + "; this unit size " + localLiftUnit.getNeighboringCollection().size() + " / units " + getCollection().size());
/*  49 */           localLiftUnit.activate();
/*     */         } else {
/*  51 */           localLiftUnit.deactivate();
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getMargin()
/*     */   {
/*  62 */     return 0;
/*     */   }
/*     */ 
/*     */   protected Class getType()
/*     */   {
/*  70 */     return LiftUnit.class;
/*     */   }
/*     */ 
/*     */   protected void onChangedCollection()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void update(xq paramxq)
/*     */   {
/*  86 */     for (Iterator localIterator = getCollection().iterator(); localIterator.hasNext(); ) ((LiftUnit)localIterator.next())
/*  87 */         .update(paramxq);
/*     */ 
/*  89 */     if (!this.toActivate.isEmpty())
/*  90 */       synchronized (this.toActivate) {
/*  91 */         paramxq = (s)this.toActivate.remove(0);
/*  92 */         activate(new q(paramxq.a, paramxq.b, paramxq.c), paramxq.d != 0);
/*  93 */         return;
/*     */       }
/*     */   }
/*     */ 
/*     */   public void updateFromNT(NetworkObject paramNetworkObject)
/*     */   {
/*     */     RemoteBuffer localRemoteBuffer;
/* 103 */     for (Iterator localIterator = (
/* 103 */       localRemoteBuffer = ((NetworkLiftInterface)paramNetworkObject).getLiftActivate())
/* 103 */       .getReceiveBuffer().iterator(); localIterator.hasNext(); ) {
/* 104 */       s locals = ((RemoteVector4i)localIterator.next())
/* 104 */         .getVector();
/* 105 */       synchronized (this.toActivate) {
/* 106 */         this.toActivate.add(locals);
/*     */       }
/*     */ 
/* 110 */       if (getSegmentController().isOnServer())
/* 111 */         localRemoteBuffer.add(new RemoteVector4i(locals, paramNetworkObject));
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean needsUpdate()
/*     */   {
/* 122 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.lift.LiftCollectionManager
 * JD-Core Version:    0.6.2
 */