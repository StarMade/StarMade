/*    */ package org.schema.game.common.data.world.migration;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.io.RandomAccessFile;
/*    */ import java.util.zip.GZIPInputStream;
/*    */ import java.util.zip.GZIPOutputStream;
/*    */ import kP;
/*    */ import mK;
/*    */ import org.schema.game.common.data.element.ElementInformation;
/*    */ import org.schema.game.common.data.element.ElementKeyMap;
/*    */ import org.schema.game.common.data.world.DeserializationException;
/*    */ import org.schema.game.common.data.world.SegmentData;
/*    */ import q;
/*    */ import sy;
/*    */ 
/*    */ public class Migration0061
/*    */ {
/*    */   private static q jdField_a_of_type_Q;
/* 22 */   private static int jdField_a_of_type_Int = (
/* 22 */     Migration0061.jdField_a_of_type_Q = new q(16, 16, 16)).jdField_a_of_type_Int * 
/* 22 */     jdField_a_of_type_Q.jdField_b_of_type_Int * jdField_a_of_type_Q.c;
/* 23 */   private static byte[] jdField_a_of_type_ArrayOfByte = new byte[8];
/* 24 */   private static int jdField_b_of_type_Int = jdField_a_of_type_Int << 3;
/* 25 */   private static byte[] jdField_b_of_type_ArrayOfByte = new byte[8];
/* 26 */   private static int c = jdField_a_of_type_Int << 3;
/*    */   private static int d;
/*    */   private static int e;
/*    */   private static int f;
/*    */   private static long jdField_a_of_type_Long;
/*    */ 
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 33 */     paramArrayOfString = new File("./server-database/DATA");
/*    */     try { a(paramArrayOfString);
/* 36 */       sy.a(new File("./client-database"));
/*    */       return; } catch (IOException localIOException) {
/* 39 */       localIOException.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   private static void a(mK parammK, SegmentData paramSegmentData)
/*    */   {
/* 43 */     for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1))
/* 44 */       for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1))
/* 45 */         for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1))
/*    */         {
/* 47 */           int i = mK.a(b1, b2, b3);
/* 48 */           int j = SegmentData.getInfoIndex(b1, b2, b3);
/*    */ 
/* 53 */           if ((
/* 53 */             i = parammK.a(i)) != 0)
/*    */           {
/* 54 */             paramSegmentData.setType(j, (short)Math.abs(i));
/*    */             try {
/* 56 */               short s = ElementKeyMap.getInfo(i).getMaxHitPoints();
/* 57 */               paramSegmentData.setHitpoints(j, s);
/*    */             }
/*    */             catch (Exception localException)
/*    */             {
/* 61 */               localException.printStackTrace();
/*    */ 
/* 60 */               System.err.println("type " + i);
/*    */             }
/*    */           }
/*    */         }
/*    */   }
/*    */ 
/*    */   private static void a(File paramFile)
/*    */   {
/* 69 */     ElementKeyMap.initializeData();
/* 70 */     paramFile = paramFile.listFiles();
/* 71 */     byte[] arrayOfByte = new byte[3145728];
/* 72 */     for (File localFile : paramFile)
/*    */     {
/* 74 */       System.err.println("migrating " + localFile.getAbsolutePath() + " " + localFile.exists());
/* 75 */       if (localFile.getName().endsWith(".smd")) {
/* 76 */         long l1 = 0L;
/*    */ 
/* 81 */         RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
/* 82 */         while (l1 < jdField_b_of_type_Int) {
/* 83 */           localRandomAccessFile.seek(l1);
/*    */ 
/* 85 */           SegmentData localSegmentData1 = localRandomAccessFile.readInt();
/* 86 */           int k = localRandomAccessFile.readInt();
/*    */           try
/*    */           {
/*    */             int m;
/* 89 */             if (localSegmentData1 >= 0)
/*    */             {
/* 92 */               Object localObject3 = arrayOfByte; int i1 = k; long l2 = l1 / jdField_a_of_type_ArrayOfByte.length; int n = localSegmentData1; Object localObject1 = localRandomAccessFile; long l3 = jdField_b_of_type_Int + l2 * jdField_b_of_type_ArrayOfByte.length; long l4 = jdField_b_of_type_Int + c + n * 5120; System.err.println("LEN " + ((RandomAccessFile)localObject1).length() + " -> " + l3 + "; offest: " + n + "; headerSize: " + jdField_b_of_type_Int + "; tsArray " + jdField_b_of_type_ArrayOfByte.length + "; read lenth: " + i1 + " -- data position: " + l4); ((RandomAccessFile)localObject1).seek(l3); ((RandomAccessFile)localObject1).readLong(); ((RandomAccessFile)localObject1).seek(l4); if ((!jdField_a_of_type_Boolean) && ((i1 <= 0) || (i1 >= 5120) || (i1 > localObject3.length))) throw new AssertionError("OHOH: " + i1 + " / " + localObject3.length); ((RandomAccessFile)localObject1).readFully((byte[])localObject3, 0, i1); ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream((byte[])localObject3, 0, i1);
/*    */               DataInputStream localDataInputStream;
/* 92 */               localObject1 = localDataInputStream = new DataInputStream(localByteArrayInputStream); if (jdField_a_of_type_Int < 0) localObject5 = new DataInputStream(new GZIPInputStream((InputStream)localObject1)); else localObject5 = new DataInputStream(new GZIPInputStream((InputStream)localObject1, jdField_a_of_type_Int)); jdField_a_of_type_Long = ((DataInputStream)localObject5).readLong(); d = ((DataInputStream)localObject5).readInt(); e = ((DataInputStream)localObject5).readInt(); f = ((DataInputStream)localObject5).readInt(); ((DataInputStream)localObject5).readByte(); (localObject6 = new mK()).a((DataInputStream)localObject5); Object localObject4 = localObject6; localByteArrayInputStream.close(); localDataInputStream.close(); localObject1 = localObject4;
/*    */ 
/* 94 */               SegmentData localSegmentData2 = new SegmentData(false);
/*    */ 
/* 96 */               a((mK)localObject1, localSegmentData2);
/*    */ 
/* 98 */               Object localObject2 = localSegmentData2; l2 = l1 / jdField_a_of_type_ArrayOfByte.length; localSegmentData2 = localSegmentData1; localObject1 = localFile; localObject3 = new kP((File)localObject1); DataOutputStream localDataOutputStream1 = new DataOutputStream((OutputStream)localObject3); ((kP)localObject3).a(jdField_b_of_type_Int + c + localSegmentData2 * 5120); localObject4 = localObject2; long l5 = jdField_a_of_type_Long; int i2 = f; m = e; int i4 = d; DataOutputStream localDataOutputStream2 = localDataOutputStream1; Object localObject5 = new DataOutputStream(localDataOutputStream2); if ((!jdField_a_of_type_Boolean) && (((DataOutputStream)localObject5).size() != 0)) throw new AssertionError();
/*    */               GZIPOutputStream localGZIPOutputStream;
/* 98 */               Object localObject6 = new DataOutputStream(localGZIPOutputStream = new GZIPOutputStream((OutputStream)localObject5)); System.err.println("SERIALIZING: " + i4 + ", " + m + ", " + i2 + "; change: " + l5); ((DataOutputStream)localObject6).writeLong(l5); ((DataOutputStream)localObject6).writeInt(i4); ((DataOutputStream)localObject6).writeInt(m); ((DataOutputStream)localObject6).writeInt(i2); ((DataOutputStream)localObject6).writeByte(1); localObject4.serialize((DataOutputStream)localObject6); localGZIPOutputStream.finish(); localGZIPOutputStream.flush(); int i3 = ((DataOutputStream)localObject5).size(); ((kP)localObject3).a(l2 * jdField_a_of_type_ArrayOfByte.length); System.err.println("WRITING AT OFFSET: " + localSegmentData2 + " with length " + i3); localDataOutputStream1.writeInt(localSegmentData2); localDataOutputStream1.writeInt(i3); ((kP)localObject3).a(jdField_b_of_type_Int + l2 * jdField_b_of_type_ArrayOfByte.length); localDataOutputStream1.writeLong(jdField_a_of_type_Long); localDataOutputStream1.flush(); ((kP)localObject3).flush(); ((kP)localObject3).close();
/*    */ 
/* 100 */               System.err.println("DONE MIGRATED: " + localFile.getAbsolutePath() + " (" + localSegmentData1 + ")");
/*    */             }
/* 102 */             else if ((!jdField_a_of_type_Boolean) && (m > 0)) { throw new AssertionError(m); }
/*    */           }
/*    */           catch (DeserializationException localDeserializationException)
/*    */           {
/* 106 */             System.err.println("SEEK POS: " + l1);
/* 107 */             localDeserializationException.printStackTrace();
/*    */           } catch (IOException localIOException) {
/* 109 */             System.err.println("SEEK POS: " + l1);
/* 110 */             localIOException.printStackTrace();
/*    */           }
/* 112 */           l1 += jdField_a_of_type_ArrayOfByte.length;
/*    */         }
/*    */ 
/* 116 */         localRandomAccessFile.close();
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.migration.Migration0061
 * JD-Core Version:    0.6.2
 */