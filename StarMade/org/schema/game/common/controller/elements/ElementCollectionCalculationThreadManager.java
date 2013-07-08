/*  1:   */package org.schema.game.common.controller.elements;
/*  2:   */
/*  3:   */import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
/*  4:   */import it.unimi.dsi.fastutil.longs.LongArrayList;
/*  5:   */import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*  6:   */import java.io.PrintStream;
/*  7:   */import org.schema.game.common.controller.SegmentController;
/*  8:   */
/*  9:   */public class ElementCollectionCalculationThreadManager extends Thread implements CollectionCalculationCallback
/* 10:   */{
/* 11:11 */  private final java.util.ArrayList queue = new java.util.ArrayList();
/* 12:   */  
/* 13:13 */  private final LongArrayList rawCollection = new LongArrayList();
/* 14:14 */  private final LongOpenHashSet closedCollection = new LongOpenHashSet();
/* 15:15 */  private final LongArrayFIFOQueue openCollection = new LongArrayFIFOQueue();
/* 16:   */  
/* 17:   */  public void enqueue(ElementCollectionCalculationThread paramElementCollectionCalculationThread) {
/* 18:18 */    synchronized (this.queue) {
/* 19:19 */      if (!this.queue.contains(paramElementCollectionCalculationThread)) {
/* 20:20 */        this.queue.add(paramElementCollectionCalculationThread);
/* 21:21 */        this.queue.notify();
/* 22:   */      }
/* 23:23 */      return;
/* 24:   */    }
/* 25:   */  }
/* 26:   */  
/* 27:   */  public ElementCollectionCalculationThread getNextQueueElement() {
/* 28:28 */    synchronized (this.queue) {
/* 29:29 */      while (this.queue.isEmpty()) {
/* 30:   */        try {
/* 31:31 */          this.queue.wait();
/* 32:32 */        } catch (Exception localException) { 
/* 33:   */          
/* 34:34 */            localException;
/* 35:   */        }
/* 36:   */      }
/* 37:   */      
/* 38:36 */      return (ElementCollectionCalculationThread)this.queue.remove(0);
/* 39:   */    }
/* 40:   */  }
/* 41:   */  
/* 46:   */  public void run()
/* 47:   */  {
/* 48:   */    for (;;)
/* 49:   */    {
/* 50:48 */      ElementCollectionCalculationThread localElementCollectionCalculationThread = getNextQueueElement();
/* 51:   */      
/* 53:51 */      this.openCollection.clear();
/* 54:   */      
/* 56:54 */      this.closedCollection.clear();
/* 57:   */      
/* 58:56 */      this.rawCollection.clear();
/* 59:   */      
/* 64:62 */      if (localElementCollectionCalculationThread.getMan().flagPrepareUpdate(this.rawCollection))
/* 65:   */      {
/* 66:64 */        this.closedCollection.addAll(this.rawCollection);
/* 67:   */        
/* 69:67 */        localElementCollectionCalculationThread.initialize(this.closedCollection, this.openCollection, this);
/* 70:   */        
/* 73:71 */        localElementCollectionCalculationThread.process();
/* 74:   */        
/* 77:75 */        localElementCollectionCalculationThread.flagUpdateFinished();
/* 78:   */      } else {
/* 79:77 */        System.err.println("[ElColManTh][" + localElementCollectionCalculationThread.getMan().getSegmentController().getState() + "] " + localElementCollectionCalculationThread.getMan() + " update timout (object is probably not updating anymore (out of sector, etc)) queued for new update if it starts updating again");
/* 80:   */      }
/* 81:   */    }
/* 82:   */  }
/* 83:   */  
/* 84:   */  public void callback(ElementCollectionCalculationThread paramElementCollectionCalculationThread) {}
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionCalculationThreadManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */