/*  1:   */package net.rudp.impl;
/*  2:   */
/* 26:   */public class FINSegment
/* 27:   */  extends Segment
/* 28:   */{
/* 29:   */  protected FINSegment() {}
/* 30:   */  
/* 54:   */  public FINSegment(int paramInt)
/* 55:   */  {
/* 56:56 */    init(2, paramInt, 6);
/* 57:   */  }
/* 58:   */  
/* 59:   */  public String type()
/* 60:   */  {
/* 61:61 */    return "FIN";
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     net.rudp.impl.FINSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */