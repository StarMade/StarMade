package com.bulletphysics.util;

public class IntArrayList
{
  private int[] array = new int[16];
  private int size;
  
  public void add(int value)
  {
    if (this.size == this.array.length) {
      expand();
    }
    this.array[(this.size++)] = value;
  }
  
  private void expand()
  {
    int[] newArray = new int[this.array.length << 1];
    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
    this.array = newArray;
  }
  
  public int remove(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    int old = this.array[index];
    System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
    this.size -= 1;
    return old;
  }
  
  public int get(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    return this.array[index];
  }
  
  public void set(int index, int value)
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
  
  public void clear()
  {
    this.size = 0;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.util.IntArrayList
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */