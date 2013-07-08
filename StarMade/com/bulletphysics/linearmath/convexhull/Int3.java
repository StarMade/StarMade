package com.bulletphysics.linearmath.convexhull;

class Int3
{
  public int field_2018;
  public int field_2019;
  public int field_2020;
  
  public Int3() {}
  
  public Int3(int local_x, int local_y, int local_z)
  {
    this.field_2018 = local_x;
    this.field_2019 = local_y;
    this.field_2020 = local_z;
  }
  
  public Int3(Int3 local_i)
  {
    this.field_2018 = local_i.field_2018;
    this.field_2019 = local_i.field_2019;
    this.field_2020 = local_i.field_2020;
  }
  
  public void set(int local_x, int local_y, int local_z)
  {
    this.field_2018 = local_x;
    this.field_2019 = local_y;
    this.field_2020 = local_z;
  }
  
  public void set(Int3 local_i)
  {
    this.field_2018 = local_i.field_2018;
    this.field_2019 = local_i.field_2019;
    this.field_2020 = local_i.field_2020;
  }
  
  public int getCoord(int coord)
  {
    switch (coord)
    {
    case 0: 
      return this.field_2018;
    case 1: 
      return this.field_2019;
    }
    return this.field_2020;
  }
  
  public void setCoord(int coord, int value)
  {
    switch (coord)
    {
    case 0: 
      this.field_2018 = value;
      break;
    case 1: 
      this.field_2019 = value;
      break;
    case 2: 
      this.field_2020 = value;
    }
  }
  
  public boolean equals(Int3 local_i)
  {
    return (this.field_2018 == local_i.field_2018) && (this.field_2019 == local_i.field_2019) && (this.field_2020 == local_i.field_2020);
  }
  
  public IntRef getRef(final int coord)
  {
    new IntRef()
    {
      public int get()
      {
        return Int3.this.getCoord(coord);
      }
      
      public void set(int value)
      {
        Int3.this.setCoord(coord, value);
      }
    };
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.convexhull.Int3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */