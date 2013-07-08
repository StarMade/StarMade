package org.lwjgl.util.mapped;

public class MappedSet
{
  public static MappedSet2 create(MappedObject local_a, MappedObject local_b)
  {
    return new MappedSet2(local_a, local_b);
  }
  
  public static MappedSet3 create(MappedObject local_a, MappedObject local_b, MappedObject local_c)
  {
    return new MappedSet3(local_a, local_b, local_c);
  }
  
  public static MappedSet4 create(MappedObject local_a, MappedObject local_b, MappedObject local_c, MappedObject local_d)
  {
    return new MappedSet4(local_a, local_b, local_c, local_d);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */