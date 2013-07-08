package com.bulletphysics.collision.shapes;

import java.io.Serializable;

public class QuantizedBvhNodes
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private static final int STRIDE = 4;
  private int[] buf;
  private int size = 0;
  
  public QuantizedBvhNodes()
  {
    resize(16);
  }
  
  public int add()
  {
    while (this.size + 1 >= capacity()) {
      resize(capacity() * 2);
    }
    return this.size++;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public int capacity()
  {
    return this.buf.length / 4;
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public void resize(int num)
  {
    int[] oldBuf = this.buf;
    this.buf = new int[num * 4];
    if (oldBuf != null) {
      System.arraycopy(oldBuf, 0, this.buf, 0, Math.min(oldBuf.length, this.buf.length));
    }
  }
  
  public static int getNodeSize()
  {
    return 16;
  }
  
  public void set(int destId, QuantizedBvhNodes srcNodes, int srcId)
  {
    int[] buf = this.buf;
    int[] srcBuf = srcNodes.buf;
    buf[(destId * 4 + 0)] = srcBuf[(srcId * 4 + 0)];
    buf[(destId * 4 + 1)] = srcBuf[(srcId * 4 + 1)];
    buf[(destId * 4 + 2)] = srcBuf[(srcId * 4 + 2)];
    buf[(destId * 4 + 3)] = srcBuf[(srcId * 4 + 3)];
  }
  
  public void swap(int id1, int id2)
  {
    int[] buf = this.buf;
    int temp0 = buf[(id1 * 4 + 0)];
    int temp1 = buf[(id1 * 4 + 1)];
    int temp2 = buf[(id1 * 4 + 2)];
    int temp3 = buf[(id1 * 4 + 3)];
    buf[(id1 * 4 + 0)] = buf[(id2 * 4 + 0)];
    buf[(id1 * 4 + 1)] = buf[(id2 * 4 + 1)];
    buf[(id1 * 4 + 2)] = buf[(id2 * 4 + 2)];
    buf[(id1 * 4 + 3)] = buf[(id2 * 4 + 3)];
    buf[(id2 * 4 + 0)] = temp0;
    buf[(id2 * 4 + 1)] = temp1;
    buf[(id2 * 4 + 2)] = temp2;
    buf[(id2 * 4 + 3)] = temp3;
  }
  
  public int getQuantizedAabbMin(int nodeId, int index)
  {
    switch (index)
    {
    case 0: 
    default: 
      return this.buf[(nodeId * 4 + 0)] & 0xFFFF;
    case 1: 
      return this.buf[(nodeId * 4 + 0)] >>> 16 & 0xFFFF;
    }
    return this.buf[(nodeId * 4 + 1)] & 0xFFFF;
  }
  
  public long getQuantizedAabbMin(int nodeId)
  {
    return this.buf[(nodeId * 4 + 0)] & 0xFFFFFFFF | (this.buf[(nodeId * 4 + 1)] & 0xFFFF) << 32;
  }
  
  public void setQuantizedAabbMin(int nodeId, long value)
  {
    this.buf[(nodeId * 4 + 0)] = ((int)value);
    setQuantizedAabbMin(nodeId, 2, (short)(int)((value & 0x0) >>> 32));
  }
  
  public void setQuantizedAabbMax(int nodeId, long value)
  {
    setQuantizedAabbMax(nodeId, 0, (short)(int)value);
    this.buf[(nodeId * 4 + 2)] = ((int)(value >>> 16));
  }
  
  public void setQuantizedAabbMin(int nodeId, int index, int value)
  {
    switch (index)
    {
    case 0: 
      this.buf[(nodeId * 4 + 0)] = (this.buf[(nodeId * 4 + 0)] & 0xFFFF0000 | value & 0xFFFF);
      break;
    case 1: 
      this.buf[(nodeId * 4 + 0)] = (this.buf[(nodeId * 4 + 0)] & 0xFFFF | (value & 0xFFFF) << 16);
      break;
    case 2: 
      this.buf[(nodeId * 4 + 1)] = (this.buf[(nodeId * 4 + 1)] & 0xFFFF0000 | value & 0xFFFF);
    }
  }
  
  public int getQuantizedAabbMax(int nodeId, int index)
  {
    switch (index)
    {
    case 0: 
    default: 
      return this.buf[(nodeId * 4 + 1)] >>> 16 & 0xFFFF;
    case 1: 
      return this.buf[(nodeId * 4 + 2)] & 0xFFFF;
    }
    return this.buf[(nodeId * 4 + 2)] >>> 16 & 0xFFFF;
  }
  
  public long getQuantizedAabbMax(int nodeId)
  {
    return (this.buf[(nodeId * 4 + 1)] & 0xFFFF0000) >>> 16 | (this.buf[(nodeId * 4 + 2)] & 0xFFFFFFFF) << 16;
  }
  
  public void setQuantizedAabbMax(int nodeId, int index, int value)
  {
    switch (index)
    {
    case 0: 
      this.buf[(nodeId * 4 + 1)] = (this.buf[(nodeId * 4 + 1)] & 0xFFFF | (value & 0xFFFF) << 16);
      break;
    case 1: 
      this.buf[(nodeId * 4 + 2)] = (this.buf[(nodeId * 4 + 2)] & 0xFFFF0000 | value & 0xFFFF);
      break;
    case 2: 
      this.buf[(nodeId * 4 + 2)] = (this.buf[(nodeId * 4 + 2)] & 0xFFFF | (value & 0xFFFF) << 16);
    }
  }
  
  public int getEscapeIndexOrTriangleIndex(int nodeId)
  {
    return this.buf[(nodeId * 4 + 3)];
  }
  
  public void setEscapeIndexOrTriangleIndex(int nodeId, int value)
  {
    this.buf[(nodeId * 4 + 3)] = value;
  }
  
  public boolean isLeafNode(int nodeId)
  {
    return getEscapeIndexOrTriangleIndex(nodeId) >= 0;
  }
  
  public int getEscapeIndex(int nodeId)
  {
    assert (!isLeafNode(nodeId));
    return -getEscapeIndexOrTriangleIndex(nodeId);
  }
  
  public int getTriangleIndex(int nodeId)
  {
    assert (isLeafNode(nodeId));
    return getEscapeIndexOrTriangleIndex(nodeId) & 0x1FFFFF;
  }
  
  public int getPartId(int nodeId)
  {
    assert (isLeafNode(nodeId));
    return getEscapeIndexOrTriangleIndex(nodeId) >>> 21;
  }
  
  public static int getCoord(long vec, int index)
  {
    switch (index)
    {
    case 0: 
    default: 
      return (int)(vec & 0xFFFF) & 0xFFFF;
    case 1: 
      return (int)((vec & 0xFFFF0000) >>> 16) & 0xFFFF;
    }
    return (int)((vec & 0x0) >>> 32) & 0xFFFF;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.QuantizedBvhNodes
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */