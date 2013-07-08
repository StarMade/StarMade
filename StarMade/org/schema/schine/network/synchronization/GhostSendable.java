package org.schema.schine.network.synchronization;

import org.schema.schine.network.objects.Sendable;

public class GhostSendable
{
  public final long timeDeleted;
  public final Sendable sendable;
  
  public GhostSendable(long paramLong, Sendable paramSendable)
  {
    this.timeDeleted = paramLong;
    this.sendable = paramSendable;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.schine.network.synchronization.GhostSendable
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */