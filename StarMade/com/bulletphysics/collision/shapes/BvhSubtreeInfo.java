package com.bulletphysics.collision.shapes;

import java.io.Serializable;

public class BvhSubtreeInfo
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public final short[] quantizedAabbMin = new short[3];
  public final short[] quantizedAabbMax = new short[3];
  public int rootNodeIndex;
  public int subtreeSize;
  
  public void setAabbFromQuantizeNode(QuantizedBvhNodes quantizedNodes, int nodeId)
  {
    this.quantizedAabbMin[0] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 0));
    this.quantizedAabbMin[1] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 1));
    this.quantizedAabbMin[2] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 2));
    this.quantizedAabbMax[0] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 0));
    this.quantizedAabbMax[1] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 1));
    this.quantizedAabbMax[2] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 2));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.shapes.BvhSubtreeInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */