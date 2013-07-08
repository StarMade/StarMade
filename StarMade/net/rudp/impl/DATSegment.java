/*  1:   */package net.rudp.impl;
/*  2:   */
/* 18:   */public class DATSegment
/* 19:   */  extends Segment
/* 20:   */{
/* 21:   */  private byte[] _data;
/* 22:   */  
/* 38:   */  protected DATSegment() {}
/* 39:   */  
/* 55:   */  public DATSegment(int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3, int paramInt4)
/* 56:   */  {
/* 57:57 */    init(64, paramInt1, 6);
/* 58:58 */    setAck(paramInt2);
/* 59:59 */    this._data = new byte[paramInt4];
/* 60:60 */    System.arraycopy(paramArrayOfByte, paramInt3, this._data, 0, paramInt4);
/* 61:   */  }
/* 62:   */  
/* 63:   */  public int length()
/* 64:   */  {
/* 65:65 */    return this._data.length + super.length();
/* 66:   */  }
/* 67:   */  
/* 68:   */  public String type()
/* 69:   */  {
/* 70:70 */    return "DAT";
/* 71:   */  }
/* 72:   */  
/* 73:   */  public byte[] getData()
/* 74:   */  {
/* 75:75 */    return this._data;
/* 76:   */  }
/* 77:   */  
/* 78:   */  public byte[] getBytes()
/* 79:   */  {
/* 80:80 */    byte[] arrayOfByte = super.getBytes();
/* 81:81 */    System.arraycopy(this._data, 0, arrayOfByte, 6, this._data.length);
/* 82:82 */    return arrayOfByte;
/* 83:   */  }
/* 84:   */  
/* 85:   */  public void parseBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 86:   */  {
/* 87:87 */    super.parseBytes(paramArrayOfByte, paramInt1, paramInt2);
/* 88:88 */    this._data = new byte[paramInt2 - 6];
/* 89:89 */    System.arraycopy(paramArrayOfByte, paramInt1 + 6, this._data, 0, this._data.length);
/* 90:   */  }
/* 91:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.impl.DATSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */