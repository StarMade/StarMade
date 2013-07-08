/*   1:    */package it.unimi.dsi.fastutil.io;
/*   2:    */
/*   3:    */import it.unimi.dsi.fastutil.bytes.ByteArrays;
/*   4:    */import java.io.IOException;
/*   5:    */
/*  46:    */public class FastByteArrayOutputStream
/*  47:    */  extends MeasurableOutputStream
/*  48:    */  implements RepositionableStream
/*  49:    */{
/*  50:    */  public static final int DEFAULT_INITIAL_CAPACITY = 16;
/*  51:    */  public byte[] array;
/*  52:    */  public int length;
/*  53:    */  private int position;
/*  54:    */  
/*  55:    */  public FastByteArrayOutputStream()
/*  56:    */  {
/*  57: 57 */    this(16);
/*  58:    */  }
/*  59:    */  
/*  63:    */  public FastByteArrayOutputStream(int initialCapacity)
/*  64:    */  {
/*  65: 65 */    this.array = new byte[initialCapacity];
/*  66:    */  }
/*  67:    */  
/*  71:    */  public FastByteArrayOutputStream(byte[] a)
/*  72:    */  {
/*  73: 73 */    this.array = a;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public void reset()
/*  77:    */  {
/*  78: 78 */    this.length = 0;
/*  79: 79 */    this.position = 0;
/*  80:    */  }
/*  81:    */  
/*  82:    */  public void trim()
/*  83:    */  {
/*  84: 84 */    this.array = ByteArrays.trim(this.array, this.length);
/*  85:    */  }
/*  86:    */  
/*  87:    */  public void write(int b) {
/*  88: 88 */    if (this.position == this.length) {
/*  89: 89 */      this.length += 1;
/*  90: 90 */      if (this.position == this.array.length) this.array = ByteArrays.grow(this.array, this.length);
/*  91:    */    }
/*  92: 92 */    this.array[(this.position++)] = ((byte)b);
/*  93:    */  }
/*  94:    */  
/*  95:    */  public void write(byte[] b, int off, int len) throws IOException {
/*  96: 96 */    ByteArrays.ensureOffsetLength(b, off, len);
/*  97: 97 */    if (this.position + len > this.array.length) this.array = ByteArrays.grow(this.array, this.position + len, this.position);
/*  98: 98 */    System.arraycopy(b, off, this.array, this.position, len);
/*  99: 99 */    if (this.position + len > this.length) this.length = (this.position += len);
/* 100:    */  }
/* 101:    */  
/* 102:    */  public void position(long newPosition) {
/* 103:103 */    if (this.position > 2147483647) throw new IllegalArgumentException("Position too large: " + newPosition);
/* 104:104 */    this.position = ((int)newPosition);
/* 105:    */  }
/* 106:    */  
/* 107:    */  public long position() {
/* 108:108 */    return this.position;
/* 109:    */  }
/* 110:    */  
/* 111:    */  public long length() throws IOException
/* 112:    */  {
/* 113:113 */    return this.length;
/* 114:    */  }
/* 115:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastByteArrayOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */