package org.schema.game.common.data.world.migration;

import class_1182;
import class_48;
import class_726;
import class_795;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.DeserializationException;
import org.schema.game.common.data.world.SegmentData;

public class Migration0061
{
  private static class_48 jdField_field_2156_of_type_Class_48;
  private static int jdField_field_2156_of_type_Int = (Migration0061.jdField_field_2156_of_type_Class_48 = new class_48(16, 16, 16)).field_475 * jdField_field_2156_of_type_Class_48.field_476 * jdField_field_2156_of_type_Class_48.field_477;
  private static byte[] jdField_field_2156_of_type_ArrayOfByte = new byte[8];
  private static int jdField_field_2157_of_type_Int = jdField_field_2156_of_type_Int << 3;
  private static byte[] jdField_field_2157_of_type_ArrayOfByte = new byte[8];
  private static int field_2158 = jdField_field_2156_of_type_Int << 3;
  private static int field_2159;
  private static int field_2160;
  private static int field_2161;
  private static long jdField_field_2156_of_type_Long;
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new File("./server-database/DATA");
    try
    {
      a1(paramArrayOfString);
      class_1182.a(new File("./client-database"));
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  private static void a(class_795 paramclass_795, SegmentData paramSegmentData)
  {
    for (byte b1 = 0; b1 < 16; b1 = (byte)(b1 + 1)) {
      for (byte b2 = 0; b2 < 16; b2 = (byte)(b2 + 1)) {
        for (byte b3 = 0; b3 < 16; b3 = (byte)(b3 + 1))
        {
          int i = class_795.a1(b1, b2, b3);
          int j = SegmentData.getInfoIndex(b1, b2, b3);
          if ((i = paramclass_795.a2(i)) != 0)
          {
            paramSegmentData.setType(j, (short)Math.abs(i));
            try
            {
              short s = ElementKeyMap.getInfo(i).getMaxHitPoints();
              paramSegmentData.setHitpoints(j, s);
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
              System.err.println("type " + i);
            }
          }
        }
      }
    }
  }
  
  private static void a1(File paramFile)
  {
    ElementKeyMap.initializeData();
    paramFile = paramFile.listFiles();
    byte[] arrayOfByte = new byte[3145728];
    for (File localFile : paramFile)
    {
      System.err.println("migrating " + localFile.getAbsolutePath() + " " + localFile.exists());
      if (localFile.getName().endsWith(".smd"))
      {
        long l1 = 0L;
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
        while (l1 < jdField_field_2157_of_type_Int)
        {
          localRandomAccessFile.seek(l1);
          SegmentData localSegmentData1 = localRandomAccessFile.readInt();
          int k = localRandomAccessFile.readInt();
          try
          {
            int m;
            if (localSegmentData1 >= 0)
            {
              Object localObject3 = arrayOfByte;
              int i1 = k;
              long l2 = l1 / jdField_field_2156_of_type_ArrayOfByte.length;
              int n = localSegmentData1;
              Object localObject1 = localRandomAccessFile;
              long l3 = jdField_field_2157_of_type_Int + l2 * jdField_field_2157_of_type_ArrayOfByte.length;
              long l4 = jdField_field_2157_of_type_Int + field_2158 + n * 5120;
              System.err.println("LEN " + ((RandomAccessFile)localObject1).length() + " -> " + l3 + "; offest: " + n + "; headerSize: " + jdField_field_2157_of_type_Int + "; tsArray " + jdField_field_2157_of_type_ArrayOfByte.length + "; read lenth: " + i1 + " -- data position: " + l4);
              ((RandomAccessFile)localObject1).seek(l3);
              ((RandomAccessFile)localObject1).readLong();
              ((RandomAccessFile)localObject1).seek(l4);
              if ((!jdField_field_2156_of_type_Boolean) && ((i1 <= 0) || (i1 >= 5120) || (i1 > localObject3.length))) {
                throw new AssertionError("OHOH: " + i1 + " / " + localObject3.length);
              }
              ((RandomAccessFile)localObject1).readFully((byte[])localObject3, 0, i1);
              ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream((byte[])localObject3, 0, i1);
              DataInputStream localDataInputStream;
              localObject1 = localDataInputStream = new DataInputStream(localByteArrayInputStream);
              if (jdField_field_2156_of_type_Int < 0) {
                localObject5 = new DataInputStream(new GZIPInputStream((InputStream)localObject1));
              } else {
                localObject5 = new DataInputStream(new GZIPInputStream((InputStream)localObject1, jdField_field_2156_of_type_Int));
              }
              jdField_field_2156_of_type_Long = ((DataInputStream)localObject5).readLong();
              field_2159 = ((DataInputStream)localObject5).readInt();
              field_2160 = ((DataInputStream)localObject5).readInt();
              field_2161 = ((DataInputStream)localObject5).readInt();
              ((DataInputStream)localObject5).readByte();
              (localObject6 = new class_795()).a((DataInputStream)localObject5);
              Object localObject4 = localObject6;
              localByteArrayInputStream.close();
              localDataInputStream.close();
              localObject1 = localObject4;
              SegmentData localSegmentData2 = new SegmentData(false);
              a((class_795)localObject1, localSegmentData2);
              Object localObject2 = localSegmentData2;
              l2 = l1 / jdField_field_2156_of_type_ArrayOfByte.length;
              localSegmentData2 = localSegmentData1;
              localObject1 = localFile;
              localObject3 = new class_726((File)localObject1);
              DataOutputStream localDataOutputStream1 = new DataOutputStream((OutputStream)localObject3);
              ((class_726)localObject3).a1(jdField_field_2157_of_type_Int + field_2158 + localSegmentData2 * 5120);
              localObject4 = localObject2;
              long l5 = jdField_field_2156_of_type_Long;
              int i2 = field_2161;
              m = field_2160;
              int i4 = field_2159;
              DataOutputStream localDataOutputStream2 = localDataOutputStream1;
              Object localObject5 = new DataOutputStream(localDataOutputStream2);
              if ((!jdField_field_2156_of_type_Boolean) && (((DataOutputStream)localObject5).size() != 0)) {
                throw new AssertionError();
              }
              GZIPOutputStream localGZIPOutputStream;
              Object localObject6 = new DataOutputStream(localGZIPOutputStream = new GZIPOutputStream((OutputStream)localObject5));
              System.err.println("SERIALIZING: " + i4 + ", " + m + ", " + i2 + "; change: " + l5);
              ((DataOutputStream)localObject6).writeLong(l5);
              ((DataOutputStream)localObject6).writeInt(i4);
              ((DataOutputStream)localObject6).writeInt(m);
              ((DataOutputStream)localObject6).writeInt(i2);
              ((DataOutputStream)localObject6).writeByte(1);
              localObject4.serialize((DataOutputStream)localObject6);
              localGZIPOutputStream.finish();
              localGZIPOutputStream.flush();
              int i3 = ((DataOutputStream)localObject5).size();
              ((class_726)localObject3).a1(l2 * jdField_field_2156_of_type_ArrayOfByte.length);
              System.err.println("WRITING AT OFFSET: " + localSegmentData2 + " with length " + i3);
              localDataOutputStream1.writeInt(localSegmentData2);
              localDataOutputStream1.writeInt(i3);
              ((class_726)localObject3).a1(jdField_field_2157_of_type_Int + l2 * jdField_field_2157_of_type_ArrayOfByte.length);
              localDataOutputStream1.writeLong(jdField_field_2156_of_type_Long);
              localDataOutputStream1.flush();
              ((class_726)localObject3).flush();
              ((class_726)localObject3).close();
              System.err.println("DONE MIGRATED: " + localFile.getAbsolutePath() + " (" + localSegmentData1 + ")");
            }
            else if ((!jdField_field_2156_of_type_Boolean) && (m > 0))
            {
              throw new AssertionError(m);
            }
          }
          catch (DeserializationException localDeserializationException)
          {
            System.err.println("SEEK POS: " + l1);
            localDeserializationException.printStackTrace();
          }
          catch (IOException localIOException)
          {
            System.err.println("SEEK POS: " + l1);
            localIOException.printStackTrace();
          }
          l1 += jdField_field_2156_of_type_ArrayOfByte.length;
        }
        localRandomAccessFile.close();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.world.migration.Migration0061
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */