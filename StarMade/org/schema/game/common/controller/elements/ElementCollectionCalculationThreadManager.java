/*    */ package org.schema.game.common.controller.elements;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
/*    */ import it.unimi.dsi.fastutil.longs.LongArrayList;
/*    */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ 
/*    */ public class ElementCollectionCalculationThreadManager extends Thread
/*    */   implements CollectionCalculationCallback
/*    */ {
/* 11 */   private final ArrayList queue = new ArrayList();
/*    */ 
/* 13 */   private final LongArrayList rawCollection = new LongArrayList();
/* 14 */   private final LongOpenHashSet closedCollection = new LongOpenHashSet();
/* 15 */   private final LongArrayFIFOQueue openCollection = new LongArrayFIFOQueue();
/*    */ 
/*    */   public void enqueue(ElementCollectionCalculationThread paramElementCollectionCalculationThread) {
/* 18 */     synchronized (this.queue) {
/* 19 */       if (!this.queue.contains(paramElementCollectionCalculationThread)) {
/* 20 */         this.queue.add(paramElementCollectionCalculationThread);
/* 21 */         this.queue.notify();
/*    */       }
/* 23 */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   public ElementCollectionCalculationThread getNextQueueElement() {
/* 28 */     synchronized (this.queue) {
/* 29 */       while (this.queue.isEmpty()) {
/*    */         try {
/* 31 */           this.queue.wait();
/*    */         }
/*    */         catch (Exception localException) {
/* 34 */           localException.printStackTrace();
/*    */         }
/*    */       }
/*    */ 
/* 36 */       return (ElementCollectionCalculationThread)this.queue.remove(0);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/*    */     while (true)
/*    */     {
/* 48 */       ElementCollectionCalculationThread localElementCollectionCalculationThread = getNextQueueElement();
/*    */ 
/* 51 */       this.openCollection.clear();
/*    */ 
/* 54 */       this.closedCollection.clear();
/*    */ 
/* 56 */       this.rawCollection.clear();
/*    */ 
/* 62 */       if (localElementCollectionCalculationThread.getMan().flagPrepareUpdate(this.rawCollection))
/*    */       {
/* 64 */         this.closedCollection.addAll(this.rawCollection);
/*    */ 
/* 67 */         localElementCollectionCalculationThread.initialize(this.closedCollection, this.openCollection, this);
/*    */ 
/* 71 */         localElementCollectionCalculationThread.process();
/*    */ 
/* 75 */         localElementCollectionCalculationThread.flagUpdateFinished();
/*    */       } else {
/* 77 */         System.err.println("[ElColManTh][" + localElementCollectionCalculationThread.getMan().getSegmentController().getState() + "] " + localElementCollectionCalculationThread.getMan() + " update timout (object is probably not updating anymore (out of sector, etc)) queued for new update if it starts updating again");
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public void callback(ElementCollectionCalculationThread paramElementCollectionCalculationThread)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionCalculationThreadManager
 * JD-Core Version:    0.6.2
 */