package org.hsqldb.lib;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5
{
  private static MessageDigest md5;
  
  public static final String encode(String paramString1, String paramString2)
    throws RuntimeException
  {
    return StringConverter.byteArrayToHexString(digest(paramString1, paramString2));
  }
  
  public static final String digest(String paramString)
    throws RuntimeException
  {
    return encode(paramString, "ISO-8859-1");
  }
  
  public static final byte[] digest(String paramString1, String paramString2)
    throws RuntimeException
  {
    if (paramString2 == null) {
      paramString2 = "ISO-8859-1";
    }
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = paramString1.getBytes(paramString2);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException.toString());
    }
    return digest(arrayOfByte);
  }
  
  public static final byte[] digest(byte[] paramArrayOfByte)
    throws RuntimeException
  {
    synchronized (MD5.class)
    {
      if (md5 == null) {
        try
        {
          md5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
          throw new RuntimeException(localNoSuchAlgorithmException.toString());
        }
      }
      return md5.digest(paramArrayOfByte);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.hsqldb.lib.MD5
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */