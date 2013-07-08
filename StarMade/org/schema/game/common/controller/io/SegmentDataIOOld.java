package org.schema.game.common.controller.io;

import class_48;
import org.schema.common.util.ByteUtil;

public class SegmentDataIOOld
{
  private static final class_48 jdField_field_1840_of_type_Class_48;
  private static int jdField_field_1840_of_type_Int;
  private static int jdField_field_1841_of_type_Int;
  private static int field_1842;
  private static byte[] jdField_field_1840_of_type_ArrayOfByte;
  private static byte[] jdField_field_1841_of_type_ArrayOfByte;
  
  public static int a(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = Math.abs(paramInt1) >> 4 & 0xF;
    int j = Math.abs(paramInt2) >> 4 & 0xF;
    int k;
    int m = (k = Math.abs(paramInt3) >> 4 & 0xF) * jdField_field_1840_of_type_Class_48.field_476 * jdField_field_1840_of_type_Class_48.field_475 + j * jdField_field_1840_of_type_Class_48.field_475 + i;
    if ((!jdField_field_1840_of_type_Boolean) && (m >= jdField_field_1840_of_type_Int)) {
      throw new AssertionError(m + "/" + jdField_field_1840_of_type_Int + ": " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + " ---> " + i + ", " + j + ", " + k + " ");
    }
    return m;
  }
  
  public static void main(String[] paramArrayOfString) {}
  
  static
  {
    jdField_field_1841_of_type_Int = (SegmentDataIOOld.jdField_field_1840_of_type_Int = (SegmentDataIOOld.jdField_field_1840_of_type_Class_48 = new class_48(16, 16, 16)).field_475 * jdField_field_1840_of_type_Class_48.field_476 * jdField_field_1840_of_type_Class_48.field_477) << 3;
    field_1842 = jdField_field_1840_of_type_Int << 3;
    jdField_field_1840_of_type_ArrayOfByte = new byte[jdField_field_1841_of_type_Int + field_1842];
    jdField_field_1841_of_type_ArrayOfByte = ByteUtil.a5(-1);
    int i = 0;
    for (int j = 0; j < jdField_field_1840_of_type_Class_48.field_477; j++) {
      for (int k = 0; k < jdField_field_1840_of_type_Class_48.field_476; k++) {
        for (int m = 0; m < jdField_field_1840_of_type_Class_48.field_475; m++)
        {
          for (int n = 0; n < jdField_field_1841_of_type_ArrayOfByte.length; n++) {
            jdField_field_1840_of_type_ArrayOfByte[(i++)] = jdField_field_1841_of_type_ArrayOfByte[n];
          }
          i += 4;
        }
      }
    }
    if ((!jdField_field_1840_of_type_Boolean) && (i != jdField_field_1840_of_type_ArrayOfByte.length / 2)) {
      throw new AssertionError(i + "/" + jdField_field_1840_of_type_ArrayOfByte.length / 2);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.io.SegmentDataIOOld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */