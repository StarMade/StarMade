package com.bulletphysics.extras.gimpact;

class PairSet
{
  private Pair[] array = new Pair[32];
  private int size = 0;
  
  public PairSet()
  {
    for (int local_i = 0; local_i < this.array.length; local_i++) {
      this.array[local_i] = new Pair();
    }
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public int size()
  {
    return this.size;
  }
  
  public Pair get(int index)
  {
    if (index >= this.size) {
      throw new IndexOutOfBoundsException();
    }
    return this.array[index];
  }
  
  private void expand()
  {
    Pair[] newArray = new Pair[this.array.length << 1];
    for (int local_i = this.array.length; local_i < newArray.length; local_i++) {
      newArray[local_i] = new Pair();
    }
    System.arraycopy(this.array, 0, newArray, 0, this.array.length);
    this.array = newArray;
  }
  
  public void push_pair(int index1, int index2)
  {
    if (this.size == this.array.length) {
      expand();
    }
    this.array[this.size].index1 = index1;
    this.array[this.size].index2 = index2;
    this.size += 1;
  }
  
  public void push_pair_inv(int index1, int index2)
  {
    if (this.size == this.array.length) {
      expand();
    }
    this.array[this.size].index1 = index2;
    this.array[this.size].index2 = index1;
    this.size += 1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.PairSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */