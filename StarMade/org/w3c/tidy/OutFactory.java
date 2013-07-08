package org.w3c.tidy;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public final class OutFactory
{
  public static Out getOut(Configuration paramConfiguration, OutputStream paramOutputStream)
  {
    try
    {
      return new OutJavaImpl(paramConfiguration, paramConfiguration.getOutCharEncodingName(), paramOutputStream);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("Unsupported encoding: " + localUnsupportedEncodingException.getMessage());
    }
  }
  
  public static Out getOut(Configuration paramConfiguration, Writer paramWriter)
  {
    return new OutJavaImpl(paramConfiguration, paramWriter);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.OutFactory
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */