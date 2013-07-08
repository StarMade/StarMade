package org.schema.game.common.controller.elements;

import class_1041;
import class_371;
import class_48;
import class_52;
import class_796;
import class_798;
import class_844;
import class_916;
import class_941;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.schema.game.common.controller.CannotImmediateRequestOnClientException;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.PointDistributionUnit;
import org.schema.game.server.controller.GameServerController;

public abstract class ElementCollectionManager
{
  private int debugID = debugIdGen++;
  private static int debugIdGen;
  private final List elementCollections = new ArrayList();
  public final LongOpenHashSet rawCollection = new LongOpenHashSet();
  private final SegmentController segmentController;
  private final short enhancerClazz;
  private Object updateLock = new Object();
  private ElementCollectionCalculationThread finishedThread;
  private LongArrayList scheduledListToUpdate;
  private final LongArrayFIFOQueue modsA = new LongArrayFIFOQueue();
  boolean modSwitch = false;
  private long lastDirtyTime;
  private long flagDirty = -1L;
  private long updateDirty = -1L;
  private long updateStatus = -1L;
  private class_48 absPosTmp = new class_48();
  private Long2ObjectOpenHashMap debug = new Long2ObjectOpenHashMap();
  private class_48 absPos = new class_48();
  protected long lastUpdate;
  protected long lastUpdateLocal;
  private boolean stopped;
  private long lastEnqueue;
  protected static final long UPDATE_FREQUENCY_MS = 50L;
  
  public ElementCollectionManager(short paramShort, SegmentController paramSegmentController)
  {
    this.enhancerClazz = paramShort;
    this.segmentController = paramSegmentController;
  }
  
  public void addModded(long paramLong)
  {
    synchronized (this.modsA)
    {
      this.modsA.enqueue(paramLong);
      return;
    }
  }
  
  public void addModded(class_48 paramclass_48)
  {
    long l = ElementCollection.getIndex(paramclass_48);
    addModded(l);
  }
  
  public void addModded(class_916 paramclass_916)
  {
    synchronized (this.modsA)
    {
      this.modsA.enqueue(ElementCollection.getIndex(paramclass_916.field_1139, paramclass_916.field_1140, paramclass_916.field_1141));
      return;
    }
  }
  
  public void addNew(ElementCollection paramElementCollection)
  {
    this.elementCollections.add(paramElementCollection);
  }
  
  public ElementCollection addNew(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController, long paramLong)
  {
    (paramShort = newElementCollection(paramShort, paramElementCollectionManager, paramSegmentController)).addElement(paramLong);
    this.elementCollections.add(paramShort);
    return paramShort;
  }
  
  private ElementCollection addToExistingCollection(long paramLong)
  {
    ElementCollection.getPosFromIndex(paramLong, this.absPos);
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      ElementCollection localElementCollection;
      if ((localElementCollection = (ElementCollection)localIterator.next()).isInsideBB(this.absPos, 1)) {
        for (int i = 0; i < 6; i++)
        {
          this.absPosTmp.b1(this.absPos);
          this.absPosTmp.a1(org.schema.game.common.data.element.Element.DIRECTIONSi[i]);
          if (localElementCollection.contains(this.absPosTmp))
          {
            localElementCollection.addElement(paramLong);
            return localElementCollection;
          }
        }
      }
    }
    return null;
  }
  
  private void checkEmpty()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      ElementCollection localElementCollection;
      if ((localElementCollection = (ElementCollection)localIterator.next()).size() <= 0)
      {
        localArrayList.add(localElementCollection);
        System.err.println("Element Collection is Empty -> removing");
      }
    }
    getCollection().removeAll(localArrayList);
  }
  
  private void checkOverlapping(ElementCollection paramElementCollection, long paramLong)
  {
    for (;;)
    {
      int i = 0;
      Object localObject = null;
      ElementCollection.getPosFromIndex(paramLong, this.absPos);
      Iterator localIterator = getCollection().iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        ElementCollection localElementCollection;
        if (((localElementCollection = (ElementCollection)localIterator.next()) != paramElementCollection) && (localElementCollection.isInsideBB(this.absPos, 1))) {
          for (int j = 0; j < 6; j++)
          {
            this.absPosTmp.b1(this.absPos);
            this.absPosTmp.a1(org.schema.game.common.data.element.Element.DIRECTIONSi[j]);
            if (localElementCollection.contains(this.absPosTmp))
            {
              localElementCollection.merge(paramElementCollection);
              i = 1;
              localObject = localElementCollection;
              break;
            }
          }
        }
      } while (i == 0);
      if (i == 0) {
        break;
      }
      boolean bool = getCollection().remove(paramElementCollection);
      paramElementCollection.cleanUp();
      assert (bool);
      paramElementCollection = localObject;
      this = this;
    }
  }
  
  public void clear()
  {
    stopUpdate();
    collectionCleanUp();
    flagDirty();
  }
  
  private void collectionCleanUp()
  {
    long l1 = System.currentTimeMillis();
    this.rawCollection.clear();
    this.modsA.clear();
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext()) {
      ((ElementCollection)localIterator.next()).cleanUp();
    }
    getCollection().clear();
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[ELEMENTCOLLECTIONMANAGER][CLEAR] WARNING COLLECTION CLEANUP OF " + this.segmentController + " ON " + this.segmentController.getState() + " TOOK " + l2);
    }
  }
  
  public void doAdd(long paramLong)
  {
    if (this.rawCollection.add(paramLong)) {
      flagDirty();
    }
  }
  
  public boolean doRemove(long paramLong)
  {
    if ((paramLong = this.rawCollection.remove(paramLong))) {
      flagDirty();
    }
    return paramLong;
  }
  
  public void flagDirty()
  {
    this.flagDirty += 1L;
    this.lastDirtyTime = System.currentTimeMillis();
  }
  
  public List getCollection()
  {
    return this.elementCollections;
  }
  
  public ManagerContainer getContainer()
  {
    return ((class_798)getSegmentController()).a();
  }
  
  public abstract int getMargin();
  
  protected final SegmentController getSegmentController()
  {
    return this.segmentController;
  }
  
  protected abstract Class getType();
  
  public ElementCollection newElementCollection(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
  {
    try
    {
      (paramShort = ElementCollection.getInstanceOfT(getType(), this.enhancerClazz, paramElementCollectionManager, this.segmentController)).resetAABB();
      return paramShort;
    }
    catch (SecurityException localSecurityException)
    {
      localSecurityException;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException;
    }
    catch (InstantiationException localInstantiationException)
    {
      localInstantiationException;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localNoSuchMethodException;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException;
    }
    throw new RuntimeException("Cannot instantiate class: " + getType());
  }
  
  public ElementCollection newElementCollection(short paramShort, SegmentController paramSegmentController)
  {
    return newElementCollection(paramShort, this, paramSegmentController);
  }
  
  protected abstract void onChangedCollection();
  
  private void onFinishedCollection()
  {
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext()) {
      ((ElementCollection)localIterator.next()).onChangeFinished();
    }
    pieceRefresh();
  }
  
  protected void pieceRefresh() {}
  
  public boolean receiveDistribution(class_844 paramclass_844)
  {
    Iterator localIterator = getCollection().iterator();
    while (localIterator.hasNext())
    {
      ElementCollection localElementCollection = (ElementCollection)localIterator.next();
      try
      {
        if ((localElementCollection.getId() != null) && (localElementCollection.getId().a2(new class_48()).equals(paramclass_844.field_1092)))
        {
          ((PointDistributionUnit)localElementCollection).receiveDistChange(paramclass_844);
          return true;
        }
      }
      catch (CannotImmediateRequestOnClientException localCannotImmediateRequestOnClientException) {}
    }
    return false;
  }
  
  public void remove(class_48 paramclass_48)
  {
    remove(ElementCollection.getIndex(paramclass_48));
  }
  
  public void remove(long paramLong)
  {
    synchronized (this.modsA)
    {
      this.modsA.enqueue(-paramLong);
      return;
    }
  }
  
  public String toString()
  {
    return getSegmentController() + "->" + ElementKeyMap.getInfo(this.enhancerClazz).getName() + "(" + this.debugID + ")";
  }
  
  protected void updateStructure(long arg1)
  {
    if ((??? - this.lastUpdateLocal < 50L) && (this.scheduledListToUpdate == null)) {
      return;
    }
    this.lastUpdateLocal = ???;
    if (!this.modsA.isEmpty()) {
      synchronized (this.modsA)
      {
        while (!this.modsA.isEmpty())
        {
          long l;
          if ((l = this.modsA.dequeueLong()) >= 0L) {
            doAdd(l);
          } else {
            doRemove(-l);
          }
        }
        this.modsA.clear();
      }
    }
    updateCollections();
  }
  
  public short getEnhancerClazz()
  {
    return this.enhancerClazz;
  }
  
  public abstract boolean needsUpdate();
  
  public void update(class_941 paramclass_941) {}
  
  public void updateCollections()
  {
    synchronized (this.updateLock)
    {
      if (this.finishedThread != null)
      {
        this.finishedThread.apply();
        this.finishedThread = null;
        onChangedCollection();
        onFinishedCollection();
      }
      if (this.scheduledListToUpdate != null)
      {
        this.scheduledListToUpdate.addAll(this.rawCollection);
        this.scheduledListToUpdate = null;
        this.updateLock.notify();
      }
      if ((this.flagDirty != this.updateStatus) && (System.currentTimeMillis() - this.lastUpdate > 1000L) && (System.currentTimeMillis() - this.lastEnqueue > 500L))
      {
        this.lastEnqueue = System.currentTimeMillis();
        if (this.segmentController.isOnServer()) {
          ((class_1041)this.segmentController.getState()).a59().a35(this);
        } else {
          ((class_371)this.segmentController.getState()).a4().a35(this);
        }
      }
      return;
    }
  }
  
  public boolean flagPrepareUpdate(LongArrayList paramLongArrayList)
  {
    assert (paramLongArrayList != null);
    synchronized (this.updateLock)
    {
      this.flagDirty = this.updateStatus;
      this.lastUpdate = System.currentTimeMillis();
      this.scheduledListToUpdate = paramLongArrayList;
      try
      {
        assert (this.scheduledListToUpdate != null);
        if (!this.stopped) {
          this.updateLock.wait(1000L);
        } else {
          this.stopped = false;
        }
        if (this.scheduledListToUpdate != null)
        {
          getSegmentController().isOnServer();
          this.scheduledListToUpdate = null;
          flagDirty();
          return false;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        paramLongArrayList = null;
        localInterruptedException.printStackTrace();
      }
    }
    return true;
  }
  
  public void stopUpdate()
  {
    synchronized (this.updateLock)
    {
      this.stopped = true;
      this.updateLock.notify();
      return;
    }
  }
  
  public void flagUpdateFinished(ElementCollectionCalculationThread paramElementCollectionCalculationThread)
  {
    synchronized (this.updateLock)
    {
      this.finishedThread = paramElementCollectionCalculationThread;
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */