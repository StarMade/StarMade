/*   1:    */package org.apache.ws.commons.util;
/*   2:    */
/*   3:    */import java.io.ByteArrayOutputStream;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.OutputStream;
/*   6:    */import java.io.StringWriter;
/*   7:    */import java.io.Writer;
/*   8:    */import java.lang.reflect.UndeclaredThrowableException;
/*   9:    */import org.xml.sax.ContentHandler;
/*  10:    */import org.xml.sax.SAXException;
/*  11:    */
/*  28:    */public class Base64
/*  29:    */{
/*  30:    */  public static final String LINE_SEPARATOR = "\n";
/*  31:    */  public static final int LINE_SIZE = 76;
/*  32:    */  
/*  33:    */  public static class DecodingException
/*  34:    */    extends IOException
/*  35:    */  {
/*  36:    */    private static final long serialVersionUID = 3257006574836135478L;
/*  37:    */    
/*  38:    */    DecodingException(String pMessage)
/*  39:    */    {
/*  40: 40 */      super();
/*  41:    */    }
/*  42:    */  }
/*  43:    */  
/*  45:    */  public static class SAXIOException
/*  46:    */    extends IOException
/*  47:    */  {
/*  48:    */    private static final long serialVersionUID = 3258131345216451895L;
/*  49:    */    final SAXException saxException;
/*  50:    */    
/*  51:    */    SAXIOException(SAXException e)
/*  52:    */    {
/*  53: 53 */      this.saxException = e;
/*  54:    */    }
/*  55:    */    
/*  57:    */    public SAXException getSAXException()
/*  58:    */    {
/*  59: 59 */      return this.saxException;
/*  60:    */    }
/*  61:    */  }
/*  62:    */  
/*  76: 76 */  private static final char[] intToBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
/*  77:    */  
/*  91: 91 */  private static final byte[] base64ToInt = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };
/*  92:    */  
/*  95:    */  public static abstract class Encoder
/*  96:    */  {
/*  97:    */    private int num;
/*  98:    */    
/* 100:    */    private int numBytes;
/* 101:    */    
/* 102:    */    private final char[] charBuffer;
/* 103:    */    
/* 104:    */    private int charOffset;
/* 105:    */    
/* 106:    */    private final int wrapSize;
/* 107:    */    
/* 108:    */    private final int skipChars;
/* 109:    */    
/* 110:    */    private final String sep;
/* 111:    */    
/* 112:112 */    private int lineChars = 0;
/* 113:    */    
/* 127:    */    protected Encoder(char[] pBuffer, int pWrapSize, String pSep)
/* 128:    */    {
/* 129:129 */      this.charBuffer = pBuffer;
/* 130:130 */      this.sep = (pSep == null ? null : "\n");
/* 131:131 */      this.skipChars = (pWrapSize == 0 ? 4 : 4 + this.sep.length());
/* 132:132 */      this.wrapSize = (this.skipChars == 4 ? 0 : pWrapSize);
/* 133:133 */      if ((this.wrapSize < 0) || (this.wrapSize % 4 > 0)) {
/* 134:134 */        throw new IllegalArgumentException("Illegal argument for wrap size: " + pWrapSize + "(Expected nonnegative multiple of 4)");
/* 135:    */      }
/* 136:    */      
/* 137:137 */      if (pBuffer.length < this.skipChars) {
/* 138:138 */        throw new IllegalArgumentException("The buffer must contain at least " + this.skipChars + " characters, but has " + pBuffer.length);
/* 139:    */      }
/* 140:    */    }
/* 141:    */    
/* 144:    */    protected abstract void writeBuffer(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/* 145:    */      throws IOException;
/* 146:    */    
/* 149:    */    private void wrap()
/* 150:    */    {
/* 151:151 */      for (int j = 0; j < this.sep.length(); j++) {
/* 152:152 */        this.charBuffer[(this.charOffset++)] = this.sep.charAt(j);
/* 153:    */      }
/* 154:154 */      this.lineChars = 0;
/* 155:    */    }
/* 156:    */    
/* 162:    */    public void write(byte[] pBuffer, int pOffset, int pLen)
/* 163:    */      throws IOException
/* 164:    */    {
/* 165:165 */      for (int i = 0; i < pLen; i++) {
/* 166:166 */        int b = pBuffer[(pOffset++)];
/* 167:167 */        if (b < 0) b += 256;
/* 168:168 */        this.num = ((this.num << 8) + b);
/* 169:169 */        if (++this.numBytes == 3) {
/* 170:170 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 18)];
/* 171:171 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 12 & 0x3F)];
/* 172:172 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 6 & 0x3F)];
/* 173:173 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num & 0x3F)];
/* 174:174 */          if (this.wrapSize > 0) {
/* 175:175 */            this.lineChars += 4;
/* 176:176 */            if (this.lineChars >= this.wrapSize) {
/* 177:177 */              wrap();
/* 178:    */            }
/* 179:    */          }
/* 180:180 */          this.num = 0;
/* 181:181 */          this.numBytes = 0;
/* 182:182 */          if (this.charOffset + this.skipChars > this.charBuffer.length) {
/* 183:183 */            writeBuffer(this.charBuffer, 0, this.charOffset);
/* 184:184 */            this.charOffset = 0;
/* 185:    */          }
/* 186:    */        }
/* 187:    */      }
/* 188:    */    }
/* 189:    */    
/* 191:    */    public void flush()
/* 192:    */      throws IOException
/* 193:    */    {
/* 194:194 */      if (this.numBytes > 0) {
/* 195:195 */        if (this.numBytes == 1) {
/* 196:196 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 2)];
/* 197:197 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num << 4 & 0x3F)];
/* 198:198 */          this.charBuffer[(this.charOffset++)] = '=';
/* 199:199 */          this.charBuffer[(this.charOffset++)] = '=';
/* 200:    */        } else {
/* 201:201 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 10)];
/* 202:202 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num >> 4 & 0x3F)];
/* 203:203 */          this.charBuffer[(this.charOffset++)] = Base64.intToBase64[(this.num << 2 & 0x3F)];
/* 204:204 */          this.charBuffer[(this.charOffset++)] = '=';
/* 205:    */        }
/* 206:206 */        this.lineChars += 4;
/* 207:207 */        this.num = 0;
/* 208:208 */        this.numBytes = 0;
/* 209:    */      }
/* 210:210 */      if ((this.wrapSize > 0) && (this.lineChars > 0)) {
/* 211:211 */        wrap();
/* 212:    */      }
/* 213:213 */      if (this.charOffset > 0) {
/* 214:214 */        writeBuffer(this.charBuffer, 0, this.charOffset);
/* 215:215 */        this.charOffset = 0;
/* 216:    */      }
/* 217:    */    }
/* 218:    */  }
/* 219:    */  
/* 223:    */  public static class EncoderOutputStream
/* 224:    */    extends OutputStream
/* 225:    */  {
/* 226:    */    private final Base64.Encoder encoder;
/* 227:    */    
/* 230:230 */    public EncoderOutputStream(Base64.Encoder pEncoder) { this.encoder = pEncoder; }
/* 231:    */    
/* 232:232 */    private final byte[] oneByte = new byte[1];
/* 233:    */    
/* 234:234 */    public void write(int b) throws IOException { this.oneByte[0] = ((byte)b);
/* 235:235 */      this.encoder.write(this.oneByte, 0, 1);
/* 236:    */    }
/* 237:    */    
/* 238:238 */    public void write(byte[] pBuffer, int pOffset, int pLen) throws IOException { this.encoder.write(pBuffer, pOffset, pLen); }
/* 239:    */    
/* 240:    */    public void close() throws IOException {
/* 241:241 */      this.encoder.flush();
/* 242:    */    }
/* 243:    */  }
/* 244:    */  
/* 253:    */  public static OutputStream newEncoder(Writer pWriter)
/* 254:    */  {
/* 255:255 */    return newEncoder(pWriter, 76, "\n");
/* 256:    */  }
/* 257:    */  
/* 270:    */  public static OutputStream newEncoder(Writer pWriter, int pLineSize, String pSeparator)
/* 271:    */  {
/* 272:272 */    Encoder encoder = new Encoder(new char[4096], pLineSize, pSeparator) { private final Writer val$pWriter;
/* 273:    */      
/* 274:274 */      protected void writeBuffer(char[] pBuffer, int pOffset, int pLen) throws IOException { this.val$pWriter.write(pBuffer, pOffset, pLen);
/* 275:    */      }
/* 276:276 */    };
/* 277:277 */    return new EncoderOutputStream(encoder);
/* 278:    */  }
/* 279:    */  
/* 287:    */  public static class SAXEncoder
/* 288:    */    extends Base64.Encoder
/* 289:    */  {
/* 290:    */    private final ContentHandler handler;
/* 291:    */    
/* 298:    */    public SAXEncoder(char[] pBuffer, int pWrapSize, String pSep, ContentHandler pHandler)
/* 299:    */    {
/* 300:300 */      super(pWrapSize, pSep);
/* 301:301 */      this.handler = pHandler;
/* 302:    */    }
/* 303:    */    
/* 304:    */    protected void writeBuffer(char[] pChars, int pOffset, int pLen)
/* 305:    */      throws IOException
/* 306:    */    {
/* 307:    */      try
/* 308:    */      {
/* 309:309 */        this.handler.characters(pChars, pOffset, pLen);
/* 310:    */      } catch (SAXException e) {
/* 311:311 */        throw new Base64.SAXIOException(e);
/* 312:    */      }
/* 313:    */    }
/* 314:    */  }
/* 315:    */  
/* 322:    */  public static String encode(byte[] pBuffer, int pOffset, int pLength)
/* 323:    */  {
/* 324:324 */    return encode(pBuffer, pOffset, pLength, 76, "\n");
/* 325:    */  }
/* 326:    */  
/* 338:    */  public static String encode(byte[] pBuffer, int pOffset, int pLength, int pLineSize, String pSeparator)
/* 339:    */  {
/* 340:340 */    StringWriter sw = new StringWriter();
/* 341:341 */    OutputStream ostream = newEncoder(sw, pLineSize, pSeparator);
/* 342:    */    try {
/* 343:343 */      ostream.write(pBuffer, pOffset, pLength);
/* 344:344 */      ostream.close();
/* 345:    */    } catch (IOException e) {
/* 346:346 */      throw new UndeclaredThrowableException(e);
/* 347:    */    }
/* 348:348 */    return sw.toString();
/* 349:    */  }
/* 350:    */  
/* 356:    */  public static String encode(byte[] pBuffer)
/* 357:    */  {
/* 358:358 */    return encode(pBuffer, 0, pBuffer.length);
/* 359:    */  }
/* 360:    */  
/* 362:    */  public static abstract class Decoder
/* 363:    */  {
/* 364:    */    private final byte[] byteBuffer;
/* 365:    */    
/* 366:    */    private int byteBufferOffset;
/* 367:    */    
/* 368:    */    private int num;
/* 369:    */    
/* 370:    */    private int numBytes;
/* 371:    */    
/* 372:    */    private int eofBytes;
/* 373:    */    
/* 374:    */    protected Decoder(int pBufLen)
/* 375:    */    {
/* 376:376 */      this.byteBuffer = new byte[pBufLen];
/* 377:    */    }
/* 378:    */    
/* 384:    */    protected abstract void writeBuffer(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 385:    */      throws IOException;
/* 386:    */    
/* 391:    */    public void write(char[] pData, int pOffset, int pLen)
/* 392:    */      throws IOException
/* 393:    */    {
/* 394:394 */      for (int i = 0; i < pLen; i++) {
/* 395:395 */        char c = pData[(pOffset++)];
/* 396:396 */        if (!Character.isWhitespace(c))
/* 397:    */        {
/* 399:399 */          if (c == '=') {
/* 400:400 */            this.eofBytes += 1;
/* 401:401 */            this.num <<= 6;
/* 402:402 */            switch (++this.numBytes) {
/* 403:    */            case 1: 
/* 404:    */            case 2: 
/* 405:405 */              throw new Base64.DecodingException("Unexpected end of stream character (=)");
/* 406:    */            
/* 407:    */            case 3: 
/* 408:408 */              break;
/* 409:    */            case 4: 
/* 410:410 */              this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 16));
/* 411:411 */              if (this.eofBytes == 1) {
/* 412:412 */                this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 8));
/* 413:    */              }
/* 414:414 */              writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
/* 415:415 */              this.byteBufferOffset = 0;
/* 416:416 */              break;
/* 417:    */            case 5: 
/* 418:418 */              throw new Base64.DecodingException("Trailing garbage detected");
/* 419:    */            default: 
/* 420:420 */              throw new IllegalStateException("Invalid value for numBytes");
/* 421:    */            }
/* 422:    */          } else {
/* 423:423 */            if (this.eofBytes > 0) {
/* 424:424 */              throw new Base64.DecodingException("Base64 characters after end of stream character (=) detected.");
/* 425:    */            }
/* 426:    */            
/* 427:427 */            if ((c >= 0) && (c < Base64.base64ToInt.length)) {
/* 428:428 */              int result = Base64.base64ToInt[c];
/* 429:429 */              if (result >= 0) {
/* 430:430 */                this.num = ((this.num << 6) + result);
/* 431:431 */                if (++this.numBytes != 4) continue;
/* 432:432 */                this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 16));
/* 433:433 */                this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num >> 8 & 0xFF));
/* 434:434 */                this.byteBuffer[(this.byteBufferOffset++)] = ((byte)(this.num & 0xFF));
/* 435:435 */                if (this.byteBufferOffset + 3 > this.byteBuffer.length) {
/* 436:436 */                  writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
/* 437:437 */                  this.byteBufferOffset = 0;
/* 438:    */                }
/* 439:439 */                this.num = 0;
/* 440:440 */                this.numBytes = 0;continue;
/* 441:    */              }
/* 442:    */            }
/* 443:    */            
/* 445:445 */            if (!Character.isWhitespace(c)) {
/* 446:446 */              throw new Base64.DecodingException("Invalid Base64 character: " + c);
/* 447:    */            }
/* 448:    */          }
/* 449:    */        }
/* 450:    */      }
/* 451:    */    }
/* 452:    */    
/* 454:    */    public void flush()
/* 455:    */      throws IOException
/* 456:    */    {
/* 457:457 */      if ((this.numBytes != 0) && (this.numBytes != 4)) {
/* 458:458 */        throw new Base64.DecodingException("Unexpected end of file");
/* 459:    */      }
/* 460:460 */      if (this.byteBufferOffset > 0) {
/* 461:461 */        writeBuffer(this.byteBuffer, 0, this.byteBufferOffset);
/* 462:462 */        this.byteBufferOffset = 0;
/* 463:    */      }
/* 464:    */    }
/* 465:    */  }
/* 466:    */  
/* 474:    */  public static Writer newDecoder(OutputStream pStream)
/* 475:    */  {
/* 476:476 */    new Writer()
/* 477:    */    {
/* 478:    */      private final Base64.Decoder decoder;
/* 479:    */      
/* 480:    */      private final OutputStream val$pStream;
/* 481:    */      
/* 483:483 */      public void close() throws IOException { flush(); }
/* 484:    */      
/* 485:    */      public void flush() throws IOException {
/* 486:486 */        this.decoder.flush();
/* 487:487 */        this.val$pStream.flush();
/* 488:    */      }
/* 489:    */      
/* 490:490 */      public void write(char[] cbuf, int off, int len) throws IOException { this.decoder.write(cbuf, off, len); }
/* 491:    */    };
/* 492:    */  }
/* 493:    */  
/* 500:    */  public static byte[] decode(char[] pBuffer, int pOffset, int pLength)
/* 501:    */    throws Base64.DecodingException
/* 502:    */  {
/* 503:503 */    ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 504:504 */    Decoder d = new Decoder(1024) { private final ByteArrayOutputStream val$baos;
/* 505:    */      
/* 506:506 */      protected void writeBuffer(byte[] pBuf, int pOff, int pLen) throws IOException { this.val$baos.write(pBuf, pOff, pLen); }
/* 507:    */    };
/* 508:    */    try
/* 509:    */    {
/* 510:510 */      d.write(pBuffer, pOffset, pLength);
/* 511:511 */      d.flush();
/* 512:    */    } catch (DecodingException e) {
/* 513:513 */      throw e;
/* 514:    */    } catch (IOException e) {
/* 515:515 */      throw new UndeclaredThrowableException(e);
/* 516:    */    }
/* 517:517 */    return baos.toByteArray();
/* 518:    */  }
/* 519:    */  
/* 523:    */  public static byte[] decode(char[] pBuffer)
/* 524:    */    throws Base64.DecodingException
/* 525:    */  {
/* 526:526 */    return decode(pBuffer, 0, pBuffer.length);
/* 527:    */  }
/* 528:    */  
/* 532:    */  public static byte[] decode(String pBuffer)
/* 533:    */    throws Base64.DecodingException
/* 534:    */  {
/* 535:535 */    return decode(pBuffer.toCharArray());
/* 536:    */  }
/* 537:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.apache.ws.commons.util.Base64
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */