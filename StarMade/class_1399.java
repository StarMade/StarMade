import java.util.Random;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.common.FastMath;

public final class class_1399
  extends class_944
{
  private Vector3f jdField_field_9_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector4f jdField_field_9_of_type_JavaxVecmathVector4f = new Vector4f();
  private Vector3f field_10 = new Vector3f();
  private Vector3f field_11 = new Vector3f();
  private Vector3f field_12 = new Vector3f();
  private Vector3f field_13 = new Vector3f();
  private float jdField_field_9_of_type_Float = 0.01F;
  private Vector3f field_272 = new Vector3f();
  
  public final void a13(Vector3f paramVector3f, float paramFloat)
  {
    for (int i = 0; i < 3; i++)
    {
      this.field_272.set(0.5F - FastMath.field_1862.nextFloat(), 0.5F - FastMath.field_1862.nextFloat(), 0.5F - FastMath.field_1862.nextFloat());
      this.field_272.scale(this.jdField_field_9_of_type_Float * (paramFloat / 4.0F));
      int j = a14(paramVector3f, this.field_272);
      this.field_9.c1(j, paramFloat / 4.0F, 0.0F, 0.0F);
    }
  }
  
  public final boolean a7(int paramInt, class_941 paramclass_941)
  {
    this.field_9.d(paramInt, this.jdField_field_9_of_type_JavaxVecmathVector3f);
    this.field_9.a2(paramInt, this.jdField_field_9_of_type_JavaxVecmathVector4f);
    this.field_9.a4(paramInt, this.field_10);
    this.field_9.b(paramInt, this.field_11);
    this.field_12.set(this.field_10);
    this.field_13.set(this.jdField_field_9_of_type_JavaxVecmathVector3f);
    float f = this.field_9.a3(paramInt);
    this.jdField_field_9_of_type_JavaxVecmathVector3f.scale((float)(paramclass_941.a() * 1000.0F * 0.2D));
    this.field_9.a6(paramInt, (float)(f + paramclass_941.a() * 1000.0D * 1.5D));
    this.field_10.add(this.jdField_field_9_of_type_JavaxVecmathVector3f);
    this.field_13.scale(0.995F);
    this.field_9.d1(paramInt, this.field_13.field_615, this.field_13.field_616, this.field_13.field_617);
    this.field_9.a7(paramInt, this.field_10.field_615, this.field_10.field_616, this.field_10.field_617);
    return f < 1000.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1399
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */