package org.hsqldb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CSVWriter
{
  private String newline = System.getProperty("line.separator");
  private OutputStreamWriter writer = null;
  private int nbrCols = 0;
  private int nbrRows = 0;
  
  public CSVWriter(File paramFile, String paramString)
    throws IOException
  {
    if (paramString == null) {
      paramString = System.getProperty("file.encoding");
    }
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    this.writer = new OutputStreamWriter(localFileOutputStream, paramString);
  }
  
  public void writeHeader(String[] paramArrayOfString)
    throws IOException
  {
    this.nbrCols = paramArrayOfString.length;
    doWriteData(paramArrayOfString);
  }
  
  public void writeData(String[] paramArrayOfString)
    throws IOException
  {
    doWriteData(paramArrayOfString);
  }
  
  public void close()
    throws IOException
  {
    this.writer.close();
  }
  
  private void doWriteData(String[] paramArrayOfString)
    throws IOException
  {
    for (int i = 0; i < paramArrayOfString.length; i++)
    {
      if (i > 0) {
        this.writer.write(";");
      }
      if (paramArrayOfString[i] != null)
      {
        this.writer.write("\"");
        this.writer.write(toCsvValue(paramArrayOfString[i]));
        this.writer.write("\"");
      }
    }
    this.writer.write(this.newline);
    this.nbrRows += 1;
  }
  
  private String toCsvValue(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramString.length(); i++)
    {
      char c = paramString.charAt(i);
      localStringBuffer.append(c);
      switch (c)
      {
      case '"': 
        localStringBuffer.append('"');
      }
    }
    return localStringBuffer.toString();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.util.CSVWriter
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */