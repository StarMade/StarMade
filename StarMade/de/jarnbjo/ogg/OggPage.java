/*     */ package de.jarnbjo.ogg;
/*     */ 
/*     */ import de.jarnbjo.util.io.BitInputStream;
/*     */ import de.jarnbjo.util.io.ByteArrayBitInputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ 
/*     */ public class OggPage
/*     */ {
/*     */   private int version;
/*     */   private boolean continued;
/*     */   private boolean bos;
/*     */   private boolean eos;
/*     */   private long absoluteGranulePosition;
/*     */   private int streamSerialNumber;
/*     */   private int pageSequenceNumber;
/*     */   private int pageCheckSum;
/*     */   private int[] segmentOffsets;
/*     */   private int[] segmentLengths;
/*     */   private int totalLength;
/*     */   private byte[] header;
/*     */   private byte[] segmentTable;
/*     */   private byte[] data;
/*     */ 
/*     */   protected OggPage()
/*     */   {
/*     */   }
/*     */ 
/*     */   private OggPage(int version, boolean continued, boolean bos, boolean eos, long absoluteGranulePosition, int streamSerialNumber, int pageSequenceNumber, int pageCheckSum, int[] segmentOffsets, int[] segmentLengths, int totalLength, byte[] header, byte[] segmentTable, byte[] data)
/*     */   {
/*  79 */     this.version = version;
/*  80 */     this.continued = continued;
/*  81 */     this.bos = bos;
/*  82 */     this.eos = eos;
/*  83 */     this.absoluteGranulePosition = absoluteGranulePosition;
/*  84 */     this.streamSerialNumber = streamSerialNumber;
/*  85 */     this.pageSequenceNumber = pageSequenceNumber;
/*  86 */     this.pageCheckSum = pageCheckSum;
/*  87 */     this.segmentOffsets = segmentOffsets;
/*  88 */     this.segmentLengths = segmentLengths;
/*  89 */     this.totalLength = totalLength;
/*  90 */     this.header = header;
/*  91 */     this.segmentTable = segmentTable;
/*  92 */     this.data = data;
/*     */   }
/*     */ 
/*     */   public static OggPage create(RandomAccessFile source)
/*     */     throws IOException, EndOfOggStreamException, OggFormatException
/*     */   {
/* 102 */     return create(source, false);
/*     */   }
/*     */ 
/*     */   public static OggPage create(RandomAccessFile source, boolean skipData)
/*     */     throws IOException, EndOfOggStreamException, OggFormatException
/*     */   {
/* 124 */     return create(source, skipData);
/*     */   }
/*     */ 
/*     */   public static OggPage create(InputStream source)
/*     */     throws IOException, EndOfOggStreamException, OggFormatException
/*     */   {
/* 134 */     return create(source, false);
/*     */   }
/*     */ 
/*     */   public static OggPage create(InputStream source, boolean skipData)
/*     */     throws IOException, EndOfOggStreamException, OggFormatException
/*     */   {
/* 156 */     return create(source, skipData);
/*     */   }
/*     */ 
/*     */   public static OggPage create(byte[] source)
/*     */     throws IOException, EndOfOggStreamException, OggFormatException
/*     */   {
/* 166 */     return create(source, false);
/*     */   }
/*     */ 
/*     */   public static OggPage create(byte[] source, boolean skipData)
/*     */     throws IOException, EndOfOggStreamException, OggFormatException
/*     */   {
/* 184 */     return create(source, skipData);
/*     */   }
/*     */ 
/*     */   private static OggPage create(Object source, boolean skipData) throws IOException, EndOfOggStreamException, OggFormatException
/*     */   {
/*     */     try {
/* 190 */       int sourceOffset = 27;
/*     */ 
/* 192 */       byte[] header = new byte[27];
/* 193 */       if ((source instanceof RandomAccessFile)) {
/* 194 */         RandomAccessFile raf = (RandomAccessFile)source;
/* 195 */         if (raf.getFilePointer() == raf.length()) {
/* 196 */           return null;
/*     */         }
/* 198 */         raf.readFully(header);
/*     */       }
/* 200 */       else if ((source instanceof InputStream)) {
/* 201 */         readFully((InputStream)source, header);
/*     */       }
/* 203 */       else if ((source instanceof byte[])) {
/* 204 */         System.arraycopy((byte[])source, 0, header, 0, 27);
/*     */       }
/*     */ 
/* 207 */       BitInputStream bdSource = new ByteArrayBitInputStream(header);
/*     */ 
/* 209 */       int capture = bdSource.getInt(32);
/*     */ 
/* 211 */       if (capture != 1399285583)
/*     */       {
/* 221 */         String cs = Integer.toHexString(capture);
/* 222 */         while (cs.length() < 8) {
/* 223 */           cs = "0" + cs;
/*     */         }
/* 225 */         cs = cs.substring(6, 8) + cs.substring(4, 6) + cs.substring(2, 4) + cs.substring(0, 2);
/* 226 */         char c1 = (char)Integer.valueOf(cs.substring(0, 2), 16).intValue();
/* 227 */         char c2 = (char)Integer.valueOf(cs.substring(2, 4), 16).intValue();
/* 228 */         char c3 = (char)Integer.valueOf(cs.substring(4, 6), 16).intValue();
/* 229 */         char c4 = (char)Integer.valueOf(cs.substring(6, 8), 16).intValue();
/* 230 */         System.out.println("Ogg packet header is 0x" + cs + " (" + c1 + c2 + c3 + c4 + "), should be 0x4f676753 (OggS)");
/*     */       }
/*     */ 
/* 233 */       int version = bdSource.getInt(8);
/* 234 */       byte tmp = (byte)bdSource.getInt(8);
/* 235 */       boolean bf1 = (tmp & 0x1) != 0;
/* 236 */       boolean bos = (tmp & 0x2) != 0;
/* 237 */       boolean eos = (tmp & 0x4) != 0;
/* 238 */       long absoluteGranulePosition = bdSource.getLong(64);
/* 239 */       int streamSerialNumber = bdSource.getInt(32);
/* 240 */       int pageSequenceNumber = bdSource.getInt(32);
/* 241 */       int pageCheckSum = bdSource.getInt(32);
/* 242 */       int pageSegments = bdSource.getInt(8);
/*     */ 
/* 246 */       int[] segmentOffsets = new int[pageSegments];
/* 247 */       int[] segmentLengths = new int[pageSegments];
/* 248 */       int totalLength = 0;
/*     */ 
/* 250 */       byte[] segmentTable = new byte[pageSegments];
/* 251 */       byte[] tmpBuf = new byte[1];
/*     */ 
/* 253 */       for (int i = 0; i < pageSegments; i++) {
/* 254 */         int l = 0;
/* 255 */         if ((source instanceof RandomAccessFile)) {
/* 256 */           l = ((RandomAccessFile)source).readByte() & 0xFF;
/*     */         }
/* 258 */         else if ((source instanceof InputStream)) {
/* 259 */           l = ((InputStream)source).read();
/*     */         }
/* 261 */         else if ((source instanceof byte[])) {
/* 262 */           l = ((byte[])(byte[])source)[(sourceOffset++)];
/* 263 */           l &= 255;
/*     */         }
/* 265 */         segmentTable[i] = ((byte)l);
/* 266 */         segmentLengths[i] = l;
/* 267 */         segmentOffsets[i] = totalLength;
/* 268 */         totalLength += l;
/*     */       }
/*     */ 
/* 271 */       byte[] data = null;
/*     */ 
/* 273 */       if (!skipData)
/*     */       {
/* 277 */         data = new byte[totalLength];
/*     */ 
/* 279 */         if ((source instanceof RandomAccessFile)) {
/* 280 */           ((RandomAccessFile)source).readFully(data);
/*     */         }
/* 282 */         else if ((source instanceof InputStream)) {
/* 283 */           readFully((InputStream)source, data);
/*     */         }
/* 285 */         else if ((source instanceof byte[])) {
/* 286 */           System.arraycopy(source, sourceOffset, data, 0, totalLength);
/*     */         }
/*     */       }
/*     */ 
/* 290 */       return new OggPage(version, bf1, bos, eos, absoluteGranulePosition, streamSerialNumber, pageSequenceNumber, pageCheckSum, segmentOffsets, segmentLengths, totalLength, header, segmentTable, data);
/*     */     } catch (EOFException e) {
/*     */     }
/* 293 */     throw new EndOfOggStreamException();
/*     */   }
/*     */ 
/*     */   private static void readFully(InputStream source, byte[] buffer) throws IOException
/*     */   {
/* 298 */     int total = 0;
/* 299 */     while (total < buffer.length) {
/* 300 */       int read = source.read(buffer, total, buffer.length - total);
/* 301 */       if (read == -1) {
/* 302 */         throw new EndOfOggStreamException();
/*     */       }
/* 304 */       total += read;
/*     */     }
/*     */   }
/*     */ 
/*     */   public long getAbsoluteGranulePosition()
/*     */   {
/* 321 */     return this.absoluteGranulePosition;
/*     */   }
/*     */ 
/*     */   public int getStreamSerialNumber()
/*     */   {
/* 331 */     return this.streamSerialNumber;
/*     */   }
/*     */ 
/*     */   public int getPageSequenceNumber()
/*     */   {
/* 341 */     return this.pageSequenceNumber;
/*     */   }
/*     */ 
/*     */   public int getPageCheckSum()
/*     */   {
/* 351 */     return this.pageCheckSum;
/*     */   }
/*     */ 
/*     */   public int getTotalLength()
/*     */   {
/* 360 */     if (this.data != null) {
/* 361 */       return 27 + this.segmentTable.length + this.data.length;
/*     */     }
/*     */ 
/* 364 */     return this.totalLength;
/*     */   }
/*     */ 
/*     */   public byte[] getData()
/*     */   {
/* 374 */     return this.data;
/*     */   }
/*     */ 
/*     */   public byte[] getHeader() {
/* 378 */     return this.header;
/*     */   }
/*     */ 
/*     */   public byte[] getSegmentTable() {
/* 382 */     return this.segmentTable;
/*     */   }
/*     */ 
/*     */   public int[] getSegmentOffsets() {
/* 386 */     return this.segmentOffsets;
/*     */   }
/*     */ 
/*     */   public int[] getSegmentLengths() {
/* 390 */     return this.segmentLengths;
/*     */   }
/*     */ 
/*     */   public boolean isContinued()
/*     */   {
/* 398 */     return this.continued;
/*     */   }
/*     */ 
/*     */   public boolean isFresh()
/*     */   {
/* 406 */     return !this.continued;
/*     */   }
/*     */ 
/*     */   public boolean isBos()
/*     */   {
/* 414 */     return this.bos;
/*     */   }
/*     */ 
/*     */   public boolean isEos()
/*     */   {
/* 422 */     return this.eos;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.ogg.OggPage
 * JD-Core Version:    0.6.2
 */