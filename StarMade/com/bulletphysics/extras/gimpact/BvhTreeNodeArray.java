package com.bulletphysics.extras.gimpact;

import javax.vecmath.Vector3f;

class BvhTreeNodeArray
{
  private int size = 0;
  private float[] bound = new float[0];
  private int[] escapeIndexOrDataIndex = new int[0];
  
  public void clear()
  {
    this.size = 0;
  }
  
  public void resize(int newSize)
  {
    float[] newBound = new float[newSize * 6];
    int[] newEIODI = new int[newSize];
    System.arraycopy(this.bound, 0, newBound, 0, this.size * 6);
    System.arraycopy(this.escapeIndexOrDataIndex, 0, newEIODI, 0, this.size);
    this.bound = newBound;
    this.escapeIndexOrDataIndex = newEIODI;
    this.size = newSize;
  }
  
  public void set(int destIdx, BvhTreeNodeArray array, int srcIdx)
  {
    int dpos = destIdx * 6;
    int spos = srcIdx * 6;
    this.bound[(dpos + 0)] = array.bound[(spos + 0)];
    this.bound[(dpos + 1)] = array.bound[(spos + 1)];
    this.bound[(dpos + 2)] = array.bound[(spos + 2)];
    this.bound[(dpos + 3)] = array.bound[(spos + 3)];
    this.bound[(dpos + 4)] = array.bound[(spos + 4)];
    this.bound[(dpos + 5)] = array.bound[(spos + 5)];
    this.escapeIndexOrDataIndex[destIdx] = array.escapeIndexOrDataIndex[srcIdx];
  }
  
  public void set(int destIdx, BvhDataArray array, int srcIdx)
  {
    int dpos = destIdx * 6;
    int spos = srcIdx * 6;
    this.bound[(dpos + 0)] = array.bound[(spos + 0)];
    this.bound[(dpos + 1)] = array.bound[(spos + 1)];
    this.bound[(dpos + 2)] = array.bound[(spos + 2)];
    this.bound[(dpos + 3)] = array.bound[(spos + 3)];
    this.bound[(dpos + 4)] = array.bound[(spos + 4)];
    this.bound[(dpos + 5)] = array.bound[(spos + 5)];
    this.escapeIndexOrDataIndex[destIdx] = array.data[srcIdx];
  }
  
  public BoxCollision.AABB getBound(int nodeIndex, BoxCollision.AABB out)
  {
    int pos = nodeIndex * 6;
    out.min.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
    out.max.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
    return out;
  }
  
  public void setBound(int nodeIndex, BoxCollision.AABB aabb)
  {
    int pos = nodeIndex * 6;
    this.bound[(pos + 0)] = aabb.min.field_615;
    this.bound[(pos + 1)] = aabb.min.field_616;
    this.bound[(pos + 2)] = aabb.min.field_617;
    this.bound[(pos + 3)] = aabb.max.field_615;
    this.bound[(pos + 4)] = aabb.max.field_616;
    this.bound[(pos + 5)] = aabb.max.field_617;
  }
  
  public boolean isLeafNode(int nodeIndex)
  {
    return this.escapeIndexOrDataIndex[nodeIndex] >= 0;
  }
  
  public int getEscapeIndex(int nodeIndex)
  {
    return -this.escapeIndexOrDataIndex[nodeIndex];
  }
  
  public void setEscapeIndex(int nodeIndex, int index)
  {
    this.escapeIndexOrDataIndex[nodeIndex] = (-index);
  }
  
  public int getDataIndex(int nodeIndex)
  {
    return this.escapeIndexOrDataIndex[nodeIndex];
  }
  
  public void setDataIndex(int nodeIndex, int index)
  {
    this.escapeIndexOrDataIndex[nodeIndex] = index;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhTreeNodeArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */