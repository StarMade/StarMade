/*   1:    */package net.rudp;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.io.InputStream;
/*   5:    */
/*  44:    */class ReliableSocketInputStream
/*  45:    */  extends InputStream
/*  46:    */{
/*  47:    */  protected ReliableSocket _sock;
/*  48:    */  protected byte[] _buf;
/*  49:    */  protected int _pos;
/*  50:    */  protected int _count;
/*  51:    */  
/*  52:    */  public ReliableSocketInputStream(ReliableSocket paramReliableSocket)
/*  53:    */    throws IOException
/*  54:    */  {
/*  55: 55 */    if (paramReliableSocket == null) {
/*  56: 56 */      throw new NullPointerException("sock");
/*  57:    */    }
/*  58:    */    
/*  59: 59 */    this._sock = paramReliableSocket;
/*  60: 60 */    this._buf = new byte[this._sock.getReceiveBufferSize()];
/*  61: 61 */    this._pos = (this._count = 0);
/*  62:    */  }
/*  63:    */  
/*  64:    */  public synchronized int read()
/*  65:    */    throws IOException
/*  66:    */  {
/*  67: 67 */    if (readImpl() < 0) {
/*  68: 68 */      return -1;
/*  69:    */    }
/*  70:    */    
/*  71: 71 */    return this._buf[(this._pos++)] & 0xFF;
/*  72:    */  }
/*  73:    */  
/*  74:    */  public synchronized int read(byte[] paramArrayOfByte)
/*  75:    */    throws IOException
/*  76:    */  {
/*  77: 77 */    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
/*  78:    */  }
/*  79:    */  
/*  80:    */  public synchronized int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
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
/*  91: 91 */    if (readImpl() < 0) {
/*  92: 92 */      return -1;
/*  93:    */    }
/*  94:    */    
/*  95: 95 */    int i = Math.min(available(), paramInt2);
/*  96: 96 */    System.arraycopy(this._buf, this._pos, paramArrayOfByte, paramInt1, i);
/*  97: 97 */    this._pos += i;
/*  98:    */    
/*  99: 99 */    return i;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public synchronized int available()
/* 103:    */  {
/* 104:104 */    return this._count - this._pos;
/* 105:    */  }
/* 106:    */  
/* 107:    */  public boolean markSupported()
/* 108:    */  {
/* 109:109 */    return false;
/* 110:    */  }
/* 111:    */  
/* 112:    */  public void close()
/* 113:    */    throws IOException
/* 114:    */  {
/* 115:115 */    this._sock.shutdownInput();
/* 116:    */  }
/* 117:    */  
/* 118:    */  private int readImpl()
/* 119:    */    throws IOException
/* 120:    */  {
/* 121:121 */    if (available() == 0) {
/* 122:122 */      this._count = this._sock.read(this._buf, 0, this._buf.length);
/* 123:123 */      this._pos = 0;
/* 124:    */    }
/* 125:    */    
/* 126:126 */    return this._count;
/* 127:    */  }
/* 128:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.ReliableSocketInputStream
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */