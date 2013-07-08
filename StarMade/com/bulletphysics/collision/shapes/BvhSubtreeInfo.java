/*  1:   */package com.bulletphysics.collision.shapes;
/*  2:   */
/*  3:   */import java.io.Serializable;
/*  4:   */
/* 33:   */public class BvhSubtreeInfo
/* 34:   */  implements Serializable
/* 35:   */{
/* 36:   */  private static final long serialVersionUID = 1L;
/* 37:37 */  public final short[] quantizedAabbMin = new short[3];
/* 38:38 */  public final short[] quantizedAabbMax = new short[3];
/* 39:   */  public int rootNodeIndex;
/* 40:   */  public int subtreeSize;
/* 41:   */  
/* 42:   */  public void setAabbFromQuantizeNode(QuantizedBvhNodes quantizedNodes, int nodeId)
/* 43:   */  {
/* 44:44 */    this.quantizedAabbMin[0] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 0));
/* 45:45 */    this.quantizedAabbMin[1] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 1));
/* 46:46 */    this.quantizedAabbMin[2] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 2));
/* 47:47 */    this.quantizedAabbMax[0] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 0));
/* 48:48 */    this.quantizedAabbMax[1] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 1));
/* 49:49 */    this.quantizedAabbMax[2] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 2));
/* 50:   */  }
/* 51:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.BvhSubtreeInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */