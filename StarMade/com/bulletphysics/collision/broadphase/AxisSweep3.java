package com.bulletphysics.collision.broadphase;

import javax.vecmath.Vector3f;

public class AxisSweep3
  extends AxisSweep3Internal
{
  public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax)
  {
    this(worldAabbMin, worldAabbMax, 16384, null);
  }
  
  public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles)
  {
    this(worldAabbMin, worldAabbMax, maxHandles, null);
  }
  
  public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles, OverlappingPairCache pairCache)
  {
    super(worldAabbMin, worldAabbMax, 65534, 65535, maxHandles, pairCache);
    assert ((maxHandles > 1) && (maxHandles < 32767));
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
    return 65535;
  }
  
  protected static class HandleImpl
    extends AxisSweep3Internal.Handle
  {
    private short minEdges0;
    private short minEdges1;
    private short minEdges2;
    private short maxEdges0;
    private short maxEdges1;
    private short maxEdges2;
    
    public int getMinEdges(int edgeIndex)
    {
      switch (edgeIndex)
      {
      case 0: 
      default: 
        return this.minEdges0 & 0xFFFF;
      case 1: 
        return this.minEdges1 & 0xFFFF;
      }
      return this.minEdges2 & 0xFFFF;
    }
    
    public void setMinEdges(int edgeIndex, int value)
    {
      switch (edgeIndex)
      {
      case 0: 
        this.minEdges0 = ((short)value);
        break;
      case 1: 
        this.minEdges1 = ((short)value);
        break;
      case 2: 
        this.minEdges2 = ((short)value);
      }
    }
    
    public int getMaxEdges(int edgeIndex)
    {
      switch (edgeIndex)
      {
      case 0: 
      default: 
        return this.maxEdges0 & 0xFFFF;
      case 1: 
        return this.maxEdges1 & 0xFFFF;
      }
      return this.maxEdges2 & 0xFFFF;
    }
    
    public void setMaxEdges(int edgeIndex, int value)
    {
      switch (edgeIndex)
      {
      case 0: 
        this.maxEdges0 = ((short)value);
        break;
      case 1: 
        this.maxEdges1 = ((short)value);
        break;
      case 2: 
        this.maxEdges2 = ((short)value);
      }
    }
  }
  
  protected static class EdgeArrayImpl
    extends AxisSweep3Internal.EdgeArray
  {
    private short[] pos;
    private short[] handle;
    
    public EdgeArrayImpl(int size)
    {
      this.pos = new short[size];
      this.handle = new short[size];
    }
    
    public void swap(int idx1, int idx2)
    {
      short tmpPos = this.pos[idx1];
      short tmpHandle = this.handle[idx1];
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
      return this.pos[index] & 0xFFFF;
    }
    
    public void setPos(int index, int value)
    {
      this.pos[index] = ((short)value);
    }
    
    public int getHandle(int index)
    {
      return this.handle[index] & 0xFFFF;
    }
    
    public void setHandle(int index, int value)
    {
      this.handle[index] = ((short)value);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */