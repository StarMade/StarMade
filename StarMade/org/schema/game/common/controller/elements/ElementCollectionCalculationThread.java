package org.schema.game.common.controller.elements;

import class_48;
import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import java.util.ArrayList;
import java.util.List;
import org.schema.game.common.data.element.ElementCollection;

public class ElementCollectionCalculationThread
{
  private ElementCollection lastElementCollection;
  private LongOpenHashSet closedCollection;
  private LongArrayFIFOQueue openCollection;
  private final ElementCollectionManager man;
  private final class_48 absPosTmp = new class_48();
  private final class_48 absPos = new class_48();
  private CollectionCalculationCallback callback;
  private final ArrayList collections = new ArrayList();
  public static long[] vals = { -1L, 1L, -65534L, 65534L, -4294705156L, 4294705156L };
  
  public static void main(String[] paramArrayOfString)
  {
    for (paramArrayOfString = -100; paramArrayOfString < 100; paramArrayOfString++) {
      for (int i = -100; i < 100; i++) {
        for (int j = -100; j < 100; j++)
        {
          class_48 localclass_481;
          long l1 = ElementCollection.getIndex(localclass_481 = new class_48(j, i, paramArrayOfString));
          for (int k = 0; k < 6; k++)
          {
            class_48 localclass_482;
            (localclass_482 = new class_48(localclass_481)).a1(org.schema.game.common.data.element.Element.DIRECTIONSi[k]);
            long l2 = ElementCollection.getIndex(localclass_482);
            assert (l1 - vals[k] == l2) : (l1 + "; " + l2 + "; " + (l1 - l2) + "; " + vals[k] + "; " + (l1 + vals[k]));
          }
        }
      }
    }
  }
  
  public ElementCollectionCalculationThread(ElementCollectionManager paramElementCollectionManager)
  {
    this.man = paramElementCollectionManager;
  }
  
  public boolean equals(Object paramObject)
  {
    return this.man == ((ElementCollectionCalculationThread)paramObject).man;
  }
  
  public int hashCode()
  {
    return this.man.hashCode();
  }
  
  public void initialize(LongOpenHashSet paramLongOpenHashSet, LongArrayFIFOQueue paramLongArrayFIFOQueue, CollectionCalculationCallback paramCollectionCalculationCallback)
  {
    this.closedCollection = paramLongOpenHashSet;
    this.openCollection = paramLongArrayFIFOQueue;
    this.callback = paramCollectionCalculationCallback;
  }
  
  public void onFinish()
  {
    this.lastElementCollection = null;
    this.closedCollection = null;
    this.openCollection = null;
  }
  
  public void process()
  {
    while (!this.closedCollection.isEmpty()) {
      if (this.openCollection.isEmpty())
      {
        assert (!this.closedCollection.isEmpty());
        LongIterator localLongIterator;
        long l2 = (localLongIterator = this.closedCollection.iterator()).nextLong();
        localLongIterator.remove();
        this.openCollection.enqueue(l2);
        ElementCollection localElementCollection;
        (localElementCollection = getCollectionInstance()).addElement(l2);
        getCollections().add(localElementCollection);
        this.lastElementCollection = localElementCollection;
      }
      else
      {
        while (!this.openCollection.isEmpty())
        {
          long l1 = this.openCollection.dequeue().longValue();
          for (int i = 0; i < 6; i++)
          {
            long l3 = l1 - vals[i];
            if (this.closedCollection.remove(l3))
            {
              this.lastElementCollection.addElement(l3);
              this.openCollection.enqueue(l3);
            }
          }
        }
      }
    }
    onCalculationFinished();
  }
  
  public void apply()
  {
    this.man.getCollection().clear();
    this.man.getCollection().addAll(getCollections());
  }
  
  private void onCalculationFinished()
  {
    this.callback.callback(this);
  }
  
  public ElementCollection getCollectionInstance()
  {
    return getMan().newElementCollection(getMan().getEnhancerClazz(), getMan(), getMan().getSegmentController());
  }
  
  public ElementCollectionManager getMan()
  {
    return this.man;
  }
  
  public void flagUpdateFinished()
  {
    getMan().flagUpdateFinished(this);
  }
  
  public ArrayList getCollections()
  {
    return this.collections;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.ElementCollectionCalculationThread
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */