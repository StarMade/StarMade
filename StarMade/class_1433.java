import org.schema.common.FastMath;

public final class class_1433
  extends class_1431
{
  private float field_286 = 0.0F;
  
  public class_1433() {}
  
  public class_1433(float paramFloat)
  {
    super(paramFloat);
  }
  
  public final void a(class_941 paramclass_941)
  {
    this.field_286 += paramclass_941.a() * this.field_1644;
    this.field_1643 = (0.5F + FastMath.i(this.field_286) / 2.0F);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1433
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */