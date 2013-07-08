package org.hsqldb.lib.tar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class PIFGenerator
  extends ByteArrayOutputStream
{
  OutputStreamWriter writer;
  String name;
  int fakePid;
  char typeFlag;
  
  public String getName()
  {
    return this.name;
  }
  
  protected PIFGenerator()
  {
    try
    {
      this.writer = new OutputStreamWriter(this, "UTF-8");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("Serious problem.  JVM can't encode UTF-8", localUnsupportedEncodingException);
    }
    this.fakePid = ((int)(new Date().getTime() % 100000L));
  }
  
  public PIFGenerator(int paramInt)
  {
    this();
    if (paramInt < 1) {
      throw new IllegalArgumentException("Sequence numbers start at 1");
    }
    this.typeFlag = 'g';
    this.name = (System.getProperty("java.io.tmpdir") + "/GlobalHead." + this.fakePid + '.' + paramInt);
  }
  
  public PIFGenerator(File paramFile)
  {
    this();
    this.typeFlag = 'x';
    String str = paramFile.getParentFile() == null ? "." : paramFile.getParentFile().getPath();
    this.name = (str + "/PaxHeaders." + this.fakePid + '/' + paramFile.getName());
  }
  
  public void addRecord(String paramString, boolean paramBoolean)
    throws TarMalformatException, IOException
  {
    addRecord(paramString, Boolean.toString(paramBoolean));
  }
  
  public void addRecord(String paramString, int paramInt)
    throws TarMalformatException, IOException
  {
    addRecord(paramString, Integer.toString(paramInt));
  }
  
  public void addRecord(String paramString, long paramLong)
    throws TarMalformatException, IOException
  {
    addRecord(paramString, Long.toString(paramLong));
  }
  
  public void addRecord(String paramString1, String paramString2)
    throws TarMalformatException, IOException
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString1.length() < 1) || (paramString2.length() < 1)) {
      throw new TarMalformatException(RB.zero_write.getString());
    }
    int i = paramString1.length() + paramString2.length() + 3;
    int j = 0;
    if (i < 8) {
      j = i + 1;
    } else if (i < 97) {
      j = i + 2;
    } else if (i < 996) {
      j = i + 3;
    } else if (i < 9995) {
      j = i + 4;
    } else if (i < 99994) {
      j = i + 5;
    } else {
      throw new TarMalformatException(RB.pif_toobig.getString(99991));
    }
    this.writer.write(Integer.toString(j));
    this.writer.write(32);
    this.writer.write(paramString1);
    this.writer.write(61);
    this.writer.write(paramString2);
    this.writer.write(10);
    this.writer.flush();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.lib.tar.PIFGenerator
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */