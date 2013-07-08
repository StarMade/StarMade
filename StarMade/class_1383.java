public final class class_1383
  extends class_1431
{
  public class_1383() {}
  
  public class_1383(float paramFloat)
  {
    super(paramFloat);
  }
  
  public final void a(class_941 paramclass_941)
  {
    if (this.field_1642 > 0.0F)
    {
      if (a1() < 1.0F)
      {
        this.field_1643 = Math.min(this.field_1643 + paramclass_941.a() * this.field_1644, 1.0F);
        return;
      }
      this.field_1642 = (-this.field_1642);
      return;
    }
    if (a1() > 0.0F)
    {
      this.field_1643 = Math.max(this.field_1643 - paramclass_941.a() * this.field_1644, 0.0F);
      return;
    }
    this.field_1642 = (-this.field_1642);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1383
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */