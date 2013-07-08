package org.apache.ws.commons.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.UndeclaredThrowableException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class Base64
{
  public static final String LINE_SEPARATOR = "\n";
  public static final int LINE_SIZE = 76;
  private static final char[] intToBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
  private static final byte[] base64ToInt = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
  
  public static OutputStream newEncoder(Writer pWriter)
  {
    return newEncoder(pWriter, 76, "\n");
  }
  
  public static OutputStream newEncoder(Writer pWriter, int pLineSize, String pSeparator)
  {
    Encoder encoder = new Encoder(new char[4096], pLineSize, pSeparator)
    {
      private final Writer val$pWriter;
      
      protected void writeBuffer(char[] pBuffer, int pOffset, int pLen)
        throws IOException
      {
        this.val$pWriter.write(pBuffer, pOffset, pLen);
      }
    };
    return new EncoderOutputStream(encoder);
  }
  
  public static String encode(byte[] pBuffer, int pOffset, int pLength)
  {
    return encode(pBuffer, pOffset, pLength, 76, "\n");
  }
  
  public static String encode(byte[] pBuffer, int pOffset, int pLength, int pLineSize, String pSeparator)
  {
    StringWriter local_sw = new StringWriter();
    OutputStream ostream = newEncoder(local_sw, pLineSize, pSeparator);
    try
    {
      ostream.write(pBuffer, pOffset, pLength);
      ostream.close();
    }
    catch (IOException local_e)
    {
      throw new UndeclaredThrowableException(local_e);
    }
    return local_sw.toString();
  }
  
  public static String encode(byte[] pBuffer)
  {
    return encode(pBuffer, 0, pBuffer.length);
  }
  
  public static Writer newDecoder(OutputStream pStream)
  {
    new Writer()
    {
      private final Base64.Decoder decoder;
      private final OutputStream val$pStream;
      
      public void close()
        throws IOException
      {
        flush();
      }
      
      public void flush()
        throws IOException
      {
        this.decoder.flush();
        this.val$pStream.flush();
      }
      
      public void write(char[] cbuf, int off, int len)
        throws IOException
      {
        this.decoder.write(cbuf, off, len);
      }
    };
  }
  
  public static byte[] decode(char[] pBuffer, int pOffset, int pLength)
    throws Base64.DecodingException
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Decoder local_d = new Decoder(1024)
    {
      private final ByteArrayOutputStream val$baos;
      
      protected void writeBuffer(byte[] pBuf, int pOff, int pLen)
        throws IOException
      {
        this.val$baos.write(pBuf, pOff, pLen);
      }
    };
    try
    {
      local_d.write(pBuffer, pOffset, pLength);
      local_d.flush();
    }
    catch (DecodingException local_e)
    {
      throw local_e;
    }
    catch (IOException local_e)
    {
      throw new UndeclaredThrowableException(local_e);
    }
    return baos.toByteArray();
  }
  
  public static byte[] decode(char[] pBuffer)
    throws Base64.DecodingException
  {
    return decode(pBuffer, 0, pBuffer.length);
  }
  
  public static byte[] decode(String pBuffer)
    throws Base64.DecodingException
  {
    return decode(pBuffer.toCharArray());
  }
  
  public static abstract class Decoder
  {
    private final byte[] byteBuffer;
    private int byteBufferOffset;
    private int num;
    private int numBytes;
    private int eofBytes;
    
    protected Decoder(int pBufLen)
    {
      this.byteBuffer = new byte[pBufLen];
    }
    
    protected abstract void writeBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException;
    
    public void write(char[] pData, int pOffset, int pLen)
      throws IOException
    {
      for (int local_i = 0; local_i < pLen; local_i++)
      {
        char local_c = pData[(pOffset++)];
        if (!Character.isWhitespace(local_c)) {
          if (local_c == '=')
          {
            this.eofBytes += 1;
            this.num <<= 6;
            switch (++this.numBytes)
            {
            case 1: 
            case 2: 
              throw new Base64.DecodingException("Unexpected end of stream character (=)");
            case 3: 
              break;
            case 4: 
              this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 16));
              if (this.eofBytes == 1) {
                this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 8));
              }
              writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
              this.byteBufferOffset = 0;
              break;
            case 5: 
              throw new Base64.DecodingException("Trailing garbage detected");
            default: 
              throw new IllegalStateException("Invalid value for numBytes");
            }
          }
          else
          {
            if (this.eofBytes > 0) {
              throw new Base64.DecodingException("Base64 characters after end of stream character (=) detected.");
            }
            if ((local_c >= 0) && (local_c < Base64.base64ToInt.length))
            {
              int result = Base64.base64ToInt[local_c];
              if (result >= 0)
              {
                this.num = ((this.num << 6) + result);
                if (++this.numBytes != 4) {
                  continue;
                }
                this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 16));
                this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 8 & 0xFF));
                this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num & 0xFF));
                if (this.byteBufferOffset + 3 > this.byteBuffer.length)
                {
                  writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
                  this.byteBufferOffset = 0;
                }
                this.num = 0;
                this.numBytes = 0;
                continue;
              }
            }
            if (!Character.isWhitespace(local_c)) {
              throw new Base64.DecodingException("Invalid Base64 character: " + local_c);
            }
          }
        }
      }
    }
    
    public void flush()
      throws IOException
    {
      if ((this.numBytes != 0) && (this.numBytes != 4)) {
        throw new Base64.DecodingException("Unexpected end of file");
      }
      if (this.byteBufferOffset > 0)
      {
        writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
        this.byteBufferOffset = 0;
      }
    }
  }
  
  public static class SAXEncoder
    extends Base64.Encoder
  {
    private final ContentHandler handler;
    
    public SAXEncoder(char[] pBuffer, int pWrapSize, String pSep, ContentHandler pHandler)
    {
      super(pWrapSize, pSep);
      this.handler = pHandler;
    }
    
    protected void writeBuffer(char[] pChars, int pOffset, int pLen)
      throws IOException
    {
      try
      {
        this.handler.characters(pChars, pOffset, pLen);
      }
      catch (SAXException local_e)
      {
        throw new Base64.SAXIOException(local_e);
      }
    }
  }
  
  public static class EncoderOutputStream
    extends OutputStream
  {
    private final Base64.Encoder encoder;
    private final byte[] oneByte = new byte[1];
    
    public EncoderOutputStream(Base64.Encoder pEncoder)
    {
      this.encoder = pEncoder;
    }
    
    public void write(int local_b)
      throws IOException
    {
      this.oneByte[0] = ((byte)local_b);
      this.encoder.write(this.oneByte, 0, 1);
    }
    
    public void write(byte[] pBuffer, int pOffset, int pLen)
      throws IOException
    {
      this.encoder.write(pBuffer, pOffset, pLen);
    }
    
    public void close()
      throws IOException
    {
      this.encoder.flush();
    }
  }
  
  public static abstract class Encoder
  {
    private int num;
    private int numBytes;
    private final char[] charBuffer;
    private int charOffset;
    private final int wrapSize;
    private final int skipChars;
    private final String sep;
    private int lineChars = 0;
    
    protected Encoder(char[] pBuffer, int pWrapSize, String pSep)
    {
      this.charBuffer = pBuffer;
      this.sep = (pSep == null ? null : "\n");
      this.skipChars = (pWrapSize == 0 ? 4 : 4 + this.sep.length());
      this.wrapSize = (this.skipChars == 4 ? 0 : pWrapSize);
      if ((this.wrapSize < 0) || (this.wrapSize % 4 > 0)) {
        throw new IllegalArgumentException("Illegal argument for wrap size: " + pWrapSize + "(Expected nonnegative multiple of 4)");
      }
      if (pBuffer.length < this.skipChars) {
        throw new IllegalArgumentException("The buffer must contain at least " + this.skipChars + " characters, but has " + pBuffer.length);
      }
    }
    
    protected abstract void writeBuffer(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException;
    
    private void wrap()
    {
      for (int local_j = 0; local_j < this.sep.length(); local_j++) {
        this.charBuffer[(this.charOffset++)] = this.sep.charAt(local_j);
      }
      this.lineChars = 0;
    }
    
    public void write(byte[] pBuffer, int pOffset, int pLen)
      throws IOException
    {
      for (int local_i = 0; local_i < pLen; local_i++)
      {
        int local_b = pBuffer[(pOffset++)];
        if (local_b < 0) {
          local_b += 256;
        }
        this.num = ((this.num << 8) + local_b);
        if (++this.numBytes == 3)
        {
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 18)];
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 12 & 0x3F)];
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 6 & 0x3F)];
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num & 0x3F)];
          if (this.wrapSize > 0)
          {
            this.lineChars += 4;
            if (this.lineChars >= this.wrapSize) {
              wrap();
            }
          }
          this.num = 0;
          this.numBytes = 0;
          if (this.charOffset + this.skipChars > this.charBuffer.length)
          {
            writeBuffer(this.charBuffer, 0, this.charOffset);
            this.charOffset = 0;
          }
        }
      }
    }
    
    public void flush()
      throws IOException
    {
      if (this.numBytes > 0)
      {
        if (this.numBytes == 1)
        {
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 2)];
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num << 4 & 0x3F)];
          this.charBuffer[(this.charOffset++)] = '=';
          this.charBuffer[(this.charOffset++)] = '=';
        }
        else
        {
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 10)];
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 4 & 0x3F)];
          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num << 2 & 0x3F)];
          this.charBuffer[(this.charOffset++)] = '=';
        }
        this.lineChars += 4;
        this.num = 0;
        this.numBytes = 0;
      }
      if ((this.wrapSize > 0) && (this.lineChars > 0)) {
        wrap();
      }
      if (this.charOffset > 0)
      {
        writeBuffer(this.charBuffer, 0, this.charOffset);
        this.charOffset = 0;
      }
    }
  }
  
  public static class SAXIOException
    extends IOException
  {
    private static final long serialVersionUID = 3258131345216451895L;
    final SAXException saxException;
    
    SAXIOException(SAXException local_e)
    {
      this.saxException = local_e;
    }
    
    public SAXException getSAXException()
    {
      return this.saxException;
    }
  }
  
  public static class DecodingException
    extends IOException
  {
    private static final long serialVersionUID = 3257006574836135478L;
    
    DecodingException(String pMessage)
    {
      super();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.ws.commons.util.Base64
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */