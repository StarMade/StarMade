/*  1:   */package org.schema.game.common.data.physics.octree;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */import o;
/*  5:   */
/*  7:   */public class IntersectionCallback
/*  8:   */{
/*  9:   */  public int leafCalcs;
/* 10:   */  public int hitCount;
/* 11:   */  private Vector3f[] hits;
/* 12:   */  private o[] range;
/* 13:   */  private int[] mask;
/* 14:14 */  public boolean initialized = false;
/* 15:   */  
/* 17:   */  public long aabbTest;
/* 18:   */  
/* 20:   */  public long aabbRetrieve;
/* 21:   */  
/* 23:   */  public void addHit(Vector3f paramVector3f1, Vector3f paramVector3f2, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, int paramInt)
/* 24:   */  {
/* 25:25 */    this.hits[(this.hitCount << 1)].set(paramVector3f1);
/* 26:26 */    this.hits[((this.hitCount << 1) + 1)].set(paramVector3f2);
/* 27:27 */    this.range[(this.hitCount << 1)].b(paramByte1, paramByte2, paramByte3);
/* 28:28 */    this.range[((this.hitCount << 1) + 1)].b(paramByte4, paramByte5, paramByte6);
/* 29:29 */    this.mask[this.hitCount] = paramInt;
/* 30:   */    
/* 31:31 */    this.hitCount += 1;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public void createHitCache(int paramInt) {
/* 35:35 */    this.hits = new Vector3f[paramInt << 1];
/* 36:36 */    this.range = new o[paramInt << 1];
/* 37:37 */    for (int i = 0; i < this.hits.length; i++) {
/* 38:38 */      this.hits[i] = new Vector3f();
/* 39:39 */      this.range[i] = new o();
/* 40:   */    }
/* 41:41 */    this.mask = new int[paramInt];
/* 42:42 */    this.initialized = true;
/* 43:   */  }
/* 44:   */  
/* 45:45 */  public int getHit(int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2, o paramo1, o paramo2) { paramVector3f1.set(this.hits[(paramInt << 1)]);
/* 46:46 */    paramVector3f2.set(this.hits[((paramInt << 1) + 1)]);
/* 47:47 */    paramo1.b(this.range[(paramInt << 1)]);
/* 48:48 */    paramo2.b(this.range[((paramInt << 1) + 1)]);
/* 49:49 */    return this.mask[paramInt];
/* 50:   */  }
/* 51:   */  
/* 52:   */  public void reset() {
/* 53:53 */    this.hitCount = 0;
/* 54:54 */    this.leafCalcs = 0;
/* 55:55 */    this.aabbTest = 0L;
/* 56:56 */    this.aabbRetrieve = 0L;
/* 57:   */  }
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.octree.IntersectionCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */