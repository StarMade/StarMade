/*    */ package org.schema.game.common.controller.io;
/*    */ 
/*    */ import org.schema.common.util.ByteUtil;
/*    */ import q;
/*    */ 
/*    */ public class SegmentDataIOOld
/*    */ {
/*    */   private static final q jdField_a_of_type_Q;
/*    */   private static int jdField_a_of_type_Int;
/*    */   private static int jdField_b_of_type_Int;
/*    */   private static int c;
/*    */   private static byte[] jdField_a_of_type_ArrayOfByte;
/*    */   private static byte[] jdField_b_of_type_ArrayOfByte;
/*    */ 
/*    */   public static int a(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 83 */     int i = Math.abs(paramInt1) >> 4 & 0xF;
/* 84 */     int j = Math.abs(paramInt2) >> 4 & 0xF;
/*    */     int k;
/* 87 */     int m = (
/* 87 */       k = Math.abs(paramInt3) >> 4 & 0xF) * 
/* 87 */       jdField_a_of_type_Q.jdField_b_of_type_Int * jdField_a_of_type_Q.jdField_a_of_type_Int + j * jdField_a_of_type_Q.jdField_a_of_type_Int + i;
/*    */ 
/* 90 */     if ((!jdField_a_of_type_Boolean) && (m >= jdField_a_of_type_Int)) throw new AssertionError(m + "/" + jdField_a_of_type_Int + ": " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + " ---> " + i + ", " + j + ", " + k + " ");
/* 91 */     return m;
/*    */   }
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 39 */     jdField_b_of_type_Int = (
/* 39 */       SegmentDataIOOld.jdField_a_of_type_Int = (
/* 38 */       SegmentDataIOOld.jdField_a_of_type_Q = new q(16, 16, 16)).jdField_a_of_type_Int * 
/* 38 */       jdField_a_of_type_Q.jdField_b_of_type_Int * jdField_a_of_type_Q.c) << 
/* 39 */       3;
/* 40 */     c = jdField_a_of_type_Int << 3;
/*    */ 
/* 47 */     jdField_a_of_type_ArrayOfByte = new byte[jdField_b_of_type_Int + c];
/* 48 */     jdField_b_of_type_ArrayOfByte = ByteUtil.a(-1);
/*    */ 
/* 51 */     int i = 0;
/* 52 */     for (int j = 0; j < jdField_a_of_type_Q.c; j++) {
/* 53 */       for (int k = 0; k < jdField_a_of_type_Q.jdField_b_of_type_Int; k++) {
/* 54 */         for (int m = 0; m < jdField_a_of_type_Q.jdField_a_of_type_Int; m++)
/*    */         {
/* 56 */           for (int n = 0; n < jdField_b_of_type_ArrayOfByte.length; n++) {
/* 57 */             jdField_a_of_type_ArrayOfByte[(i++)] = jdField_b_of_type_ArrayOfByte[n];
/*    */           }
/* 59 */           i += 4;
/*    */         }
/*    */       }
/*    */     }
/* 63 */     if ((!jdField_a_of_type_Boolean) && (i != jdField_a_of_type_ArrayOfByte.length / 2)) throw new AssertionError(i + "/" + jdField_a_of_type_ArrayOfByte.length / 2);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.io.SegmentDataIOOld
 * JD-Core Version:    0.6.2
 */