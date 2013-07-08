/*   1:    */package de.jarnbjo.ogg;
/*   2:    */
/*   3:    */import de.jarnbjo.util.io.BitInputStream;
/*   4:    */import de.jarnbjo.util.io.ByteArrayBitInputStream;
/*   5:    */import java.io.EOFException;
/*   6:    */import java.io.IOException;
/*   7:    */import java.io.InputStream;
/*   8:    */import java.io.PrintStream;
/*   9:    */import java.io.RandomAccessFile;
/*  10:    */
/*  58:    */public class OggPage
/*  59:    */{
/*  60:    */  private int version;
/*  61:    */  private boolean continued;
/*  62:    */  private boolean bos;
/*  63:    */  private boolean eos;
/*  64:    */  private long absoluteGranulePosition;
/*  65:    */  private int streamSerialNumber;
/*  66:    */  private int pageSequenceNumber;
/*  67:    */  private int pageCheckSum;
/*  68:    */  private int[] segmentOffsets;
/*  69:    */  private int[] segmentLengths;
/*  70:    */  private int totalLength;
/*  71:    */  private byte[] header;
/*  72:    */  private byte[] segmentTable;
/*  73:    */  private byte[] data;
/*  74:    */  
/*  75:    */  protected OggPage() {}
/*  76:    */  
/*  77:    */  private OggPage(int version, boolean continued, boolean bos, boolean eos, long absoluteGranulePosition, int streamSerialNumber, int pageSequenceNumber, int pageCheckSum, int[] segmentOffsets, int[] segmentLengths, int totalLength, byte[] header, byte[] segmentTable, byte[] data)
/*  78:    */  {
/*  79: 79 */    this.version = version;
/*  80: 80 */    this.continued = continued;
/*  81: 81 */    this.bos = bos;
/*  82: 82 */    this.eos = eos;
/*  83: 83 */    this.absoluteGranulePosition = absoluteGranulePosition;
/*  84: 84 */    this.streamSerialNumber = streamSerialNumber;
/*  85: 85 */    this.pageSequenceNumber = pageSequenceNumber;
/*  86: 86 */    this.pageCheckSum = pageCheckSum;
/*  87: 87 */    this.segmentOffsets = segmentOffsets;
/*  88: 88 */    this.segmentLengths = segmentLengths;
/*  89: 89 */    this.totalLength = totalLength;
/*  90: 90 */    this.header = header;
/*  91: 91 */    this.segmentTable = segmentTable;
/*  92: 92 */    this.data = data;
/*  93:    */  }
/*  94:    */  
/*  99:    */  public static OggPage create(RandomAccessFile source)
/* 100:    */    throws IOException, EndOfOggStreamException, OggFormatException
/* 101:    */  {
/* 102:102 */    return create(source, false);
/* 103:    */  }
/* 104:    */  
/* 121:    */  public static OggPage create(RandomAccessFile source, boolean skipData)
/* 122:    */    throws IOException, EndOfOggStreamException, OggFormatException
/* 123:    */  {
/* 124:124 */    return create(source, skipData);
/* 125:    */  }
/* 126:    */  
/* 131:    */  public static OggPage create(InputStream source)
/* 132:    */    throws IOException, EndOfOggStreamException, OggFormatException
/* 133:    */  {
/* 134:134 */    return create(source, false);
/* 135:    */  }
/* 136:    */  
/* 153:    */  public static OggPage create(InputStream source, boolean skipData)
/* 154:    */    throws IOException, EndOfOggStreamException, OggFormatException
/* 155:    */  {
/* 156:156 */    return create(source, skipData);
/* 157:    */  }
/* 158:    */  
/* 163:    */  public static OggPage create(byte[] source)
/* 164:    */    throws IOException, EndOfOggStreamException, OggFormatException
/* 165:    */  {
/* 166:166 */    return create(source, false);
/* 167:    */  }
/* 168:    */  
/* 181:    */  public static OggPage create(byte[] source, boolean skipData)
/* 182:    */    throws IOException, EndOfOggStreamException, OggFormatException
/* 183:    */  {
/* 184:184 */    return create(source, skipData);
/* 185:    */  }
/* 186:    */  
/* 187:    */  private static OggPage create(Object source, boolean skipData) throws IOException, EndOfOggStreamException, OggFormatException
/* 188:    */  {
/* 189:    */    try {
/* 190:190 */      int sourceOffset = 27;
/* 191:    */      
/* 192:192 */      byte[] header = new byte[27];
/* 193:193 */      if ((source instanceof RandomAccessFile)) {
/* 194:194 */        RandomAccessFile raf = (RandomAccessFile)source;
/* 195:195 */        if (raf.getFilePointer() == raf.length()) {
/* 196:196 */          return null;
/* 197:    */        }
/* 198:198 */        raf.readFully(header);
/* 199:    */      }
/* 200:200 */      else if ((source instanceof InputStream)) {
/* 201:201 */        readFully((InputStream)source, header);
/* 202:    */      }
/* 203:203 */      else if ((source instanceof byte[])) {
/* 204:204 */        System.arraycopy((byte[])source, 0, header, 0, 27);
/* 205:    */      }
/* 206:    */      
/* 207:207 */      BitInputStream bdSource = new ByteArrayBitInputStream(header);
/* 208:    */      
/* 209:209 */      int capture = bdSource.getInt(32);
/* 210:    */      
/* 211:211 */      if (capture != 1399285583)
/* 212:    */      {
/* 221:221 */        String cs = Integer.toHexString(capture);
/* 222:222 */        while (cs.length() < 8) {
/* 223:223 */          cs = "0" + cs;
/* 224:    */        }
/* 225:225 */        cs = cs.substring(6, 8) + cs.substring(4, 6) + cs.substring(2, 4) + cs.substring(0, 2);
/* 226:226 */        char c1 = (char)Integer.valueOf(cs.substring(0, 2), 16).intValue();
/* 227:227 */        char c2 = (char)Integer.valueOf(cs.substring(2, 4), 16).intValue();
/* 228:228 */        char c3 = (char)Integer.valueOf(cs.substring(4, 6), 16).intValue();
/* 229:229 */        char c4 = (char)Integer.valueOf(cs.substring(6, 8), 16).intValue();
/* 230:230 */        System.out.println("Ogg packet header is 0x" + cs + " (" + c1 + c2 + c3 + c4 + "), should be 0x4f676753 (OggS)");
/* 231:    */      }
/* 232:    */      
/* 233:233 */      int version = bdSource.getInt(8);
/* 234:234 */      byte tmp = (byte)bdSource.getInt(8);
/* 235:235 */      boolean bf1 = (tmp & 0x1) != 0;
/* 236:236 */      boolean bos = (tmp & 0x2) != 0;
/* 237:237 */      boolean eos = (tmp & 0x4) != 0;
/* 238:238 */      long absoluteGranulePosition = bdSource.getLong(64);
/* 239:239 */      int streamSerialNumber = bdSource.getInt(32);
/* 240:240 */      int pageSequenceNumber = bdSource.getInt(32);
/* 241:241 */      int pageCheckSum = bdSource.getInt(32);
/* 242:242 */      int pageSegments = bdSource.getInt(8);
/* 243:    */      
/* 246:246 */      int[] segmentOffsets = new int[pageSegments];
/* 247:247 */      int[] segmentLengths = new int[pageSegments];
/* 248:248 */      int totalLength = 0;
/* 249:    */      
/* 250:250 */      byte[] segmentTable = new byte[pageSegments];
/* 251:251 */      byte[] tmpBuf = new byte[1];
/* 252:    */      
/* 253:253 */      for (int i = 0; i < pageSegments; i++) {
/* 254:254 */        int l = 0;
/* 255:255 */        if ((source instanceof RandomAccessFile)) {
/* 256:256 */          l = ((RandomAccessFile)source).readByte() & 0xFF;
/* 257:    */        }
/* 258:258 */        else if ((source instanceof InputStream)) {
/* 259:259 */          l = ((InputStream)source).read();
/* 260:    */        }
/* 261:261 */        else if ((source instanceof byte[])) {
/* 262:262 */          l = ((byte[])(byte[])source)[(sourceOffset++)];
/* 263:263 */          l &= 255;
/* 264:    */        }
/* 265:265 */        segmentTable[i] = ((byte)l);
/* 266:266 */        segmentLengths[i] = l;
/* 267:267 */        segmentOffsets[i] = totalLength;
/* 268:268 */        totalLength += l;
/* 269:    */      }
/* 270:    */      
/* 271:271 */      byte[] data = null;
/* 272:    */      
/* 273:273 */      if (!skipData)
/* 274:    */      {
/* 277:277 */        data = new byte[totalLength];
/* 278:    */        
/* 279:279 */        if ((source instanceof RandomAccessFile)) {
/* 280:280 */          ((RandomAccessFile)source).readFully(data);
/* 281:    */        }
/* 282:282 */        else if ((source instanceof InputStream)) {
/* 283:283 */          readFully((InputStream)source, data);
/* 284:    */        }
/* 285:285 */        else if ((source instanceof byte[])) {
/* 286:286 */          System.arraycopy(source, sourceOffset, data, 0, totalLength);
/* 287:    */        }
/* 288:    */      }
/* 289:    */      
/* 290:290 */      return new OggPage(version, bf1, bos, eos, absoluteGranulePosition, streamSerialNumber, pageSequenceNumber, pageCheckSum, segmentOffsets, segmentLengths, totalLength, header, segmentTable, data);
/* 291:    */    }
/* 292:    */    catch (EOFException e) {
/* 293:293 */      throw new EndOfOggStreamException();
/* 294:    */    }
/* 295:    */  }
/* 296:    */  
/* 297:    */  private static void readFully(InputStream source, byte[] buffer) throws IOException {
/* 298:298 */    int total = 0;
/* 299:299 */    while (total < buffer.length) {
/* 300:300 */      int read = source.read(buffer, total, buffer.length - total);
/* 301:301 */      if (read == -1) {
/* 302:302 */        throw new EndOfOggStreamException();
/* 303:    */      }
/* 304:304 */      total += read;
/* 305:    */    }
/* 306:    */  }
/* 307:    */  
/* 319:    */  public long getAbsoluteGranulePosition()
/* 320:    */  {
/* 321:321 */    return this.absoluteGranulePosition;
/* 322:    */  }
/* 323:    */  
/* 329:    */  public int getStreamSerialNumber()
/* 330:    */  {
/* 331:331 */    return this.streamSerialNumber;
/* 332:    */  }
/* 333:    */  
/* 339:    */  public int getPageSequenceNumber()
/* 340:    */  {
/* 341:341 */    return this.pageSequenceNumber;
/* 342:    */  }
/* 343:    */  
/* 349:    */  public int getPageCheckSum()
/* 350:    */  {
/* 351:351 */    return this.pageCheckSum;
/* 352:    */  }
/* 353:    */  
/* 358:    */  public int getTotalLength()
/* 359:    */  {
/* 360:360 */    if (this.data != null) {
/* 361:361 */      return 27 + this.segmentTable.length + this.data.length;
/* 362:    */    }
/* 363:    */    
/* 364:364 */    return this.totalLength;
/* 365:    */  }
/* 366:    */  
/* 372:    */  public byte[] getData()
/* 373:    */  {
/* 374:374 */    return this.data;
/* 375:    */  }
/* 376:    */  
/* 377:    */  public byte[] getHeader() {
/* 378:378 */    return this.header;
/* 379:    */  }
/* 380:    */  
/* 381:    */  public byte[] getSegmentTable() {
/* 382:382 */    return this.segmentTable;
/* 383:    */  }
/* 384:    */  
/* 385:    */  public int[] getSegmentOffsets() {
/* 386:386 */    return this.segmentOffsets;
/* 387:    */  }
/* 388:    */  
/* 389:    */  public int[] getSegmentLengths() {
/* 390:390 */    return this.segmentLengths;
/* 391:    */  }
/* 392:    */  
/* 396:    */  public boolean isContinued()
/* 397:    */  {
/* 398:398 */    return this.continued;
/* 399:    */  }
/* 400:    */  
/* 404:    */  public boolean isFresh()
/* 405:    */  {
/* 406:406 */    return !this.continued;
/* 407:    */  }
/* 408:    */  
/* 412:    */  public boolean isBos()
/* 413:    */  {
/* 414:414 */    return this.bos;
/* 415:    */  }
/* 416:    */  
/* 420:    */  public boolean isEos()
/* 421:    */  {
/* 422:422 */    return this.eos;
/* 423:    */  }
/* 424:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.OggPage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */