/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BvhSubtreeInfo
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 37 */   public final short[] quantizedAabbMin = new short[3];
/* 38 */   public final short[] quantizedAabbMax = new short[3];
/*    */   public int rootNodeIndex;
/*    */   public int subtreeSize;
/*    */ 
/*    */   public void setAabbFromQuantizeNode(QuantizedBvhNodes quantizedNodes, int nodeId)
/*    */   {
/* 44 */     this.quantizedAabbMin[0] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 0));
/* 45 */     this.quantizedAabbMin[1] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 1));
/* 46 */     this.quantizedAabbMin[2] = ((short)quantizedNodes.getQuantizedAabbMin(nodeId, 2));
/* 47 */     this.quantizedAabbMax[0] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 0));
/* 48 */     this.quantizedAabbMax[1] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 1));
/* 49 */     this.quantizedAabbMax[2] = ((short)quantizedNodes.getQuantizedAabbMax(nodeId, 2));
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.BvhSubtreeInfo
 * JD-Core Version:    0.6.2
 */