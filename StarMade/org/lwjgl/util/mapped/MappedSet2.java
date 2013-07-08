package org.lwjgl.util.mapped;

public class MappedSet2
{
  private final MappedObject field_1697;
  private final MappedObject field_1698;
  public int view;
  
  MappedSet2(MappedObject local_a, MappedObject local_b)
  {
    this.field_1697 = local_a;
    this.field_1698 = local_b;
  }
  
  void view(int view)
  {
    this.field_1697.setViewAddress(this.field_1697.getViewAddress(view));
    this.field_1698.setViewAddress(this.field_1698.getViewAddress(view));
  }
  
  public void next()
  {
    this.field_1697.next();
    this.field_1698.next();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.mapped.MappedSet2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */