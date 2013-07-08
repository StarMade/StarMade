import javax.vecmath.Vector4f;

public class class_1426
{
  private float field_226 = 1000.0F;
  protected Vector4f field_225;
  private long field_225;
  public float field_225;
  
  public class_1426()
  {
    this.jdField_field_225_of_type_Float = 10.0F;
    this.jdField_field_225_of_type_Long = System.currentTimeMillis();
  }
  
  public class_1426(byte paramByte)
  {
    this.jdField_field_225_of_type_Float = 10.0F;
    this.jdField_field_225_of_type_Long = System.currentTimeMillis();
    this.jdField_field_225_of_type_Float = 10.0F;
  }
  
  public final float a1()
  {
    return Math.max(0.0F, 1.0F - b() / this.field_226);
  }
  
  private float b()
  {
    return (float)(System.currentTimeMillis() - this.jdField_field_225_of_type_Long);
  }
  
  public final boolean a2()
  {
    return b() < this.field_226;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1426
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */