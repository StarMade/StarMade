/*   1:    */package org.schema.game.common.data.world.migration;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.File;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.InputStream;
/*   7:    */import java.io.OutputStream;
/*   8:    */import java.io.PrintStream;
/*   9:    */import java.util.zip.GZIPInputStream;
/*  10:    */import java.util.zip.GZIPOutputStream;
/*  11:    */import mK;
/*  12:    */import org.schema.game.common.data.element.ElementInformation;
/*  13:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  14:    */import org.schema.game.common.data.world.DeserializationException;
/*  15:    */import org.schema.game.common.data.world.SegmentData;
/*  16:    */import q;
/*  17:    */import sy;
/*  18:    */
/*  19:    */public class Migration0061
/*  20:    */{
/*  21:    */  private static q jdField_a_of_type_Q;
/*  22: 22 */  private static int jdField_a_of_type_Int = (Migration0061.jdField_a_of_type_Q = new q(16, 16, 16)).jdField_a_of_type_Int * jdField_a_of_type_Q.jdField_b_of_type_Int * jdField_a_of_type_Q.c;
/*  23: 23 */  private static byte[] jdField_a_of_type_ArrayOfByte = new byte[8];
/*  24: 24 */  private static int jdField_b_of_type_Int = jdField_a_of_type_Int << 3;
/*  25: 25 */  private static byte[] jdField_b_of_type_ArrayOfByte = new byte[8];
/*  26: 26 */  private static int c = jdField_a_of_type_Int << 3;
/*  27:    */  private static int d;
/*  28:    */  private static int e;
/*  29:    */  private static int f;
/*  30:    */  private static long jdField_a_of_type_Long;
/*  31:    */  
/*  32:    */  public static void main(String[] paramArrayOfString) {
/*  33: 33 */    paramArrayOfString = new File("./server-database/DATA");
/*  34:    */    try {
/*  35: 35 */      a(paramArrayOfString);
/*  36: 36 */      sy.a(new File("./client-database")); return;
/*  37: 37 */    } catch (IOException localIOException) { 
/*  38:    */      
/*  39: 39 */        localIOException;
/*  40:    */    }
/*  41:    */  }
/*  42:    */  
/*  43:    */  private static void a(mK parammK, SegmentData paramSegmentData)
/*  44:    */  {
/*  45: 43 */    for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
/*  46: 44 */      for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1))
/*  47: 45 */        for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1))
/*  48:    */        {
/*  49: 47 */          int i = mK.a(b1, b2, b3);
/*  50: 48 */          int j = SegmentData.getInfoIndex(b1, b2, b3);
/*  51:    */          
/*  55: 53 */          if ((i = parammK.a(i)) != 0) {
/*  56: 54 */            paramSegmentData.setType(j, (short)Math.abs(i));
/*  57:    */            try {
/*  58: 56 */              short s = ElementKeyMap.getInfo(i).getMaxHitPoints();
/*  59: 57 */              paramSegmentData.setHitpoints(j, s);
/*  60: 58 */            } catch (Exception localException) { 
/*  61:    */              
/*  63: 61 */                localException.printStackTrace();System.err.println("type " + i);
/*  64:    */            }
/*  65:    */          }
/*  66:    */        }
/*  67:    */    }
/*  68:    */  }
/*  69:    */  
/*  70:    */  private static void a(File paramFile) {
/*  71: 69 */    ElementKeyMap.initializeData();
/*  72: 70 */    paramFile = paramFile.listFiles();
/*  73: 71 */    byte[] arrayOfByte = new byte[3145728];
/*  74: 72 */    for (File localFile : paramFile)
/*  75:    */    {
/*  76: 74 */      System.err.println("migrating " + localFile.getAbsolutePath() + " " + localFile.exists());
/*  77: 75 */      if (localFile.getName().endsWith(".smd")) {
/*  78: 76 */        long l1 = 0L;
/*  79:    */        
/*  83: 81 */        java.io.RandomAccessFile localRandomAccessFile = new java.io.RandomAccessFile(localFile, "rw");
/*  84: 82 */        while (l1 < jdField_b_of_type_Int) {
/*  85: 83 */          localRandomAccessFile.seek(l1);
/*  86:    */          
/*  87: 85 */          SegmentData localSegmentData1 = localRandomAccessFile.readInt();
/*  88: 86 */          int k = localRandomAccessFile.readInt();
/*  89:    */          try {
/*  90:    */            int m;
/*  91: 89 */            if (localSegmentData1 >= 0)
/*  92:    */            {
/*  94: 92 */              Object localObject3 = arrayOfByte;int i1 = k;long l2 = l1 / jdField_a_of_type_ArrayOfByte.length;int n = localSegmentData1;Object localObject1 = localRandomAccessFile;long l3 = jdField_b_of_type_Int + l2 * jdField_b_of_type_ArrayOfByte.length;long l4 = jdField_b_of_type_Int + c + n * 5120;System.err.println("LEN " + ((java.io.RandomAccessFile)localObject1).length() + " -> " + l3 + "; offest: " + n + "; headerSize: " + jdField_b_of_type_Int + "; tsArray " + jdField_b_of_type_ArrayOfByte.length + "; read lenth: " + i1 + " -- data position: " + l4);((java.io.RandomAccessFile)localObject1).seek(l3);((java.io.RandomAccessFile)localObject1).readLong();((java.io.RandomAccessFile)localObject1).seek(l4); if ((!jdField_a_of_type_Boolean) && ((i1 <= 0) || (i1 >= 5120) || (i1 > localObject3.length))) throw new AssertionError("OHOH: " + i1 + " / " + localObject3.length); ((java.io.RandomAccessFile)localObject1).readFully((byte[])localObject3, 0, i1);ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream((byte[])localObject3, 0, i1); java.io.DataInputStream localDataInputStream; localObject1 = localDataInputStream = new java.io.DataInputStream(localByteArrayInputStream); if (jdField_a_of_type_Int < 0) localObject5 = new java.io.DataInputStream(new GZIPInputStream((InputStream)localObject1)); else localObject5 = new java.io.DataInputStream(new GZIPInputStream((InputStream)localObject1, jdField_a_of_type_Int)); jdField_a_of_type_Long = ((java.io.DataInputStream)localObject5).readLong();d = ((java.io.DataInputStream)localObject5).readInt();e = ((java.io.DataInputStream)localObject5).readInt();f = ((java.io.DataInputStream)localObject5).readInt();((java.io.DataInputStream)localObject5).readByte();(localObject6 = new mK()).a((java.io.DataInputStream)localObject5);Object localObject4 = localObject6;localByteArrayInputStream.close();localDataInputStream.close();localObject1 = localObject4;
/*  95:    */              
/*  96: 94 */              SegmentData localSegmentData2 = new SegmentData(false);
/*  97:    */              
/*  98: 96 */              a((mK)localObject1, localSegmentData2);
/*  99:    */              
/* 100: 98 */              Object localObject2 = localSegmentData2;l2 = l1 / jdField_a_of_type_ArrayOfByte.length;localSegmentData2 = localSegmentData1;localObject1 = localFile;localObject3 = new kP((File)localObject1);java.io.DataOutputStream localDataOutputStream1 = new java.io.DataOutputStream((OutputStream)localObject3);((kP)localObject3).a(jdField_b_of_type_Int + c + localSegmentData2 * 5120);localObject4 = localObject2;long l5 = jdField_a_of_type_Long;int i2 = f;m = e;int i4 = d;java.io.DataOutputStream localDataOutputStream2 = localDataOutputStream1;Object localObject5 = new java.io.DataOutputStream(localDataOutputStream2); if ((!jdField_a_of_type_Boolean) && (((java.io.DataOutputStream)localObject5).size() != 0)) throw new AssertionError(); GZIPOutputStream localGZIPOutputStream; Object localObject6 = new java.io.DataOutputStream(localGZIPOutputStream = new GZIPOutputStream((OutputStream)localObject5));System.err.println("SERIALIZING: " + i4 + ", " + m + ", " + i2 + "; change: " + l5);((java.io.DataOutputStream)localObject6).writeLong(l5);((java.io.DataOutputStream)localObject6).writeInt(i4);((java.io.DataOutputStream)localObject6).writeInt(m);((java.io.DataOutputStream)localObject6).writeInt(i2);((java.io.DataOutputStream)localObject6).writeByte(1);localObject4.serialize((java.io.DataOutputStream)localObject6);localGZIPOutputStream.finish();localGZIPOutputStream.flush();int i3 = ((java.io.DataOutputStream)localObject5).size();((kP)localObject3).a(l2 * jdField_a_of_type_ArrayOfByte.length);System.err.println("WRITING AT OFFSET: " + localSegmentData2 + " with length " + i3);localDataOutputStream1.writeInt(localSegmentData2);localDataOutputStream1.writeInt(i3);((kP)localObject3).a(jdField_b_of_type_Int + l2 * jdField_b_of_type_ArrayOfByte.length);localDataOutputStream1.writeLong(jdField_a_of_type_Long);localDataOutputStream1.flush();((kP)localObject3).flush();((kP)localObject3).close();
/* 101:    */              
/* 102:100 */              System.err.println("DONE MIGRATED: " + localFile.getAbsolutePath() + " (" + localSegmentData1 + ")");
/* 103:    */            }
/* 104:102 */            else if ((!jdField_a_of_type_Boolean) && (m > 0)) { throw new AssertionError(m);
/* 105:    */            }
/* 106:    */          }
/* 107:    */          catch (DeserializationException localDeserializationException) {
/* 108:106 */            System.err.println("SEEK POS: " + l1);
/* 109:107 */            localDeserializationException.printStackTrace();
/* 110:    */          } catch (IOException localIOException) {
/* 111:109 */            System.err.println("SEEK POS: " + l1);
/* 112:110 */            localIOException.printStackTrace();
/* 113:    */          }
/* 114:112 */          l1 += jdField_a_of_type_ArrayOfByte.length;
/* 115:    */        }
/* 116:    */        
/* 118:116 */        localRandomAccessFile.close();
/* 119:    */      }
/* 120:    */    }
/* 121:    */  }
/* 122:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.migration.Migration0061
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */