package org.hsqldb.lib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.hsqldb.lib.java.JavaSystem;

public class FileArchiver
{
  public static final int COMPRESSION_NONE = 0;
  public static final int COMPRESSION_ZIP = 1;
  public static final int COMPRESSION_GZIP = 2;
  private static final int COPY_BLOCK_SIZE = 65536;
  
  public static void archive(String paramString1, String paramString2, FileAccess paramFileAccess, int paramInt)
    throws IOException
  {
    InputStream localInputStream = null;
    Object localObject1 = null;
    Object localObject2 = null;
    Object localObject3 = null;
    int i = 0;
    if (!paramFileAccess.isStreamElement(paramString1)) {
      return;
    }
    try
    {
      byte[] arrayOfByte = new byte[65536];
      localInputStream = paramFileAccess.openInputStreamElement(paramString1);
      localObject1 = paramFileAccess.openOutputStreamElement(paramString2);
      localObject2 = localObject1;
      switch (paramInt)
      {
      case 1: 
        localObject1 = localObject3 = new DeflaterOutputStream((OutputStream)localObject1, new Deflater(1), arrayOfByte.length);
        break;
      case 2: 
        localObject1 = localObject3 = new GZIPOutputStream((OutputStream)localObject1, arrayOfByte.length);
        break;
      case 0: 
        break;
      default: 
        throw new RuntimeException("FileArchiver" + paramInt);
      }
      for (;;)
      {
        int j = localInputStream.read(arrayOfByte, 0, arrayOfByte.length);
        if (j == -1) {
          break;
        }
        ((OutputStream)localObject1).write(arrayOfByte, 0, j);
      }
      i = 1;
      return;
    }
    catch (Throwable localThrowable2)
    {
      throw JavaSystem.toIOException(localThrowable2);
    }
    finally
    {
      try
      {
        if (localInputStream != null) {
          localInputStream.close();
        }
        if (localObject1 != null)
        {
          if (localObject3 != null) {
            ((DeflaterOutputStream)localObject3).finish();
          }
          if ((localObject2 instanceof FileOutputStream)) {
            paramFileAccess.getFileSync(localObject2).sync();
          }
          ((OutputStream)localObject1).close();
        }
        if ((i == 0) && (paramFileAccess.isStreamElement(paramString2))) {
          paramFileAccess.removeElement(paramString2);
        }
      }
      catch (Throwable localThrowable3)
      {
        throw JavaSystem.toIOException(localThrowable3);
      }
    }
  }
  
  public static void unarchive(String paramString1, String paramString2, FileAccess paramFileAccess, int paramInt)
    throws IOException
  {
    Object localObject1 = null;
    OutputStream localOutputStream = null;
    int i = 0;
    try
    {
      if (!paramFileAccess.isStreamElement(paramString1)) {
        return;
      }
      paramFileAccess.removeElement(paramString2);
      byte[] arrayOfByte = new byte[65536];
      localObject1 = paramFileAccess.openInputStreamElement(paramString1);
      switch (paramInt)
      {
      case 1: 
        localObject1 = new InflaterInputStream((InputStream)localObject1, new Inflater());
        break;
      case 2: 
        localObject1 = new GZIPInputStream((InputStream)localObject1, arrayOfByte.length);
        break;
      case 0: 
        break;
      default: 
        throw new RuntimeException("FileArchiver: " + paramInt);
      }
      localOutputStream = paramFileAccess.openOutputStreamElement(paramString2);
      for (;;)
      {
        int j = ((InputStream)localObject1).read(arrayOfByte, 0, arrayOfByte.length);
        if (j == -1) {
          break;
        }
        localOutputStream.write(arrayOfByte, 0, j);
      }
      i = 1;
      return;
    }
    catch (Throwable localThrowable3)
    {
      throw JavaSystem.toIOException(localThrowable3);
    }
    finally
    {
      try
      {
        if (localObject1 != null) {
          ((InputStream)localObject1).close();
        }
        if (localOutputStream != null)
        {
          localOutputStream.flush();
          if ((localOutputStream instanceof FileOutputStream)) {
            paramFileAccess.getFileSync(localOutputStream).sync();
          }
          localOutputStream.close();
        }
        if ((i == 0) && (paramFileAccess.isStreamElement(paramString2))) {
          paramFileAccess.removeElement(paramString2);
        }
      }
      catch (Throwable localThrowable4)
      {
        throw JavaSystem.toIOException(localThrowable4);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.FileArchiver
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */