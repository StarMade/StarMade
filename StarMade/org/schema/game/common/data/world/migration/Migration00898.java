/*   1:    */package org.schema.game.common.data.world.migration;
/*   2:    */
/*   3:    */import java.io.ByteArrayInputStream;
/*   4:    */import java.io.DataInputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.PrintStream;
/*   7:    */import kQ;
/*   8:    */import mw;
/*   9:    */import org.schema.game.common.controller.io.SegmentDataIOOld;
/*  10:    */import org.schema.game.common.data.element.ElementKeyMap;
/*  11:    */import org.schema.game.common.data.world.DeserializationException;
/*  12:    */import org.schema.game.common.data.world.SegmentData;
/*  13:    */import q;
/*  14:    */import sy;
/*  15:    */
/*  16:    */public class Migration00898
/*  17:    */{
/*  18:    */  private static final q jdField_a_of_type_Q;
/*  19:    */  private static int jdField_a_of_type_Int;
/*  20:    */  
/*  21:    */  public static void main(String[] paramArrayOfString)
/*  22:    */  {
/*  23: 23 */    paramArrayOfString = new java.io.File("./server-database/DATA");
/*  24:    */    try {
/*  25: 25 */      a(paramArrayOfString);
/*  26: 26 */      sy.a(new java.io.File("./client-database")); return;
/*  27: 27 */    } catch (IOException localIOException) { 
/*  28:    */      
/*  29: 29 */        localIOException;
/*  30:    */    }
/*  31:    */  }
/*  32:    */  
/*  63: 61 */  private static int jdField_b_of_type_Int = (Migration00898.jdField_a_of_type_Int = (Migration00898.jdField_a_of_type_Q = new q(16, 16, 16)).jdField_a_of_type_Int * jdField_a_of_type_Q.jdField_b_of_type_Int * jdField_a_of_type_Q.c) << 3;
/*  64: 62 */  private static int c = jdField_a_of_type_Int << 3;
/*  65: 63 */  private static byte[] jdField_a_of_type_ArrayOfByte = new byte[8];
/*  66: 64 */  private static byte[] jdField_b_of_type_ArrayOfByte = new byte[8];
/*  67:    */  
/*  72:    */  public static void a(java.io.File paramFile)
/*  73:    */  {
/*  74: 72 */    byte[] arrayOfByte = new byte[1048576];
/*  75: 73 */    ElementKeyMap.initializeData();
/*  76: 74 */    paramFile = paramFile.listFiles();
/*  77: 75 */    int[] arrayOfInt1 = new int[2];
/*  78:    */    
/*  79: 77 */    SegmentData localSegmentData = new SegmentData(true);
/*  80:    */    
/*  81: 79 */    for (java.io.File localFile : paramFile) {
/*  82:    */      try
/*  83:    */      {
/*  84: 82 */        System.err.println("migrating " + localFile.getAbsolutePath() + " " + localFile.exists());
/*  85:    */        
/*  86: 84 */        if (localFile.getName().endsWith(".smd"))
/*  87:    */        {
/*  89: 87 */          System.err.println("TRYING TO MIGRATE: " + localFile.getAbsolutePath());
/*  90: 88 */          java.io.RandomAccessFile localRandomAccessFile = new java.io.RandomAccessFile(localFile.getAbsolutePath(), "r");
/*  91:    */          
/*  94: 92 */          for (int k = 0; k < 256; k += 16) {
/*  95: 93 */            for (int m = 0; m < 256; m += 16) {
/*  96: 94 */              for (int n = 0; n < 256; n += 16)
/*  97:    */              {
/*  98:    */                int i1;
/*  99:    */                
/* 102:100 */                Object localObject = localRandomAccessFile;int[] arrayOfInt2 = arrayOfInt1;int i2 = i1 = SegmentDataIOOld.a(n, m, k);((java.io.RandomAccessFile)localObject).seek(i2 * jdField_a_of_type_ArrayOfByte.length);arrayOfInt2[0] = ((java.io.RandomAccessFile)localObject).readInt();arrayOfInt2[1] = ((java.io.RandomAccessFile)localObject).readInt();
/* 103:101 */                i2 = arrayOfInt1[0];
/* 104:102 */                int i3 = arrayOfInt1[1];
/* 105:    */                
/* 106:104 */                if (i2 >= 0) {
/* 107:105 */                  long l1 = jdField_b_of_type_Int + i1 * jdField_b_of_type_ArrayOfByte.length;
/* 108:    */                  
/* 112:110 */                  if ((!jdField_a_of_type_Boolean) && (l1 >= localRandomAccessFile.length())) { throw new AssertionError(" " + l1 + " / " + localRandomAccessFile.length() + " on  (" + n + ", " + m + ", " + k + ") on " + localFile.getName() + "  offest(" + i2 + "); offsetIndex(" + i1 + ")");
/* 113:    */                  }
/* 114:    */                  
/* 118:116 */                  localRandomAccessFile.seek(l1);
/* 119:117 */                  localRandomAccessFile.readLong();
/* 120:    */                  
/* 121:119 */                  if ((!jdField_a_of_type_Boolean) && ((i3 <= 0) || (i3 >= 5120))) { throw new AssertionError(" len: " + i3 + " / 5120 ON " + localFile.getName() + " (" + n + ", " + m + ", " + k + ")");
/* 122:    */                  }
/* 123:    */                  
/* 134:132 */                  long l2 = jdField_b_of_type_Int + c + i2 * 5120;
/* 135:    */                  
/* 136:134 */                  localRandomAccessFile.seek(l2);
/* 137:    */                  
/* 138:136 */                  mw localmw = new mw(null);
/* 139:137 */                  localSegmentData.reset();
/* 140:138 */                  localmw.a(localSegmentData);
/* 141:    */                  
/* 143:141 */                  localRandomAccessFile.readFully(arrayOfByte, 0, i3);
/* 144:    */                  
/* 147:145 */                  ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte, 0, i3);
/* 148:    */                  
/* 149:147 */                  localObject = new DataInputStream(localByteArrayInputStream);
/* 150:    */                  
/* 151:    */                  try
/* 152:    */                  {
/* 153:151 */                    localmw.a((DataInputStream)localObject, i3, true);
/* 154:152 */                  } catch (DeserializationException localDeserializationException) { 
/* 155:    */                    
/* 156:154 */                      localDeserializationException;
/* 157:    */                  }
/* 158:    */                  
/* 160:156 */                  localmw.b(1);
/* 161:    */                  
/* 162:158 */                  System.err.println("MIGRATING: " + n + ", " + m + ", " + k + " -> " + localmw.jdField_a_of_type_Q);
/* 163:    */                  
/* 167:163 */                  localmw.a(System.currentTimeMillis());
/* 168:164 */                  localByteArrayInputStream.close();
/* 169:    */                  
/* 170:166 */                  ((DataInputStream)localObject).close();
/* 171:    */                  
/* 177:173 */                  kQ.a(localmw, arrayOfInt1, localFile);
/* 178:    */                }
/* 179:    */              }
/* 180:    */            }
/* 181:    */          }
/* 182:178 */          localRandomAccessFile.close();
/* 183:179 */          localFile.deleteOnExit();
/* 184:180 */          boolean bool = localFile.delete();
/* 185:181 */          System.err.println("DELETING: " + localFile.getAbsolutePath() + ": " + bool);
/* 186:    */        }
/* 187:    */      }
/* 188:    */      catch (Exception localException) {
/* 189:185 */        
/* 190:    */        
/* 191:187 */          localException;
/* 192:    */      }
/* 193:    */    }
/* 194:    */  }
/* 195:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.world.migration.Migration00898
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */