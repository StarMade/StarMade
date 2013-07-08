package net.rudp.impl;

public class DATSegment
  extends Segment
{
  private byte[] _data;
  
  protected DATSegment() {}
  
  public DATSegment(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3, int paramInt4)
  {
    init(64, paramInt1, 6);
    setAck(paramInt2);
    this._data = new byte[paramInt4];
    System.arraycopy(paramArrayOfByte, paramInt3, this._data, 0, paramInt4);
  }
  
  public int length()
  {
    return this._data.length + super.length();
  }
  
  public String type()
  {
    return "DAT";
  }
  
  public byte[] getData()
  {
    return this._data;
  }
  
  public byte[] getBytes()
  {
    byte[] arrayOfByte = super.getBytes();
    System.arraycopy(this._data, 0, arrayOfByte, 6, this._data.length);
    return arrayOfByte;
  }
  
  public void parseBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super.parseBytes(paramArrayOfByte, paramInt1, paramInt2);
    this._data = new byte[paramInt2 - 6];
    System.arraycopy(paramArrayOfByte, paramInt1 + 6, this._data, 0, this._data.length);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.DATSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */