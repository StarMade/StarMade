package net.rudp.impl;

public class NULSegment
  extends Segment
{
  protected NULSegment() {}
  
  public NULSegment(int paramInt)
  {
    init(8, paramInt, 6);
  }
  
  public String type()
  {
    return "NUL";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.NULSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */