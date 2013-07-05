/*     */ package org.apache.ws.commons.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.UndeclaredThrowableException;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class Base64
/*     */ {
/*     */   public static final String LINE_SEPARATOR = "\n";
/*     */   public static final int LINE_SIZE = 76;
/*  76 */   private static final char[] intToBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
/*     */ 
/*  91 */   private static final byte[] base64ToInt = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
/*     */ 
/*     */   public static OutputStream newEncoder(Writer pWriter)
/*     */   {
/* 255 */     return newEncoder(pWriter, 76, "\n");
/*     */   }
/*     */ 
/*     */   public static OutputStream newEncoder(Writer pWriter, int pLineSize, String pSeparator)
/*     */   {
/* 272 */     Encoder encoder = new Encoder(new char[4096], pLineSize, pSeparator) { private final Writer val$pWriter;
/*     */ 
/* 274 */       protected void writeBuffer(char[] pBuffer, int pOffset, int pLen) throws IOException { this.val$pWriter.write(pBuffer, pOffset, pLen); }
/*     */ 
/*     */     };
/* 277 */     return new EncoderOutputStream(encoder);
/*     */   }
/*     */ 
/*     */   public static String encode(byte[] pBuffer, int pOffset, int pLength)
/*     */   {
/* 324 */     return encode(pBuffer, pOffset, pLength, 76, "\n");
/*     */   }
/*     */ 
/*     */   public static String encode(byte[] pBuffer, int pOffset, int pLength, int pLineSize, String pSeparator)
/*     */   {
/* 340 */     StringWriter sw = new StringWriter();
/* 341 */     OutputStream ostream = newEncoder(sw, pLineSize, pSeparator);
/*     */     try {
/* 343 */       ostream.write(pBuffer, pOffset, pLength);
/* 344 */       ostream.close();
/*     */     } catch (IOException e) {
/* 346 */       throw new UndeclaredThrowableException(e);
/*     */     }
/* 348 */     return sw.toString();
/*     */   }
/*     */ 
/*     */   public static String encode(byte[] pBuffer)
/*     */   {
/* 358 */     return encode(pBuffer, 0, pBuffer.length);
/*     */   }
/*     */ 
/*     */   public static Writer newDecoder(OutputStream pStream)
/*     */   {
/* 476 */     return new Writer()
/*     */     {
/*     */       private final Base64.Decoder decoder;
/*     */       private final OutputStream val$pStream;
/*     */ 
/*     */       public void close() throws IOException {
/* 483 */         flush();
/*     */       }
/*     */       public void flush() throws IOException {
/* 486 */         this.decoder.flush();
/* 487 */         this.val$pStream.flush();
/*     */       }
/*     */       public void write(char[] cbuf, int off, int len) throws IOException {
/* 490 */         this.decoder.write(cbuf, off, len);
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public static byte[] decode(char[] pBuffer, int pOffset, int pLength)
/*     */     throws Base64.DecodingException
/*     */   {
/* 503 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 504 */     Decoder d = new Decoder(1024) { private final ByteArrayOutputStream val$baos;
/*     */ 
/* 506 */       protected void writeBuffer(byte[] pBuf, int pOff, int pLen) throws IOException { this.val$baos.write(pBuf, pOff, pLen); }
/*     */     };
/*     */     try
/*     */     {
/* 510 */       d.write(pBuffer, pOffset, pLength);
/* 511 */       d.flush();
/*     */     } catch (DecodingException e) {
/* 513 */       throw e;
/*     */     } catch (IOException e) {
/* 515 */       throw new UndeclaredThrowableException(e);
/*     */     }
/* 517 */     return baos.toByteArray();
/*     */   }
/*     */ 
/*     */   public static byte[] decode(char[] pBuffer)
/*     */     throws Base64.DecodingException
/*     */   {
/* 526 */     return decode(pBuffer, 0, pBuffer.length);
/*     */   }
/*     */ 
/*     */   public static byte[] decode(String pBuffer)
/*     */     throws Base64.DecodingException
/*     */   {
/* 535 */     return decode(pBuffer.toCharArray());
/*     */   }
/*     */ 
/*     */   public static abstract class Decoder
/*     */   {
/*     */     private final byte[] byteBuffer;
/*     */     private int byteBufferOffset;
/*     */     private int num;
/*     */     private int numBytes;
/*     */     private int eofBytes;
/*     */ 
/*     */     protected Decoder(int pBufLen)
/*     */     {
/* 376 */       this.byteBuffer = new byte[pBufLen];
/*     */     }
/*     */ 
/*     */     protected abstract void writeBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*     */       throws IOException;
/*     */ 
/*     */     public void write(char[] pData, int pOffset, int pLen)
/*     */       throws IOException
/*     */     {
/* 394 */       for (int i = 0; i < pLen; i++) {
/* 395 */         char c = pData[(pOffset++)];
/* 396 */         if (!Character.isWhitespace(c))
/*     */         {
/* 399 */           if (c == '=') {
/* 400 */             this.eofBytes += 1;
/* 401 */             this.num <<= 6;
/* 402 */             switch (++this.numBytes) {
/*     */             case 1:
/*     */             case 2:
/* 405 */               throw new Base64.DecodingException("Unexpected end of stream character (=)");
/*     */             case 3:
/* 408 */               break;
/*     */             case 4:
/* 410 */               this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 16));
/* 411 */               if (this.eofBytes == 1) {
/* 412 */                 this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 8));
/*     */               }
/* 414 */               writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
/* 415 */               this.byteBufferOffset = 0;
/* 416 */               break;
/*     */             case 5:
/* 418 */               throw new Base64.DecodingException("Trailing garbage detected");
/*     */             default:
/* 420 */               throw new IllegalStateException("Invalid value for numBytes");
/*     */             }
/*     */           } else {
/* 423 */             if (this.eofBytes > 0) {
/* 424 */               throw new Base64.DecodingException("Base64 characters after end of stream character (=) detected.");
/*     */             }
/*     */ 
/* 427 */             if ((c >= 0) && (c < Base64.base64ToInt.length)) {
/* 428 */               int result = Base64.base64ToInt[c];
/* 429 */               if (result >= 0) {
/* 430 */                 this.num = ((this.num << 6) + result);
/* 431 */                 if (++this.numBytes != 4) continue;
/* 432 */                 this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 16));
/* 433 */                 this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 8 & 0xFF));
/* 434 */                 this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num & 0xFF));
/* 435 */                 if (this.byteBufferOffset + 3 > this.byteBuffer.length) {
/* 436 */                   writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
/* 437 */                   this.byteBufferOffset = 0;
/*     */                 }
/* 439 */                 this.num = 0;
/* 440 */                 this.numBytes = 0; continue;
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/* 445 */             if (!Character.isWhitespace(c))
/* 446 */               throw new Base64.DecodingException("Invalid Base64 character: " + c);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     public void flush()
/*     */       throws IOException
/*     */     {
/* 457 */       if ((this.numBytes != 0) && (this.numBytes != 4)) {
/* 458 */         throw new Base64.DecodingException("Unexpected end of file");
/*     */       }
/* 460 */       if (this.byteBufferOffset > 0) {
/* 461 */         writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
/* 462 */         this.byteBufferOffset = 0;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class SAXEncoder extends Base64.Encoder
/*     */   {
/*     */     private final ContentHandler handler;
/*     */ 
/*     */     public SAXEncoder(char[] pBuffer, int pWrapSize, String pSep, ContentHandler pHandler)
/*     */     {
/* 300 */       super(pWrapSize, pSep);
/* 301 */       this.handler = pHandler;
/*     */     }
/*     */ 
/*     */     protected void writeBuffer(char[] pChars, int pOffset, int pLen)
/*     */       throws IOException
/*     */     {
/*     */       try
/*     */       {
/* 309 */         this.handler.characters(pChars, pOffset, pLen);
/*     */       } catch (SAXException e) {
/* 311 */         throw new Base64.SAXIOException(e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class EncoderOutputStream extends OutputStream
/*     */   {
/*     */     private final Base64.Encoder encoder;
/* 232 */     private final byte[] oneByte = new byte[1];
/*     */ 
/*     */     public EncoderOutputStream(Base64.Encoder pEncoder)
/*     */     {
/* 230 */       this.encoder = pEncoder;
/*     */     }
/*     */ 
/*     */     public void write(int b) throws IOException {
/* 234 */       this.oneByte[0] = ((byte)b);
/* 235 */       this.encoder.write(this.oneByte, 0, 1);
/*     */     }
/*     */     public void write(byte[] pBuffer, int pOffset, int pLen) throws IOException {
/* 238 */       this.encoder.write(pBuffer, pOffset, pLen);
/*     */     }
/*     */     public void close() throws IOException {
/* 241 */       this.encoder.flush();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static abstract class Encoder
/*     */   {
/*     */     private int num;
/*     */     private int numBytes;
/*     */     private final char[] charBuffer;
/*     */     private int charOffset;
/*     */     private final int wrapSize;
/*     */     private final int skipChars;
/*     */     private final String sep;
/* 112 */     private int lineChars = 0;
/*     */ 
/*     */     protected Encoder(char[] pBuffer, int pWrapSize, String pSep)
/*     */     {
/* 129 */       this.charBuffer = pBuffer;
/* 130 */       this.sep = (pSep == null ? null : "\n");
/* 131 */       this.skipChars = (pWrapSize == 0 ? 4 : 4 + this.sep.length());
/* 132 */       this.wrapSize = (this.skipChars == 4 ? 0 : pWrapSize);
/* 133 */       if ((this.wrapSize < 0) || (this.wrapSize % 4 > 0)) {
/* 134 */         throw new IllegalArgumentException("Illegal argument for wrap size: " + pWrapSize + "(Expected nonnegative multiple of 4)");
/*     */       }
/*     */ 
/* 137 */       if (pBuffer.length < this.skipChars)
/* 138 */         throw new IllegalArgumentException("The buffer must contain at least " + this.skipChars + " characters, but has " + pBuffer.length);
/*     */     }
/*     */ 
/*     */     protected abstract void writeBuffer(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*     */       throws IOException;
/*     */ 
/*     */     private void wrap()
/*     */     {
/* 151 */       for (int j = 0; j < this.sep.length(); j++) {
/* 152 */         this.charBuffer[(this.charOffset++)] = this.sep.charAt(j);
/*     */       }
/* 154 */       this.lineChars = 0;
/*     */     }
/*     */ 
/*     */     public void write(byte[] pBuffer, int pOffset, int pLen)
/*     */       throws IOException
/*     */     {
/* 165 */       for (int i = 0; i < pLen; i++) {
/* 166 */         int b = pBuffer[(pOffset++)];
/* 167 */         if (b < 0) b += 256;
/* 168 */         this.num = ((this.num << 8) + b);
/* 169 */         if (++this.numBytes == 3) {
/* 170 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 18)];
/* 171 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 12 & 0x3F)];
/* 172 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 6 & 0x3F)];
/* 173 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num & 0x3F)];
/* 174 */           if (this.wrapSize > 0) {
/* 175 */             this.lineChars += 4;
/* 176 */             if (this.lineChars >= this.wrapSize) {
/* 177 */               wrap();
/*     */             }
/*     */           }
/* 180 */           this.num = 0;
/* 181 */           this.numBytes = 0;
/* 182 */           if (this.charOffset + this.skipChars > this.charBuffer.length) {
/* 183 */             writeBuffer(this.charBuffer, 0, this.charOffset);
/* 184 */             this.charOffset = 0;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     public void flush()
/*     */       throws IOException
/*     */     {
/* 194 */       if (this.numBytes > 0) {
/* 195 */         if (this.numBytes == 1) {
/* 196 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 2)];
/* 197 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num << 4 & 0x3F)];
/* 198 */           this.charBuffer[(this.charOffset++)] = '=';
/* 199 */           this.charBuffer[(this.charOffset++)] = '=';
/*     */         } else {
/* 201 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 10)];
/* 202 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 4 & 0x3F)];
/* 203 */           this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num << 2 & 0x3F)];
/* 204 */           this.charBuffer[(this.charOffset++)] = '=';
/*     */         }
/* 206 */         this.lineChars += 4;
/* 207 */         this.num = 0;
/* 208 */         this.numBytes = 0;
/*     */       }
/* 210 */       if ((this.wrapSize > 0) && (this.lineChars > 0)) {
/* 211 */         wrap();
/*     */       }
/* 213 */       if (this.charOffset > 0) {
/* 214 */         writeBuffer(this.charBuffer, 0, this.charOffset);
/* 215 */         this.charOffset = 0;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class SAXIOException extends IOException
/*     */   {
/*     */     private static final long serialVersionUID = 3258131345216451895L;
/*     */     final SAXException saxException;
/*     */ 
/*     */     SAXIOException(SAXException e)
/*     */     {
/*  53 */       this.saxException = e;
/*     */     }
/*     */ 
/*     */     public SAXException getSAXException()
/*     */     {
/*  59 */       return this.saxException;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class DecodingException extends IOException
/*     */   {
/*     */     private static final long serialVersionUID = 3257006574836135478L;
/*     */ 
/*     */     DecodingException(String pMessage)
/*     */     {
/*  40 */       super();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.util.Base64
 * JD-Core Version:    0.6.2
 */