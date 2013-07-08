package org.schema.game.common.data.physics.octree;

import class_35;
import javax.vecmath.Vector3f;

public class IntersectionCallback
{
  public int leafCalcs;
  public int hitCount;
  private Vector3f[] hits;
  private class_35[] range;
  private int[] mask;
  public boolean initialized = false;
  public long aabbTest;
  public long aabbRetrieve;
  
  public void addHit(Vector3f paramVector3f1, Vector3f paramVector3f2, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, int paramInt)
  {
    this.hits[(this.hitCount << 1)].set(paramVector3f1);
    this.hits[((this.hitCount << 1) + 1)].set(paramVector3f2);
    this.range[(this.hitCount << 1)].b(paramByte1, paramByte2, paramByte3);
    this.range[((this.hitCount << 1) + 1)].b(paramByte4, paramByte5, paramByte6);
    this.mask[this.hitCount] = paramInt;
    this.hitCount += 1;
  }
  
  public void createHitCache(int paramInt)
  {
    this.hits = new Vector3f[paramInt << 1];
    this.range = new class_35[paramInt << 1];
    for (int i = 0; i < this.hits.length; i++)
    {
      this.hits[i] = new Vector3f();
      this.range[i] = new class_35();
    }
    this.mask = new int[paramInt];
    this.initialized = true;
  }
  
  public int getHit(int paramInt, Vector3f paramVector3f1, Vector3f paramVector3f2, class_35 paramclass_351, class_35 paramclass_352)
  {
    paramVector3f1.set(this.hits[(paramInt << 1)]);
    paramVector3f2.set(this.hits[((paramInt << 1) + 1)]);
    paramclass_351.b1(this.range[(paramInt << 1)]);
    paramclass_352.b1(this.range[((paramInt << 1) + 1)]);
    return this.mask[paramInt];
  }
  
  public void reset()
  {
    this.hitCount = 0;
    this.leafCalcs = 0;
    this.aabbTest = 0L;
    this.aabbRetrieve = 0L;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.octree.IntersectionCallback
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */