package net.rudp.impl;

public class FINSegment
  extends Segment
{
  protected FINSegment() {}
  
  public FINSegment(int paramInt)
  {
    init(2, paramInt, 6);
  }
  
  public String type()
  {
    return "FIN";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.FINSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */