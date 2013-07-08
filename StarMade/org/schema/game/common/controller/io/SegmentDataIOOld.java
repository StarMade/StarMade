/*  1:   */package org.schema.game.common.controller.io;
/*  2:   */
/*  3:   */import org.schema.common.util.ByteUtil;
/*  4:   */import q;
/*  5:   */
/* 28:   */public class SegmentDataIOOld
/* 29:   */{
/* 30:   */  private static final q jdField_a_of_type_Q;
/* 31:   */  private static int jdField_a_of_type_Int;
/* 32:   */  private static int jdField_b_of_type_Int;
/* 33:   */  private static int c;
/* 34:   */  private static byte[] jdField_a_of_type_ArrayOfByte;
/* 35:   */  private static byte[] jdField_b_of_type_ArrayOfByte;
/* 36:   */  
/* 37:   */  static
/* 38:   */  {
/* 39:39 */    jdField_b_of_type_Int = (SegmentDataIOOld.jdField_a_of_type_Int = (SegmentDataIOOld.jdField_a_of_type_Q = new q(16, 16, 16)).jdField_a_of_type_Int * jdField_a_of_type_Q.jdField_b_of_type_Int * jdField_a_of_type_Q.c) << 3;
/* 40:40 */    c = jdField_a_of_type_Int << 3;
/* 41:   */    
/* 47:47 */    jdField_a_of_type_ArrayOfByte = new byte[jdField_b_of_type_Int + c];
/* 48:48 */    jdField_b_of_type_ArrayOfByte = ByteUtil.a(-1);
/* 49:   */    
/* 51:51 */    int i = 0;
/* 52:52 */    for (int j = 0; j < jdField_a_of_type_Q.c; j++) {
/* 53:53 */      for (int k = 0; k < jdField_a_of_type_Q.jdField_b_of_type_Int; k++) {
/* 54:54 */        for (int m = 0; m < jdField_a_of_type_Q.jdField_a_of_type_Int; m++)
/* 55:   */        {
/* 56:56 */          for (int n = 0; n < jdField_b_of_type_ArrayOfByte.length; n++) {
/* 57:57 */            jdField_a_of_type_ArrayOfByte[(i++)] = jdField_b_of_type_ArrayOfByte[n];
/* 58:   */          }
/* 59:59 */          i += 4;
/* 60:   */        }
/* 61:   */      }
/* 62:   */    }
/* 63:63 */    if ((!jdField_a_of_type_Boolean) && (i != jdField_a_of_type_ArrayOfByte.length / 2)) { throw new AssertionError(i + "/" + jdField_a_of_type_ArrayOfByte.length / 2);
/* 64:   */    }
/* 65:   */  }
/* 66:   */  
/* 73:   */  public static void main(String[] paramArrayOfString) {}
/* 74:   */  
/* 81:   */  public static int a(int paramInt1, int paramInt2, int paramInt3)
/* 82:   */  {
/* 83:83 */    int i = Math.abs(paramInt1) >> 4 & 0xF;
/* 84:84 */    int j = Math.abs(paramInt2) >> 4 & 0xF;
/* 85:   */    
/* 86:   */    int k;
/* 87:87 */    int m = (k = Math.abs(paramInt3) >> 4 & 0xF) * jdField_a_of_type_Q.jdField_b_of_type_Int * jdField_a_of_type_Q.jdField_a_of_type_Int + j * jdField_a_of_type_Q.jdField_a_of_type_Int + i;
/* 88:   */    
/* 90:90 */    if ((!jdField_a_of_type_Boolean) && (m >= jdField_a_of_type_Int)) throw new AssertionError(m + "/" + jdField_a_of_type_Int + ": " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + " ---> " + i + ", " + j + ", " + k + " ");
/* 91:91 */    return m;
/* 92:   */  }
/* 93:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.io.SegmentDataIOOld
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */