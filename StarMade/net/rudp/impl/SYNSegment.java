package net.rudp.impl;

public class SYNSegment
  extends Segment
{
  private int _version;
  private int _maxseg;
  private int _optflags;
  private int _maxsegsize;
  private int _rettoval;
  private int _cumacktoval;
  private int _niltoval;
  private int _maxret;
  private int _maxcumack;
  private int _maxoutseq;
  private int _maxautorst;
  private static final int SYN_HEADER_LEN = 22;
  
  protected SYNSegment() {}
  
  public SYNSegment(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10)
  {
    init(-128, paramInt1, 22);
    this._version = 1;
    this._maxseg = paramInt2;
    this._optflags = 1;
    this._maxsegsize = paramInt3;
    this._rettoval = paramInt4;
    this._cumacktoval = paramInt5;
    this._niltoval = paramInt6;
    this._maxret = paramInt7;
    this._maxcumack = paramInt8;
    this._maxoutseq = paramInt9;
    this._maxautorst = paramInt10;
  }
  
  public String type()
  {
    return "SYN";
  }
  
  public int getVersion()
  {
    return this._version;
  }
  
  public int getMaxOutstandingSegments()
  {
    return this._maxseg;
  }
  
  public int getOptionFlags()
  {
    return this._optflags;
  }
  
  public int getMaxSegmentSize()
  {
    return this._maxsegsize;
  }
  
  public int getRetransmissionTimeout()
  {
    return this._rettoval;
  }
  
  public int getCummulativeAckTimeout()
  {
    return this._cumacktoval;
  }
  
  public int getNulSegmentTimeout()
  {
    return this._niltoval;
  }
  
  public int getMaxRetransmissions()
  {
    return this._maxret;
  }
  
  public int getMaxCumulativeAcks()
  {
    return this._maxcumack;
  }
  
  public int getMaxOutOfSequence()
  {
    return this._maxoutseq;
  }
  
  public int getMaxAutoReset()
  {
    return this._maxautorst;
  }
  
  public byte[] getBytes()
  {
    byte[] arrayOfByte = super.getBytes();
    arrayOfByte[4] = ((byte)(this._version << 4 & 0xFF));
    arrayOfByte[5] = ((byte)(this._maxseg & 0xFF));
    arrayOfByte[6] = ((byte)(this._optflags & 0xFF));
    arrayOfByte[7] = 0;
    arrayOfByte[8] = ((byte)(this._maxsegsize >>> 8 & 0xFF));
    arrayOfByte[9] = ((byte)(this._maxsegsize >>> 0 & 0xFF));
    arrayOfByte[10] = ((byte)(this._rettoval >>> 8 & 0xFF));
    arrayOfByte[11] = ((byte)(this._rettoval >>> 0 & 0xFF));
    arrayOfByte[12] = ((byte)(this._cumacktoval >>> 8 & 0xFF));
    arrayOfByte[13] = ((byte)(this._cumacktoval >>> 0 & 0xFF));
    arrayOfByte[14] = ((byte)(this._niltoval >>> 8 & 0xFF));
    arrayOfByte[15] = ((byte)(this._niltoval >>> 0 & 0xFF));
    arrayOfByte[16] = ((byte)(this._maxret & 0xFF));
    arrayOfByte[17] = ((byte)(this._maxcumack & 0xFF));
    arrayOfByte[18] = ((byte)(this._maxoutseq & 0xFF));
    arrayOfByte[19] = ((byte)(this._maxautorst & 0xFF));
    return arrayOfByte;
  }
  
  protected void parseBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super.parseBytes(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt2 < 22) {
      throw new IllegalArgumentException("Invalid SYN segment");
    }
    this._version = ((paramArrayOfByte[(paramInt1 + 4)] & 0xFF) >>> 4);
    if (this._version != 1) {
      throw new IllegalArgumentException("Invalid RUDP version");
    }
    this._maxseg = (paramArrayOfByte[(paramInt1 + 5)] & 0xFF);
    this._optflags = (paramArrayOfByte[(paramInt1 + 6)] & 0xFF);
    this._maxsegsize = ((paramArrayOfByte[(paramInt1 + 8)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 9)] & 0xFF) << 0);
    this._rettoval = ((paramArrayOfByte[(paramInt1 + 10)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 11)] & 0xFF) << 0);
    this._cumacktoval = ((paramArrayOfByte[(paramInt1 + 12)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 13)] & 0xFF) << 0);
    this._niltoval = ((paramArrayOfByte[(paramInt1 + 14)] & 0xFF) << 8 | (paramArrayOfByte[(paramInt1 + 15)] & 0xFF) << 0);
    this._maxret = (paramArrayOfByte[(paramInt1 + 16)] & 0xFF);
    this._maxcumack = (paramArrayOfByte[(paramInt1 + 17)] & 0xFF);
    this._maxoutseq = (paramArrayOfByte[(paramInt1 + 18)] & 0xFF);
    this._maxautorst = (paramArrayOfByte[(paramInt1 + 19)] & 0xFF);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.SYNSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */