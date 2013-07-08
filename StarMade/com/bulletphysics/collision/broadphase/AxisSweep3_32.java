package com.bulletphysics.collision.broadphase;

import javax.vecmath.Vector3f;

public class AxisSweep3_32
  extends AxisSweep3Internal
{
  public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax)
  {
    this(worldAabbMin, worldAabbMax, 1500000, null);
  }
  
  public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles)
  {
    this(worldAabbMin, worldAabbMax, maxHandles, null);
  }
  
  public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles, OverlappingPairCache pairCache)
  {
    super(worldAabbMin, worldAabbMax, -2, 2147483647, maxHandles, pairCache);
    assert ((maxHandles > 1) && (maxHandles < 2147483647));
  }
  
  protected AxisSweep3Internal.EdgeArray createEdgeArray(int size)
  {
    return new EdgeArrayImpl(size);
  }
  
  protected AxisSweep3Internal.Handle createHandle()
  {
    return new HandleImpl();
  }
  
  protected int getMask()
  {
    return -1;
  }
  
  protected static class HandleImpl
    extends AxisSweep3Internal.Handle
  {
    private int minEdges0;
    private int minEdges1;
    private int minEdges2;
    private int maxEdges0;
    private int maxEdges1;
    private int maxEdges2;
    
    public int getMinEdges(int edgeIndex)
    {
      switch (edgeIndex)
      {
      case 0: 
      default: 
        return this.minEdges0;
      case 1: 
        return this.minEdges1;
      }
      return this.minEdges2;
    }
    
    public void setMinEdges(int edgeIndex, int value)
    {
      switch (edgeIndex)
      {
      case 0: 
        this.minEdges0 = value;
        break;
      case 1: 
        this.minEdges1 = value;
        break;
      case 2: 
        this.minEdges2 = value;
      }
    }
    
    public int getMaxEdges(int edgeIndex)
    {
      switch (edgeIndex)
      {
      case 0: 
      default: 
        return this.maxEdges0;
      case 1: 
        return this.maxEdges1;
      }
      return this.maxEdges2;
    }
    
    public void setMaxEdges(int edgeIndex, int value)
    {
      switch (edgeIndex)
      {
      case 0: 
        this.maxEdges0 = value;
        break;
      case 1: 
        this.maxEdges1 = value;
        break;
      case 2: 
        this.maxEdges2 = value;
      }
    }
  }
  
  protected static class EdgeArrayImpl
    extends AxisSweep3Internal.EdgeArray
  {
    private int[] pos;
    private int[] handle;
    
    public EdgeArrayImpl(int size)
    {
      this.pos = new int[size];
      this.handle = new int[size];
    }
    
    public void swap(int idx1, int idx2)
    {
      int tmpPos = this.pos[idx1];
      int tmpHandle = this.handle[idx1];
      this.pos[idx1] = this.pos[idx2];
      this.handle[idx1] = this.handle[idx2];
      this.pos[idx2] = tmpPos;
      this.handle[idx2] = tmpHandle;
    }
    
    public void set(int dest, int src)
    {
      this.pos[dest] = this.pos[src];
      this.handle[dest] = this.handle[src];
    }
    
    public int getPos(int index)
    {
      return this.pos[index];
    }
    
    public void setPos(int index, int value)
    {
      this.pos[index] = value;
    }
    
    public int getHandle(int index)
    {
      return this.handle[index];
    }
    
    public void setHandle(int index, int value)
    {
      this.handle[index] = value;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3_32
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */