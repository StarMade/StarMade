/*  1:   */package net.rudp.impl;
/*  2:   */
/* 20:   */public class EAKSegment
/* 21:   */  extends ACKSegment
/* 22:   */{
/* 23:   */  private int[] _acks;
/* 24:   */  
/* 42:   */  protected EAKSegment() {}
/* 43:   */  
/* 60:   */  public EAKSegment(int paramInt1, int paramInt2, int[] paramArrayOfInt)
/* 61:   */  {
/* 62:62 */    init(32, paramInt1, 6 + paramArrayOfInt.length);
/* 63:63 */    setAck(paramInt2);
/* 64:64 */    this._acks = paramArrayOfInt;
/* 65:   */  }
/* 66:   */  
/* 67:   */  public String type()
/* 68:   */  {
/* 69:69 */    return "EAK";
/* 70:   */  }
/* 71:   */  
/* 72:   */  public int[] getACKs()
/* 73:   */  {
/* 74:74 */    return this._acks;
/* 75:   */  }
/* 76:   */  
/* 77:   */  public byte[] getBytes()
/* 78:   */  {
/* 79:79 */    byte[] arrayOfByte = super.getBytes();
/* 80:   */    
/* 81:81 */    for (int i = 0; i < this._acks.length; i++) {
/* 82:82 */      arrayOfByte[(4 + i)] = ((byte)(this._acks[i] & 0xFF));
/* 83:   */    }
/* 84:   */    
/* 85:85 */    return arrayOfByte;
/* 86:   */  }
/* 87:   */  
/* 88:   */  protected void parseBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/* 89:   */  {
/* 90:90 */    super.parseBytes(paramArrayOfByte, paramInt1, paramInt2);
/* 91:91 */    this._acks = new int[paramInt2 - 6];
/* 92:92 */    for (int i = 0; i < this._acks.length; i++) {
/* 93:93 */      this._acks[i] = (paramArrayOfByte[(paramInt1 + 4 + i)] & 0xFF);
/* 94:   */    }
/* 95:   */  }
/* 96:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.impl.EAKSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */