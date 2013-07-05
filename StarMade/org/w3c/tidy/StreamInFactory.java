package org.w3c.tidy;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public final class StreamInFactory
{
  public static StreamIn getStreamIn(Configuration paramConfiguration, InputStream paramInputStream)
  {
    try
    {
      return new StreamInJavaImpl(paramInputStream, paramConfiguration.getInCharEncodingName(), paramConfiguration.tabsize);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("Unsupported encoding: " + localUnsupportedEncodingException.getMessage());
    }
  }

  public static StreamIn getStreamIn(Configuration paramConfiguration, Reader paramReader)
  {
    return new StreamInJavaImpl(paramReader, paramConfiguration.tabsize);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.w3c.tidy.StreamInFactory
 * JD-Core Version:    0.6.2
 */