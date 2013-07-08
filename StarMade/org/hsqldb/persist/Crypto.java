package org.hsqldb.persist;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;

public class Crypto
{
  SecretKeySpec key;
  Cipher outCipher;
  Cipher inCipher;
  Cipher inStreamCipher;
  Cipher outStreamCipher;
  
  public Crypto(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      byte[] arrayOfByte = StringConverter.hexStringToByteArray(paramString1);
      this.key = new SecretKeySpec(arrayOfByte, paramString2);
      this.outCipher = (paramString3 == null ? Cipher.getInstance(paramString2) : Cipher.getInstance(paramString2, paramString3));
      this.outCipher.init(1, this.key);
      this.outStreamCipher = (paramString3 == null ? Cipher.getInstance(paramString2) : Cipher.getInstance(paramString2, paramString3));
      this.outStreamCipher.init(1, this.key);
      this.inCipher = (paramString3 == null ? Cipher.getInstance(paramString2) : Cipher.getInstance(paramString2, paramString3));
      this.inCipher.init(2, this.key);
      this.inStreamCipher = (paramString3 == null ? Cipher.getInstance(paramString2) : Cipher.getInstance(paramString2, paramString3));
      this.inStreamCipher.init(2, this.key);
      return;
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      throw Error.error(331, localNoSuchPaddingException);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw Error.error(331, localNoSuchAlgorithmException);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      throw Error.error(331, localInvalidKeyException);
    }
    catch (NoSuchProviderException localNoSuchProviderException)
    {
      throw Error.error(331, localNoSuchProviderException);
    }
    catch (IOException localIOException)
    {
      throw Error.error(331, localIOException);
    }
  }
  
  public synchronized InputStream getInputStream(InputStream paramInputStream)
  {
    if (this.inCipher == null) {
      return paramInputStream;
    }
    try
    {
      this.inStreamCipher.init(2, this.key);
      return new CipherInputStream(paramInputStream, this.inStreamCipher);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      throw Error.error(331, localInvalidKeyException);
    }
  }
  
  public synchronized OutputStream getOutputStream(OutputStream paramOutputStream)
  {
    if (this.outCipher == null) {
      return paramOutputStream;
    }
    try
    {
      this.outStreamCipher.init(1, this.key);
      return new CipherOutputStream(paramOutputStream, this.outStreamCipher);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      throw Error.error(331, localInvalidKeyException);
    }
  }
  
  public synchronized int decode(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
  {
    if (this.inCipher == null) {
      return paramInt2;
    }
    try
    {
      this.inCipher.init(2, this.key);
      return this.inCipher.doFinal(paramArrayOfByte1, paramInt1, paramInt2, paramArrayOfByte2, paramInt3);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      throw Error.error(331, localInvalidKeyException);
    }
    catch (BadPaddingException localBadPaddingException)
    {
      throw Error.error(331, localBadPaddingException);
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      throw Error.error(331, localIllegalBlockSizeException);
    }
    catch (ShortBufferException localShortBufferException)
    {
      throw Error.error(331, localShortBufferException);
    }
  }
  
  public synchronized int encode(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
  {
    if (this.outCipher == null) {
      return paramInt2;
    }
    try
    {
      this.outCipher.init(1, this.key);
      return this.outCipher.doFinal(paramArrayOfByte1, paramInt1, paramInt2, paramArrayOfByte2, paramInt3);
    }
    catch (InvalidKeyException localInvalidKeyException)
    {
      throw Error.error(331, localInvalidKeyException);
    }
    catch (BadPaddingException localBadPaddingException)
    {
      throw Error.error(331, localBadPaddingException);
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      throw Error.error(331, localIllegalBlockSizeException);
    }
    catch (ShortBufferException localShortBufferException)
    {
      throw Error.error(331, localShortBufferException);
    }
  }
  
  public static byte[] getNewKey(String paramString1, String paramString2)
  {
    try
    {
      KeyGenerator localKeyGenerator = paramString2 == null ? KeyGenerator.getInstance(paramString1) : KeyGenerator.getInstance(paramString1, paramString2);
      SecretKey localSecretKey = localKeyGenerator.generateKey();
      byte[] arrayOfByte = localSecretKey.getEncoded();
      return arrayOfByte;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw Error.error(331, localNoSuchAlgorithmException);
    }
    catch (NoSuchProviderException localNoSuchProviderException)
    {
      throw Error.error(331, localNoSuchProviderException);
    }
  }
  
  public synchronized int getEncodedSize(int paramInt)
  {
    try
    {
      return this.outCipher.getOutputSize(paramInt);
    }
    catch (IllegalStateException localIllegalStateException)
    {
      try
      {
        this.outCipher.init(1, this.key);
        return this.outCipher.getOutputSize(paramInt);
      }
      catch (InvalidKeyException localInvalidKeyException)
      {
        throw Error.error(331, localInvalidKeyException);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.hsqldb.persist.Crypto
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */