package com.bulletphysics.linearmath.convexhull;

class Int4
{
  public int field_2014;
  public int field_2015;
  public int field_2016;
  public int field_2017;
  
  public Int4() {}
  
  public Int4(int local_x, int local_y, int local_z, int local_w)
  {
    this.field_2014 = local_x;
    this.field_2015 = local_y;
    this.field_2016 = local_z;
    this.field_2017 = local_w;
  }
  
  public void set(int local_x, int local_y, int local_z, int local_w)
  {
    this.field_2014 = local_x;
    this.field_2015 = local_y;
    this.field_2016 = local_z;
    this.field_2017 = local_w;
  }
  
  public int getCoord(int coord)
  {
    switch (coord)
    {
    case 0: 
      return this.field_2014;
    case 1: 
      return this.field_2015;
    case 2: 
      return this.field_2016;
    }
    return this.field_2017;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Int4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */