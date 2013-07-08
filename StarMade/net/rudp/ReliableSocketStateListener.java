package net.rudp;

public abstract interface ReliableSocketStateListener
{
  public abstract void connectionOpened(ReliableSocket paramReliableSocket);
  
  public abstract void connectionRefused(ReliableSocket paramReliableSocket);
  
  public abstract void connectionClosed(ReliableSocket paramReliableSocket);
  
  public abstract void connectionFailure(ReliableSocket paramReliableSocket);
  
  public abstract void connectionReset(ReliableSocket paramReliableSocket);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     net.rudp.ReliableSocketStateListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */