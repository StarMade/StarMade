/*    */ package org.schema.game.common.data.world.migration;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.RandomAccessFile;
/*    */ import kQ;
/*    */ import mw;
/*    */ import org.schema.game.common.controller.io.SegmentDataIOOld;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ import org.schema.game.common.data.world.DeserializationException;
/*    */ import org.schema.game.common.data.world.SegmentData;
/*    */ import q;
/*    */ import sy;
/*    */ 
/*    */ public class Migration00898
/*    */ {
/*    */   private static final q jdField_a_of_type_Q;
/*    */   private static int jdField_a_of_type_Int;
/* 61 */   private static int jdField_b_of_type_Int = (
/* 61 */     Migration00898.jdField_a_of_type_Int = (
/* 60 */     Migration00898.jdField_a_of_type_Q = new q(16, 16, 16)).jdField_a_of_type_Int * 
/* 60 */     jdField_a_of_type_Q.jdField_b_of_type_Int * jdField_a_of_type_Q.c) << 
/* 61 */     3;
/* 62 */   private static int c = jdField_a_of_type_Int << 3;
/* 63 */   private static byte[] jdField_a_of_type_ArrayOfByte = new byte[8];
/* 64 */   private static byte[] jdField_b_of_type_ArrayOfByte = new byte[8];
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 23 */     paramArrayOfString = new File("./server-database/DATA");
/*    */     try { a(paramArrayOfString);
/* 26 */       sy.a(new File("./client-database"));
/*    */       return; } catch (IOException localIOException) {
/* 29 */       localIOException.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void a(File paramFile)
/*    */   {
/* 72 */     byte[] arrayOfByte = new byte[1048576];
/* 73 */     ElementKeyMap.initializeData();
/* 74 */     paramFile = paramFile.listFiles();
/* 75 */     int[] arrayOfInt1 = new int[2];
/*    */ 
/* 77 */     SegmentData localSegmentData = new SegmentData(true);
/*    */ 
/* 79 */     for (File localFile : paramFile)
/*    */       try
/*    */       {
/* 82 */         System.err.println("migrating " + localFile.getAbsolutePath() + " " + localFile.exists());
/*    */ 
/* 84 */         if (localFile.getName().endsWith(".smd"))
/*    */         {
/* 87 */           System.err.println("TRYING TO MIGRATE: " + localFile.getAbsolutePath());
/* 88 */           RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile.getAbsolutePath(), "r");
/*    */ 
/* 92 */           for (int k = 0; k < 256; k += 16) {
/* 93 */             for (int m = 0; m < 256; m += 16) {
/* 94 */               for (int n = 0; n < 256; n += 16)
/*    */               {
/*    */                 int i1;
/* 100 */                 Object localObject = localRandomAccessFile; int[] arrayOfInt2 = arrayOfInt1; int i2 = i1 = SegmentDataIOOld.a(n, m, k);
/*    */ 
/* 100 */                 ((RandomAccessFile)localObject).seek(i2 * jdField_a_of_type_ArrayOfByte.length); arrayOfInt2[0] = ((RandomAccessFile)localObject).readInt(); arrayOfInt2[1] = ((RandomAccessFile)localObject).readInt();
/* 101 */                 i2 = arrayOfInt1[0];
/* 102 */                 int i3 = arrayOfInt1[1];
/*    */ 
/* 104 */                 if (i2 >= 0) {
/* 105 */                   long l1 = jdField_b_of_type_Int + i1 * jdField_b_of_type_ArrayOfByte.length;
/*    */ 
/* 110 */                   if ((!jdField_a_of_type_Boolean) && (l1 >= localRandomAccessFile.length())) throw new AssertionError(" " + l1 + " / " + localRandomAccessFile.length() + " on  (" + n + ", " + m + ", " + k + ") on " + localFile.getName() + "  offest(" + i2 + "); offsetIndex(" + i1 + ")");
/*    */ 
/* 116 */                   localRandomAccessFile.seek(l1);
/* 117 */                   localRandomAccessFile.readLong();
/*    */ 
/* 119 */                   if ((!jdField_a_of_type_Boolean) && ((i3 <= 0) || (i3 >= 5120))) throw new AssertionError(" len: " + i3 + " / 5120 ON " + localFile.getName() + " (" + n + ", " + m + ", " + k + ")");
/*    */ 
/* 132 */                   long l2 = jdField_b_of_type_Int + c + i2 * 5120;
/*    */ 
/* 134 */                   localRandomAccessFile.seek(l2);
/*    */ 
/* 136 */                   mw localmw = new mw(null);
/* 137 */                   localSegmentData.reset();
/* 138 */                   localmw.a(localSegmentData);
/*    */ 
/* 141 */                   localRandomAccessFile.readFully(arrayOfByte, 0, i3);
/*    */ 
/* 145 */                   ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte, 0, i3);
/*    */ 
/* 147 */                   localObject = new DataInputStream(localByteArrayInputStream);
/*    */                   try
/*    */                   {
/* 151 */                     localmw.a((DataInputStream)localObject, i3, true);
/*    */                   }
/*    */                   catch (DeserializationException localDeserializationException) {
/* 154 */                     localDeserializationException.printStackTrace();
/*    */                   }
/*    */ 
/* 156 */                   localmw.b(1);
/*    */ 
/* 158 */                   System.err.println("MIGRATING: " + n + ", " + m + ", " + k + " -> " + localmw.jdField_a_of_type_Q);
/*    */ 
/* 163 */                   localmw.a(System.currentTimeMillis());
/* 164 */                   localByteArrayInputStream.close();
/*    */ 
/* 166 */                   ((DataInputStream)localObject).close();
/*    */ 
/* 173 */                   kQ.a(localmw, arrayOfInt1, localFile);
/*    */                 }
/*    */               }
/*    */             }
/*    */           }
/* 178 */           localRandomAccessFile.close();
/* 179 */           localFile.deleteOnExit();
/* 180 */           boolean bool = localFile.delete();
/* 181 */           System.err.println("DELETING: " + localFile.getAbsolutePath() + ": " + bool);
/*    */         }
/*    */ 
/*    */       }
/*    */       catch (Exception localException)
/*    */       {
/* 187 */         localException.printStackTrace();
/*    */       }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.migration.Migration00898
 * JD-Core Version:    0.6.2
 */