import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix3f;
import org.lwjgl.util.vector.Matrix4f;

public class class_1370
{
  protected class_925 field_274;
  protected class_925 field_1540;
  private static FloatBuffer field_1540 = BufferUtils.createFloatBuffer(9);
  protected class_925 field_1541;
  protected int field_274;
  static FloatBuffer field_274;
  
  public static float[] a1()
  {
    float[] arrayOfFloat = new float[19];
    double d1 = 1.0D / (Math.sqrt(6.283185307179586D) * 9.0D);
    int i = -9;
    double d2 = 0.0D;
    for (int j = 0; j < arrayOfFloat.length; j++)
    {
      double tmp36_35 = i;
      double d4 = tmp36_35 * tmp36_35;
      arrayOfFloat[j] = ((float)(d1 * Math.exp(-d4 * d1)));
      i++;
      d2 += arrayOfFloat[j];
    }
    double d3 = d2;
    for (int k = 0; k < arrayOfFloat.length; k++)
    {
      int tmp91_89 = k;
      arrayOfFloat[tmp91_89] = ((float)(arrayOfFloat[tmp91_89] / d3));
    }
    return arrayOfFloat;
  }
  
  static void d()
  {
    Object localObject = new Matrix4f(class_969.field_1260);
    Matrix4f localMatrix4f1 = new Matrix4f(class_969.field_1259);
    Matrix4f localMatrix4f2 = new Matrix4f();
    Matrix4f.mul((Matrix4f)localObject, localMatrix4f1, localMatrix4f2);
    jdField_field_274_of_type_JavaNioFloatBuffer.rewind();
    localMatrix4f2.store(jdField_field_274_of_type_JavaNioFloatBuffer);
    jdField_field_274_of_type_JavaNioFloatBuffer.rewind();
    (localObject = new Matrix3f()).m00 = localMatrix4f1.m00;
    ((Matrix3f)localObject).m10 = localMatrix4f1.m10;
    ((Matrix3f)localObject).m20 = localMatrix4f1.m20;
    ((Matrix3f)localObject).m01 = localMatrix4f1.m01;
    ((Matrix3f)localObject).m11 = localMatrix4f1.m11;
    ((Matrix3f)localObject).m21 = localMatrix4f1.m21;
    ((Matrix3f)localObject).m02 = localMatrix4f1.m02;
    ((Matrix3f)localObject).m12 = localMatrix4f1.m12;
    ((Matrix3f)localObject).m22 = localMatrix4f1.m22;
    ((Matrix3f)localObject).invert();
    field_1540.rewind();
    ((Matrix3f)localObject).store(field_1540);
    field_1540.rewind();
  }
  
  public final void a2(int paramInt)
  {
    this.jdField_field_274_of_type_Int = paramInt;
  }
  
  static
  {
    jdField_field_274_of_type_JavaNioFloatBuffer = BufferUtils.createFloatBuffer(16);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1370
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */