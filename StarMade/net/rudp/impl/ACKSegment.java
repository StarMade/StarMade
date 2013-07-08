package net.rudp.impl;

public class ACKSegment
  extends Segment
{
  protected ACKSegment() {}
  
  public ACKSegment(int paramInt1, int paramInt2)
  {
    init(64, paramInt1, 6);
    setAck(paramInt2);
  }
  
  public String type()
  {
    return "ACK";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.impl.ACKSegment
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */