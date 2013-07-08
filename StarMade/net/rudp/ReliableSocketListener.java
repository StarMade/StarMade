package net.rudp;

public abstract interface ReliableSocketListener
{
  public abstract void packetSent();
  
  public abstract void packetRetransmitted();
  
  public abstract void packetReceivedInOrder();
  
  public abstract void packetReceivedOutOfOrder();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.ReliableSocketListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */