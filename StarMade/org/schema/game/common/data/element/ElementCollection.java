package org.schema.game.common.data.element;

import class_48;
import class_747;
import class_796;
import class_886;
import com.bulletphysics.linearmath.AabbUtil2;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ElementCollectionManager;
import org.schema.game.common.data.world.Segment;

public abstract class ElementCollection
  extends Observable
{
  protected static final long SHORT_MAX2 = 65534L;
  protected static final long SHORT_MAX2x2 = 4294705156L;
  private final LongArrayList neighboringCollection = new LongArrayList();
  private final class_48 min = new class_48(2147483647, 2147483647, 2147483647);
  private final class_48 max = new class_48(-2147483648, -2147483648, -2147483648);
  protected ElementCollectionManager col;
  private SegmentController controller;
  private short clazzId;
  private final Vector3f halfSize = new Vector3f();
  private final class_48 minScaled = new class_48();
  private final class_48 maxScaled = new class_48();
  private final class_48 idPos = new class_48(2147483647, 2147483647, 2147483647);
  private class_796 field_1668 = null;
  private final class_48 posTmp = new class_48();
  private boolean radiusChanged = true;
  private float radius;
  private boolean idChanged;
  private class_48 absPos = new class_48();
  class_48 mPos = new class_48();
  
  public static ElementCollection getInstanceOfT(Class paramClass, short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
  {
    (paramClass = (ElementCollection)paramClass.newInstance()).initialize(paramShort, paramElementCollectionManager, paramSegmentController);
    return paramClass;
  }
  
  public static final long getIndex(class_48 paramclass_48)
  {
    return getIndex(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477);
  }
  
  public static final long getIndex(int paramInt1, int paramInt2, int paramInt3)
  {
    long l1 = paramInt1 + 32767;
    long l2 = paramInt2 + 32767;
    long l3;
    if ((l3 = (paramInt3 + 32767) * 4294705156L + l2 * 65534L + l1) < 0L) {
      throw new IllegalArgumentException("ElementCollection Index out of bounds: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + " -> " + l3);
    }
    return l3;
  }
  
  public static class_48 getPosFromIndex(long paramLong, class_48 paramclass_48)
  {
    long l1 = paramLong / 4294705156L;
    long l2 = (paramLong -= l1 * 4294705156L) / 65534L;
    paramLong -= l2 * 65534L;
    paramclass_48.b((int)(paramLong - 32767L), (int)(l2 - 32767L), (int)(l1 - 32767L));
    return paramclass_48;
  }
  
  public static void writeIndexAsShortPos(long paramLong, DataOutputStream paramDataOutputStream)
  {
    long l1 = paramLong / 4294705156L;
    long l2 = (paramLong -= l1 * 4294705156L) / 65534L;
    paramLong -= l2 * 65534L;
    paramDataOutputStream.writeShort((short)(int)(paramLong - 32767L));
    paramDataOutputStream.writeShort((short)(int)(l2 - 32767L));
    paramDataOutputStream.writeShort((short)(int)(l1 - 32767L));
  }
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new class_48();
    class_48 localclass_48 = new class_48();
    for (int i = -32767; i < 32767; i++)
    {
      System.err.println("Z " + i);
      for (int j = -32767; j < 32767; j++) {
        for (int k = -32767; k < 32767; k++)
        {
          paramArrayOfString.b(k, j, i);
          long l;
          getPosFromIndex(l = getIndex(paramArrayOfString), localclass_48);
          if (!paramArrayOfString.equals(localclass_48)) {
            throw new IllegalArgumentException(paramArrayOfString + "; " + localclass_48 + "; index " + l);
          }
        }
      }
    }
  }
  
  public static boolean isOverlapping(ElementCollection paramElementCollection1, ElementCollection paramElementCollection2, int paramInt)
  {
    Vector3f localVector3f1 = new Vector3f(paramElementCollection2.max.field_475 + paramInt, paramElementCollection2.max.field_476 + paramInt, paramElementCollection2.max.field_477 + paramInt);
    paramElementCollection2 = new Vector3f(paramElementCollection2.min.field_475 - paramInt, paramElementCollection2.min.field_476 - paramInt, paramElementCollection2.min.field_477 - paramInt);
    Vector3f localVector3f2 = new Vector3f(paramElementCollection1.max.field_475 + paramInt, paramElementCollection1.max.field_476 + paramInt, paramElementCollection1.max.field_477 + paramInt);
    return AabbUtil2.testAabbAgainstAabb2(new Vector3f(paramElementCollection1.min.field_475 - paramInt, paramElementCollection1.min.field_476 - paramInt, paramElementCollection1.min.field_477 - paramInt), localVector3f2, paramElementCollection2, localVector3f1);
  }
  
  public boolean addElement(long paramLong)
  {
    boolean bool;
    if ((bool = this.neighboringCollection.add(paramLong)))
    {
      long l1 = paramLong / 4294705156L;
      long l2 = (paramLong -= l1 * 4294705156L) / 65534L;
      paramLong = (int)(paramLong - l2 * 65534L - 32767L);
      int i = (int)(l2 - 32767L);
      int j = (int)(l1 - 32767L);
      updateBB(paramLong, i, j);
    }
    return bool;
  }
  
  public void cleanUp()
  {
    deleteObservers();
    clear();
  }
  
  public void clear()
  {
    this.neighboringCollection.clear();
  }
  
  public boolean contains(class_48 paramclass_48)
  {
    return this.neighboringCollection.contains(getIndex(paramclass_48));
  }
  
  public Vector3f getCenter(Vector3f paramVector3f)
  {
    paramVector3f.set(this.max.field_475 - Math.abs(this.max.field_475 - this.min.field_475) / 2.0F, this.max.field_476 - Math.abs(this.max.field_476 - this.min.field_476) / 2.0F, this.max.field_477 - Math.abs(this.max.field_477 - this.min.field_477) / 2.0F);
    return paramVector3f;
  }
  
  public class_796 getId()
  {
    if (this.idChanged) {
      try
      {
        this.field_1668 = this.controller.getSegmentBuffer().a9(this.idPos, true);
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
    }
    return this.field_1668;
  }
  
  public class_48 getMax()
  {
    return this.max;
  }
  
  public class_48 getMin()
  {
    return this.min;
  }
  
  public Collection getNeighboringCollection()
  {
    return this.neighboringCollection;
  }
  
  public Vector3f getOpenGLCenter(Vector3f paramVector3f)
  {
    paramVector3f.set(this.max.field_475 - Math.abs(this.max.field_475 - this.min.field_475) / 2.0F - 8.0F, this.max.field_476 - Math.abs(this.max.field_476 - this.min.field_476) / 2.0F - 8.0F, this.max.field_477 - Math.abs(this.max.field_477 - this.min.field_477) / 2.0F - 8.0F);
    return paramVector3f;
  }
  
  public float getRadius()
  {
    if (this.radiusChanged)
    {
      this.minScaled.b1(this.min);
      this.minScaled.c(this.col.getMargin(), this.col.getMargin(), this.col.getMargin());
      this.minScaled.c1(class_747.field_136);
      this.minScaled.a5(1);
      this.maxScaled.b1(this.max);
      this.maxScaled.a(this.col.getMargin(), this.col.getMargin(), this.col.getMargin());
      this.maxScaled.c1(class_747.field_136);
      this.maxScaled.a5(1);
      this.halfSize.set((this.maxScaled.field_475 - this.minScaled.field_475) / 2.0F, (this.maxScaled.field_476 - this.minScaled.field_476) / 2.0F, (this.maxScaled.field_477 - this.minScaled.field_477) / 2.0F);
      this.radiusChanged = false;
      this.radius = this.halfSize.length();
    }
    return this.radius;
  }
  
  public abstract class_48 getSignificator();
  
  public void initialize(short paramShort, ElementCollectionManager paramElementCollectionManager, SegmentController paramSegmentController)
  {
    this.col = paramElementCollectionManager;
    this.controller = paramSegmentController;
    this.clazzId = paramShort;
  }
  
  public boolean isInsideBB(class_48 paramclass_48)
  {
    return isInsideBB(paramclass_48, 0);
  }
  
  public boolean isInsideBB(class_48 paramclass_48, int paramInt)
  {
    return (paramclass_48.field_475 >= this.min.field_475 - paramInt) && (paramclass_48.field_475 <= this.max.field_475 + paramInt) && (paramclass_48.field_476 >= this.min.field_476 - paramInt) && (paramclass_48.field_476 <= this.max.field_476 + paramInt) && (paramclass_48.field_477 >= this.min.field_477 - paramInt) && (paramclass_48.field_477 <= this.max.field_477 + paramInt);
  }
  
  public void merge(ElementCollection paramElementCollection)
  {
    merge(paramElementCollection.neighboringCollection);
  }
  
  private void merge(Collection paramCollection)
  {
    if (paramCollection.size() > 0)
    {
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        long l1;
        getPosFromIndex(l1 = ((Long)localIterator.next()).longValue(), this.mPos);
        long l2 = l1 / 4294705156L;
        long l3 = (l1 -= l2 * 4294705156L) / 65534L;
        int i = (int)(l1 - l3 * 65534L - 32767L);
        int j = (int)(l3 - 32767L);
        int k = (int)(l2 - 32767L);
        updateBB(i, j, k);
      }
      this.neighboringCollection.addAll(paramCollection);
    }
  }
  
  public boolean narrowTest(ElementCollection paramElementCollection)
  {
    paramElementCollection = paramElementCollection.neighboringCollection.iterator();
    while (paramElementCollection.hasNext())
    {
      getPosFromIndex(((Long)paramElementCollection.next()).longValue(), this.absPos);
      for (int i = 0; i < 6; i++)
      {
        this.posTmp.b1(this.absPos);
        this.posTmp.a1(Element.DIRECTIONSi[i]);
        if (this.neighboringCollection.contains(getIndex(this.posTmp))) {
          return true;
        }
      }
    }
    return false;
  }
  
  public void onChangeFinished() {}
  
  public void onRemove(class_48 paramclass_48) {}
  
  public boolean overlaps(ElementCollection paramElementCollection, int paramInt)
  {
    return isOverlapping(this, paramElementCollection, paramInt);
  }
  
  public boolean remove(class_48 paramclass_48)
  {
    boolean bool;
    if ((bool = this.neighboringCollection.remove(paramclass_48)))
    {
      updateBBFull();
      onRemove(paramclass_48);
    }
    return bool;
  }
  
  protected abstract void significatorUpdate(int paramInt1, int paramInt2, int paramInt3);
  
  public int size()
  {
    return this.neighboringCollection.size();
  }
  
  public String toString()
  {
    return getClass().getSimpleName() + hashCode() + "[" + getMin() + "/" + getMax() + "](" + this.neighboringCollection.size() + ")";
  }
  
  private void updateBBFull()
  {
    if (this.neighboringCollection.isEmpty()) {
      resetAABB();
    }
    getSignificator().b(2147483647, 2147483647, 2147483647);
    this.min.b(2147483647, 2147483647, 2147483647);
    this.max.b(-2147483648, -2147483648, -2147483648);
    this.idPos.b(2147483647, 2147483647, 2147483647);
    Iterator localIterator = this.neighboringCollection.iterator();
    while (localIterator.hasNext())
    {
      getPosFromIndex(((Long)localIterator.next()).longValue(), this.absPos);
      if ((this.absPos.field_475 < this.idPos.field_475) || ((this.absPos.field_475 == this.idPos.field_475) && (this.absPos.field_476 < this.idPos.field_476)) || ((this.absPos.field_475 == this.idPos.field_475) && (this.absPos.field_476 == this.idPos.field_476) && (this.absPos.field_477 < this.idPos.field_477))) {
        this.idPos.b1(this.absPos);
      }
      this.min.field_475 = Math.min(this.absPos.field_475, this.min.field_475);
      this.min.field_476 = Math.min(this.absPos.field_476, this.min.field_476);
      this.min.field_477 = Math.min(this.absPos.field_477, this.min.field_477);
      this.max.field_475 = Math.max(this.absPos.field_475, this.max.field_475);
      this.max.field_476 = Math.max(this.absPos.field_476, this.max.field_476);
      this.max.field_477 = Math.max(this.absPos.field_477, this.max.field_477);
      significatorUpdate(this.absPos.field_475, this.absPos.field_476, this.absPos.field_477);
    }
    this.field_1668 = this.controller.getSegmentBuffer().a9(this.idPos, true);
    this.radiusChanged = true;
  }
  
  public void resetAABB()
  {
    getSignificator().b(0, 0, 0);
    this.min.b(2147483647, 2147483647, 2147483647);
    this.max.b(-2147483648, -2147483648, -2147483648);
    this.field_1668 = null;
    this.minScaled.b(0, 0, 0);
    this.maxScaled.b(0, 0, 0);
    this.halfSize.set(0.0F, 0.0F, 0.0F);
    this.idPos.b(2147483647, 2147483647, 2147483647);
    this.radiusChanged = true;
    this.idChanged = true;
  }
  
  protected void updateBB(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 < this.idPos.field_475) || (paramInt2 < this.idPos.field_476) || (paramInt3 < this.idPos.field_477))
    {
      this.idPos.b(paramInt1, paramInt2, paramInt3);
      this.idChanged = true;
    }
    this.min.field_475 = Math.min(paramInt1, this.min.field_475);
    this.min.field_476 = Math.min(paramInt2, this.min.field_476);
    this.min.field_477 = Math.min(paramInt3, this.min.field_477);
    this.max.field_475 = Math.max(paramInt1, this.max.field_475);
    this.max.field_476 = Math.max(paramInt2, this.max.field_476);
    this.max.field_477 = Math.max(paramInt3, this.max.field_477);
    significatorUpdate(paramInt1, paramInt2, paramInt3);
    this.radiusChanged = true;
  }
  
  public short getClazzId()
  {
    return this.clazzId;
  }
  
  public SegmentController getController()
  {
    return this.controller;
  }
  
  public static long getIndex(byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment)
  {
    return getIndex(paramSegment.field_34.field_475 + paramByte1, paramSegment.field_34.field_476 + paramByte2, paramSegment.field_34.field_477 + paramByte3);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.element.ElementCollection
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */