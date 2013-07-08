/*   1:    */package it.unimi.dsi.fastutil.io;
/*   2:    */
/*   3:    */import java.io.EOFException;
/*   4:    */import java.io.IOException;
/*   5:    */import java.io.InputStream;
/*   6:    */
/*  53:    */public class FastMultiByteArrayInputStream
/*  54:    */  extends MeasurableInputStream
/*  55:    */  implements RepositionableStream
/*  56:    */{
/*  57:    */  public static final int SLICE_BITS = 30;
/*  58:    */  public static final int SLICE_SIZE = 1073741824;
/*  59:    */  public static final int SLICE_MASK = 1073741823;
/*  60:    */  public byte[][] array;
/*  61:    */  public long length;
/*  62:    */  private long position;
/*  63:    */  private long mark;
/*  64:    */  
/*  65:    */  public FastMultiByteArrayInputStream(MeasurableInputStream is)
/*  66:    */    throws IOException
/*  67:    */  {
/*  68: 68 */    this(is, is.length());
/*  69:    */  }
/*  70:    */  
/*  75:    */  public FastMultiByteArrayInputStream(InputStream is, long size)
/*  76:    */    throws IOException
/*  77:    */  {
/*  78: 78 */    this.length = size;
/*  79: 79 */    this.array = new byte[(int)((size + 1073741824L - 1L) / 1073741824L)][];
/*  80:    */    
/*  81: 81 */    for (int i = 0; i < this.array.length; i++) {
/*  82: 82 */      this.array[i] = new byte[size >= 1073741824L ? 1073741824 : (int)size];
/*  83: 83 */      if (is.read(this.array[i]) != this.array[i].length) throw new EOFException();
/*  84: 84 */      size -= this.array[i].length;
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  91:    */  public FastMultiByteArrayInputStream(FastMultiByteArrayInputStream is)
/*  92:    */  {
/*  93: 93 */    this.array = is.array;
/*  94: 94 */    this.length = is.length;
/*  95:    */  }
/*  96:    */  
/* 101:    */  public FastMultiByteArrayInputStream(byte[] array)
/* 102:    */  {
/* 103:103 */    this.array = new byte[1][];
/* 104:104 */    this.array[0] = array;
/* 105:105 */    this.length = array.length;
/* 106:    */  }
/* 107:    */  
/* 109:    */  public boolean markSupported()
/* 110:    */  {
/* 111:111 */    return true;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public void reset() {
/* 115:115 */    this.position = this.mark;
/* 116:    */  }
/* 117:    */  
/* 118:    */  public void close() {}
/* 119:    */  
/* 120:    */  public void mark(int dummy)
/* 121:    */  {
/* 122:122 */    this.mark = this.position;
/* 123:    */  }
/* 124:    */  
/* 132:    */  public int available()
/* 133:    */  {
/* 134:134 */    if (this.length - this.position > 2147483647L) return 2147483647;
/* 135:135 */    return (int)(this.length - this.position);
/* 136:    */  }
/* 137:    */  
/* 138:    */  public long skip(long n) {
/* 139:139 */    if (n <= this.length - this.position) {
/* 140:140 */      this.position += n;
/* 141:    */      
/* 142:142 */      return n;
/* 143:    */    }
/* 144:144 */    n = this.length - this.position;
/* 145:145 */    this.position = this.length;
/* 146:146 */    return n;
/* 147:    */  }
/* 148:    */  
/* 149:    */  public int read() {
/* 150:150 */    if (this.length == this.position) return -1;
/* 151:151 */    return this.array[((int)(this.position >>> 30))][((int)(this.position++ & 0x3FFFFFFF))] & 0xFF;
/* 152:    */  }
/* 153:    */  
/* 154:    */  public int read(byte[] b, int offset, int length) {
/* 155:155 */    if (this.length == this.position) return length == 0 ? 0 : -1;
/* 156:156 */    int n = (int)Math.min(length, this.length - this.position);int m = n;
/* 157:    */    do
/* 158:    */    {
/* 159:159 */      int res = Math.min(n, this.array[((int)(this.position >>> 30))].length - (int)(this.position & 0x3FFFFFFF));
/* 160:160 */      System.arraycopy(this.array[((int)(this.position >>> 30))], (int)(this.position & 0x3FFFFFFF), b, offset, res);
/* 161:    */      
/* 163:163 */      n -= res;
/* 164:164 */      offset += res;
/* 165:165 */      this.position += res;
/* 166:    */    }
/* 167:167 */    while (n > 0);
/* 168:    */    
/* 169:169 */    return m;
/* 170:    */  }
/* 171:    */  
/* 172:    */  public long position() {
/* 173:173 */    return this.position;
/* 174:    */  }
/* 175:    */  
/* 176:    */  public void position(long newPosition) {
/* 177:177 */    this.position = Math.min(newPosition, this.length);
/* 178:    */  }
/* 179:    */  
/* 180:    */  public long length() throws IOException
/* 181:    */  {
/* 182:182 */    return this.length;
/* 183:    */  }
/* 184:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastMultiByteArrayInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */