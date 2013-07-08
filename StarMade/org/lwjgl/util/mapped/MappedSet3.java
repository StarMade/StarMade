package org.lwjgl.util.mapped;

public class MappedSet3
{
  private final MappedObject field_1699;
  private final MappedObject field_1700;
  private final MappedObject field_1701;
  public int view;
  
  MappedSet3(MappedObject local_a, MappedObject local_b, MappedObject local_c)
  {
    this.field_1699 = local_a;
    this.field_1700 = local_b;
    this.field_1701 = local_c;
  }
  
  void view(int view)
  {
    this.field_1699.setViewAddress(this.field_1699.getViewAddress(view));
    this.field_1700.setViewAddress(this.field_1700.getViewAddress(view));
    this.field_1701.setViewAddress(this.field_1701.getViewAddress(view));
  }
  
  public void next()
  {
    this.field_1699.next();
    this.field_1700.next();
    this.field_1701.next();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */