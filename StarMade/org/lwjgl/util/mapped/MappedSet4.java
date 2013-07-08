package org.lwjgl.util.mapped;

public class MappedSet4
{
  private final MappedObject field_1693;
  private final MappedObject field_1694;
  private final MappedObject field_1695;
  private final MappedObject field_1696;
  public int view;
  
  MappedSet4(MappedObject local_a, MappedObject local_b, MappedObject local_c, MappedObject local_d)
  {
    this.field_1693 = local_a;
    this.field_1694 = local_b;
    this.field_1695 = local_c;
    this.field_1696 = local_d;
  }
  
  void view(int view)
  {
    this.field_1693.setViewAddress(this.field_1693.getViewAddress(view));
    this.field_1694.setViewAddress(this.field_1694.getViewAddress(view));
    this.field_1695.setViewAddress(this.field_1695.getViewAddress(view));
    this.field_1696.setViewAddress(this.field_1696.getViewAddress(view));
  }
  
  public void next()
  {
    this.field_1693.next();
    this.field_1694.next();
    this.field_1695.next();
    this.field_1696.next();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */