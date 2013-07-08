/*   1:    */package net.rudp;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.OutputStream;
/*   5:    */
/*  45:    */class ReliableSocketOutputStream
/*  46:    */  extends OutputStream
/*  47:    */{
/*  48:    */  protected ReliableSocket _sock;
/*  49:    */  protected byte[] _buf;
/*  50:    */  protected int _count;
/*  51:    */  
/*  52:    */  public ReliableSocketOutputStream(ReliableSocket paramReliableSocket)
/*  53:    */    throws IOException
/*  54:    */  {
/*  55: 55 */    if (paramReliableSocket == null) {
/*  56: 56 */      throw new NullPointerException("sock");
/*  57:    */    }
/*  58:    */    
/*  59: 59 */    this._sock = paramReliableSocket;
/*  60: 60 */    this._buf = new byte[this._sock.getSendBufferSize()];
/*  61: 61 */    this._count = 0;
/*  62:    */  }
/*  63:    */  
/*  64:    */  public synchronized void write(int paramInt)
/*  65:    */    throws IOException
/*  66:    */  {
/*  67: 67 */    if (this._count >= this._buf.length) {
/*  68: 68 */      flush();
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    this._buf[(this._count++)] = ((byte)(paramInt & 0xFF));
/*  72:    */  }
/*  73:    */  
/*  74:    */  public synchronized void write(byte[] paramArrayOfByte)
/*  75:    */    throws IOException
/*  76:    */  {
/*  77: 77 */    write(paramArrayOfByte, 0, paramArrayOfByte.length);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public synchronized void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*  81:    */    throws IOException
/*  82:    */  {
/*  83: 83 */    if (paramArrayOfByte == null) {
/*  84: 84 */      throw new NullPointerException();
/*  85:    */    }
/*  86:    */    
/*  87: 87 */    if ((paramInt1 < 0) || (paramInt2 < 0) || (paramInt1 + paramInt2 > paramArrayOfByte.length)) {
/*  88: 88 */      throw new IndexOutOfBoundsException();
/*  89:    */    }
/*  90:    */    
/*  92: 92 */    int j = 0;
/*  93:    */    
/*  94: 94 */    while (j < paramInt2) {
/*  95: 95 */      int i = Math.min(this._buf.length, paramInt2 - j);
/*  96: 96 */      if (i > this._buf.length - this._count) {
/*  97: 97 */        flush();
/*  98:    */      }
/*  99: 99 */      System.arraycopy(paramArrayOfByte, paramInt1 + j, this._buf, this._count, i);
/* 100:100 */      this._count += i;
/* 101:101 */      j += i;
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 105:    */  public synchronized void flush()
/* 106:    */    throws IOException
/* 107:    */  {
/* 108:108 */    if (this._count > 0) {
/* 109:109 */      this._sock.write(this._buf, 0, this._count);
/* 110:110 */      this._count = 0;
/* 111:    */    }
/* 112:    */  }
/* 113:    */  
/* 114:    */  public synchronized void close()
/* 115:    */    throws IOException
/* 116:    */  {
/* 117:117 */    flush();
/* 118:118 */    this._sock.shutdownOutput();
/* 119:    */  }
/* 120:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.ReliableSocketOutputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */