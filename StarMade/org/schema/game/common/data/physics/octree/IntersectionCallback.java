/*    */ package org.schema.game.common.data.physics.octree;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ import o;
/*    */ 
/*    */ public class IntersectionCallback
/*    */ {
/*    */   public int leafCalcs;
/*    */   public int hitCount;
/*    */   private Vector3f[] hits;
/*    */   private o[] range;
/*    */   private int[] mask;
/* 14 */   public boolean initialized = false;
/*    */   public long aabbTest;
/*    */   public long aabbRetrieve;
/*    */ 
/*    */   public void addHit(Vector3f paramVector3f1, Vector3f paramVector3f2, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, int paramInt)
/*    */   {
/* 25 */     this.hits[(this.hitCount << 1)].set(paramVector3f1);
/* 26 */     this.hits[((this.hitCount << 1) + 1)].set(paramVector3f2);
/* 27 */     this.range[(this.hitCount << 1)].b(paramByte1, paramByte2, paramByte3);
/* 28 */     this.range[((this.hitCount << 1) + 1)].b(paramByte4, paramByte5, paramByte6);
/* 29 */     this.mask[this.hitCount] = paramInt;
/*    */ 
/* 31 */     this.hitCount += 1;
/*    */   }
/*    */ 
/*    */   public void createHitCache(int paramInt) {
/* 35 */     this.hits = new Vector3f[paramInt << 1];
/* 36 */     this.range = new o[paramInt << 1];
/* 37 */     for (int i = 0; i < this.hits.length; i++) {
/* 38 */       this.hits[i] = new Vector3f();
/* 39 */       this.range[i] = new o();
/*    */     }
/* 41 */     this.mask = new int[paramInt];
/* 42 */     this.initialized = true;
/*    */   }
/*    */   public int getHit(int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2, o paramo1, o paramo2) {
/* 45 */     paramVector3f1.set(this.hits[(paramInt << 1)]);
/* 46 */     paramVector3f2.set(this.hits[((paramInt << 1) + 1)]);
/* 47 */     paramo1.b(this.range[(paramInt << 1)]);
/* 48 */     paramo2.b(this.range[((paramInt << 1) + 1)]);
/* 49 */     return this.mask[paramInt];
/*    */   }
/*    */ 
/*    */   public void reset() {
/* 53 */     this.hitCount = 0;
/* 54 */     this.leafCalcs = 0;
/* 55 */     this.aabbTest = 0L;
/* 56 */     this.aabbRetrieve = 0L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.IntersectionCallback
 * JD-Core Version:    0.6.2
 */