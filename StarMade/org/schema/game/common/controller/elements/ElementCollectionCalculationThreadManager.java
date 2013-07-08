package org.schema.game.common.controller.elements;

import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.io.PrintStream;
import java.util.ArrayList;
import org.schema.game.common.controller.SegmentController;

public class ElementCollectionCalculationThreadManager
  extends Thread
  implements CollectionCalculationCallback
{
  private final ArrayList queue = new ArrayList();
  private final LongArrayList rawCollection = new LongArrayList();
  private final LongOpenHashSet closedCollection = new LongOpenHashSet();
  private final LongArrayFIFOQueue openCollection = new LongArrayFIFOQueue();
  
  public void enqueue(ElementCollectionCalculationThread paramElementCollectionCalculationThread)
  {
    synchronized (this.queue)
    {
      if (!this.queue.contains(paramElementCollectionCalculationThread))
      {
        this.queue.add(paramElementCollectionCalculationThread);
        this.queue.notify();
      }
      return;
    }
  }
  
  public ElementCollectionCalculationThread getNextQueueElement()
  {
    synchronized (this.queue)
    {
      while (this.queue.isEmpty()) {
        try
        {
          this.queue.wait();
        }
        catch (Exception localException)
        {
          localException;
        }
      }
      return (ElementCollectionCalculationThread)this.queue.remove(0);
    }
  }
  
  public void run()
  {
    for (;;)
    {
      ElementCollectionCalculationThread localElementCollectionCalculationThread = getNextQueueElement();
      this.openCollection.clear();
      this.closedCollection.clear();
      this.rawCollection.clear();
      if (localElementCollectionCalculationThread.getMan().flagPrepareUpdate(this.rawCollection))
      {
        this.closedCollection.addAll(this.rawCollection);
        localElementCollectionCalculationThread.initialize(this.closedCollection, this.openCollection, this);
        localElementCollectionCalculationThread.process();
        localElementCollectionCalculationThread.flagUpdateFinished();
      }
      else
      {
        System.err.println("[ElColManTh][" + localElementCollectionCalculationThread.getMan().getSegmentController().getState() + "] " + localElementCollectionCalculationThread.getMan() + " update timout (object is probably not updating anymore (out of sector, etc)) queued for new update if it starts updating again");
      }
    }
  }
  
  public void callback(ElementCollectionCalculationThread paramElementCollectionCalculationThread) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionCalculationThreadManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */