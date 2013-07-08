import java.util.Arrays;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

public class class_950
{
  private float[] jdField_field_1257_of_type_ArrayOfFloat;
  private int jdField_field_1257_of_type_Int;
  
  public class_950()
  {
    this(512);
  }
  
  public class_950(int paramInt)
  {
    new Vector3f();
    new Vector3f();
    this.jdField_field_1257_of_type_Int = paramInt;
    this.jdField_field_1257_of_type_ArrayOfFloat = new float[paramInt * 17];
  }
  
  public final void a(int paramInt1, int paramInt2)
  {
    paramInt1 *= 17;
    paramInt2 *= 17;
    for (int i = 0; i < 17; i++) {
      this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt2 + i)] = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt1 + i)];
    }
  }
  
  public final int a1()
  {
    return this.jdField_field_1257_of_type_Int;
  }
  
  public final Vector4f a2(int paramInt, Vector4f paramVector4f)
  {
    paramInt *= 17;
    paramVector4f.field_596 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 9)];
    paramVector4f.field_597 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 9 + 1)];
    paramVector4f.field_598 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 9 + 2)];
    paramVector4f.field_598 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 9 + 3)];
    return paramVector4f;
  }
  
  public final float a3(int paramInt)
  {
    paramInt *= 17;
    return this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 13)];
  }
  
  public final Vector3f a4(int paramInt, Vector3f paramVector3f)
  {
    paramInt *= 17;
    paramVector3f.field_615 = this.jdField_field_1257_of_type_ArrayOfFloat[paramInt];
    paramVector3f.field_616 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 1)];
    paramVector3f.field_617 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 2)];
    return paramVector3f;
  }
  
  public final Vector3f b(int paramInt, Vector3f paramVector3f)
  {
    paramInt *= 17;
    paramVector3f.field_615 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 6)];
    paramVector3f.field_616 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 6 + 1)];
    paramVector3f.field_617 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 6 + 2)];
    return paramVector3f;
  }
  
  public final Vector3f c(int paramInt, Vector3f paramVector3f)
  {
    paramInt *= 17;
    paramVector3f.field_615 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 14)];
    paramVector3f.field_616 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 14 + 1)];
    paramVector3f.field_617 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 14 + 2)];
    return paramVector3f;
  }
  
  public final Vector3f d(int paramInt, Vector3f paramVector3f)
  {
    paramInt *= 17;
    paramVector3f.field_615 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 3)];
    paramVector3f.field_616 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 3 + 1)];
    paramVector3f.field_617 = this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 3 + 2)];
    return paramVector3f;
  }
  
  public final float[] a5()
  {
    return this.jdField_field_1257_of_type_ArrayOfFloat;
  }
  
  public final void a6(int paramInt, float paramFloat)
  {
    paramInt *= 17;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 13)] = paramFloat;
  }
  
  public final void a7(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramInt *= 17;
    this.jdField_field_1257_of_type_ArrayOfFloat[paramInt] = paramFloat1;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 1)] = paramFloat2;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 2)] = paramFloat3;
  }
  
  public final void b1(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramInt *= 17;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 6)] = paramFloat1;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 6 + 1)] = paramFloat2;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 6 + 2)] = paramFloat3;
  }
  
  public final void c1(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramInt *= 17;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 14)] = paramFloat1;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 14 + 1)] = paramFloat2;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 14 + 2)] = paramFloat3;
  }
  
  public final void d1(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    paramInt *= 17;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 3)] = paramFloat1;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 3 + 1)] = paramFloat2;
    this.jdField_field_1257_of_type_ArrayOfFloat[(paramInt + 3 + 2)] = paramFloat3;
  }
  
  public final void a8()
  {
    this.jdField_field_1257_of_type_Int <<= 1;
    if ((this.jdField_field_1257_of_type_Int > 16384) && (!jdField_field_1257_of_type_Boolean)) {
      throw new AssertionError();
    }
    this.jdField_field_1257_of_type_ArrayOfFloat = Arrays.copyOf(this.jdField_field_1257_of_type_ArrayOfFloat, this.jdField_field_1257_of_type_Int * 17);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_950
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */