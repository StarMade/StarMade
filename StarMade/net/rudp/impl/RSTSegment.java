package net.rudp.impl;

public class RSTSegment
  extends Segment
{
  protected RSTSegment() {}
  
  public RSTSegment(int paramInt)
  {
    init(16, paramInt, 6);
  }
  
  public String type()
  {
    return "RST";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.RSTSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */