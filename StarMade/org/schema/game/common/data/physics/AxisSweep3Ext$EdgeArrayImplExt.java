/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.AxisSweep3Internal.EdgeArray;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class AxisSweep3Ext$EdgeArrayImplExt extends AxisSweep3Internal.EdgeArray
/*     */ {
/*     */   private short[] pos;
/*     */   private short[] handle;
/*     */ 
/*     */   public AxisSweep3Ext$EdgeArrayImplExt(int paramInt)
/*     */   {
/* 127 */     this.pos = new short[paramInt];
/* 128 */     this.handle = new short[paramInt];
/*     */   }
/*     */   public void insert(EdgeArrayImplExt paramEdgeArrayImplExt) {
/* 131 */     System.err.println("[Physics][AXIS-SWEEP] EDGE ARRAY INSERTING grow: " + paramEdgeArrayImplExt.pos.length + " -> " + this.pos.length);
/* 132 */     for (int i = 0; i < paramEdgeArrayImplExt.pos.length; i++) {
/* 133 */       this.pos[i] = paramEdgeArrayImplExt.pos[i];
/* 134 */       this.handle[i] = paramEdgeArrayImplExt.handle[i];
/*     */     }
/*     */   }
/*     */ 
/*     */   public void swap(int paramInt1, int paramInt2)
/*     */   {
/* 140 */     int i = this.pos[paramInt1];
/* 141 */     int j = this.handle[paramInt1];
/*     */ 
/* 143 */     this.pos[paramInt1] = this.pos[paramInt2];
/* 144 */     this.handle[paramInt1] = this.handle[paramInt2];
/*     */ 
/* 146 */     this.pos[paramInt2] = i;
/* 147 */     this.handle[paramInt2] = j;
/*     */   }
/*     */ 
/*     */   public void set(int paramInt1, int paramInt2) {
/* 151 */     this.pos[paramInt1] = this.pos[paramInt2];
/* 152 */     this.handle[paramInt1] = this.handle[paramInt2];
/*     */   }
/*     */ 
/*     */   public int getPos(int paramInt)
/*     */   {
/* 157 */     return this.pos[paramInt] & 0xFFFF;
/*     */   }
/*     */ 
/*     */   public void setPos(int paramInt1, int paramInt2)
/*     */   {
/* 162 */     this.pos[paramInt1] = ((short)paramInt2);
/*     */   }
/*     */ 
/*     */   public int getHandle(int paramInt)
/*     */   {
/* 167 */     return this.handle[paramInt] & 0xFFFF;
/*     */   }
/*     */ 
/*     */   public void setHandle(int paramInt1, int paramInt2)
/*     */   {
/* 172 */     this.handle[paramInt1] = ((short)paramInt2);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.AxisSweep3Ext.EdgeArrayImplExt
 * JD-Core Version:    0.6.2
 */