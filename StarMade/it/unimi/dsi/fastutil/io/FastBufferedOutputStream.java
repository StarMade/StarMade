/*   1:    */package it.unimi.dsi.fastutil.io;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.OutputStream;
/*   5:    */import java.lang.reflect.InvocationTargetException;
/*   6:    */import java.lang.reflect.Method;
/*   7:    */import java.nio.channels.FileChannel;
/*   8:    */
/*  73:    */public class FastBufferedOutputStream
/*  74:    */  extends MeasurableOutputStream
/*  75:    */  implements RepositionableStream
/*  76:    */{
/*  77:    */  private static final boolean ASSERTS = false;
/*  78:    */  public static final int DEFAULT_BUFFER_SIZE = 8192;
/*  79:    */  protected byte[] buffer;
/*  80:    */  protected int pos;
/*  81:    */  protected int avail;
/*  82:    */  protected OutputStream os;
/*  83:    */  private FileChannel fileChannel;
/*  84:    */  private RepositionableStream repositionableStream;
/*  85:    */  private MeasurableStream measurableStream;
/*  86:    */  
/*  87:    */  public FastBufferedOutputStream(OutputStream os, int bufferSize)
/*  88:    */  {
/*  89: 89 */    this.os = os;
/*  90: 90 */    this.buffer = new byte[bufferSize];
/*  91: 91 */    this.avail = bufferSize;
/*  92:    */    
/*  93: 93 */    if ((os instanceof RepositionableStream)) this.repositionableStream = ((RepositionableStream)os);
/*  94: 94 */    if ((os instanceof MeasurableStream)) { this.measurableStream = ((MeasurableStream)os);
/*  95:    */    }
/*  96: 96 */    if (this.repositionableStream == null) {
/*  97:    */      try
/*  98:    */      {
/*  99: 99 */        this.fileChannel = ((FileChannel)os.getClass().getMethod("getChannel", new Class[0]).invoke(os, new Object[0]));
/* 100:    */      }
/* 101:    */      catch (IllegalAccessException e) {}catch (IllegalArgumentException e) {}catch (NoSuchMethodException e) {}catch (InvocationTargetException e) {}catch (ClassCastException e) {}
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 113:    */  public FastBufferedOutputStream(OutputStream os)
/* 114:    */  {
/* 115:115 */    this(os, 8192);
/* 116:    */  }
/* 117:    */  
/* 118:    */  private void dumpBuffer(boolean ifFull) throws IOException {
/* 119:119 */    if ((!ifFull) || (this.avail == 0)) {
/* 120:120 */      this.os.write(this.buffer, 0, this.pos);
/* 121:121 */      this.pos = 0;
/* 122:122 */      this.avail = this.buffer.length;
/* 123:    */    }
/* 124:    */  }
/* 125:    */  
/* 126:    */  public void write(int b) throws IOException
/* 127:    */  {
/* 128:128 */    this.avail -= 1;
/* 129:129 */    this.buffer[(this.pos++)] = ((byte)b);
/* 130:130 */    dumpBuffer(true);
/* 131:    */  }
/* 132:    */  
/* 133:    */  public void write(byte[] b, int offset, int length) throws IOException
/* 134:    */  {
/* 135:135 */    if (length >= this.buffer.length) {
/* 136:136 */      dumpBuffer(false);
/* 137:137 */      this.os.write(b, offset, length);
/* 138:138 */      return;
/* 139:    */    }
/* 140:    */    
/* 141:141 */    if (length <= this.avail)
/* 142:    */    {
/* 143:143 */      System.arraycopy(b, offset, this.buffer, this.pos, length);
/* 144:144 */      this.pos += length;
/* 145:145 */      this.avail -= length;
/* 146:146 */      dumpBuffer(true);
/* 147:147 */      return;
/* 148:    */    }
/* 149:    */    
/* 150:150 */    dumpBuffer(false);
/* 151:151 */    System.arraycopy(b, offset, this.buffer, 0, length);
/* 152:152 */    this.pos = length;
/* 153:153 */    this.avail -= length;
/* 154:    */  }
/* 155:    */  
/* 156:    */  public void flush() throws IOException {
/* 157:157 */    dumpBuffer(false);
/* 158:158 */    this.os.flush();
/* 159:    */  }
/* 160:    */  
/* 161:    */  public void close() throws IOException {
/* 162:162 */    if (this.os == null) return;
/* 163:163 */    flush();
/* 164:164 */    if (this.os != System.out) this.os.close();
/* 165:165 */    this.os = null;
/* 166:166 */    this.buffer = null;
/* 167:    */  }
/* 168:    */  
/* 169:    */  public long position() throws IOException {
/* 170:170 */    if (this.repositionableStream != null) return this.repositionableStream.position() + this.pos;
/* 171:171 */    if (this.measurableStream != null) return this.measurableStream.position() + this.pos;
/* 172:172 */    if (this.fileChannel != null) return this.fileChannel.position() + this.pos;
/* 173:173 */    throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the MeasurableStream or RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
/* 174:    */  }
/* 175:    */  
/* 179:    */  public void position(long newPosition)
/* 180:    */    throws IOException
/* 181:    */  {
/* 182:182 */    flush();
/* 183:183 */    if (this.repositionableStream != null) { this.repositionableStream.position(newPosition);
/* 184:184 */    } else if (this.fileChannel != null) this.fileChannel.position(newPosition); else {
/* 185:185 */      throw new UnsupportedOperationException("position() can only be called if the underlying byte stream implements the RepositionableStream interface or if the getChannel() method of the underlying byte stream exists and returns a FileChannel");
/* 186:    */    }
/* 187:    */  }
/* 188:    */  
/* 195:    */  public long length()
/* 196:    */    throws IOException
/* 197:    */  {
/* 198:198 */    flush();
/* 199:199 */    if (this.measurableStream != null) return this.measurableStream.length();
/* 200:200 */    if (this.fileChannel != null) return this.fileChannel.size();
/* 201:201 */    throw new UnsupportedOperationException();
/* 202:    */  }
/* 203:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.io.FastBufferedOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */