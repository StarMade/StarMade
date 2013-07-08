package org.w3c.tidy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class OutJavaImpl
  implements Out
{
  private Writer writer;
  private char[] newline;
  
  protected OutJavaImpl(Configuration paramConfiguration, String paramString, OutputStream paramOutputStream)
    throws UnsupportedEncodingException
  {
    this.writer = new OutputStreamWriter(paramOutputStream, paramString);
    this.newline = paramConfiguration.newline;
  }
  
  protected OutJavaImpl(Configuration paramConfiguration, Writer paramWriter)
  {
    this.writer = paramWriter;
    this.newline = paramConfiguration.newline;
  }
  
  public void outc(int paramInt)
  {
    try
    {
      this.writer.write(paramInt);
    }
    catch (IOException localIOException)
    {
      System.err.println("OutJavaImpl.outc: " + localIOException.getMessage());
    }
  }
  
  public void outc(byte paramByte)
  {
    try
    {
      this.writer.write(paramByte);
    }
    catch (IOException localIOException)
    {
      System.err.println("OutJavaImpl.outc: " + localIOException.getMessage());
    }
  }
  
  public void newline()
  {
    try
    {
      this.writer.write(this.newline);
    }
    catch (IOException localIOException)
    {
      System.err.println("OutJavaImpl.newline: " + localIOException.getMessage());
    }
  }
  
  public void flush()
  {
    try
    {
      this.writer.flush();
    }
    catch (IOException localIOException)
    {
      System.err.println("OutJavaImpl.flush: " + localIOException.getMessage());
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.w3c.tidy.OutJavaImpl
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */