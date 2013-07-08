import org.schema.common.FastMath;

public final class class_382
{
  private float[] jdField_field_774_of_type_ArrayOfFloat = new float[6];
  public class_388[] field_774;
  private static int jdField_field_774_of_type_Int = 52;
  
  public class_382()
  {
    this.jdField_field_774_of_type_ArrayOfClass_388 = new class_388[40];
  }
  
  public final void a()
  {
    float f1 = 3.141593F * (3.0F - FastMath.l(5.0F));
    float f2 = 2.0F / this.jdField_field_774_of_type_ArrayOfClass_388.length;
    for (int i = 0; i < this.jdField_field_774_of_type_ArrayOfClass_388.length; i++)
    {
      this.jdField_field_774_of_type_ArrayOfClass_388[i] = new class_388(jdField_field_774_of_type_Int);
      float f3 = i * f2 - 1.0F + f2 / 2.0F;
      float f4 = FastMath.l(1.0F - f3 * f3);
      float f5 = i * f1;
      float f6 = FastMath.i(f5) * f4;
      f5 = f3;
      f4 = FastMath.j(f5) * f4;
      class_388 tmp108_83 = this.jdField_field_774_of_type_ArrayOfClass_388[i];
      class_388 localclass_3881;
      tmp108_83.jdField_field_781_of_type_ArrayOfByte = new byte[(localclass_3881 = tmp108_83).jdField_field_781_of_type_Int * 3];
      localclass_3881.field_782 = new float[localclass_3881.jdField_field_781_of_type_Int];
      float[] arrayOfFloat = localclass_3881.field_782;
      byte[] arrayOfByte = localclass_3881.jdField_field_781_of_type_ArrayOfByte;
      float f9 = f6;
      float f8 = f5;
      float f7 = f4;
      class_388 localclass_3882 = localclass_3881;
      float f10 = f7 * 0.3F;
      float f11 = f8 * 0.3F;
      float f12 = f9 * 0.3F;
      f7 = 0.0F;
      f8 = 0.0F;
      f9 = 0.0F;
      int k = 0;
      int m = 0;
      int n = 0;
      int i1 = 0;
      int i2 = 0;
      while (i1 < localclass_3882.jdField_field_781_of_type_Int * 3)
      {
        int i3 = Math.round(f7);
        int i4 = Math.round(f8);
        int i5 = Math.round(f9);
        if ((i3 != k) || (i4 != m) || (i5 != n)) {
          while ((i1 < localclass_3882.jdField_field_781_of_type_Int * 3) && ((i3 != k) || (i4 != m) || (i5 != n)))
          {
            if (i3 != k)
            {
              if (k < i3) {
                k++;
              } else {
                k--;
              }
            }
            else if (i4 != m)
            {
              if (m < i4) {
                m++;
              } else {
                m--;
              }
            }
            else if (i5 != n) {
              if (n < i5) {
                n++;
              } else {
                n--;
              }
            }
            float f13 = FastMath.l(f7 * f7 + f8 * f8 + f9 * f9);
            arrayOfByte[i1] = ((byte)k);
            arrayOfByte[(i1 + 1)] = ((byte)m);
            arrayOfByte[(i1 + 2)] = ((byte)n);
            arrayOfFloat[i2] = (1.0F / f13);
            i1 += 3;
            i2++;
          }
        }
        f7 += f10;
        f8 += f11;
        f9 += f12;
      }
      localclass_3881.jdField_field_781_of_type_ArrayOfFloat[0] = ((f4 < 0.0F ? -f4 : 0.0F) * 1.5F);
      localclass_3881.jdField_field_781_of_type_ArrayOfFloat[1] = ((f4 > 0.0F ? f4 : 0.0F) * 1.5F);
      localclass_3881.jdField_field_781_of_type_ArrayOfFloat[2] = ((f5 < 0.0F ? -f5 : 0.0F) * 1.5F);
      localclass_3881.jdField_field_781_of_type_ArrayOfFloat[3] = ((f5 > 0.0F ? f5 : 0.0F) * 1.5F);
      localclass_3881.jdField_field_781_of_type_ArrayOfFloat[4] = ((f6 < 0.0F ? -f6 : 0.0F) * 1.5F);
      localclass_3881.jdField_field_781_of_type_ArrayOfFloat[5] = ((f6 > 0.0F ? f6 : 0.0F) * 1.5F);
      for (int j = 0; j < 6; j++) {
        this.jdField_field_774_of_type_ArrayOfFloat[j] += this.jdField_field_774_of_type_ArrayOfClass_388[i].jdField_field_781_of_type_ArrayOfFloat[j];
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_382
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */