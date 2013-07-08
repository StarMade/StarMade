package net.rudp.impl;

public class EAKSegment
  extends ACKSegment
{
  private int[] _acks;
  
  protected EAKSegment() {}
  
  public EAKSegment(int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    init(32, paramInt1, 6 + paramArrayOfInt.length);
    setAck(paramInt2);
    this._acks = paramArrayOfInt;
  }
  
  public String type()
  {
    return "EAK";
  }
  
  public int[] getACKs()
  {
    return this._acks;
  }
  
  public byte[] getBytes()
  {
    byte[] arrayOfByte = super.getBytes();
    for (int i = 0; i < this._acks.length; i++) {
      arrayOfByte[(4 + i)] = ((byte)(this._acks[i] & 0xFF));
    }
    return arrayOfByte;
  }
  
  protected void parseBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super.parseBytes(paramArrayOfByte, paramInt1, paramInt2);
    this._acks = new int[paramInt2 - 6];
    for (int i = 0; i < this._acks.length; i++) {
      this._acks[i] = (paramArrayOfByte[(paramInt1 + 4 + i)] & 0xFF);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.EAKSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */