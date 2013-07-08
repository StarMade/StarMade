package com.bulletphysics.extras.gimpact;

class Pair
{
  public int index1;
  public int index2;
  
  public Pair() {}
  
  public Pair(int index1, int index2)
  {
    this.index1 = index1;
    this.index2 = index2;
  }
  
  public Pair(Pair local_p)
  {
    this.index1 = local_p.index1;
    this.index2 = local_p.index2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.extras.gimpact.Pair
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */