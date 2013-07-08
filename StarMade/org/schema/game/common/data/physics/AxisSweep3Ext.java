package org.schema.game.common.data.physics;

import com.bulletphysics.collision.broadphase.AxisSweep3;
import com.bulletphysics.collision.broadphase.AxisSweep3.HandleImpl;
import com.bulletphysics.collision.broadphase.AxisSweep3Internal.EdgeArray;
import com.bulletphysics.collision.broadphase.AxisSweep3Internal.Handle;
import com.bulletphysics.collision.broadphase.OverlappingPairCache;
import java.io.PrintStream;
import javax.vecmath.Vector3f;

public class AxisSweep3Ext
  extends AxisSweep3
{
  public AxisSweep3Ext(Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt, OverlappingPairCache paramOverlappingPairCache)
  {
    super(paramVector3f1, paramVector3f2, paramInt, paramOverlappingPairCache);
  }
  
  public AxisSweep3Ext(Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt)
  {
    super(paramVector3f1, paramVector3f2, paramInt);
  }
  
  public AxisSweep3Ext(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    super(paramVector3f1, paramVector3f2);
  }
  
  public void cleanUpReferences()
  {
    for (int i = 0; i < this.pHandles.length; i++) {
      this.pHandles[i].clientObject = null;
    }
  }
  
  private boolean assertNonNull()
  {
    for (int i = 0; i < this.maxHandles; i++) {
      assert (this.pHandles[i] != null) : i;
    }
    return true;
  }
  
  protected AxisSweep3Internal.EdgeArray createEdgeArray(int paramInt)
  {
    return new AxisSweep3Ext.EdgeArrayImplExt(paramInt);
  }
  
  protected int allocHandle()
  {
    if (this.firstFreeHandle == 0)
    {
      AxisSweep3Internal.Handle[] arrayOfHandle1 = this.pHandles;
      int j = (this.pHandles.length - 1 << 1) + 1;
      System.err.println("[Physics][AXIS-SWEEP] Handle Array grows: " + arrayOfHandle1.length + " -> " + j);
      this.pHandles = new AxisSweep3.HandleImpl[j];
      for (AxisSweep3Internal.Handle[] arrayOfHandle2 = 0; arrayOfHandle2 < arrayOfHandle1.length; arrayOfHandle2++) {
        this.pHandles[arrayOfHandle2] = arrayOfHandle1[arrayOfHandle2];
      }
      arrayOfHandle2 = arrayOfHandle1.length;
      this.maxHandles = j;
      for (arrayOfHandle1 = arrayOfHandle2; arrayOfHandle1 < this.maxHandles; arrayOfHandle1++)
      {
        this.pHandles[arrayOfHandle1] = createHandle();
        this.pHandles[arrayOfHandle1].setNextFree(arrayOfHandle1 + 1);
      }
      this.pHandles[(this.maxHandles - 1)].setNextFree(0);
      this.firstFreeHandle = arrayOfHandle2;
      for (i = 0; i < 3; i++)
      {
        AxisSweep3Internal.EdgeArray localEdgeArray = this.pEdges[i];
        this.pEdges[i] = createEdgeArray(this.maxHandles << 1);
        ((AxisSweep3Ext.EdgeArrayImplExt)this.pEdges[i]).insert((AxisSweep3Ext.EdgeArrayImplExt)localEdgeArray);
      }
      assert (assertNonNull());
    }
    assert (this.firstFreeHandle != 0);
    int i = this.firstFreeHandle;
    this.firstFreeHandle = getHandle(i).getNextFree();
    this.numHandles += 1;
    return i;
  }
  
  public void cleanUp()
  {
    for (int i = 0; i < this.pHandles.length; i++)
    {
      this.pHandles[i].clientObject = null;
      this.pHandles[i] = null;
    }
    for (i = 0; i < this.pEdges.length; i++) {
      this.pEdges[i] = null;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.AxisSweep3Ext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */