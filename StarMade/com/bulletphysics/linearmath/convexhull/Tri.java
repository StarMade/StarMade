package com.bulletphysics.linearmath.convexhull;

class Tri
  extends Int3
{
  public Int3 field_314 = new Int3();
  public int field_315;
  public int vmax;
  public float rise;
  private static int field_316 = -1;
  private static IntRef erRef = new IntRef()
  {
    public int get()
    {
      return Tri.field_316;
    }
    
    public void set(int value)
    {
      Tri.access$002(value);
    }
  };
  
  public Tri(int local_a, int local_b, int local_c)
  {
    super(local_a, local_b, local_c);
    this.field_314.set(-1, -1, -1);
    this.vmax = -1;
    this.rise = 0.0F;
  }
  
  public IntRef neib(int local_a, int local_b)
  {
    for (int local_i = 0; local_i < 3; local_i++)
    {
      int local_i1 = (local_i + 1) % 3;
      int local_i2 = (local_i + 2) % 3;
      if ((getCoord(local_i) == local_a) && (getCoord(local_i1) == local_b)) {
        return this.field_314.getRef(local_i2);
      }
      if ((getCoord(local_i) == local_b) && (getCoord(local_i1) == local_a)) {
        return this.field_314.getRef(local_i2);
      }
    }
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return erRef;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Tri
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */