/*  1:   */package net.rudp.impl;
/*  2:   */
/* 26:   */public class ACKSegment
/* 27:   */  extends Segment
/* 28:   */{
/* 29:   */  protected ACKSegment() {}
/* 30:   */  
/* 54:   */  public ACKSegment(int paramInt1, int paramInt2)
/* 55:   */  {
/* 56:56 */    init(64, paramInt1, 6);
/* 57:57 */    setAck(paramInt2);
/* 58:   */  }
/* 59:   */  
/* 60:   */  public String type()
/* 61:   */  {
/* 62:62 */    return "ACK";
/* 63:   */  }
/* 64:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.impl.ACKSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */