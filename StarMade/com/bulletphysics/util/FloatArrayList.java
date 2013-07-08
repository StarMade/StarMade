package com.bulletphysics.util;

public class FloatArrayList
{
  private float[] array = new float[16];
  private int size;
  
  public void add(float value)
  {
    if (this.size == this.array.length) {
      expand();
    }
    this.array[(this.size++)] = value;
  }
  
  private void expand()
  {
    float[] newArray = new float[this.array.length << 1];
    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
    this.array = newArray;
  }
  
  public float remove(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    float old = this.array[index];
    System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
    this.size -= 1;
    return old;
  }
  
  public float get(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    return this.array[index];
  }
  
  public void set(int index, float value)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    this.array[index] = value;
  }
  
  public int size()
  {
    return this.size;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.util.FloatArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */