package net.rudp.impl;

public abstract class Segment
{
  public static final int RUDP_VERSION = 1;
  public static final int RUDP_HEADER_LEN = 6;
  public static final byte SYN_FLAG = -128;
  public static final byte ACK_FLAG = 64;
  public static final byte EAK_FLAG = 32;
  public static final byte RST_FLAG = 16;
  public static final byte NUL_FLAG = 8;
  public static final byte CHK_FLAG = 4;
  public static final byte FIN_FLAG = 2;
  private int _flags;
  private int _hlen;
  private int _seqn;
  private int _ackn = -1;
  private int _nretx = 0;
  
  public abstract String type();
  
  public int flags()
  {
    return this._flags;
  }
  
  public int seq()
  {
    return this._seqn;
  }
  
  public int length()
  {
    return this._hlen;
  }
  
  public void setAck(int paramInt)
  {
    this._flags |= 64;
    this._ackn = paramInt;
  }
  
  public int getAck()
  {
    if ((this._flags & 0x40) == 64) {
      return this._ackn;
    }
    return -1;
  }
  
  public int getRetxCounter()
  {
    return this._nretx;
  }
  
  public void setRetxCounter(int paramInt)
  {
    this._nretx = paramInt;
  }
  
  public byte[] getBytes()
  {
    byte[] arrayOfByte = new byte[length()];
    arrayOfByte[0] = ((byte)(this._flags & 0xFF));
    arrayOfByte[1] = ((byte)(this._hlen & 0xFF));
    arrayOfByte[2] = ((byte)(this._seqn & 0xFF));
    arrayOfByte[3] = ((byte)(this._ackn & 0xFF));
    return arrayOfByte;
  }
  
  public String toString()
  {
    return type() + " [" + " SEQ = " + seq() + ", ACK = " + (getAck() >= 0 ? "" + getAck() : "N/A") + ", LEN = " + length() + " ]";
  }
  
  public static Segment parse(byte[] paramArrayOfByte)
  {
    return parse(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static Segment parse(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Object localObject = null;
    if (paramInt2 < 6) {
      throw new IllegalArgumentException("Invalid segment");
    }
    int i = paramArrayOfByte[paramInt1];
    if ((i & 0xFFFFFF80) != 0) {
      localObject = new SYNSegment();
    } else if ((i & 0x8) != 0) {
      localObject = new NULSegment();
    } else if ((i & 0x20) != 0) {
      localObject = new EAKSegment();
    } else if ((i & 0x10) != 0) {
      localObject = new RSTSegment();
    } else if ((i & 0x2) != 0) {
      localObject = new FINSegment();
    } else if ((i & 0x40) != 0) {
      if (paramInt2 == 6) {
        localObject = new ACKSegment();
      } else {
        localObject = new DATSegment();
      }
    }
    if (localObject == null) {
      throw new IllegalArgumentException("Invalid segment");
    }
    ((Segment)localObject).parseBytes(paramArrayOfByte, paramInt1, paramInt2);
    return localObject;
  }
  
  protected void init(int paramInt1, int paramInt2, int paramInt3)
  {
    this._flags = paramInt1;
    this._seqn = paramInt2;
    this._hlen = paramInt3;
  }
  
  protected void parseBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this._flags = (paramArrayOfByte[paramInt1] & 0xFF);
    this._hlen = (paramArrayOfByte[(paramInt1 + 1)] & 0xFF);
    this._seqn = (paramArrayOfByte[(paramInt1 + 2)] & 0xFF);
    this._ackn = (paramArrayOfByte[(paramInt1 + 3)] & 0xFF);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.Segment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */