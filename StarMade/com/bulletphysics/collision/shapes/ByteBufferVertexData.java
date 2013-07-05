/*    */ package com.bulletphysics.collision.shapes;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import javax.vecmath.Tuple3f;
/*    */ 
/*    */ public class ByteBufferVertexData extends VertexData
/*    */ {
/*    */   public ByteBuffer vertexData;
/*    */   public int vertexCount;
/*    */   public int vertexStride;
/*    */   public ScalarType vertexType;
/*    */   public ByteBuffer indexData;
/*    */   public int indexCount;
/*    */   public int indexStride;
/*    */   public ScalarType indexType;
/*    */ 
/*    */   public int getVertexCount()
/*    */   {
/* 47 */     return this.vertexCount;
/*    */   }
/*    */ 
/*    */   public int getIndexCount()
/*    */   {
/* 52 */     return this.indexCount;
/*    */   }
/*    */ 
/*    */   public <T extends Tuple3f> T getVertex(int idx, T out)
/*    */   {
/* 57 */     int off = idx * this.vertexStride;
/* 58 */     out.x = this.vertexData.getFloat(off + 0);
/* 59 */     out.y = this.vertexData.getFloat(off + 4);
/* 60 */     out.z = this.vertexData.getFloat(off + 8);
/* 61 */     return out;
/*    */   }
/*    */ 
/*    */   public void setVertex(int idx, float x, float y, float z)
/*    */   {
/* 66 */     int off = idx * this.vertexStride;
/* 67 */     this.vertexData.putFloat(off + 0, x);
/* 68 */     this.vertexData.putFloat(off + 4, y);
/* 69 */     this.vertexData.putFloat(off + 8, z);
/*    */   }
/*    */ 
/*    */   public int getIndex(int idx)
/*    */   {
/* 74 */     if (this.indexType == ScalarType.SHORT) {
/* 75 */       return this.indexData.getShort(idx * this.indexStride) & 0xFFFF;
/*    */     }
/* 77 */     if (this.indexType == ScalarType.INTEGER) {
/* 78 */       return this.indexData.getInt(idx * this.indexStride);
/*    */     }
/*    */ 
/* 81 */     throw new IllegalStateException("indicies type must be short or integer");
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.shapes.ByteBufferVertexData
 * JD-Core Version:    0.6.2
 */