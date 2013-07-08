package org.schema.game.common.data.world.migration;

import class_1182;
import class_48;
import class_672;
import class_720;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import org.schema.game.common.controller.io.SegmentDataIOOld;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.world.DeserializationException;
import org.schema.game.common.data.world.SegmentData;

public class Migration00898
{
  private static final class_48 jdField_field_1365_of_type_Class_48;
  private static int jdField_field_1365_of_type_Int;
  private static int jdField_field_1366_of_type_Int = (Migration00898.jdField_field_1365_of_type_Int = (Migration00898.jdField_field_1365_of_type_Class_48 = new class_48(16, 16, 16)).field_475 * jdField_field_1365_of_type_Class_48.field_476 * jdField_field_1365_of_type_Class_48.field_477) << 3;
  private static int field_1367 = jdField_field_1365_of_type_Int << 3;
  private static byte[] jdField_field_1365_of_type_ArrayOfByte = new byte[8];
  private static byte[] jdField_field_1366_of_type_ArrayOfByte = new byte[8];
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new File("./server-database/DATA");
    try
    {
      a(paramArrayOfString);
      class_1182.a(new File("./client-database"));
      return;
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
  }
  
  public static void a(File paramFile)
  {
    byte[] arrayOfByte = new byte[1048576];
    ElementKeyMap.initializeData();
    paramFile = paramFile.listFiles();
    int[] arrayOfInt1 = new int[2];
    SegmentData localSegmentData = new SegmentData(true);
    for (File localFile : paramFile) {
      try
      {
        System.err.println("migrating " + localFile.getAbsolutePath() + " " + localFile.exists());
        if (localFile.getName().endsWith(".smd"))
        {
          System.err.println("TRYING TO MIGRATE: " + localFile.getAbsolutePath());
          RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile.getAbsolutePath(), "r");
          for (int k = 0; k < 256; k += 16) {
            for (int m = 0; m < 256; m += 16) {
              for (int n = 0; n < 256; n += 16)
              {
                int i1;
                Object localObject = localRandomAccessFile;
                int[] arrayOfInt2 = arrayOfInt1;
                int i2 = i1 = SegmentDataIOOld.a(n, m, k);
                ((RandomAccessFile)localObject).seek(i2 * jdField_field_1365_of_type_ArrayOfByte.length);
                arrayOfInt2[0] = ((RandomAccessFile)localObject).readInt();
                arrayOfInt2[1] = ((RandomAccessFile)localObject).readInt();
                i2 = arrayOfInt1[0];
                int i3 = arrayOfInt1[1];
                if (i2 >= 0)
                {
                  long l1 = jdField_field_1366_of_type_Int + i1 * jdField_field_1366_of_type_ArrayOfByte.length;
                  if ((!jdField_field_1365_of_type_Boolean) && (l1 >= localRandomAccessFile.length())) {
                    throw new AssertionError(" " + l1 + " / " + localRandomAccessFile.length() + " on  (" + n + ", " + m + ", " + k + ") on " + localFile.getName() + "  offest(" + i2 + "); offsetIndex(" + i1 + ")");
                  }
                  localRandomAccessFile.seek(l1);
                  localRandomAccessFile.readLong();
                  if ((!jdField_field_1365_of_type_Boolean) && ((i3 <= 0) || (i3 >= 5120))) {
                    throw new AssertionError(" len: " + i3 + " / 5120 ON " + localFile.getName() + " (" + n + ", " + m + ", " + k + ")");
                  }
                  long l2 = jdField_field_1366_of_type_Int + field_1367 + i2 * 5120;
                  localRandomAccessFile.seek(l2);
                  class_672 localclass_672 = new class_672(null);
                  localSegmentData.reset();
                  localclass_672.a22(localSegmentData);
                  localRandomAccessFile.readFully(arrayOfByte, 0, i3);
                  ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte, 0, i3);
                  localObject = new DataInputStream(localByteArrayInputStream);
                  try
                  {
                    localclass_672.a43((DataInputStream)localObject, i3, true);
                  }
                  catch (DeserializationException localDeserializationException)
                  {
                    localDeserializationException;
                  }
                  localclass_672.b2(1);
                  System.err.println("MIGRATING: " + n + ", " + m + ", " + k + " -> " + localclass_672.field_34);
                  localclass_672.a46(System.currentTimeMillis());
                  localByteArrayInputStream.close();
                  ((DataInputStream)localObject).close();
                  class_720.a10(localclass_672, arrayOfInt1, localFile);
                }
              }
            }
          }
          localRandomAccessFile.close();
          localFile.deleteOnExit();
          boolean bool = localFile.delete();
          System.err.println("DELETING: " + localFile.getAbsolutePath() + ": " + bool);
        }
      }
      catch (Exception localException)
      {
        localException;
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.world.migration.Migration00898
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */