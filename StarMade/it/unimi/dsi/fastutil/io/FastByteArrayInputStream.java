/*   1:    */package it.unimi.dsi.fastutil.io;
/*   2:    */
/*  11:    */public class FastByteArrayInputStream
/*  12:    */  extends MeasurableInputStream
/*  13:    */  implements RepositionableStream
/*  14:    */{
/*  15:    */  public byte[] array;
/*  16:    */  
/*  23:    */  public int offset;
/*  24:    */  
/*  31:    */  public int length;
/*  32:    */  
/*  39:    */  private int position;
/*  40:    */  
/*  47:    */  private int mark;
/*  48:    */  
/*  56:    */  public FastByteArrayInputStream(byte[] array, int offset, int length)
/*  57:    */  {
/*  58: 58 */    this.array = array;
/*  59: 59 */    this.offset = offset;
/*  60: 60 */    this.length = length;
/*  61:    */  }
/*  62:    */  
/*  66:    */  public FastByteArrayInputStream(byte[] array)
/*  67:    */  {
/*  68: 68 */    this(array, 0, array.length);
/*  69:    */  }
/*  70:    */  
/*  71:    */  public boolean markSupported() {
/*  72: 72 */    return true;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public void reset() {
/*  76: 76 */    this.position = this.mark;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public void close() {}
/*  80:    */  
/*  81:    */  public void mark(int dummy)
/*  82:    */  {
/*  83: 83 */    this.mark = this.position;
/*  84:    */  }
/*  85:    */  
/*  86:    */  public int available() {
/*  87: 87 */    return this.length - this.position;
/*  88:    */  }
/*  89:    */  
/*  90:    */  public long skip(long n) {
/*  91: 91 */    if (n <= this.length - this.position) {
/*  92: 92 */      this.position += (int)n;
/*  93: 93 */      return n;
/*  94:    */    }
/*  95: 95 */    n = this.length - this.position;
/*  96: 96 */    this.position = this.length;
/*  97: 97 */    return n;
/*  98:    */  }
/*  99:    */  
/* 100:    */  public int read() {
/* 101:101 */    if (this.length == this.position) return -1;
/* 102:102 */    return this.array[(this.offset + this.position++)] & 0xFF;
/* 103:    */  }
/* 104:    */  
/* 110:    */  public int read(byte[] b, int offset, int length)
/* 111:    */  {
/* 112:112 */    if (this.length == this.position) return length == 0 ? 0 : -1;
/* 113:113 */    int n = Math.min(length, this.length - this.position);
/* 114:114 */    System.arraycopy(this.array, this.offset + this.position, b, offset, n);
/* 115:115 */    this.position += n;
/* 116:116 */    return n;
/* 117:    */  }
/* 118:    */  
/* 119:    */  public long position() {
/* 120:120 */    return this.position;
/* 121:    */  }
/* 122:    */  
/* 123:    */  public void position(long newPosition) {
/* 124:124 */    this.position = ((int)Math.min(newPosition, this.length));
/* 125:    */  }
/* 126:    */  
/* 127:    */  public long length()
/* 128:    */  {
/* 129:129 */    return this.length;
/* 130:    */  }
/* 131:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastByteArrayInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */