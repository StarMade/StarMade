package com.bulletphysics.extras.gimpact;

import javax.vecmath.Vector3f;

class BvhDataArray
{
  private int size = 0;
  float[] bound = new float[0];
  int[] data = new int[0];
  
  public int size()
  {
    return this.size;
  }
  
  public void resize(int newSize)
  {
    float[] newBound = new float[newSize * 6];
    int[] newData = new int[newSize];
    System.arraycopy(this.bound, 0, newBound, 0, this.size * 6);
    System.arraycopy(this.data, 0, newData, 0, this.size);
    this.bound = newBound;
    this.data = newData;
    this.size = newSize;
  }
  
  public void swap(int idx1, int idx2)
  {
    int pos1 = idx1 * 6;
    int pos2 = idx2 * 6;
    float local_b0 = this.bound[(pos1 + 0)];
    float local_b1 = this.bound[(pos1 + 1)];
    float local_b2 = this.bound[(pos1 + 2)];
    float local_b3 = this.bound[(pos1 + 3)];
    float local_b4 = this.bound[(pos1 + 4)];
    float local_b5 = this.bound[(pos1 + 5)];
    int local_d = this.data[idx1];
    this.bound[(pos1 + 0)] = this.bound[(pos2 + 0)];
    this.bound[(pos1 + 1)] = this.bound[(pos2 + 1)];
    this.bound[(pos1 + 2)] = this.bound[(pos2 + 2)];
    this.bound[(pos1 + 3)] = this.bound[(pos2 + 3)];
    this.bound[(pos1 + 4)] = this.bound[(pos2 + 4)];
    this.bound[(pos1 + 5)] = this.bound[(pos2 + 5)];
    this.data[idx1] = this.data[idx2];
    this.bound[(pos2 + 0)] = local_b0;
    this.bound[(pos2 + 1)] = local_b1;
    this.bound[(pos2 + 2)] = local_b2;
    this.bound[(pos2 + 3)] = local_b3;
    this.bound[(pos2 + 4)] = local_b4;
    this.bound[(pos2 + 5)] = local_b5;
    this.data[idx2] = local_d;
  }
  
  public BoxCollision.AABB getBound(int idx, BoxCollision.AABB out)
  {
    int pos = idx * 6;
    out.min.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
    out.max.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
    return out;
  }
  
  public Vector3f getBoundMin(int idx, Vector3f out)
  {
    int pos = idx * 6;
    out.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
    return out;
  }
  
  public Vector3f getBoundMax(int idx, Vector3f out)
  {
    int pos = idx * 6;
    out.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
    return out;
  }
  
  public void setBound(int idx, BoxCollision.AABB aabb)
  {
    int pos = idx * 6;
    this.bound[(pos + 0)] = aabb.min.field_615;
    this.bound[(pos + 1)] = aabb.min.field_616;
    this.bound[(pos + 2)] = aabb.min.field_617;
    this.bound[(pos + 3)] = aabb.max.field_615;
    this.bound[(pos + 4)] = aabb.max.field_616;
    this.bound[(pos + 5)] = aabb.max.field_617;
  }
  
  public int getData(int idx)
  {
    return this.data[idx];
  }
  
  public void setData(int idx, int value)
  {
    this.data[idx] = value;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhDataArray
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */